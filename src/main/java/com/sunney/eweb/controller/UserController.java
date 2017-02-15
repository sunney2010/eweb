/*
 * Copyright 1999-2024 Colotnet.com All right reserved. This software is the confidential and proprietary information of
 * Colotnet.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Colotnet.com.
 */
package com.sunney.eweb.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.sunney.eweb.commons.Result;
import com.sunney.eweb.model.UsersDTO;
import com.sunney.eweb.query.UserQuery;
import com.sunney.eweb.service.UsersService;

/**
 * 类UserController.java的实现描述：TODO 类实现描述
 * 
 * @author Sunney 2016年12月1日 下午12:36:16
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    private final String FORWARD_URL="/user/";
    private final String REDIRECT_URL = "redirect:/user/";

    @Autowired
    UsersService         usersService;

    @Autowired
    private UsersDTO     usersDTO;

    @RequestMapping(value = "/")
    public String queryUserList(HttpServletRequest request) {
        logger.info("=============queryUserList==========");
        return "user/queryUserList";
    }

    @ResponseBody
    @RequestMapping(value = "/queryUserListAjax", method = RequestMethod.POST)
    public Object queryUserListJson(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");

        // 得到客户端传递的页码和每页记录数，并转换成int类型
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        String orderNum = request.getParameter("orderNum");
        logger.info("pageNumber:{},pageSize:{},orderNum:{}", pageNumber, pageSize, orderNum);
        UserQuery userQuery = new UserQuery();
        userQuery.setPageSize(pageSize);
        userQuery.setPageNum(pageNumber);
        Page<UsersDTO> page = (Page<UsersDTO>) usersService.queryUsersList(userQuery);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("rows", page.getResult());// JSONArray
        jsonObject.put("total", page.getTotal());// 总记录数

        return jsonObject;
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.POST)
    public Object delete(@PathVariable String userId, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        Result result = new Result();
        try {
            usersService.deleteUserById(userId);
            result.setSuccess(true);
            result.setInfo(FORWARD_URL);
            logger.info("delete is success,userId:{}", userId);
        } catch (Exception e) {
            logger.error("delete is error,userId:{},message:{}", userId, e);
        }
        jsonObject.put("result", result);
        return jsonObject;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String _new(Model model) {
        model.addAttribute("usersDTO", usersDTO);
        logger.info("new success");
        return "user/addUser";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute("usersDTO") UsersDTO usersDTO, Model model) {

        usersService.saveUsers(usersDTO);
        logger.info("save userId:{} successed", usersDTO.getUserId());
        return new ModelAndView(REDIRECT_URL);
    }

    @RequestMapping(value = "/edit/{userId}")
    public ModelAndView edit(@PathVariable String userId, HttpServletRequest request, HttpServletResponse response) {
        logger.info("userId:{}", userId);
        UsersDTO user = usersService.queryUsersByUserId(userId);
        return new ModelAndView("/user/editUser", "user", user);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute("usersDTO") UsersDTO usersDTO, Model model) {

        usersService.updateUsers(usersDTO);
        logger.info("update userId:{} successed", usersDTO.getUserId());
        return new ModelAndView(REDIRECT_URL);
    }

}
