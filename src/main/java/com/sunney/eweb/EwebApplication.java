/*
 * Copyright 1999-2024 Colotnet.com All right reserved. This software is the confidential and proprietary information of
 * Colotnet.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Colotnet.com.
 */
package com.sunney.eweb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 类EwebApplication.java的实现描述：TODO 类实现描述
 * 
 * @author Sunney 2016年10月21日 下午10:42:38
 */
@SpringBootApplication
@ComponentScan
@EnableWebMvc
@EnableAutoConfiguration
@ImportResource(locations = { "classpath:applicationContext.xml" })
public class EwebApplication {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        SpringApplication.run(EwebApplication.class, args);
    }
}
