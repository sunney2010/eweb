/*
 * Copyright 1999-2024 Colotnet.com All right reserved. This software is the confidential and proprietary information of
 * Colotnet.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Colotnet.com.
 */
package com.sunney.eweb.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 类PermissionsVO.java的实现描述：TODO 类实现描述
 * 
 * @author Sunney 2016年12月7日 上午9:56:14
 */
public class PermissionsVO implements Serializable {

    /**
     * 
     */
    private static final long   serialVersionUID = 9161029620385156690L;

    private Long                permissionId;
    private String              permissionName;
    private Long             parentId;
    private String              url;
    private Integer             sort;
    private String              type;

    private List<PermissionsVO> subPermissionsVOList;

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
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the subPermissionsVOList
     */
    public List<PermissionsVO> getSubPermissionsVOList() {
        return subPermissionsVOList;
    }

    /**
     * @param subPermissionsVOList the subPermissionsVOList to set
     */
    public void setSubPermissionsVOList(List<PermissionsVO> subPermissionsVOList) {
        this.subPermissionsVOList = subPermissionsVOList;
    }

}
