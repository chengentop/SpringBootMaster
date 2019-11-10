package com.master.config;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import com.master.core.framework.db.CommonDao;
import com.master.core.framework.db.IBaseDao;
import com.master.core.framework.db.ICommonDao;
import com.master.core.framework.db.PageIntercept;
import com.master.core.framework.db.PagerBean;
import com.master.core.framework.db.QueryBean;
import com.master.core.util.DaoUtil;

@SpringBootConfiguration
public class MybatisConfig {

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setTypeAliasesPackage("com.nbcprotect.business.domain.model");
        Class<?>[] clazzes = {PagerBean.class, QueryBean.class};
        sessionFactory.setTypeAliases(clazzes);
        Interceptor[] interceptors = {new PageIntercept("mysql")};
        sessionFactory.setPlugins(interceptors);
        Resource[] mapperLocations = applicationContext.getResources("classpath*:mapper/**/*.xml");
        sessionFactory.setMapperLocations(mapperLocations);
        return sessionFactory;
    }


    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.master.business.domain.iface");
        configurer.setMarkerInterface(IBaseDao.class);
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return configurer;
    }

    @Bean
    public ICommonDao commonDao(SqlSessionFactory factory) throws Exception {
        CommonDao dao = new CommonDao();
        dao.setSqlSessionFactory(factory);
        return dao;
    }

    @Bean
    public DaoUtil daoUtil(ICommonDao commonDao) throws Exception {
        return new DaoUtil(commonDao);
    }
}
