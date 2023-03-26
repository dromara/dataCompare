package com.vince.xq.project.system.ProbeJobInstance.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.vince.xq.common.utils.text.Convert;
import com.vince.xq.project.common.DbTypeEnum;
import com.vince.xq.project.common.RunUtil;
import com.vince.xq.project.system.ProbeJobInstance.domain.ProbeJobInstance;
import com.vince.xq.project.system.ProbeJobInstance.domain.ProbeJobInstanceResult;
import com.vince.xq.project.system.ProbeJobInstance.mapper.ProbeJobInstanceMapper;
import com.vince.xq.project.system.dbconfig.domain.Dbconfig;
import com.vince.xq.project.system.dbconfig.mapper.DbconfigMapper;
import com.vince.xq.project.system.email.service.IEmailService;
import com.vince.xq.project.system.jobconfig.domain.Jobconfig;
import com.vince.xq.project.system.jobconfig.mapper.JobconfigMapper;
import com.vince.xq.project.system.probeJobConfig.domain.Probejobconfig;
import com.vince.xq.project.system.probeJobConfig.mapper.ProbeJobconfigMapper;
import com.vince.xq.project.system.user.domain.User;
import com.vince.xq.project.system.user.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 岗位信息 服务层处理
 *
 * @author ruoyi
 */
@Service
public class ProbeJobInstanceServiceImpl implements IProbeJobInstanceService {

    @Autowired
    private ProbeJobInstanceMapper instanceMapper;

    @Autowired
    private DbconfigMapper dbconfigMapper;

    @Autowired
    private ProbeJobconfigMapper jobconfigMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IEmailService emailService;

    @Override
    public List<ProbeJobInstance> selectInstanceList(ProbeJobInstance instance) {
        return instanceMapper.selectInstanceList(instance);
    }

    @Override
    public List<ProbeJobInstance> selectInstanceAll() {
        return instanceMapper.selectInstanceAll();
    }

    @Override
    public ProbeJobInstanceResult selectInstanceById(Long id) {
        ProbeJobInstance probeJobInstance = instanceMapper.selectInstanceById(id);
        return extract(probeJobInstance);
    }

    private ProbeJobInstanceResult extract(ProbeJobInstance instance) {
        ProbeJobInstanceResult probeJobInstanceResult = new ProbeJobInstanceResult();
        probeJobInstanceResult.setId(instance.getId());
        probeJobInstanceResult.setTableName(instance.getTableName());
        probeJobInstanceResult.setFilter(instance.getFilter());

        if (StringUtils.isNotEmpty(instance.getPrimaryResult())){
            JSONObject primaryResultObj = JSONObject.parseObject(instance.getPrimaryResult());
            for (String key : primaryResultObj.keySet()) {
                ProbeJobInstanceResult.PrimaryResult primaryResult = new ProbeJobInstanceResult.PrimaryResult();
                primaryResult.setPrimaryField(key);
                JSONObject jsonObject = JSONObject.parseObject(primaryResultObj.getString(key));
                primaryResult.setCnt(jsonObject.getLong("cnt"));
                primaryResult.setDistinctCnt(jsonObject.getLong("distinct_cnt"));
                probeJobInstanceResult.setPrimaryResult(primaryResult);
            }
        }

        if (StringUtils.isNotEmpty(instance.getNullResult())){
            JSONObject nullResultObj = JSONObject.parseObject(instance.getNullResult());
            List<ProbeJobInstanceResult.NullResult> nullResultList = new ArrayList<>();
            for (String key : nullResultObj.keySet()) {
                ProbeJobInstanceResult.NullResult nullResult = new ProbeJobInstanceResult.NullResult();
                nullResult.setNullField(key);
                JSONArray jsonArray = JSONObject.parseArray(nullResultObj.getString(key));
                long totalCnt = 0;
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject = JSONObject.parseObject(jsonArray.get(i).toString());
                    if (jsonObject.getString("dict").equals("0")) {
                        nullResult.setNullCnt(jsonObject.getLong("cnt"));
                    }
                    totalCnt += jsonObject.getLong("cnt");
                }
                nullResult.setTotalCnt(totalCnt);
                nullResultList.add(nullResult);
            }
            probeJobInstanceResult.setNullResultList(nullResultList);
        }


        if (StringUtils.isNotEmpty(instance.getEnumResult())){
            JSONObject enumObj = JSONObject.parseObject(instance.getEnumResult());
            Map<String, List<ProbeJobInstanceResult.EnumResult>> enumResultMap = new HashMap<>();
            for (String key : enumObj.keySet()) {
                JSONArray jsonArray = JSONObject.parseArray(enumObj.getString(key));
                List<ProbeJobInstanceResult.EnumResult> list = new ArrayList<>();
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject = JSONObject.parseObject(jsonArray.get(i).toString());
                    ProbeJobInstanceResult.EnumResult enumResult = new ProbeJobInstanceResult.EnumResult();
                    enumResult.setEnumValue(jsonObject.getString("dict"));
                    enumResult.setCnt(jsonObject.getLong("cnt"));
                    list.add(enumResult);
                }
                enumResultMap.put(key, list);
            }
            probeJobInstanceResult.setEnumResultMap(enumResultMap);
        }

        if (StringUtils.isNotEmpty(instance.getLenResult())) {
            JSONObject lenObj = JSONObject.parseObject(instance.getLenResult());
            Map<String, List<ProbeJobInstanceResult.EnumResult>> lenResultMap = new HashMap<>();
            for (String key : lenObj.keySet()) {
                JSONArray jsonArray = JSONObject.parseArray(lenObj.getString(key));
                List<ProbeJobInstanceResult.EnumResult> list = new ArrayList<>();
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject = JSONObject.parseObject(jsonArray.get(i).toString());
                    ProbeJobInstanceResult.EnumResult enumResult = new ProbeJobInstanceResult.EnumResult();
                    enumResult.setEnumValue(jsonObject.getString("len"));
                    enumResult.setCnt(jsonObject.getLong("cnt"));
                    list.add(enumResult);
                }
                lenResultMap.put(key, list);
            }
            probeJobInstanceResult.setLenResultMap(lenResultMap);
        }

        return probeJobInstanceResult;
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
    public int insertInstance(ProbeJobInstance instance) {
        return instanceMapper.insertInstance(instance);
    }


    @Override
    public int countUserPostById(Long postId) {
        return 0;
    }

    @Override
    public void runJob(String ids) throws Exception {
        Long[] idsArray = Convert.toLongArray(ids);
        for (Long id : idsArray) {
            Probejobconfig jobconfig = jobconfigMapper.selectJobconfigById(id);
            Dbconfig dbconfig = dbconfigMapper.selectDbconfigById(jobconfig.getDbConfigId());
            ProbeJobInstance instance = RunUtil.runProbeJob(dbconfig, jobconfig);
            instance.setJobconfigId(id);
            instanceMapper.insertInstance(instance);
        }
    }

    /*@Override
    public List<LinkedHashMap<String, String>> getDiffDetail(Long id) throws Exception {
        ProbeJobInstance instance = instanceMapper.selectInstanceById(id);
        Probejobconfig jobconfig = jobconfigMapper.selectJobconfigById(instance.getJobconfigId());
        Dbconfig dbconfig = dbconfigMapper.selectDbconfigById(jobconfig.getDbConfigId());
        List<LinkedHashMap<String, String>> list = RunUtil.runDiffDetail(dbconfig, jobconfig);
        return list;
    }*/
}
