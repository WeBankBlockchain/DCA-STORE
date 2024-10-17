package com.webank.dca.store.handler;

import com.webank.dca.store.aspect.UseTime;
import com.webank.dca.store.config.SystemConfig;
import com.webank.dca.store.constant.Constant;
import com.webank.dca.store.db.mapper.WmFileVectorMapper;
import com.webank.dca.store.model.WmComputeTask;
import com.webank.dca.store.model.WmFileVector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class WmVectorHandler {

    @Autowired
    private SystemConfig config;
    @Autowired
    private WmFileVectorMapper fileVectorMapper;

    @UseTime
    public void saveVector(WmComputeTask computeTask){
        WmFileVector vectorInfo = new WmFileVector();
        vectorInfo.setAlgorithmType(computeTask.getAlgorithm());
        vectorInfo.setFeature(computeTask.getVector());
        vectorInfo.setUniqueId(computeTask.getUniqueId());
        vectorInfo.setAppId(computeTask.getAppId());
        vectorInfo.setUniqueHash(computeTask.getUniqueHash());
        int selectTable = Math.toIntExact(computeTask.getPkId() % Constant.table_num);
        fileVectorMapper.insert(vectorInfo, selectTable);
        log.info("saveVector finish, uniqueId = {}, table = {}",computeTask.getUniqueId(), selectTable);
    }

    public WmFileVector getVectorByHash(String uniqueHash){
        return fileVectorMapper.getVectorByUniqueId(Constant.tables,uniqueHash,Constant.algorithmType);
    }

    public String queryHashByUniqueId(String uniqueId,String appId,int table){
        return fileVectorMapper.queryHashByUniqueId(uniqueId,appId,table);
    }

    public List<String> queryUniqueHashByAppIdAndTable(String appId, int table){
        return fileVectorMapper.queryUniqueHashByAppIdAndTable(appId,table);
    }

    public void delVector(String uniqueId,String appId,int table){
        fileVectorMapper.delVector(uniqueId,appId,table);
    }
}
