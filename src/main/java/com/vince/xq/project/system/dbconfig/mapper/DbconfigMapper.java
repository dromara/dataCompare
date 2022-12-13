package com.vince.xq.project.system.dbconfig.mapper;

import com.vince.xq.project.system.dbconfig.domain.Dbconfig;

import java.util.List;


public interface DbconfigMapper
{

    public List<Dbconfig> selectDbconfigList(Dbconfig dbconfig);

    public List<Dbconfig> selectDbconfigAll();

    public Dbconfig selectDbconfigById(Long id);

    public int deleteDbconfigByIds(Long[] ids);

    public int updateDbconfig(Dbconfig dbconfig);

    public int insertDbconfig(Dbconfig dbconfig);

    public Dbconfig checkConnectNameUnique(String connectName);

    public List<Dbconfig> selectDbconfigsByUser(String createBy);
}
