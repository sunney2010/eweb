/*
 * Copyright 1999-2024 Colotnet.com All right reserved. This software is the confidential and proprietary information of
 * Colotnet.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Colotnet.com.
 */
package com.sunney.eweb.commons;

import java.io.Serializable;

/**
 * 类Result.java的实现描述：返回结果
 * 
 * @author Sunney 2016年2月1日 下午1:22:30
 */
public class Result implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7018644655421921872L;
    /**
     * 是否成功
     */

    private boolean           isSuccess;

    /**
     * 错误代码
     */
    private String            errorCode;
    
    private String            infoCode;
    /**
     * 错误信息
     */
    private String            errorHint;
    
    /**
     * 成功返回的信息
     */
    private String info="";

    public Result() {
        this.isSuccess = false;
    }

    public Result(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Result(boolean isSuccess, String errorCode, String errorHint) {
        this.isSuccess = isSuccess;
        this.errorCode = errorCode;
        this.errorHint = errorHint;
    }

    public String getErrorHint() {
        return errorHint;
    }

    public void setErrorHint(String errorHint) {
        this.errorHint = errorHint;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    
    /**
     * @return the infoCode
     */
    public String getInfoCode() {
        return infoCode;
    }

    
    /**
     * @param infoCode the infoCode to set
     */
    public void setInfoCode(String infoCode) {
        this.infoCode = infoCode;
    }

    
    /**
     * @return the info
     */
    public String getInfo() {
        return info;
    }

    
    /**
     * @param info the info to set
     */
    public void setInfo(String info) {
        this.info = info;
    }

  
}
