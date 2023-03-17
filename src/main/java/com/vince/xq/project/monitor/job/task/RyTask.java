package com.vince.xq.project.monitor.job.task;

import com.vince.xq.common.utils.StringUtils;
import com.vince.xq.project.system.instance.service.IInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 定时任务调度测试
 *
 * @author ruoyi
 */
@Component("ryTask")
public class RyTask {

    @Autowired
    private IInstanceService instanceService;

    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i) {
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void ryParams(String params) throws Exception {
        //System.out.println("执行有参方法：" + params);
        instanceService.runJob(params);
    }

    public void ryNoParams() {
        System.out.println("执行无参方法");
    }
}
