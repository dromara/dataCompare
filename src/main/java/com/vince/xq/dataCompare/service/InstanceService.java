package com.vince.xq.dataCompare.service;

import com.vince.xq.dataCompare.common.Constant;
import com.vince.xq.dataCompare.model.CountCompareModel;
import com.vince.xq.dataCompare.model.DbConfigEbo;
import com.vince.xq.dataCompare.model.JobConfigEbo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author xiaoqiu2017wy@163.com
 * @Date 2022/8/6
 */
@Service
public class InstanceService {

    private static final Logger log = LoggerFactory.getLogger(InstanceService.class);
    @Autowired
    private JobService jobService;

    @Autowired
    private DbService dbService;

    public void run(Long job_config_id) throws Exception {
        JobConfigEbo jobConfigEbo = jobService.getDbConfigEboById(job_config_id);
        DbConfigEbo dbConfigEbo = dbService.getDbConfigEboById(jobConfigEbo.getDbConfigId());
        runAndInert(jobConfigEbo, dbConfigEbo);
    }

    public void runAndInert(JobConfigEbo jobConfigEbo, DbConfigEbo dbConfigEbo) throws Exception {
        String originTablePrimaryMd5 = DigestUtils.md5DigestAsHex((jobConfigEbo.getOriginTablePrimary()).getBytes());
        String toTablePrimaryMd5 = DigestUtils.md5DigestAsHex((jobConfigEbo.getToTablePrimary()).getBytes());
        String originTable = jobConfigEbo.getOriginTableName();
        String toTable = jobConfigEbo.getToTableName();
        String countCompareSql = Constant.countCompareSql.replace("[origin_primary_key]", originTablePrimaryMd5)
                .replace("[to_primary_key]", toTablePrimaryMd5).replace("[origin_table]", originTable)
                .replace("[to_table]", toTable);
        String connectDriver;
        switch (dbConfigEbo.getType()) {
            case Constant.hiveType:
                connectDriver = Constant.hiveConnectDriver;
                break;
            case Constant.mysqlType:
                connectDriver = Constant.mysqlConnectDriver;
                break;
            default:
                connectDriver = "";
        }
        log.info("=====runAndInert countCompareSql:{}=========", countCompareSql);
        CountCompareModel countCompareModel = runCountCompare(countCompareSql, dbConfigEbo, connectDriver);
        if (countCompareModel.getDiffUv() == 0L) {
            String md5CompareSql = Constant.md5CompareSql;
            jobConfigEbo.getOriginTablePrimary();
            jobConfigEbo.getOriginTableFields();
            runMd5Compare();
        }
    }


    private CountCompareModel runCountCompare(String sql, DbConfigEbo dbConfigEbo, String connectDriver) throws Exception {
        try {
            Class.forName(connectDriver);
        } catch (ClassNotFoundException e) {
            throw new Exception("注册驱动失败");
        }
        Connection conn = null;
        try {
            String url = dbConfigEbo.getUrl();
            conn = DriverManager.getConnection(url, dbConfigEbo.getUser(), dbConfigEbo.getPwd());
            Statement stat = conn.createStatement();
            ResultSet re = stat.executeQuery(sql);
            long baseUv = -1;
            long verifyUv = -1;
            long diffUv = -1;
            while (re.next()) {
                baseUv = re.getLong(1);
                verifyUv = re.getLong(2);
                diffUv = re.getLong(3);
            }
            re.close();
            stat.close();
            conn.close();
            CountCompareModel countCompareModel = new CountCompareModel(baseUv, verifyUv, diffUv);
            return countCompareModel;
        } catch (SQLException e) {
            throw new Exception("数据库连接失败");
        }
    }

    private void runMd5Compare() {

    }

}
