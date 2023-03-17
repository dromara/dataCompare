package com.vince.xq.project.system.ProbeJobInstance.service;

import com.vince.xq.project.system.ProbeJobInstance.domain.ProbeJobInstance;
import com.vince.xq.project.system.ProbeJobInstance.domain.ProbeJobInstanceResult;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 岗位信息 服务层
 *
 * @author ruoyi
 */
public interface IProbeJobInstanceService {

    public List<ProbeJobInstance> selectInstanceList(ProbeJobInstance instance);

    public List<ProbeJobInstance> selectInstanceAll();

    public ProbeJobInstanceResult selectInstanceById(Long id);

    public List<String> selectDbTypesAll();

    public int insertInstance(ProbeJobInstance instance);

    public int countUserPostById(Long postId);

    public void runJob(String ids) throws Exception;

    //public List<LinkedHashMap<String, String>> getDiffDetail(Long id) throws Exception;

}
