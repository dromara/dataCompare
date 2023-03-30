package com.vince.xq.project.system.probeJobConfig.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vince.xq.framework.aspectj.lang.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

/**
 * 岗位表 sys_post
 * 
 * @author ruoyi
 */
public class Probejobconfig implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** id */
    @Excel(name = "id", cellType = Excel.ColumnType.NUMERIC)
    private Long id;

    @Excel(name = "tableName")
    private String tableName;

    @Excel(name = "tablePrimary")
    private String tablePrimary;

    @Excel(name = "tableEnumFields")
    private String tableEnumFields;

    @Excel(name = "tableNullFields")
    private String tableNullFields;

    @Excel(name = "tableLengthFields")
    private String tableLengthFields;

    /** createBy */
    @Excel(name = "createBy")
    private String createBy;

    @Excel(name = "filter")
    private String filter;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Excel(name = "dbConfigId")
    private Long dbConfigId;

    public String getTableLengthFields() {
        return tableLengthFields;
    }

    public void setTableLengthFields(String tableLengthFields) {
        this.tableLengthFields = tableLengthFields;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTablePrimary() {
        return tablePrimary;
    }

    public void setTablePrimary(String tablePrimary) {
        this.tablePrimary = tablePrimary;
    }

    public String getTableEnumFields() {
        return tableEnumFields;
    }

    public void setTableEnumFields(String tableEnumFields) {
        this.tableEnumFields = tableEnumFields;
    }

    public String getTableNullFields() {
        return tableNullFields;
    }

    public void setTableNullFields(String tableNullFields) {
        this.tableNullFields = tableNullFields;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getDbConfigId() {
        return dbConfigId;
    }

    public void setDbConfigId(Long dbConfigId) {
        this.dbConfigId = dbConfigId;
    }
}
