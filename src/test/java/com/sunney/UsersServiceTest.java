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
import com.sunney.eweb.commons.UserQuery;
import com.sunney.eweb.model.UsersDTO;
import com.sunney.eweb.service.UsersService;

/**
 * 类UsersServiceTest.java的实现描述：TODO 类实现描述
 * 
 * @author Sunney 2016年11月30日 下午4:27:09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureAfter({ EwebApplication.class })
@SpringBootTest(classes = EwebApplication.class)
public class UsersServiceTest {

    protected Logger     logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UsersService usersService;

    @Test
    @Rollback(true)
    public void saveUsersTest() {
        logger.info("=======================start");
        for (int i = 1; i < 100; i++) {
            UsersDTO user = new UsersDTO();
            user.setUserId("555_"+i);
            user.setUserName("sunney_"+i);
            user.setPassword("55555555555555555");
            user.setSalt("555555555555555");
            user.setEmail("4444444444");
            user.setRemark("44444444444444");
            user.setMobile("15989569150");
            usersService.saveUsers(user);
            logger.info("=======================end");
        }

    }

    @Test
    @Rollback(true)
    public void queryUsersListTest() {
        logger.info("=======================start");
        UserQuery userQuery = new UserQuery();
        userQuery.setPageSize(3);
        List<UsersDTO> userList = usersService.queryUsersList(userQuery);
        logger.info("=======================size:" + userList.size());
        logger.info("=======================userList:" + JSON.toJSONString(userList));
        logger.info("=======================end");

    }
    @Test
    @Rollback(true)
    public void deleteTest() {
        logger.info("=======================start");
       
        usersService.deleteUserById("555");
        logger.info("=======================end");

    }

}
