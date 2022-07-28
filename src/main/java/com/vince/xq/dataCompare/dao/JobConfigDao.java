package com.vince.xq.dataCompare.dao;

import com.vince.xq.dataCompare.model.JobConfigEbo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JobConfigDao {

    List<JobConfigEbo> findAll();

    JobConfigEbo getJobConfigById(long id);

    int insert(JobConfigEbo stu);

    int delete(long id);

}