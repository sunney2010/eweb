/*
 * Copyright 1999-2024 Colotnet.com All right reserved. This software is the confidential and proprietary information of
 * Colotnet.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Colotnet.com.
 */
package com.sunney.eweb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 类HelloController.java的实现描述：TODO 类实现描述
 * 
 * @author Sunney 2016年10月21日 下午11:28:40
 */
@Controller
public class HelloController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/main")
    public String main() {
        logger.info("====================main");
        return "main";
    }

}
