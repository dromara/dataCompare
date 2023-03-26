package com.vince.xq.project.system.ProbeJobInstance.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vince.xq.framework.aspectj.lang.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

/**
 * 岗位表 sys_post
 * 
 * @author ruoyi
 */
public class ProbeJobInstance implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** id */
    @Excel(name = "id", cellType = Excel.ColumnType.NUMERIC)
    private Long id;

    @Excel(name = "jobconfigId")
    private Long jobconfigId;

    @Excel(name = "tableName")
    private String tableName;

    @Excel(name = "primaryResult")
    private String primaryResult;

    @Excel(name = "enumResult")
    private String enumResult;

    @Excel(name = "nullResult")
    private String nullResult;

    @Excel(name = "lenResult")
    private String lenResult;

    @Excel(name = "filter")
    private String filter;

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public String getLenResult() {
        return lenResult;
    }

    public void setLenResult(String lenResult) {
        this.lenResult = lenResult;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Long getJobconfigId() {
        return jobconfigId;
    }

    public void setJobconfigId(Long jobconfigId) {
        this.jobconfigId = jobconfigId;
    }

    public String getPrimaryResult() {
        return primaryResult;
    }

    public void setPrimaryResult(String primaryResult) {
        this.primaryResult = primaryResult;
    }

    public String getEnumResult() {
        return enumResult;
    }

    public void setEnumResult(String enumResult) {
        this.enumResult = enumResult;
    }

    public String getNullResult() {
        return nullResult;
    }

    public void setNullResult(String nullResult) {
        this.nullResult = nullResult;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
