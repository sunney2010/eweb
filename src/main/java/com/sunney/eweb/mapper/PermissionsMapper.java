/*
 * Copyright 1999-2024 Colotnet.com All right reserved. This software is the confidential and proprietary information of
 * Colotnet.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Colotnet.com.
 */
package com.sunney.eweb.mapper;

import java.util.List;

import com.sunney.eweb.model.PermissionsDTO;

/**
 * 类PermissionsMapper.java的实现描述：TODO 类实现描述
 * 
 * @author Sunney 2016年12月2日 上午11:12:57
 */
public interface PermissionsMapper {

    public void savePermissions(PermissionsDTO permission);

    public List<PermissionsDTO> queryPermissionsList();

    public PermissionsDTO queryPermissionsId(Integer permissionId);

    public void updatePermissions(PermissionsDTO permission);

    public List<PermissionsDTO> queryPermissionsListByRoleId(Long roleId);

}
