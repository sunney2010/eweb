/*
 * Copyright 1999-2024 Colotnet.com All right reserved. This software is the confidential and proprietary information of
 * Colotnet.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Colotnet.com.
 */
package com.sunney.eweb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sunney.eweb.commons.Result;
import com.sunney.eweb.constant.GlobalConstant;
import com.sunney.eweb.model.UsersDTO;
import com.sunney.eweb.service.UsersService;

/**
 * 类LoginController.java的实现描述：TODO 类实现描述
 * 
 * @author Sunney 2016年12月1日 下午12:40:29
 */
@Controller
public class LoginController extends BaseController {

    @Autowired
    UsersService usersService;

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    @ResponseBody
    public Object checkLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        Result result = new Result();
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(password)) {

        }
        logger.info("======================" + userId + password);
        UsersDTO user = usersService.queryUsersByUserId(userId);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                logger.info("login success");
                result.setSuccess(true);
                result.setInfoCode("success");
                session.setAttribute(GlobalConstant.ADMIN_COOKIE_NAME, user);
            } else {
                result.setSuccess(false);
                result.setInfoCode("密码不正确");
                logger.info("密码不正确");
            }
        } else {
            result.setSuccess(false);
            result.setInfoCode("用户不存在");
            logger.info("用户不存在");
        }
        return result;
    }

}
