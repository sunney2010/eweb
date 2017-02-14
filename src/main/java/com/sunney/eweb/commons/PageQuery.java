package com.sunney.eweb.commons;

import java.io.Serializable;

public class PageQuery implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8266369736035588788L;
    /** 当前页 */
    private Integer           pageNum          = 1;
    /** 查询时页面数据条数 */
    private Integer           pageSize         = 20;
    /** 重置页面数据条数 */
    private Integer           numPerPage;
    /** 上次查询时页面数据条数 */
    private Integer           numPerNum;

    /** 是否分页 */
    private Boolean           flag             = true;

    public Integer getPageNum() {
        if (null == pageNum || 0 == pageNum) {
            return 1;
        }
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        if (null != numPerNum && 0 != numPerNum && this.pageSize != numPerNum) {
            this.pageNum = 1;
        } else {
            this.pageNum = pageNum;
        }
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Integer getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(Integer numPerPage) {
        if (null != numPerNum && 0 != numPerNum && this.pageSize != numPerNum) {
            setPageNum(1);
        }
        if (null != numPerPage && 0 != numPerPage) {// 当重置页数据条数不为0或null时，重置查询页面大小
            this.pageSize = numPerPage;
        }
        this.numPerPage = numPerPage;
    }

    public Integer getNumPerNum() {
        return numPerNum;
    }

    public void setNumPerNum(Integer numPerNum) {
        if (null != numPerNum && 0 != numPerNum && this.pageSize != numPerNum) {
            setPageNum(1);
        }
        this.numPerNum = numPerNum;
    }

}
