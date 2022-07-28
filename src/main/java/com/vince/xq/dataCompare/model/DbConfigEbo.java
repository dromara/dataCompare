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
public class DbConfigEbo {

    private long id;
    private String connectName;
    private String type;
    private String url;
    private String user;
    private String pwd;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getConnectName() {
        return connectName;
    }

    public void setConnectName(String connectName) {
        this.connectName = connectName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}