package com.vince.xq.project.common;

import com.vince.xq.project.system.instance.controller.InstanceController;
import com.vince.xq.project.system.jobconfig.domain.Jobconfig;
import com.vince.xq.project.system.dbconfig.domain.Dbconfig;
import com.vince.xq.project.system.instance.domain.Instance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RunUtil {
    private static final Logger log = LoggerFactory.getLogger(RunUtil.class);

    public static final String PV_UV_SQL = "select base.pv,\n" +
            "       verify.pv as verify_pv,\n" +
            "       base.pv - verify.pv as diff_pv,\n" +
            "       base.uv,\n" +
            "       verify.uv as verify_uv,\n" +
            "       base.uv - verify.uv as diff_uv\n" +
            "  from (\n" +
            "        select 'num' as compareKey,\n" +
            "               count(1) as pv,\n" +
            "               count(distinct %s) as uv\n" +
            "          from %s\n" +
            "         %s\n" +
            "       )base\n" +
            "  left outer join (\n" +
            "        select 'num' as compareKey,\n" +
            "               count(1) as pv,\n" +
            "               count(distinct %s) as uv\n" +
            "          from %s\n" +
            "         %s\n" +
            "       )verify\n" +
            "    on base.compareKey=verify.compareKey";

    public static final String CONSISTENCY_SQL = "select compareKey,sum(base_num) as base_num,sum(verify_num) as verify_num,sum(base_verify_equal_num) as base_verify_equal_num from (\n" +
            "\n" +
            "select Coalesce(base.compareKey, verify.compareKey) as compareKey,\n" +
            "       sum(case when base.record_key is not null or base.record_key !='' then 1 else 0 end) as base_num,\n" +
            "       sum(case when verify.record_key is not null or verify.record_key !='' then 1 else 0 end) as verify_num,\n" +
            "       sum(case when base.record_key = verify.record_key then 1 else 0 end) as base_verify_equal_num\n" +
            "  from (\n" +
            "        select 'consistency' as compareKey,\n" +
            "               MD5(concat(%s)) as record_key\n" +
            "          from %s\n" +
            "         %s\n" +
            "       )base\n" +
            "  left join (\n" +
            "        select 'consistency' as compareKey,\n" +
            "               MD5(concat(%s)) as record_key\n" +
            "          from %s\n" +
            "          %s\n" +
            "       )verify\n" +
            "    on base.compareKey=verify.compareKey\n" +
            "   and base.record_key=verify.record_key\n" +
            " group by Coalesce(base.compareKey, verify.compareKey)\n" +
            "\n" +
            "UNION\n" +
            "\n" +
            "select Coalesce(base.compareKey, verify.compareKey) as compareKey,\n" +
            "       sum(case when base.record_key is not null or base.record_key !='' then 1 else 0 end) as base_num,\n" +
            "       sum(case when verify.record_key is not null or verify.record_key !='' then 1 else 0 end) as verify_num,\n" +
            "       sum(case when base.record_key = verify.record_key then 1 else 0 end) as base_verify_equal_num\n" +
            "  from (\n" +
            "        select 'consistency' as compareKey,\n" +
            "               MD5(concat(%s)) as record_key\n" +
            "          from %s\n" +
            "          %s\n" +
            "       )base\n" +
            "  right join (\n" +
            "        select 'consistency' as compareKey,\n" +
            "               MD5(concat(%s)) as record_key\n" +
            "          from %s\n" +
            "         %s\n" +
            "       )verify\n" +
            "    on base.compareKey=verify.compareKey\n" +
            "   and base.record_key=verify.record_key\n" +
            " group by Coalesce(base.compareKey, verify.compareKey)\n" +
            "\n" +
            ") t GROUP BY compareKey";

    public static final String COLUMN = "if({column} is null, '-', {column})";

    public static Instance run(Dbconfig dbconfig, Jobconfig jobconfig) throws Exception {

        DbTypeEnum dbTypeEnum = DbTypeEnum.findEnumByType(dbconfig.getType());

        String compareNumSql = String.format(PV_UV_SQL, jobconfig.getOriginTablePrimary(), jobconfig.getOriginTableName(), Optional.ofNullable(jobconfig.getOriginTableFilter()).orElse("")
                , jobconfig.getToTablePrimary(), jobconfig.getToTableName(), Optional.ofNullable(jobconfig.getToTableFilter()).orElse(""));

        log.info("====compareNumSql:{}====", compareNumSql);

        String[] fileds = jobconfig.getOriginTableFields().split(",");
        List<String> originColumns = new ArrayList<>();
        originColumns.add(COLUMN.replace("{column}", jobconfig.getOriginTablePrimary()));
        for (int i = 0; i < fileds.length; i++) {
            originColumns.add(COLUMN.replace("{column}", fileds[i]));
        }
        List<String> toColumns = new ArrayList<>();
        toColumns.add(COLUMN.replace("{column}", jobconfig.getToTablePrimary()));
        for (int i = 0; i < fileds.length; i++) {
            toColumns.add(COLUMN.replace("{column}", fileds[i]));
        }
        String consistencyNumSql = String.format(CONSISTENCY_SQL,
                String.join(",", originColumns),
                jobconfig.getOriginTableName(),
                Optional.ofNullable(jobconfig.getOriginTableFilter()).orElse(""),

                String.join(",", toColumns),
                jobconfig.getToTableName(),
                Optional.ofNullable(jobconfig.getToTableFilter()).orElse(""),

                String.join(",", originColumns),
                jobconfig.getOriginTableName(),
                Optional.ofNullable(jobconfig.getOriginTableFilter()).orElse(""),

                String.join(",", toColumns),
                jobconfig.getToTableName(),
                Optional.ofNullable(jobconfig.getToTableFilter()).orElse(""));

        log.info("====consistencyNumSql:{}====", consistencyNumSql);

        Instance instance = runNum(dbconfig, dbTypeEnum.getConnectDriver(), compareNumSql, consistencyNumSql);
        instance.setMagnitudeSql(compareNumSql);
        instance.setConsistencySql(consistencyNumSql);
        return instance;
    }

    //量级对比
    public static Instance runNum(Dbconfig dbconfig, String connectDriver, String compareSql, String consistencyNumSql) throws Exception {
        Instance instance = new Instance();
        try {
            Class.forName(connectDriver);
        } catch (ClassNotFoundException e) {
            throw new Exception("注册驱动失败");
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbconfig.getUrl(), dbconfig.getUserName(), dbconfig.getPwd());
            Statement stat = conn.createStatement();
            ResultSet re = stat.executeQuery(compareSql);
            while (re.next()) {
                instance.setOriginTablePv(re.getString(1));
                instance.setToTablePv(re.getString(2));
                instance.setPvDiff(re.getString(3));
                instance.setOriginTableUv(re.getString(4));
                instance.setToTableUv(re.getString(5));
                instance.setUvDiff(re.getString(6));
            }
            re.close();
            re = stat.executeQuery(consistencyNumSql);
            while (re.next()) {
                instance.setOriginTableCount(re.getString(2));
                instance.setToTableCount(re.getString(3));
                instance.setCountDiff(re.getString(4));
            }
            re.close();
            stat.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("连接数据库失败");
        }
        return instance;
    }
}
