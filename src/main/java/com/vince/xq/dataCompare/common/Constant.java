package com.vince.xq.dataCompare.common;

public class Constant {

    public static final int errCode=-1;

    public static final int successCode=0;

    public static final String connectionSql="show databases";

    public static final String mysqlType="MySQL";

    public static final String hiveType="Hive";

    public static final String hiveConnectDriver="org.apache.hive.jdbc.HiveDriver";

    public static final String mysqlConnectDriver="com.mysql.cj.jdbc.Driver";

    public static String countCompareSql="SELECT  base.uv,\n" +
            "         verify.uv AS verify_uv,\n" +
            "         base.uv - verify.uv AS diff_uv \n" +
            "from\n" +
            "    (SELECT 'total_num' AS name,  count(distinct [origin_primary_key]) AS uv\n" +
            "    FROM [origin_table] ) base left outer join\n" +
            "    (SELECT 'total_num' AS name, count(distinct [to_primary_key]) AS uv\n" +
            "    FROM [to_table] ) verify\n" +
            "ON base.name = verify.name;";

    public static String md5CompareSql="select Coalesce(base.name, verify.name) as name,\n" +
            "       sum(case when base.record_key is not null or base.record_key !='' then 1 else 0 end) as base_num,\n" +
            "       sum(case when verify.record_key is not null or verify.record_key !='' then 1 else 0 end) as verify_num,\n" +
            "       sum(case when base.record_key = verify.record_key then 1 else 0 end) as base_verify_equal_num\n" +
            "  from (\n" +
            "        select 'md5_num' AS name,\n" +
            "               md5(concat([origin_table_field])) as record_key\n" +
            "          from [origin_table]\n" +
            "       )base\n" +
            "  full outer join (\n" +
            "        select 'md5_num' AS name,\n" +
            "               md5(concat([to_table_field])) as record_key\n" +
            "          from [to_table]\n" +
            "       )verify\n" +
            "    on base.name=verify.name\n" +
            "   and base.record_key=verify.record_key\n" +
            " group by Coalesce(base.name, verify.name);";
}
