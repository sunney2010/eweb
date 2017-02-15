/*
 * Copyright 1999-2024 Colotnet.com All right reserved. This software is the confidential and proprietary information of
 * Colotnet.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Colotnet.com.
 */
package com.sunney.eweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunney.eweb.mapper.UsersMapper;
import com.sunney.eweb.model.UsersDTO;
import com.sunney.eweb.query.UserQuery;
import com.sunney.eweb.service.UsersService;

/**
 * 类UsersServiceImpl.java的实现描述：TODO 类实现描述
 * 
 * @author Sunney 2016年11月30日 下午1:40:57
 */
@Service("usersService")
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public void saveUsers(UsersDTO user) {
        usersMapper.saveUsers(user);

    }

    @Override
    public List<UsersDTO> queryUsersList(UserQuery userQuery) {
        return usersMapper.queryUsersList(userQuery);
    }

    @Override
    public UsersDTO queryUsersByUserId(String userId) {
        return usersMapper.queryUsersByUserId(userId);
    }

    @Override
    public void deleteUserById(String userId) {
        usersMapper.deleteUserById(userId);

    }

    @Override
    public void updateUsers(UsersDTO user) {
        usersMapper.updateUsers(user);

    }

}
