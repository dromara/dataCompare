package com.vince.xq.dataCompare.service;

import com.vince.xq.dataCompare.antrl4.HiveSqlLexer;
import com.vince.xq.dataCompare.antrl4.HiveSqlParser;
import com.vince.xq.dataCompare.common.Constant;
import com.vince.xq.dataCompare.controller.DbController;
import com.vince.xq.dataCompare.dao.DbConfigDao;
import com.vince.xq.dataCompare.dao.JobConfigDao;
import com.vince.xq.dataCompare.model.DbConfigEbo;
import com.vince.xq.dataCompare.model.JobConfigEbo;
import com.vince.xq.dataCompare.utils.HiveSqlMetadataParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author xiaoqiu2017wy@163.com
 * @Date 2022/7/13
 */
@Service
public class JobService {
    private static final Logger log = LoggerFactory.getLogger(JobService.class);

    @Autowired
    private JobConfigDao jobConfigDao;

    @Autowired
    private DbConfigDao dbConfigDao;

    public void testStr(){
        System.out.println(111);
    }

    public JobConfigEbo getDbConfigEboById(Long id){
        return jobConfigDao.getJobConfigById(id);
    }

    public void insert(String originTableName,String originTablePrimary,String originTableFields, String toTableName,
                       String toTablePrimary,String toTableFields,int dbConfigId,
                       String schduleTime,int schduleStatus) {
        JobConfigEbo jobConfigEbo=new JobConfigEbo();
        jobConfigEbo.setOriginTableName(originTableName);
        jobConfigEbo.setOriginTablePrimary(originTablePrimary);
        jobConfigEbo.setOriginTableFields(originTableFields);
        jobConfigEbo.setToTableName(toTableName);
        jobConfigEbo.setToTablePrimary(toTablePrimary);
        jobConfigEbo.setToTableFields(toTableFields);
        jobConfigEbo.setToTableFields(toTableFields);
        jobConfigEbo.setDbConfigId(dbConfigId);
        jobConfigEbo.setSchduleStatus(schduleStatus);
        if(StringUtils.isBlank(schduleTime)){
            jobConfigEbo.setSchduleTime(schduleTime);
        }
        jobConfigDao.insert(jobConfigEbo);
    }

    public void delete(Long id) {
        jobConfigDao.delete(id);
    }

    public List<JobConfigEbo> getAllJobConfig() {
        return jobConfigDao.findAll();
    }

    public List<String> getTableFields(String sql,int dbConfigId) throws Exception {
        DbConfigEbo dbConfigEbo= dbConfigDao.getDbConfigById(dbConfigId);

        CharStream input = CharStreams.fromString(sql);
        HiveSqlLexer lexer = new HiveSqlLexer(input);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        HiveSqlParser parser = new HiveSqlParser(tokenStream);
        HiveSqlMetadataParser visitor = new HiveSqlMetadataParser();
        visitor.visit(parser.program());
        List<String> metaData = visitor.getMetaData();
        if (metaData.size() == 0) {
            return null;
        } else if (metaData.size() == 1) {
            if (metaData.get(0).equals("*")) {
                log.info("===getTableFields one field===*");
                metaData.clear();
                switch(dbConfigEbo.getType()){
                    case Constant.hiveType:
                        metaData.addAll(getTableField(sql,dbConfigEbo,Constant.hiveConnectDriver));
                        break; //可选
                    case Constant.mysqlType:
                        metaData.addAll(getTableField(sql,dbConfigEbo,Constant.mysqlConnectDriver));
                        break; //可选
                }
            } else {
                log.info("===getTableFields field:{}===",metaData.toString());
            }
        } else if (metaData.size() > 1) {
            log.info("===getTableFields field:{}===",metaData.toString());
        }
        return metaData;
    }

    private List<String> getTableField(String sql,DbConfigEbo dbConfigEbo,String connectDriver) throws Exception {
        List<String> columnNames=new ArrayList<>();
        try {
            Class.forName(connectDriver);
        } catch (ClassNotFoundException e) {
            throw new Exception("注册驱动失败");
        }
        Connection conn = null;
        try {
            String url = dbConfigEbo.getUrl();
            conn = DriverManager.getConnection(url,dbConfigEbo.getUser(),dbConfigEbo.getPwd());
            Statement stat = conn.createStatement();
            ResultSet re = stat.executeQuery(sql);
            ResultSetMetaData rsmd = re.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); ++i) {
                columnNames.add(rsmd.getColumnName(i).split("\\.")[1]);
            }
            re.close();
            stat.close();
            conn.close();
        } catch (SQLException e) {
            throw new Exception("数据库连接失败");
        }
        return columnNames;
    }



}
