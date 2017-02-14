/*
 * Copyright 1999-2024 Colotnet.com All right reserved. This software is the
 * confidential and proprietary information of Colotnet.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Colotnet.com.
 */
package com.sunney.eweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 类RoleController.java的实现描述：TODO 类实现描述 
 * @author Sunney 2016年12月7日 下午4:52:39
 */
@Controller("/role")
public class RoleController extends BaseController{

    @RequestMapping(value = "/queryRoleList")
    public String queryRoleList() {
        logger.info("=============queryRoleList==========");
        return "role/queryRoleList";
    }

}
