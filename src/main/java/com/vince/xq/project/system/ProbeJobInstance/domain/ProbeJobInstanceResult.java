package com.vince.xq.project.system.ProbeJobInstance.domain;

import com.vince.xq.framework.aspectj.lang.annotation.Excel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ProbeJobInstanceResult implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Excel(name = "id", cellType = Excel.ColumnType.NUMERIC)
    private Long id;

    @Excel(name = "jobconfigId")
    private Long jobconfigId;

    @Excel(name = "tableName")
    private String tableName;

    @Excel(name = "primaryResult")
    private PrimaryResult primaryResult;

    @Excel(name = "enumResultMap")
    private Map<String,List<EnumResult>> enumResultMap;

    @Excel(name = "lenResultMap")
    private Map<String,List<EnumResult>> lenResultMap;

    @Excel(name = "nullResultList")
    private List<NullResult> nullResultList;

    @Excel(name = "filter")
    private String filter;

    public Map<String, List<EnumResult>> getLenResultMap() {
        return lenResultMap;
    }

    public void setLenResultMap(Map<String, List<EnumResult>> lenResultMap) {
        this.lenResultMap = lenResultMap;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobconfigId() {
        return jobconfigId;
    }

    public void setJobconfigId(Long jobconfigId) {
        this.jobconfigId = jobconfigId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public PrimaryResult getPrimaryResult() {
        return primaryResult;
    }

    public void setPrimaryResult(PrimaryResult primaryResult) {
        this.primaryResult = primaryResult;
    }

    public Map<String, List<EnumResult>> getEnumResultMap() {
        return enumResultMap;
    }

    public void setEnumResultMap(Map<String, List<EnumResult>> enumResultMap) {
        this.enumResultMap = enumResultMap;
    }

    public List<NullResult> getNullResultList() {
        return nullResultList;
    }

    public void setNullResultList(List<NullResult> nullResultList) {
        this.nullResultList = nullResultList;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public static class NullResult{
        private String nullField;

        private Long totalCnt;

        private Long nullCnt;

        public String getNullField() {
            return nullField;
        }

        public void setNullField(String nullField) {
            this.nullField = nullField;
        }

        public Long getTotalCnt() {
            return totalCnt;
        }

        public void setTotalCnt(Long totalCnt) {
            this.totalCnt = totalCnt;
        }

        public Long getNullCnt() {
            return nullCnt;
        }

        public void setNullCnt(Long nullCnt) {
            this.nullCnt = nullCnt;
        }
    }

    public static class EnumResult{
        private String enumValue;

        private Long cnt;

        public String getEnumValue() {
            return enumValue;
        }

        public void setEnumValue(String enumValue) {
            this.enumValue = enumValue;
        }

        public Long getCnt() {
            return cnt;
        }

        public void setCnt(Long cnt) {
            this.cnt = cnt;
        }
    }

    public static class PrimaryResult{

        private String primaryField;

        private Long cnt;

        private Long distinctCnt;

        public String getPrimaryField() {
            return primaryField;
        }

        public void setPrimaryField(String primaryField) {
            this.primaryField = primaryField;
        }

        public Long getCnt() {
            return cnt;
        }

        public void setCnt(Long cnt) {
            this.cnt = cnt;
        }

        public Long getDistinctCnt() {
            return distinctCnt;
        }

        public void setDistinctCnt(Long distinctCnt) {
            this.distinctCnt = distinctCnt;
        }
    }


}
