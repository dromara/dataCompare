package com.vince.xq.project.system.probeJobConfig.service;

import com.vince.xq.common.exception.job.TaskException;
import com.vince.xq.project.system.probeJobConfig.domain.Probejobconfig;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * 岗位信息 服务层
 * 
 * @author ruoyi
 */
public interface IProbeJobconfigService
{
    public List<Probejobconfig> selectJobconfigList(Probejobconfig dbconfig);

    public List<Probejobconfig> selectJobconfigAll();

    /*public List<Jobconfig> selectJobconfigsByUserId(Long userId);*/

    public Probejobconfig selectJobconfigById(Long id);

    public List<String> selectDbTypesAll();

    public int deleteJobconfigByIds(String ids);

    public void insertJobconfig(Probejobconfig dbconfig) throws TaskException, SchedulerException;

    public int updateJobconfig(Probejobconfig dbconfig);

    public int countUserPostById(Long postId);

    public void checkTableName(Probejobconfig jobconfig) throws Exception;

}
