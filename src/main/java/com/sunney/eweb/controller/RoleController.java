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
import com.sunney.eweb.model.RoleDTO;
import com.sunney.eweb.query.RoleQuery;
import com.sunney.eweb.service.RoleService;

/**
 * 类RoleController.java的实现描述：TODO 类实现描述
 * 
 * @author Sunney 2016年12月7日 下午4:52:39
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    private final String FORWARD_URL  = "/role/";
    private final String REDIRECT_URL = "redirect:/role/";

    @Autowired
    RoleService          roleService;

    @Autowired
    RoleDTO              roleDTO;

    @RequestMapping(value = "/")
    public String queryRoleList() {
        logger.info("=============queryRoleList==========");
        return "role/queryRoleList";
    }

    @ResponseBody
    @RequestMapping(value = "/queryRoleListAjax", method = RequestMethod.POST)
    public Object queryUserListJson(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");

        // 得到客户端传递的页码和每页记录数，并转换成int类型
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        String orderNum = request.getParameter("orderNum");
        logger.info("pageNumber:{},pageSize:{},orderNum:{}", pageNumber, pageSize, orderNum);
        RoleQuery roleQuery = new RoleQuery();
        roleQuery.setPageSize(pageSize);
        roleQuery.setPageNum(pageNumber);
        Page<RoleDTO> page = (Page<RoleDTO>) roleService.queryRoleList(roleQuery);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("rows", page.getResult());// JSONArray
        jsonObject.put("total", page.getTotal());// 总记录数

        return jsonObject;
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{roleId}", method = RequestMethod.POST)
    public Object delete(@PathVariable Long roleId, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        Result result = new Result();
        try {
            roleService.deleteRoleById(roleId);
            result.setSuccess(true);
            result.setInfo(FORWARD_URL);
            logger.info("delete is success,roleId:{}", roleId);
        } catch (Exception e) {
            logger.error("delete is error,roleId:{},message:{}", roleId, e);
        }
        jsonObject.put("result", result);
        return jsonObject;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String _new(Model model) {
        model.addAttribute("roleDTO", roleDTO);
        logger.info("new success");
        return "role/addRole";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute("RoleDTO") RoleDTO roleDTO, Model model) {

        roleService.saveRole(roleDTO);
        logger.info("save roleId:{} successed", roleDTO.getRoleId());
        return new ModelAndView(REDIRECT_URL);
    }

    @RequestMapping(value = "/edit/{roleId}")
    public ModelAndView edit(@PathVariable Long roleId, HttpServletRequest request, HttpServletResponse response) {
        logger.info("roleId:{}", roleId);
        RoleDTO role = roleService.queryRoleById(roleId);
        return new ModelAndView("/role/editRole", "role", role);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute("RoleDTO") RoleDTO roleDTO, Model model) {

        roleService.updateRole(roleDTO);
        logger.info("update roleId:{} successed", roleDTO.getRoleId());
        return new ModelAndView(REDIRECT_URL);
    }

}
