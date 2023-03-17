package com.vince.xq.project.system.ProbeJobInstance.mapper;

import com.vince.xq.project.system.ProbeJobInstance.domain.ProbeJobInstance;

import java.util.List;


public interface ProbeJobInstanceMapper {

    public List<ProbeJobInstance> selectInstanceList(ProbeJobInstance Instance);

    public List<ProbeJobInstance> selectInstanceAll();

    public ProbeJobInstance selectInstanceById(Long id);

    public int insertInstance(ProbeJobInstance Instance);

    public List<ProbeJobInstance> selectInstancesByUser(String createBy);
}
