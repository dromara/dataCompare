package com.vince.xq.dataCompare.controller;

import com.alibaba.fastjson.JSONObject;
import com.vince.xq.dataCompare.service.DbService;
import com.vince.xq.dataCompare.service.JobService;
import com.vince.xq.dataCompare.common.Constant;
import com.vince.xq.dataCompare.model.JobConfigEbo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author xiaoqiu2017wy@163.com
 * @Date 2022/7/9
 */
@RequestMapping("/job")
@Controller
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private DbService dbService;

    @RequestMapping("/jobConfig.html")
    public String index(){
        return "jobConfig";
    }

    @RequestMapping("/addJobConfig.html")
    public String addJobConfig(ModelMap map){
        map.addAttribute("dbConfig",dbService.getAllDbConfig());
        return "addJobConfig";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject insert(String originTableName,String originTablePrimary, String originTableFields, String toTableName,
                             String toTablePrimary,String toTableFields,int dbConfigId,
                             String schduleTime,int schduleStatus) {
        JSONObject target = new JSONObject();
        try {
            jobService.insert(originTableName,originTablePrimary,originTableFields,toTableName,toTablePrimary,toTableFields,dbConfigId,schduleTime,schduleStatus);
            target.put("code", Constant.successCode);
        } catch (Exception e) {
            target.put("code", Constant.errCode);
            target.put("msg", e.getMessage());
        }
        return target;
    }

    @RequestMapping(value = "/get_all_job_config", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getAllDbConfig(String sql) {
        JSONObject target = new JSONObject();
        try {
            List<JobConfigEbo> jobConfigEboList= jobService.getAllJobConfig();
            target.put("code", Constant.successCode);
            target.put("count",jobConfigEboList.size());
            target.put("data",jobConfigEboList);
        } catch (Exception e) {
            target.put("code", Constant.errCode);
            target.put("msg", e.getMessage());
        }
        return target;
    }


    @RequestMapping(value = "/get_table_field", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getTableFileds(String sql,int dbConfigId) {
        JSONObject target = new JSONObject();
        try {
            List<String> tableFields= jobService.getTableFields(sql,dbConfigId);
            target.put("code", Constant.successCode);
            target.put("data",tableFields);
        } catch (Exception e) {
            target.put("code", Constant.errCode);
            target.put("msg", e.getMessage());
        }
        return target;
    }


    /*
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject update(Long id,String type, String url, String user, String pwd) {
        JSONObject target = new JSONObject();
        try {
            dbService.testConnection(type, url, user, pwd);
            if(dbService.update(id,type, url, user, pwd)){
                target.put("code", Constant.successCode);
                target.put("msg","update succes");
            }else {
                target.put("code", Constant.errCode);
                target.put("msg","update error");
            }
        } catch (Exception e) {
            target.put("code", Constant.errCode);
            target.put("msg", e.getMessage());
        }
        return target;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject delete(Long id) {
        JSONObject target = new JSONObject();
        try {
            dbService.delete(id);
            target.put("code", Constant.successCode);
            target.put("msg","delete success");
        } catch (Exception e) {
            target.put("code", Constant.errCode);
            target.put("msg", e.getMessage());
        }
        return target;
    }*/
}
