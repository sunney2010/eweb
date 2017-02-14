/*
 * Copyright 1999-2024 Colotnet.com All right reserved. This software is the confidential and proprietary information of
 * Colotnet.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Colotnet.com.
 */
package com.sunney.eweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunney.eweb.mapper.PermissionsMapper;
import com.sunney.eweb.model.PermissionsDTO;
import com.sunney.eweb.service.PermissionsService;

/**
 * 类PermissionsServiceImpl.java的实现描述：TODO 类实现描述
 * 
 * @author Sunney 2016年12月6日 下午2:59:22
 */
@Service("permissionsService")
public class PermissionsServiceImpl implements PermissionsService {

    @Autowired
    private PermissionsMapper permissionsMapper;

    /*
     * (non-Javadoc)
     * @see com.sunney.eweb.service.PermissionsService#savePermissions(com.sunney.eweb.model.PermissionsDTO)
     */
    @Override
    public void savePermissions(PermissionsDTO permission) {
        permissionsMapper.savePermissions(permission);

    }

    /*
     * (non-Javadoc)
     * @see com.sunney.eweb.service.PermissionsService#queryPermissionsList()
     */
    @Override
    public List<PermissionsDTO> queryPermissionsList() {
        return permissionsMapper.queryPermissionsList();
    }

    /*
     * (non-Javadoc)
     * @see com.sunney.eweb.service.PermissionsService#queryPermissionsId(java.lang.Integer)
     */
    @Override
    public PermissionsDTO queryPermissionsId(Integer permissionId) {
        // TODO Auto-generated method stub
        return permissionsMapper.queryPermissionsId(permissionId);
    }

    /*
     * (non-Javadoc)
     * @see com.sunney.eweb.service.PermissionsService#updatePermissions(com.sunney.eweb.model.PermissionsDTO)
     */
    @Override
    public void updatePermissions(PermissionsDTO permission) {
        permissionsMapper.updatePermissions(permission);

    }

    /* (non-Javadoc)
     * @see com.sunney.eweb.service.PermissionsService#queryPermissionsListByRoleId(java.lang.Long)
     */
    @Override
    public List<PermissionsDTO> queryPermissionsListByRoleId(Long roleId) {
        return permissionsMapper.queryPermissionsListByRoleId(roleId);
    }

}
