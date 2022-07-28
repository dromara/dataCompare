package com.vince.xq.dataCompare.service;

import com.vince.xq.dataCompare.common.Constant;
import com.vince.xq.dataCompare.dao.DbConfigDao;
import com.vince.xq.dataCompare.model.DbConfigEbo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.Date;
import java.util.List;

@Service
public class DbService {
    private static final Logger log = LoggerFactory.getLogger(DbService.class);

    @Autowired
    private DbConfigDao dbConfigDao;

    public DbConfigEbo getDbConfigEboById(Long id){
        return dbConfigDao.getDbConfigById(id);
    }

    public void insert(String connectName,String type, String url, String user, String pwd) {
        DbConfigEbo dbConfigEbo = new DbConfigEbo();
        dbConfigEbo.setConnectName(connectName);
        dbConfigEbo.setType(type);
        dbConfigEbo.setUrl(url);
        dbConfigEbo.setUser(user);
        dbConfigEbo.setPwd(pwd);
        dbConfigEbo.setCreateTime(new Date());
        dbConfigDao.insert(dbConfigEbo);
    }

    public boolean update(Long id,String connectName,String type, String url, String user, String pwd) {
        DbConfigEbo dbConfigEbo = new DbConfigEbo();
        dbConfigEbo.setId(id);
        dbConfigEbo.setConnectName(connectName);
        dbConfigEbo.setType(type);
        dbConfigEbo.setUrl(url);
        dbConfigEbo.setUser(user);
        dbConfigEbo.setPwd(pwd);
        dbConfigEbo.setCreateTime(new Date());
        if (dbConfigDao.update(dbConfigEbo) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void delete(Long id) {
        dbConfigDao.delete(id);
    }

    public List<DbConfigEbo> getAllDbConfig() {
        return dbConfigDao.findAll();
    }

    public void testConnection(String type, String url, String user, String pwd) throws Exception {
        if (type.equals(Constant.mysqlType)) {
            testConnectionDriver(url, user, pwd,Constant.hiveConnectDriver);
        } else if (type.equals(Constant.hiveType)) {
            testConnectionDriver(url, user, pwd,Constant.mysqlConnectDriver);
        } else {
            throw new Exception("不识别的类型");
        }
    }

    /*private void testConnectionHive(String url, String user, String pwd) throws Exception {
        try {
            Class.forName(Constant.hiveConnectDriver);
        } catch (ClassNotFoundException e) {
            log.error("注册驱动失败");
            throw new Exception("注册驱动失败");
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pwd);
            Statement stat = conn.createStatement();
            ResultSet re = stat.executeQuery(Constant.connectionSql);
            int i = 0;
            while (re.next()) {
                i++;
                //System.out.println(re.getString(1));
            }
            re.close();
            stat.close();
            conn.close();
            if (i == 0) {
                log.error("该连接下没有库");
                throw new Exception("该连接下没有库");
            }
        } catch (SQLException e) {
            log.error("连接数据库失败");
            throw new Exception("连接数据库失败");
        }
    }*/

    private void testConnectionDriver(String url, String user, String pwd,String connectDriver) throws Exception {
        try {
            Class.forName(connectDriver);
        } catch (ClassNotFoundException e) {
            log.error("注册驱动失败");
            throw new Exception("注册驱动失败");
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pwd);
            Statement stat = conn.createStatement();
            ResultSet re = stat.executeQuery(Constant.connectionSql);
            int i = 0;
            while (re.next()) {
                i++;
                //System.out.println(re.getString(1));
            }
            re.close();
            stat.close();
            conn.close();
            if (i == 0) {
                log.error("该连接下没有库");
                throw new Exception("该连接下没有库");
            }
        } catch (SQLException e) {
            log.error("连接数据库失败");
            throw new Exception("连接数据库失败");
        }
    }

}
