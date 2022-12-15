package com.vince.xq.project.system.instance.controller;

import com.vince.xq.framework.aspectj.lang.annotation.Log;
import com.vince.xq.framework.aspectj.lang.enums.BusinessType;
import com.vince.xq.framework.web.controller.BaseController;
import com.vince.xq.framework.web.domain.AjaxResult;
import com.vince.xq.framework.web.page.TableDataInfo;
import com.vince.xq.project.system.instance.domain.Instance;
import com.vince.xq.project.system.instance.service.IInstanceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 岗位信息操作处理
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/jobInstance")
public class InstanceController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(InstanceController.class);

    private String prefix = "system/jobInstance";

    @Autowired
    private IInstanceService InstanceService;

    @RequiresPermissions("system:jobInstance:view")
    @GetMapping()
    public String operlog() {
        return prefix + "/jobInstance";
    }

    @RequiresPermissions("system:jobInstance:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Instance Instance) {
        startPage();
        List<Instance> list = InstanceService.selectInstanceList(Instance);
        return getDataTable(list);
    }

    @RequiresPermissions("system:jobInstance:insert")
    @Log(title = "instance管理", businessType = BusinessType.INSERT)
    @PostMapping("/insert")
    @ResponseBody
    public AjaxResult insert(Instance instance) {
        try {
            return toAjax(InstanceService.insertInstance(instance));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    /**
     * 新增保存
     */
    @RequiresPermissions("system:jobInstance:add")
    @Log(title = "add", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated Instance Instance) {
        try {
            return toAjax(InstanceService.insertInstance(Instance));
        } catch (Exception e) {
            e.printStackTrace();
            return error(e.getMessage());
        }
    }

    /**
     * 修改岗位
     */
    @RequiresPermissions("system:jobInstance:detail")
    @GetMapping("/detail/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Instance instance = InstanceService.selectInstanceById(id);
        boolean numFlag = instance.getPvDiff().equals("0") && instance.getUvDiff().equals("0") ? true : false;
        boolean consistencyFlag = instance.getCountDiff().equals(instance.getOriginTableCount()) && instance.getCountDiff().equals(instance.getToTableCount()) ? true : false;
        mmap.put("Instance", instance);
        mmap.put("numFlag", numFlag);
        mmap.put("consistencyFlag", consistencyFlag);
        return prefix + "/detail";
    }

}
