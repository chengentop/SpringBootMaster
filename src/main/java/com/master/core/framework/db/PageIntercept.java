package com.master.core.framework.db;

/**
 * @author zhangkui
 * @since 2017.10.17
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class PageIntercept implements Interceptor {
	private Logger log = LoggerFactory.getLogger(PageIntercept.class);
	private static String pageSqlId = ".*ByPage$"; // 需要拦截的ID(正则匹配)
	private String dbType;// 数据库类型，不同的数据库有不同的分页方法

	public PageIntercept(String dbType) {
		this.dbType = dbType;
	}

	/**
	 * 拦截后要执行的方法
	 */
	public Object intercept(Invocation invocation) throws Throwable {
		if (!(invocation.getTarget() instanceof RoutingStatementHandler)) {
			return invocation.proceed();
		}
		RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();
		
		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, // 数据来源
				new DefaultObjectFactory(), new DefaultObjectWrapperFactory(), new DefaultReflectorFactory());
		BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
		MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
		/*
		 * 可以判断传入参数类型进行判断，具体操作如下： if(parameterObject instanceof QueryBean<?>){
		 * //传入参数是分页查询对象 此处使用判断方法的方式，因此不做判断参数处理
		 */
		if (mappedStatement.getId().matches(pageSqlId)) { // 传入参数是分页查询对象
			log.debug("###需要执行  分页查询  拦截方法");
			PagerBean page = (PagerBean) metaStatementHandler.getValue("delegate.boundSql.parameterObject.page");
			if (null == page) {
				throw new NullPointerException("分页参数信息不存在");
			} else {
				log.debug("构建分页Sql，参数：currentPage={},offSet={},orderKey={},ascend={}, pageSize={}", page.getCurrentPage(), page.getOffSet(), page.getOrderKey(), page.getAscend(), page.getPageSize());
				String sql = boundSql.getSql();// 原sql
				String pageSql = rewritePageSql(sql, page); // 使用分页参数重写原sql
				metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
				// 采用物理分页后，就不需要mybatis的内存分页了，所以重置下面的两个参数
				metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
				metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
				Connection connection = (Connection) invocation.getArgs()[0];
				// 重设分页参数里的总页数等
				setPageParameter(sql, connection, mappedStatement, boundSql, page);
				log.debug("获取到的总记录条数：totalSize={}, totalPage={}", page.getTotalSize(), page.getTotalPage());
			}
		}
		return invocation.proceed();
	}

	private void setPageParameter(String sql, Connection connection, MappedStatement mappedStatement, BoundSql boundSql, PagerBean page) throws Exception {
		String countSql = getCountSql(sql.toLowerCase());
		PreparedStatement countStmt = null;
		ResultSet rs = null;
		try {
			countStmt = connection.prepareStatement(countSql);
			BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
			setParameters(countStmt, mappedStatement, countBS, boundSql.getParameterObject());
			rs = countStmt.executeQuery();
			int totalCount = 0;
			if (rs.next()) {
				totalCount = rs.getInt(1);
			}
			page.setTotalSize(totalCount);
			int totalPage = totalCount / page.getPageSize() + ((totalCount % page.getPageSize() == 0) ? 0 : 1);
			page.setTotalPage(totalPage);
		} catch (SQLException e) {
			log.error("Ignore this exception", e);
		} finally {
			if (null != rs) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.error("Ignore this exception", e);
				}
			}
			if (null != countStmt) {
				try {
					countStmt.close();
				} catch (SQLException e) {
					log.error("Ignore this exception", e);
				}
			}
		}
	}

	private String getCountSql(String sql) {
/*		int from = sql.lastIndexOf("from") - 1;
		String front = sql.substring(0, from);
		int distinct = front.indexOf("distinct");
		if (distinct > 0) {
			if (front.indexOf(",") == -1) {
				// distinct 有一个属性，返回 select count(distinct a) from table
				front = "select count(" + front.substring(distinct) + ")";
			} else {
				// 有distinct，且有多个属性值时，返回 select count(distinct (a, b)) from
				// table
				front = "select count(distinct (" + front.substring(distinct + 9) + "))";
			}
		} else {
			front = "select count(1)";
		}
		String end = sql.substring(from);
		int order = end.lastIndexOf("order by");
		if (order != -1) {
			end = end.substring(0, order);
		}*/
		sql = "select count(*) from ( " + sql + " ) basedata";
		/*return front + end;*/
		return sql;
	}

	private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) throws SQLException {
		ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
		parameterHandler.setParameters(ps);
	}

	private String rewritePageSql(String sql, PagerBean page) {
		if ("postgres".equalsIgnoreCase(dbType)) {
			return getPostgresPageSql(sql, page);
		} else if ("mysql".equalsIgnoreCase(dbType)) {
			return getMysqlPageSql(sql, page);
		}else if ("phoenix".equalsIgnoreCase(dbType)) {
			return getMysqlPageSql(sql, page);
		}
		return sql;
	}

	private String getMysqlPageSql(String sql, PagerBean page) {
		return getPostgresPageSql(sql, page); // mysql与pgsql、phoenix兼容
	}

	private String getPostgresPageSql(String sql, PagerBean page) {
		if (page.getPageSize() == -1) {
			return sql;
		} else {
			return sql + " limit " + page.getPageSize() + " offset " + page.getOffSet();
		}
	}

	/**
	 * 拦截器对应的封装原始对象的方法
	 */
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	/**
	 * 设置注册拦截器时设定的属性
	 */
	public void setProperties(Properties properties) {
		if (properties.containsKey("dbType")) {
			this.dbType = properties.getProperty("dbType");
		}
	}
}