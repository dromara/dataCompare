package com.vince.xq.dataCompare.controller;

import com.alibaba.fastjson.JSONObject;
import com.vince.xq.dataCompare.service.DbService;
import com.vince.xq.dataCompare.common.Constant;
import com.vince.xq.dataCompare.model.DbConfigEbo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/db")
@Controller
public class DbController {
    private static final Logger log = LoggerFactory.getLogger(DbController.class);

    @Autowired
    private DbService dbService;

    @RequestMapping("/dbConfig.html")
    public String index(ModelMap map){
        return "dbConfig";
    }

    @RequestMapping("/addDbConfig.html")
    public String addDbConfig(ModelMap map){
        return "addDbConfig";
    }

    @RequestMapping("/editDbConfig.html")
    public String editDbConfig(ModelMap map,Long id){
        map.addAttribute("name","111");
        map.addAttribute("dbConfig",dbService.getDbConfigEboById(id));
        return "editDbConfig";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject insert(String connectName,String type, String url, String user, String pwd) {
        JSONObject target = new JSONObject();
        try {
            dbService.testConnection(type, url, user, pwd);
            dbService.insert(connectName,type, url, user, pwd);
            target.put("code", Constant.successCode);
        } catch (Exception e) {
            target.put("code", Constant.errCode);
            target.put("msg", e.getMessage());
        }
        return target;
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject update(Long id,String connectName,String type, String url, String user, String pwd) {
        JSONObject target = new JSONObject();
        try {
            dbService.testConnection(type, url, user, pwd);
            if(dbService.update(id,connectName,type, url, user, pwd)){
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
    }


    @RequestMapping(value = "/test_connection", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject testConnection(String type, String url, String user, String pwd) {
        JSONObject target = new JSONObject();
        try {
            dbService.testConnection(type, url, user, pwd);
            target.put("code", Constant.successCode);
        } catch (Exception e) {
            target.put("code", Constant.errCode);
            target.put("msg", e.getMessage());
        }
        return target;
    }

    @RequestMapping(value = "/get_all_db_config", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getAllDbConfig() {
        JSONObject target = new JSONObject();
        try {
            List<DbConfigEbo> dbConfigEboList= dbService.getAllDbConfig();
            target.put("code", Constant.successCode);
            target.put("count",dbConfigEboList.size());
            target.put("data",dbConfigEboList);
        } catch (Exception e) {
            target.put("code", Constant.errCode);
            target.put("msg", e.getMessage());
        }
        return target;
/*
        String json="{\"code\":0,\"msg\":\"success\",\"count\":1000,\"data\":[{\"id\":10000,\"username\":\"user-0\",\"sex\":\"女\",\"city\":\"城市-0\",\"sign\":\"签名-0\",\"experience\":255,\"logins\":24,\"words\":82830700,\"classify\":\"作家\",\"score\":57},{\"id\":10001,\"username\":\"user-1\",\"sex\":\"男\",\"city\":\"城市-1\",\"sign\":\"签名-1\",\"experience\":884,\"logins\":58,\"words\":64928690,\"classify\":\"词人\",\"score\":70.5},{\"id\":10002,\"username\":\"user-2\",\"sex\":\"女\",\"city\":\"城市-2\",\"sign\":\"签名-2\",\"experience\":650,\"logins\":77,\"words\":6298078,\"classify\":\"酱油\",\"score\":31},{\"id\":10003,\"username\":\"user-3\",\"sex\":\"女\",\"city\":\"城市-3\",\"sign\":\"签名-3\",\"experience\":362,\"logins\":157,\"words\":37117017,\"classify\":\"诗人\",\"score\":68},{\"id\":10004,\"username\":\"user-4\",\"sex\":\"男\",\"city\":\"城市-4\",\"sign\":\"签名-4\",\"experience\":807,\"logins\":51,\"words\":76263262,\"classify\":\"作家\",\"score\":6},{\"id\":10005,\"username\":\"user-5\",\"sex\":\"女\",\"city\":\"城市-5\",\"sign\":\"签名-5\",\"experience\":173,\"logins\":68,\"words\":60344147,\"classify\":\"作家\",\"score\":87},{\"id\":10006,\"username\":\"user-6\",\"sex\":\"女\",\"city\":\"城市-6\",\"sign\":\"签名-6\",\"experience\":982,\"logins\":37,\"words\":57768166,\"classify\":\"作家\",\"score\":34},{\"id\":10007,\"username\":\"user-7\",\"sex\":\"男\",\"city\":\"城市-7\",\"sign\":\"签名-7\",\"experience\":727,\"logins\":150,\"words\":82030578,\"classify\":\"作家\",\"score\":28},{\"id\":10008,\"username\":\"user-8\",\"sex\":\"男\",\"city\":\"城市-8\",\"sign\":\"签名-8\",\"experience\":951,\"logins\":133,\"words\":16503371,\"classify\":\"词人\",\"score\":14},{\"id\":10009,\"username\":\"user-9\",\"sex\":\"女\",\"city\":\"城市-9\",\"sign\":\"签名-9\",\"experience\":484,\"logins\":25,\"words\":86801934,\"classify\":\"词人\",\"score\":75}]}";
        return json;*/
    }

}
