package com.vince.xq.dataCompare.dao;

import com.vince.xq.dataCompare.model.DbConfigEbo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DbConfigDao {

    List<DbConfigEbo> findAll();

    DbConfigEbo getDbConfigById(long id);

    int insert(DbConfigEbo stu);

    int update(DbConfigEbo stu);

    int delete(long id);

}