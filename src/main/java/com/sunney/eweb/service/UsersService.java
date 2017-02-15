/*
 * Copyright 1999-2024 Colotnet.com All right reserved. This software is the confidential and proprietary information of
 * Colotnet.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Colotnet.com.
 */
package com.sunney.eweb.service;

import java.util.List;

import com.sunney.eweb.model.UsersDTO;
import com.sunney.eweb.query.UserQuery;

/**
 * 类UsersService.java的实现描述：TODO 类实现描述
 * 
 * @author Sunney 2016年11月30日 下午1:40:36
 */
public interface UsersService {

    public void saveUsers(UsersDTO user);

    public List<UsersDTO> queryUsersList(UserQuery userQuery);

    public UsersDTO queryUsersByUserId(String userId);

    public void deleteUserById(String userId);

    public void updateUsers(UsersDTO user);

}
