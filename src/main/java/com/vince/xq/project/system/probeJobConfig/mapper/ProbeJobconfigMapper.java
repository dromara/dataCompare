package com.vince.xq.project.system.probeJobConfig.mapper;

import com.vince.xq.project.system.probeJobConfig.domain.Probejobconfig;

import java.util.List;


public interface ProbeJobconfigMapper
{

    public List<Probejobconfig> selectJobconfigList(Probejobconfig dbconfig);

    public List<Probejobconfig> selectJobconfigAll();

    public Probejobconfig selectJobconfigById(Long id);

    public int deleteJobconfigByIds(Long[] ids);

    //public int updateJobconfig(Probejobconfig dbconfig);

    public int insertJobconfig(Probejobconfig dbconfig);


    public List<Probejobconfig> selectJobconfigsByUser(String createBy);
}
