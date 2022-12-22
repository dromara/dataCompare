package com.vince.xq.project.monitor.job.util;

import com.vince.xq.project.system.jobconfig.domain.Jobconfig;
import org.quartz.JobExecutionContext;
import com.vince.xq.project.monitor.job.domain.Job;

/**
 * 定时任务处理（允许并发执行）
 * 
 * @author ruoyi
 *
 */
public class QuartzJobExecution extends AbstractQuartzJob
{
    @Override
    protected void doExecute(JobExecutionContext context, Jobconfig job) throws Exception
    {
        JobInvokeUtil.invokeMethod(job);
    }
}
