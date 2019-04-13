package com.test.springbm.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 数据源配置接口：
 * 为方便以后拓展，这里提供一个数据源配置接口，druid配置也只是这个接口的一个实现类，方便以后切换不同的数据源
 * @version 1.0
 * @author: CXP
 * @date: 2019/4/11 16:17
 */
public interface DatabaseConfig {
    /**
     * 定义数据源
     * @return
     * @throws Exception
     */
    DataSource dataSource() throws Exception;

    /**
     * 定义session工厂
     * @param dataSource
     * @return
     * @throws Exception
     */
    SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception;

    /**
     * 定义事务管理器
     * @param dataSource
     * @return
     */
    DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource);
}
