/*
 * Copyright 1999-2024 Colotnet.com All right reserved. This software is the
 * confidential and proprietary information of Colotnet.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Colotnet.com.
 */
package com.sunney.eweb.config;

/**
 * 类StatViewServlet.java的实现描述：TODO 类实现描述 
 * @author Sunney 2016年11月30日 下午6:12:33
 */
import javax.servlet.annotation.WebInitParam;  
import javax.servlet.annotation.WebServlet;  
  
import com.alibaba.druid.support.http.StatViewServlet;  
  
@WebServlet(urlPatterns="/druid/*",  
    initParams={  
         @WebInitParam(name="allow",value=""),// IP白名单(没有配置或者为空，则允许所有访问)  
         @WebInitParam(name="deny",value="192.168.1.73"),// IP黑名单 (存在共同时，deny优先于allow)  
         @WebInitParam(name="loginUsername",value="admin"),// 用户名  
         @WebInitParam(name="loginPassword",value="123456"),// 密码  
         @WebInitParam(name="resetEnable",value="false")// 禁用HTML页面上的“Reset All”功能  
})  
public class DruidStatViewServlet extends StatViewServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 8514823014909931773L;

}
