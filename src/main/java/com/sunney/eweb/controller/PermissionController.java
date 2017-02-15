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
import com.sunney.eweb.model.PermissionsDTO;
import com.sunney.eweb.query.PermissonsQuery;
import com.sunney.eweb.service.PermissionsService;

/**
 * 类RoleController.java的实现描述：TODO 类实现描述
 * 
 * @author Sunney 2016年12月7日 下午4:52:39
 */
@Controller
@RequestMapping("/permission")
public class PermissionController extends BaseController {

    private final String FORWARD_URL  = "/permission/";
    private final String REDIRECT_URL = "redirect:/permission/";

    @Autowired
    PermissionsService   permissionsService;

    @Autowired
    PermissionsDTO       permissionsDTO;

    @RequestMapping(value = "/")
    public String queryPermissionList() {
        logger.info("=============queryPermissionList==========");
        return "permission/queryPermissionList";
    }

    @ResponseBody
    @RequestMapping(value = "/queryPermissionListAjax", method = RequestMethod.POST)
    public Object queryUserListJson(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");

        // 得到客户端传递的页码和每页记录数，并转换成int类型
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        String orderNum = request.getParameter("orderNum");
        logger.info("pageNumber:{},pageSize:{},orderNum:{}", pageNumber, pageSize, orderNum);
        PermissonsQuery query = new PermissonsQuery();
        query.setPageSize(pageSize);
        query.setPageNum(pageNumber);
        Page<PermissionsDTO> page = (Page<PermissionsDTO>) permissionsService.queryPermissionsList(query);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("rows", page.getResult());// JSONArray
        jsonObject.put("total", page.getTotal());// 总记录数

        return jsonObject;
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{permissionId}", method = RequestMethod.POST)
    public Object delete(@PathVariable Long permissionId, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        Result result = new Result();
        try {
            permissionsService.deletePermissionsById(permissionId);
            result.setSuccess(true);
            result.setInfo(FORWARD_URL);
            logger.info("delete is success,permissionId:{}", permissionId);
        } catch (Exception e) {
            logger.error("delete is error,permissionId:{},message:{}", permissionId, e);
        }
        jsonObject.put("result", result);
        return jsonObject;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String _new(Model model) {
        model.addAttribute("permissionsDTO", permissionsDTO);
        logger.info("new success");
        return "permission/addPermission";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute("PermissionsDTO") PermissionsDTO permissionsDTO, Model model) {

        permissionsService.savePermissions(permissionsDTO);
        logger.info("save permissionId:{} successed", permissionsDTO.getPermissionId());
        return new ModelAndView(REDIRECT_URL);
    }

    @RequestMapping(value = "/edit/{permissionId}")
    public ModelAndView edit(@PathVariable Long permissionId, HttpServletRequest request,
                             HttpServletResponse response) {
        logger.info("roleId:{}", permissionId);
        PermissionsDTO permissionsDTO = permissionsService.queryPermissionsId(permissionId);
        return new ModelAndView("/permission/editPermission", "role", permissionsDTO);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute("PermissionsDTO") PermissionsDTO permissionsDTO, Model model) {

        permissionsService.updatePermissions(permissionsDTO);
        logger.info("update PermissionId:{} successed", permissionsDTO.getPermissionId());
        return new ModelAndView(REDIRECT_URL);
    }

}
