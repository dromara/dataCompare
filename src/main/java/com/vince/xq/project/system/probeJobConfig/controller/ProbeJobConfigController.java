package com.vince.xq.project.system.probeJobConfig.controller;

import com.vince.xq.framework.aspectj.lang.annotation.Log;
import com.vince.xq.framework.aspectj.lang.enums.BusinessType;
import com.vince.xq.framework.web.controller.BaseController;
import com.vince.xq.framework.web.domain.AjaxResult;
import com.vince.xq.framework.web.page.TableDataInfo;
import com.vince.xq.project.monitor.job.util.CronUtils;
import com.vince.xq.project.system.ProbeJobInstance.service.IProbeJobInstanceService;
import com.vince.xq.project.system.dbconfig.service.IDbconfigService;
import com.vince.xq.project.system.instance.service.IInstanceService;
import com.vince.xq.project.system.probeJobConfig.domain.Probejobconfig;
import com.vince.xq.project.system.probeJobConfig.service.IProbeJobconfigService;
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
@RequestMapping("/system/probeJobConfig")
public class ProbeJobConfigController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(ProbeJobConfigController.class);

    private String prefix = "system/probeJobConfig";

    @Autowired
    private IProbeJobconfigService probeJobconfigService;

    @Autowired
    private IDbconfigService dbconfigService;

    @Autowired
    private IProbeJobInstanceService instanceService;

    @RequiresPermissions("system:probeJobConfig:view")
    @GetMapping()
    public String operlog() {
        return prefix + "/probeJobConfig";
    }

    @RequiresPermissions("system:probeJobConfig:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Probejobconfig jobconfig) {
        startPage();
        List<Probejobconfig> list = probeJobconfigService.selectJobconfigList(jobconfig);
        return getDataTable(list);
    }

    @RequiresPermissions("system:probeJobConfig:remove")
    @Log(title = "job管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        try {
            return toAjax(probeJobconfigService.deleteJobconfigByIds(ids));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    @RequiresPermissions("system:probeJobConfig:run")
    @Log(title = "job管理", businessType = BusinessType.RUN)
    @PostMapping("/run")
    @ResponseBody
    public AjaxResult run(String ids) {
        try {
            instanceService.runJob(ids);
            return success();
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    /**
     * 新增
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        mmap.put("dbConfigList", dbconfigService.selectDbconfigAll());
        return prefix + "/add";
    }

    /**
     * 新增保存
     */
    @RequiresPermissions("system:probeJobConfig:add")
    @Log(title = "add", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated Probejobconfig jobconfig) {
        try {
            /*if (jobconfig.getSchduleStatus().equals("0")) {//定时调度
                if (!CronUtils.isValid(jobconfig.getSchduleTime())) {
                    return error("新增任务失败，Cron表达式不正确");
                }
            }*/
            probeJobconfigService.insertJobconfig(jobconfig);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error(e.getMessage());
        }
    }

    /*@RequiresPermissions("system:probeJobConfig:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Probejobconfig jobconfig = probeJobconfigService.selectJobconfigById(id);
        mmap.put("jobconfig", jobconfig);
        return prefix + "/edit";
    }*/


    /*@RequiresPermissions("system:probeJobConfig:edit")
    @Log(title = "job管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated Probejobconfig jobconfig) {
        return toAjax(probeJobconfigService.updateJobconfig(jobconfig));
    }*/

    /*@RequestMapping(value = "/checkTableName", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult checkTableName(Probejobconfig jobconfig) {
        try {
            log.info("========checkTableName==========");
            jobconfigService.checkTableName(jobconfig);
        } catch (Exception e) {
            e.printStackTrace();
            return error("错误信息:" + e.getMessage());
        }
        return success("表和字段校验成功");
    }*/

}
