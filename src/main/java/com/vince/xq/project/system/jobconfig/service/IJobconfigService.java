package com.vince.xq.project.system.jobconfig.service;

import com.vince.xq.common.exception.job.TaskException;
import com.vince.xq.project.system.jobconfig.domain.Jobconfig;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * 岗位信息 服务层
 * 
 * @author ruoyi
 */
public interface IJobconfigService
{
    public List<Jobconfig> selectJobconfigList(Jobconfig dbconfig);

    public List<Jobconfig> selectJobconfigAll();

    /*public List<Jobconfig> selectJobconfigsByUserId(Long userId);*/

    public Jobconfig selectJobconfigById(Long id);

    public List<String> selectDbTypesAll();

    public int deleteJobconfigByIds(String ids);

    public void insertJobconfig(Jobconfig dbconfig) throws TaskException, SchedulerException;

    public int updateJobconfig(Jobconfig dbconfig);

    public int countUserPostById(Long postId);

    public void checkTableName(Jobconfig jobconfig) throws Exception;

}
