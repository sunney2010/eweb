/*
 * Copyright 1999-2024 Colotnet.com All right reserved. This software is the confidential and proprietary information of
 * Colotnet.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Colotnet.com.
 */
package com.sunney.eweb.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 类PermissionsDTO.java的实现描述：TODO 类实现描述
 * 
 * @author Sunney 2016年12月2日 上午11:13:19
 */
public class PermissionsDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4927608379726729208L;

    private Long              permissionId;
    private String            permissionName;
    private Long              parentId;
    private String            url;
    private Integer           sort;
    private String            type;
    private Date              createTime;
    private String            createOperator;
    private String            modifiedOperator;
    private Date              modifiedTime;
    private String            remark;

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

    /**
     * @return the permissionName
     */
    public String getPermissionName() {
        return permissionName;
    }

    /**
     * @param permissionName the permissionName to set
     */
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    /**
     * @return the parentId
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * @param parentId the parentId to set
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the sort
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @param sort the sort to set
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the createOperator
     */
    public String getCreateOperator() {
        return createOperator;
    }

    /**
     * @param createOperator the createOperator to set
     */
    public void setCreateOperator(String createOperator) {
        this.createOperator = createOperator;
    }

    /**
     * @return the modifiedOperator
     */
    public String getModifiedOperator() {
        return modifiedOperator;
    }

    /**
     * @param modifiedOperator the modifiedOperator to set
     */
    public void setModifiedOperator(String modifiedOperator) {
        this.modifiedOperator = modifiedOperator;
    }

    /**
     * @return the modifiedTime
     */
    public Date getModifiedTime() {
        return modifiedTime;
    }

    /**
     * @param modifiedTime the modifiedTime to set
     */
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

}
