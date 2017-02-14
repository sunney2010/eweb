/*
 * Copyright 1999-2024 Colotnet.com All right reserved. This software is the confidential and proprietary information of
 * Colotnet.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Colotnet.com.
 */
package com.sunney.eweb.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 类DruidConfiguration.java的实现描述：TODO 类实现描述
 * 
 * @author Sunney 2016年11月30日 下午6:01:20
 */
@Configuration
@ConfigurationProperties(prefix = "mySQLDataSource")
public class DruidConfiguration {

    private Logger  logger = LoggerFactory.getLogger(DruidConfiguration.class);

    private String  dbUrl;

    private String  userName;

    private String  password;

    private String  driverClassName;

    private int     initialSize;

    private int     minIdle;

    private int     maxActive;

    private int     maxWait;

    private int     timeBetweenEvictionRunsMillis;

    private int     minEvictableIdleTimeMillis;

    private String  validationQuery;

    private boolean testWhileIdle;

    private boolean testOnBorrow;

    private boolean testOnReturn;

    private boolean poolPreparedStatements;

    private int     maxPoolPreparedStatementPerConnectionSize;

    private String  filters;

    private String  connectionProperties;

    // 声明其为Bean实例
    @Bean(name = "dataSource", destroyMethod = "close", initMethod = "init")
    // 在同样的DataSource中，首先使用被标注的DataSource
    @Primary
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
       
        datasource.setUrl(this.dbUrl);
        datasource.setUsername(userName);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        // configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
        datasource.setConnectionProperties(connectionProperties);

        return datasource;
    }

    
    /**
     * @return the logger
     */
    public Logger getLogger() {
        return logger;
    }

    
    /**
     * @param logger the logger to set
     */
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    
    /**
     * @return the dbUrl
     */
    public String getDbUrl() {
        return dbUrl;
    }

    
    /**
     * @param dbUrl the dbUrl to set
     */
    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    
    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    
    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    
    /**
     * @return the driverClassName
     */
    public String getDriverClassName() {
        return driverClassName;
    }

    
    /**
     * @param driverClassName the driverClassName to set
     */
    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    
    /**
     * @return the initialSize
     */
    public int getInitialSize() {
        return initialSize;
    }

    
    /**
     * @param initialSize the initialSize to set
     */
    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    
    /**
     * @return the minIdle
     */
    public int getMinIdle() {
        return minIdle;
    }

    
    /**
     * @param minIdle the minIdle to set
     */
    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    
    /**
     * @return the maxActive
     */
    public int getMaxActive() {
        return maxActive;
    }

    
    /**
     * @param maxActive the maxActive to set
     */
    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    
    /**
     * @return the maxWait
     */
    public int getMaxWait() {
        return maxWait;
    }

    
    /**
     * @param maxWait the maxWait to set
     */
    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }

    
    /**
     * @return the timeBetweenEvictionRunsMillis
     */
    public int getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    
    /**
     * @param timeBetweenEvictionRunsMillis the timeBetweenEvictionRunsMillis to set
     */
    public void setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    
    /**
     * @return the minEvictableIdleTimeMillis
     */
    public int getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    
    /**
     * @param minEvictableIdleTimeMillis the minEvictableIdleTimeMillis to set
     */
    public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    
    /**
     * @return the validationQuery
     */
    public String getValidationQuery() {
        return validationQuery;
    }

    
    /**
     * @param validationQuery the validationQuery to set
     */
    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    
    /**
     * @return the testWhileIdle
     */
    public boolean isTestWhileIdle() {
        return testWhileIdle;
    }

    
    /**
     * @param testWhileIdle the testWhileIdle to set
     */
    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    
    /**
     * @return the testOnBorrow
     */
    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    
    /**
     * @param testOnBorrow the testOnBorrow to set
     */
    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    
    /**
     * @return the testOnReturn
     */
    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    
    /**
     * @param testOnReturn the testOnReturn to set
     */
    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    
    /**
     * @return the poolPreparedStatements
     */
    public boolean isPoolPreparedStatements() {
        return poolPreparedStatements;
    }

    
    /**
     * @param poolPreparedStatements the poolPreparedStatements to set
     */
    public void setPoolPreparedStatements(boolean poolPreparedStatements) {
        this.poolPreparedStatements = poolPreparedStatements;
    }

    
    /**
     * @return the maxPoolPreparedStatementPerConnectionSize
     */
    public int getMaxPoolPreparedStatementPerConnectionSize() {
        return maxPoolPreparedStatementPerConnectionSize;
    }

    
    /**
     * @param maxPoolPreparedStatementPerConnectionSize the maxPoolPreparedStatementPerConnectionSize to set
     */
    public void setMaxPoolPreparedStatementPerConnectionSize(int maxPoolPreparedStatementPerConnectionSize) {
        this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
    }

    
    /**
     * @return the filters
     */
    public String getFilters() {
        return filters;
    }

    
    /**
     * @param filters the filters to set
     */
    public void setFilters(String filters) {
        this.filters = filters;
    }

    
    /**
     * @return the connectionProperties
     */
    public String getConnectionProperties() {
        return connectionProperties;
    }

    
    /**
     * @param connectionProperties the connectionProperties to set
     */
    public void setConnectionProperties(String connectionProperties) {
        this.connectionProperties = connectionProperties;
    }
    

}
