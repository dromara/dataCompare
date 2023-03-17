package com.vince.xq.project.system.ProbeJobInstance.controller;

import com.alibaba.fastjson.JSONObject;
import com.vince.xq.framework.aspectj.lang.annotation.Log;
import com.vince.xq.framework.aspectj.lang.enums.BusinessType;
import com.vince.xq.framework.web.controller.BaseController;
import com.vince.xq.framework.web.domain.AjaxResult;
import com.vince.xq.framework.web.page.TableDataInfo;
import com.vince.xq.project.system.ProbeJobInstance.domain.ProbeJobInstance;
import com.vince.xq.project.system.ProbeJobInstance.domain.ProbeJobInstanceResult;
import com.vince.xq.project.system.ProbeJobInstance.service.IProbeJobInstanceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 岗位信息操作处理
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/probeJobInstance")
public class ProbeJobInstanceController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(ProbeJobInstanceController.class);

    private String prefix = "system/probeJobInstance";

    @Autowired
    private IProbeJobInstanceService instanceService;

    @RequiresPermissions("system:probeJobInstance:view")
    @GetMapping()
    public String operlog() {
        return prefix + "/probeJobInstance";
    }

    @RequiresPermissions("system:probeJobInstance:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProbeJobInstance Instance) {
        startPage();
        List<ProbeJobInstance> list = instanceService.selectInstanceList(Instance);
        return getDataTable(list);
    }

    @RequiresPermissions("system:probeJobInstance:insert")
    @Log(title = "instance管理", businessType = BusinessType.INSERT)
    @PostMapping("/insert")
    @ResponseBody
    public AjaxResult insert(ProbeJobInstance instance) {
        try {
            return toAjax(instanceService.insertInstance(instance));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    /**
     * 新增保存
     */
    @RequiresPermissions("system:probeJobInstance:add")
    @Log(title = "add", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated ProbeJobInstance Instance) {
        try {
            return toAjax(instanceService.insertInstance(Instance));
        } catch (Exception e) {
            e.printStackTrace();
            return error(e.getMessage());
        }
    }

    @RequiresPermissions("system:probeJobInstance:detail")
    @GetMapping("/detail/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        ProbeJobInstanceResult instance = instanceService.selectInstanceById(id);
        mmap.put("Instance", instance);
        /*boolean numFlag = instance.getPvDiff().equals("0") && instance.getUvDiff().equals("0") ? true : false;
        boolean consistencyFlag = instance.getCountDiff().equals(instance.getOriginTableCount()) && instance.getCountDiff().equals(instance.getToTableCount()) ? true : false;
        mmap.put("Instance", instance);
        mmap.put("numFlag", numFlag);
        mmap.put("consistencyFlag", consistencyFlag);*/
        return prefix + "/detail";
    }

}
