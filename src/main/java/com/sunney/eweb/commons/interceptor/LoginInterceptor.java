/*
 * Copyright 1999-2024 Colotnet.com All right reserved. This software is the confidential and proprietary information of
 * Colotnet.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Colotnet.com.
 */
package com.sunney.eweb.commons.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sunney.eweb.constant.GlobalConstant;
import com.sunney.eweb.model.UsersDTO;

/**
 * 类LoginInterceptor.java的实现描述：TODO 类实现描述
 * 
 * @author Sunney 2016年12月7日 下午1:57:39
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        String servletPath = request.getServletPath();
        HttpSession session = request.getSession();
        UsersDTO user = (UsersDTO) session.getAttribute(GlobalConstant.ADMIN_COOKIE_NAME);
        if(null==user){
            logger.info("=================user is no login");
            response.sendRedirect(request.getContextPath() + "/login");
        }
        return true;
    }

}
