package com.vince.xq.dataCompare;

import com.vince.xq.dataCompare.antrl4.HiveSqlLexer;
import com.vince.xq.dataCompare.antrl4.HiveSqlParser;
import com.vince.xq.dataCompare.utils.HiveSqlMetadataParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlTest {

    /**
     * 字段解析
     */
    @Test
    public void sqlParese() throws IOException {
        String sql = "select name,age as y from pokes limit 2";
        CharStream input = CharStreams.fromString(sql);
        HiveSqlLexer lexer = new HiveSqlLexer(input);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        HiveSqlParser parser = new HiveSqlParser(tokenStream);
        HiveSqlMetadataParser visitor = new HiveSqlMetadataParser();
        visitor.visit(parser.program());
        visitor.getMetaData();
    }

    @Test
    public void sqlPareseALl() throws IOException {
        String sql = "select * from pokes limit 2";
        CharStream input = CharStreams.fromString(sql);
        HiveSqlLexer lexer = new HiveSqlLexer(input);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        HiveSqlParser parser = new HiveSqlParser(tokenStream);
        HiveSqlMetadataParser visitor = new HiveSqlMetadataParser();
        visitor.visit(parser.program());
        List<String> metaData = visitor.getMetaData();
        if (metaData.size() == 1) {
            if (metaData.get(0).equals("*")) {
                System.out.println(111);
            }
        }
    }


    @Test
    public void sqlTest() {
        String sql = "select * from pokes limit 2";
        CharStream input = CharStreams.fromString(sql);
        HiveSqlLexer lexer = new HiveSqlLexer(input);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        HiveSqlParser parser = new HiveSqlParser(tokenStream);
        HiveSqlMetadataParser visitor = new HiveSqlMetadataParser();
        visitor.visit(parser.program());
        List<String> metaData = visitor.getMetaData();
        if (metaData.size() == 0) {
            System.out.println("is null");
        } else if (metaData.size() == 1) {
            if (metaData.get(0).equals("*")) {
                System.out.println("*");
                metaData.clear();;
                metaData.addAll(testHive(sql));
            } else {

            }
        } else if (metaData.size() > 1) {

        }
    }


    public List<String> testHive(String sql) {
        List<String> columnNames=new ArrayList<>();
        //String sql = "select * from default.pokes";
        try {
            Class.forName("org.apache.hive.jdbc.HiveDriver");
        } catch (ClassNotFoundException e) {
            //log.error("注册驱动失败");
            //throw new Exception("注册驱动失败");
        }
        Connection conn = null;
        try {
            String url = "jdbc:hive2://122.112.182.244:10000";
            conn = DriverManager.getConnection(url);
            Statement stat = conn.createStatement();
            ResultSet re = stat.executeQuery(sql);
            ResultSetMetaData rsmd = re.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); ++i) {
                //System.out.println(rsmd.getColumnName(i));
                columnNames.add(rsmd.getColumnName(i).split("\\.")[1]);
                System.out.println(rsmd.getColumnName(i).split("\\.")[1]);
            }
            re.close();
            stat.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return columnNames;
    }

}
