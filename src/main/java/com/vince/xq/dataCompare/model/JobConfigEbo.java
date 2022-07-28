package com.vince.xq.dataCompare.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Package: com.vince.model
 * User: 诸葛子房
 * Email: xiaoiqu2017wy@163.com
 * Date: 2019/3/21
 * Time: 11:35
 * Description:
 */
public class JobConfigEbo {

    private long id;
    private String originTableName;
    private String originTablePrimary;
    private String originTableFields;
    private String toTableName;
    private String toTablePrimary;
    private String toTableFields;
    private long dbConfigId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    private String schduleTime;
    private int schduleStatus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOriginTablePrimary() {
        return originTablePrimary;
    }

    public void setOriginTablePrimary(String originTablePrimary) {
        this.originTablePrimary = originTablePrimary;
    }

    public String getToTablePrimary() {
        return toTablePrimary;
    }

    public void setToTablePrimary(String toTablePrimary) {
        this.toTablePrimary = toTablePrimary;
    }

    public String getOriginTableName() {
        return originTableName;
    }

    public void setOriginTableName(String originTableName) {
        this.originTableName = originTableName;
    }

    public String getOriginTableFields() {
        return originTableFields;
    }

    public void setOriginTableFields(String originTableFields) {
        this.originTableFields = originTableFields;
    }

    public String getToTableName() {
        return toTableName;
    }

    public void setToTableName(String toTableName) {
        this.toTableName = toTableName;
    }

    public String getToTableFields() {
        return toTableFields;
    }

    public void setToTableFields(String toTableFields) {
        this.toTableFields = toTableFields;
    }

    public long getDbConfigId() {
        return dbConfigId;
    }

    public void setDbConfigId(long dbConfigId) {
        this.dbConfigId = dbConfigId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSchduleTime() {
        return schduleTime;
    }

    public void setSchduleTime(String schduleTime) {
        this.schduleTime = schduleTime;
    }

    public int getSchduleStatus() {
        return schduleStatus;
    }

    public void setSchduleStatus(int schduleStatus) {
        this.schduleStatus = schduleStatus;
    }
}