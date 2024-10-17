package com.webank.dca.store.service;

import cn.hutool.core.io.IoUtil;
import com.webank.dca.store.aspect.UseTime;
import com.webank.dca.store.config.SystemConfig;
import com.webank.dca.store.constant.Constant;
import com.webank.dca.store.handler.WmComputeTaskHandler;
import com.webank.dca.store.handler.WmVectorHandler;
import com.webank.dca.store.model.WmComputeTask;
import com.webank.dca.store.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class WmStoreService {

    @Autowired
    private SystemConfig systemConfig;
    @Autowired
    private WmVectorHandler vectorHandler;
    @Autowired
    private WmComputeTaskHandler taskHandler;


    @UseTime
    public CommonResponse<String> uploadFile(MultipartFile file,
                                     String seqNo,
                                     String uniqueId,
                                     String uniqueHash,
                                     int algorithm,
                                     String appId,
                                     String fileHash) throws Exception {
        WmComputeTask computeTask = new WmComputeTask();
        computeTask.setUniqueHash(uniqueHash);
        computeTask.setUniqueId(uniqueId);
        computeTask.setAppId(appId);
        computeTask.setSeqNo(seqNo);
        computeTask.setWatermarkFileHash(fileHash);
        computeTask.setAlgorithm(algorithm);
        byte[] bytes = IoUtil.readBytes(file.getInputStream());
        computeTask.setFile(bytes);
        String vector = handleTask(computeTask);
        computeTask.setVector(vector);
        saveTaskAndVector(computeTask);
        return CommonResponse.OK(seqNo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveTaskAndVector(WmComputeTask computeTask){
        taskHandler.saveWmTask(computeTask);
        vectorHandler.saveVector(computeTask);
    }



    public String handleTask(WmComputeTask computeTask) {
        String vector;
        try{
            vector = taskHandler.handleTask(computeTask);
            log.info("task handler success, seqNo = {}, appId = {}, uniqueId = {} ",
                    computeTask.getSeqNo(),
                    computeTask.getAppId(),
                    computeTask.getUniqueId());
        }catch (Exception e){
            log.error("OnError: task handler failed, seqNo = {}, appId = {}, uniqueId = {} ,reason is ",
                    computeTask.getSeqNo(),
                    computeTask.getAppId(),
                    computeTask.getUniqueId(),
                    e);
            throw e;
        }
        return vector;
    }



    @Transactional
    public void delDBData(String uniqueId, String appId, long taskId) {
        vectorHandler.delVector(uniqueId,appId, Math.toIntExact(taskId % Constant.table_num));
        taskHandler.delWmTask(uniqueId,appId);
    }
}
