/*
 * Copyright 1999-2024 Colotnet.com All right reserved. This software is the confidential and proprietary information of
 * Colotnet.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Colotnet.com.
 */
package com.sunney.eweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunney.eweb.mapper.RoleMapper;
import com.sunney.eweb.model.RoleDTO;
import com.sunney.eweb.query.RoleQuery;
import com.sunney.eweb.service.RoleService;

/**
 * 类RoleServiceImpl.java的实现描述：TODO 类实现描述
 * 
 * @author Sunney 2017年2月14日 下午10:16:12
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void saveRole(RoleDTO role) {
        roleMapper.saveRole(role);

    }

    @Override
    public List<RoleDTO> queryRoleList(RoleQuery roleQuery) {
        return roleMapper.queryRoleList(roleQuery);
    }

    @Override
    public RoleDTO queryRoleById(Long roleId) {
        return roleMapper.queryRoleById(roleId);
    }

    @Override
    public int deleteRoleById(Long roleId) {
        return roleMapper.deleteRoleById(roleId);
    }

    @Override
    public int updateRole(RoleDTO role) {
        return roleMapper.updateRole(role);
    }

}
