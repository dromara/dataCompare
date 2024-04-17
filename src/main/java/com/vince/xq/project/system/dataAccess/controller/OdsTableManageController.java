package com.vince.xq.project.system.dataAccess.controller;

import com.vince.xq.common.utils.StringUtils;
import com.vince.xq.framework.web.controller.BaseController;
import com.vince.xq.framework.web.page.TableDataInfo;
import com.vince.xq.project.system.dataAccess.domain.BigDataTableQuery;
import com.vince.xq.project.tool.gen.util.HdfsUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * ods表管理
 */
@Controller
@RequestMapping("/system/dataAccess")
public class OdsTableManageController extends BaseController {
    private String prefix = "system/dataAccess";
    @Value("${hdfs.sqoop.file}")
    private String sqoopFile;
    @Value("${hdfs.url}")
    private String HDFS_PATH;

    @GetMapping("")
    public String bigDataTablePage() { return prefix + "/odsTableManage"; }
    @RequestMapping("/list")
    @RequiresPermissions("system:odsTableManage:view")
    @ResponseBody
    public TableDataInfo bigDataTableQuery(@RequestParam String table) throws Exception{
        startPage();
        List<BigDataTableQuery> list=new ArrayList<>();
        List<BigDataTableQuery> result=new ArrayList<>();
        String[] split = sqoopFile.replaceAll("\n","").split(",");
        HdfsUtils hdfsUtils=new HdfsUtils();
        hdfsUtils.setHDFS_PATH(HDFS_PATH);
        hdfsUtils.initialHdfsSession();
        for(String str:split){
            String file = hdfsUtils.text(str, "utf8").replaceAll("\n","");
            String[] split1 = file.split(";");
            for(String cmd:split1){
                while (true){
                    if(cmd.contains("  ")){
                        cmd=cmd.replaceAll("  "," ");
                    }else{
                        break;
                    }
                }
                try {
                    BigDataTableQuery bigDataTableQuery=new BigDataTableQuery();
                    bigDataTableQuery.setThread(cmd.substring(cmd.length()-1,cmd.length()));
                    bigDataTableQuery.setDb(cmd.split("--hive-database ")[1].split(" --fields-terminated-by")[0].split(" ")[0]);
                    bigDataTableQuery.setTable(cmd.split("--hive-database ")[1].split(" --fields-terminated-by")[0].split(" ")[2]);
                    bigDataTableQuery.setFile(new File(str).getName());
                    list.add(bigDataTableQuery);
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println(cmd);
                }
            }
        }
        //表名查找返回
        for (BigDataTableQuery tableQuery:list) {
            if (tableQuery.getTable().equals(table)){
                result.add(tableQuery);
                return getDataTable(result);
            }
        }
        return getDataTable(list);
    }
}
