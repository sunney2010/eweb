/*
 * Copyright 1999-2024 Colotnet.com All right reserved. This software is the confidential and proprietary information of
 * Colotnet.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Colotnet.com.
 */
package com.sunney.eweb.config;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.pagehelper.PageHelper;

/**
 * 类MybatisConfiguration.java的实现描述：TODO 类实现描述
 * 
 * @author Sunney 2016年11月30日 下午6:18:00
 */
@Configuration
@AutoConfigureAfter({ DruidConfiguration.class })
@MapperScan(basePackages = { "com.sunney.eweb.mapper" })
@EnableTransactionManagement
@ConfigurationProperties(prefix = "myBatis")
public class MybatisConfiguration {

    private Logger     logger = LoggerFactory.getLogger(MybatisConfiguration.class);
    // 配置类型别名
    private String     typeAliasesPackage;

    // 配置mapper的扫描，找到所有的mapper.xml映射文件
    private String     mapperLocations;

    // 加载全局的配置文件
    private String     configLocation;

    @Resource(name = "dataSource")
    private DataSource dataSource;

    // 提供SqlSeesion
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {
        try {
            SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
            sessionFactory.setDataSource(dataSource);

            // 分页插件
            PageHelper pageHelper = new PageHelper();
            Properties properties = new Properties();
            properties.setProperty("reasonable", "true");
            properties.setProperty("supportMethodsArguments", "true");
            properties.setProperty("returnPageInfo", "check");
            properties.setProperty("params", "count=countSql");
            pageHelper.setProperties(properties);

            // 添加插件
            sessionFactory.setPlugins(new Interceptor[] { pageHelper });

            sessionFactory.setTypeAliasesPackage(typeAliasesPackage);
            sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
            sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(configLocation));
            return sessionFactory.getObject();
        } catch (IOException e) {
            logger.error("mybatis resolver mapper*xml is error");
            return null;
        } catch (Exception e) {
            logger.error("mybatis sqlSessionFactoryBean create error");
            return null;
        }
    }

    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * @return the typeAliasesPackage
     */
    public String getTypeAliasesPackage() {
        return typeAliasesPackage;
    }

    /**
     * @param typeAliasesPackage the typeAliasesPackage to set
     */
    public void setTypeAliasesPackage(String typeAliasesPackage) {
        this.typeAliasesPackage = typeAliasesPackage;
    }

    /**
     * @return the mapperLocations
     */
    public String getMapperLocations() {
        return mapperLocations;
    }

    /**
     * @param mapperLocations the mapperLocations to set
     */
    public void setMapperLocations(String mapperLocations) {
        this.mapperLocations = mapperLocations;
    }

    /**
     * @return the configLocation
     */
    public String getConfigLocation() {
        return configLocation;
    }

    /**
     * @param configLocation the configLocation to set
     */
    public void setConfigLocation(String configLocation) {
        this.configLocation = configLocation;
    }

}
