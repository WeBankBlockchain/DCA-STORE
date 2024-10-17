package com.webank.dca.store.handler;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.webank.dca.store.aspect.UseTime;
import com.webank.dca.store.config.SystemConfig;
import com.webank.dca.store.constant.Constant;
import com.webank.dca.store.db.mapper.WmComputeTaskMapper;
import com.webank.dca.store.exception.ComputeException;
import com.webank.dca.store.model.WmComputeTask;
import com.webank.dca.store.utils.JsonUtils;
import com.webank.dca.store.vo.ComputeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class WmComputeTaskHandler {

    @Autowired
    private WmComputeTaskMapper taskMapper;

    @Autowired
    private SystemConfig config;

    public void saveWmTask(WmComputeTask computeTask){
        taskMapper.insert(computeTask);
    }

    public long queryTaskId(String uniqueId,String appId){
        return taskMapper.queryByUniqueId(uniqueId,appId);
    }

    public void delWmTask(String uniqueId,String appId){
        taskMapper.delComputeTask(uniqueId,appId);
    }

    @UseTime
    public String handleTask(WmComputeTask task) {
        Map<String, Object> formData = new HashMap<>();
        formData.put("seqNo", task.getSeqNo());
        formData.put("algorithm", task.getAlgorithm());
        formData.put("fileType", Constant.File_TYPE_IMAGE);
        HttpResponse resp = HttpRequest.post(config.getComputeUrl())
                .form(formData)
                .form("file", task.getFile(), task.getUniqueHash())
                .execute();
        if (!resp.isOk()) {
            log.error("OnError: compute request failed, status is " + resp.getStatus());
            throw new ComputeException();
        }
        log.info("handle task, post to compute finish, seqNo = {}", task.getSeqNo());
        ComputeResponse computeResponse = JsonUtils.fromJson(resp.body(), ComputeResponse.class);
        String result = computeResponse.getVector();
        return result;
    }


    public boolean existUniqueId(String uniqueId, String appId){
        return taskMapper.existUniqueId(uniqueId,appId) != 0;
    }
}
