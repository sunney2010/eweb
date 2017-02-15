/*
 * Copyright 1999-2024 Colotnet.com All right reserved. This software is the confidential and proprietary information of
 * Colotnet.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Colotnet.com.
 */
package com.sunney.eweb.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sunney.eweb.model.RoleDTO;
import com.sunney.eweb.query.RoleQuery;

/**
 * 类RoleMapper.java的实现描述：TODO 类实现描述
 * 
 * @author Sunney 2017年2月14日 下午10:08:12
 */
@Component("roleMapper")
public interface RoleMapper {

    public void saveRole(RoleDTO role);

    public List<RoleDTO> queryRoleList(RoleQuery roleQuery);

    public RoleDTO queryRoleById(Long roleId);

    public int deleteRoleById(Long roleId);

    public int updateRole(RoleDTO role);

   

}
