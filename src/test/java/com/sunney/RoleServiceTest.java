/*
 * Copyright 1999-2024 Colotnet.com All right reserved. This software is the confidential and proprietary information of
 * Colotnet.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Colotnet.com.
 */
package com.sunney;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.sunney.eweb.EwebApplication;
import com.sunney.eweb.model.RoleDTO;
import com.sunney.eweb.query.RoleQuery;
import com.sunney.eweb.service.RoleService;

/**
 * 类UsersServiceTest.java的实现描述：TODO 类实现描述
 * 
 * @author Sunney 2016年11月30日 下午4:27:09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureAfter({ EwebApplication.class })
@SpringBootTest(classes = EwebApplication.class)
public class RoleServiceTest {

    protected Logger    logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RoleService roleService;

    @Test
    @Rollback(true)
    public void saveRoleTest() {
        logger.info("=======================start");
        RoleDTO role = new RoleDTO();
        role.setRoleName("test");
        roleService.saveRole(role);
        logger.info("=======================end");

    }

    @Test
    @Rollback(true)
    public void queryUsersListTest() {
        logger.info("=======================start");
        RoleQuery roleQuery = new RoleQuery();
        roleQuery.setPageSize(3);
        List<RoleDTO> roleList = roleService.queryRoleList(roleQuery);
        logger.info("=======================size:" + roleList.size());
        logger.info("=======================userList:" + JSON.toJSONString(roleList));
        logger.info("=======================end");

    }

    @Test
    @Rollback(true)
    public void deleteTest() {
        logger.info("=======================start");

        roleService.deleteRoleById(2L);
        logger.info("=======================end");

    }

}
