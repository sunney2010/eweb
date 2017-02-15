/*
 * Copyright 1999-2024 Colotnet.com All right reserved. This software is the confidential and proprietary information of
 * Colotnet.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Colotnet.com.
 */
package com.sunney.eweb.model;

import java.io.Serializable;

/**
 * 类RolePermissionDTO.java的实现描述：TODO 类实现描述
 * 
 * @author Sunney 2017年2月14日 下午10:29:41
 */
public class RolePermissionDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8808371933300044651L;

    private Long              roleId;
    private Long              permissionId;
    
    /**
     * @return the roleId
     */
    public Long getRoleId() {
        return roleId;
    }
    
    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    
    /**
     * @return the permissionId
     */
    public Long getPermissionId() {
        return permissionId;
    }
    
    /**
     * @param permissionId the permissionId to set
     */
    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
    

}
