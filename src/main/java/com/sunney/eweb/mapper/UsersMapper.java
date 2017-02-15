/*
 * Copyright 1999-2024 Colotnet.com All right reserved. This software is the
 * confidential and proprietary information of Colotnet.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Colotnet.com.
 */
package com.sunney.eweb.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sunney.eweb.model.UsersDTO;
import com.sunney.eweb.query.UserQuery;

/**
 * 类UsersMapper.java的实现描述：TODO 类实现描述 
 * @author Sunney 2016年11月30日 下午1:49:45
 */
@Component("usersMapper") 
public interface  UsersMapper {

    public void saveUsers(UsersDTO user);

    public List<UsersDTO> queryUsersList(UserQuery userQuery);

    public UsersDTO queryUsersByUserId(String userId);

    public int deleteUserById(String userId);

    public int updateUsers(UsersDTO user);
    

}
