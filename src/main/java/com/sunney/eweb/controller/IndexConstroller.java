/*
 * Copyright 1999-2024 Colotnet.com All right reserved. This software is the confidential and proprietary information of
 * Colotnet.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Colotnet.com.
 */
package com.sunney.eweb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.sunney.eweb.constant.GlobalConstant;
import com.sunney.eweb.model.PermissionsDTO;
import com.sunney.eweb.model.UsersDTO;
import com.sunney.eweb.service.PermissionsService;
import com.sunney.eweb.vo.PermissionsVO;

/**
 * 类IndexConstroller.java的实现描述：TODO 类实现描述
 * 
 * @author Sunney 2016年12月6日 下午6:00:34
 */
@Controller
public class IndexConstroller extends BaseController {

    @Autowired
    PermissionsService permissionsService;

    @RequestMapping(value = "/index")
    public ModelAndView index(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView viem = new ModelAndView("index");
        UsersDTO user = (UsersDTO) session.getAttribute(GlobalConstant.ADMIN_COOKIE_NAME);
       
        logger.info("user:" + JSON.toJSONString(user));
        List<PermissionsDTO> list = permissionsService.queryPermissionsListByRoleId(1l);
        List<PermissionsVO> treeMenuList=treeMenuList(list);
        viem.addObject("treeMenuList", treeMenuList);
        viem.addObject("user", user);
        logger.info("list:" + JSON.toJSONString(list));
        logger.info("====================index");
        return viem;
    }

    /**
     * @param list
     * @param parentId
     * @return
     */
    public List<PermissionsVO> treeMenuList(List<PermissionsDTO> menuList) {
        List<PermissionsVO> voList = new ArrayList<PermissionsVO>();
        PermissionsVO mainMenu;
        for (PermissionsDTO permiss : menuList) {
            if (permiss.getType().equals("MainMenu")) {
                mainMenu = new PermissionsVO();
                BeanUtils.copyProperties(permiss, mainMenu);
                List<PermissionsVO> subList = new ArrayList<PermissionsVO>();
                PermissionsVO subMenu;
                for (PermissionsDTO subpermiss : menuList) {

                    if (subpermiss.getParentId().equals(permiss.getPermissionId())
                        && subpermiss.getType().equals("SecondMenu")) {
                        subMenu = new PermissionsVO();
                        BeanUtils.copyProperties(subpermiss, subMenu);
                        subList.add(subMenu);
                    }
                }
                mainMenu.setSubPermissionsVOList(subList);
                voList.add(mainMenu);
            }

        }
        return voList;
    }
}
