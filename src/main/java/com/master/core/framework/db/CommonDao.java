package com.master.core.framework.db;

import java.sql.Timestamp;
import java.util.List;

import com.master.core.framework.exception.DataAccessException;
import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * 通用数据库操作
 * @author Rofly
 */
public class CommonDao extends SqlSessionDaoSupport implements ICommonDao{
	
	@Override
	public Timestamp currentTimestamp() throws DataAccessException {
		try {
			return selectOne("current_timestamp");
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public <T> T selectOne(String statement) throws DataAccessException {
		try {
			return getSqlSession().<T> selectOne(statement);
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public <T> T selectOne(String statement, Object parameter) throws DataAccessException {
		try {
			return getSqlSession().<T> selectOne(statement, parameter);
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public <E> List<E> selectList(String statement) throws DataAccessException {
		try {
			return getSqlSession().<E> selectList(statement);
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public <E> List<E> selectList(String statement, Object parameter) throws DataAccessException {
		try {
			return getSqlSession().<E> selectList(statement, parameter);
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage());
		}
	}
	
	@Override
	public int update(String statement, Object parameter) throws DataAccessException {
		try {
			return getSqlSession().update(statement, parameter);
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage());
		}
	}
}
