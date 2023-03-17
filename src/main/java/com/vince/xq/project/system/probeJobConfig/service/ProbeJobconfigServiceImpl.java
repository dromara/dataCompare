package com.vince.xq.project.system.probeJobConfig.service;

import com.vince.xq.common.constant.Constants;
import com.vince.xq.common.exception.job.TaskException;
import com.vince.xq.common.utils.security.ShiroUtils;
import com.vince.xq.common.utils.text.Convert;
import com.vince.xq.project.common.DbTypeEnum;
import com.vince.xq.project.monitor.job.mapper.JobMapper;
import com.vince.xq.project.monitor.job.util.ScheduleUtils;
import com.vince.xq.project.system.dbconfig.domain.Dbconfig;
import com.vince.xq.project.system.dbconfig.mapper.DbconfigMapper;
import com.vince.xq.project.system.probeJobConfig.domain.Probejobconfig;
import com.vince.xq.project.system.probeJobConfig.mapper.ProbeJobconfigMapper;
import com.vince.xq.project.system.probeJobConfig.mapper.ProbeJobconfigMapper;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 岗位信息 服务层处理
 *
 * @author ruoyi
 */
@Service
public class ProbeJobconfigServiceImpl implements IProbeJobconfigService {

    @Autowired
    private ProbeJobconfigMapper probeJobconfigMapper;

    @Autowired
    private DbconfigMapper dbconfigMapper;

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private Scheduler scheduler;

    @PostConstruct
    public void init() throws SchedulerException, TaskException {
        /*scheduler.clear();
        List<Probejobconfig> jobList = probeJobconfigMapper.selectJobconfigAll();
        for (Probejobconfig job : jobList) {
            if (job.getSchduleStatus().equals("0")) {
                ScheduleUtils.createScheduleJob(scheduler, job);
            }
        }*/
    }

    @Override
    public List<Probejobconfig> selectJobconfigList(Probejobconfig dbconfig) {
        return probeJobconfigMapper.selectJobconfigList(dbconfig);
    }

    @Override
    public List<Probejobconfig> selectJobconfigAll() {
        return probeJobconfigMapper.selectJobconfigAll();
    }

    /*@Override
    public List<Jobconfig> selectJobconfigsByUserId(Long userId)
    {
        List<Jobconfig> userJobconfigs = probeJobconfigMapper.selectJobconfigsByUserId(userId);
        List<Jobconfig> dbconfigs = probeJobconfigMapper.selectJobconfigAll();
        for (Jobconfig dbconfig : dbconfigs)
        {
            for (Jobconfig userJobconfig : userJobconfigs)
            {
                if (dbconfig.getId().longValue() == userJobconfig.getId().longValue())
                {
                    dbconfig.setFlag(true);
                    break;
                }
            }
        }
        return dbconfigs;
    }*/


    @Override
    public Probejobconfig selectJobconfigById(Long id) {
        return probeJobconfigMapper.selectJobconfigById(id);
    }

    @Override
    public List<String> selectDbTypesAll() {
        List<String> list = new ArrayList<>();
        for (DbTypeEnum dbTypeEnum : DbTypeEnum.values()) {
            list.add(dbTypeEnum.getType());
        }
        return list;
    }

    @Override
    public int deleteJobconfigByIds(String ids) {
        Long[] idsArray = Convert.toLongArray(ids);
        /*for (Long id : idsArray)
        {
            Jobconfig dbconfig = selectJobconfigById(id);
            if (countUserPostById(dbconfig) > 0)
            {
                throw new ServiceException(String.format("%1$s已分配,不能删除", post.getPostName()));
            }
        }*/
        return probeJobconfigMapper.deleteJobconfigByIds(idsArray);
    }


    @Override
    public void insertJobconfig(Probejobconfig jobconfig) throws TaskException, SchedulerException {
        jobconfig.setCreateBy(ShiroUtils.getLoginName());
        int rows = probeJobconfigMapper.insertJobconfig(jobconfig);
        /*if (rows > 0 && jobconfig.getSchduleStatus().equals("0")) {
            ScheduleUtils.createScheduleJob(scheduler, jobconfig);
        }*/
    }

    @Override
    public int updateJobconfig(Probejobconfig dbconfig) {
        /*dbconfig.setCreateBy(ShiroUtils.getLoginName());
        return probeJobconfigMapper.updateJobconfig(dbconfig);*/
        return 0;
    }

    @Override
    public int countUserPostById(Long postId) {
        return 0;
    }

    @Override
    public void checkTableName(Probejobconfig jobconfig) throws Exception {
        Dbconfig dbconfig = dbconfigMapper.selectDbconfigById(jobconfig.getDbConfigId());
        //checkTableNameSql(dbconfig, DbTypeEnum.findEnumByType(dbconfig.getType()).getConnectDriver(), jobconfig);
    }
}
