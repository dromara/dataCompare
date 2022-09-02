package com.vince.xq.dataCompare.controller;

import com.alibaba.fastjson.JSONObject;
import com.vince.xq.dataCompare.common.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author xiaoqiu2017wy@163.com
 * @Date 2022/8/6
 */
@RequestMapping("/instance")
@Controller
public class InstanceController {
    private static final Logger log = LoggerFactory.getLogger(InstanceController.class);

    @RequestMapping(value = "/run", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject run(Long job_config_id) {
        JSONObject target = new JSONObject();
        try {
            //jobService.insert(originTableName,originTablePrimary,originTableFields,toTableName,toTablePrimary,toTableFields,dbConfigId,schduleTime,schduleStatus);

            target.put("code", Constant.successCode);
        } catch (Exception e) {
            target.put("code", Constant.errCode);
            target.put("msg", e.getMessage());
        }
        return target;
    }


}
