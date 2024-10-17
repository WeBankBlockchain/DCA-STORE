package com.webank.dca.store.controller;

import com.webank.dca.store.handler.WmVectorHandler;
import com.webank.dca.store.monitor.UploadMonitor;
import com.webank.dca.store.service.WmQueryService;
import com.webank.dca.store.service.WmStoreService;
import com.webank.dca.store.vo.CommonResponse;
import com.webank.dca.store.vo.VecQueryHash;
import com.webank.dca.store.vo.WmMatchVectorInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("store/wm")
@Slf4j
public class WmStoreController {

    @Autowired
    private WmQueryService queryService;

    @Autowired
    private WmStoreService storeService;

    @Autowired
    private WmVectorHandler wmVectorHandler;

    @PostMapping("/getVectorByHash")
    public CommonResponse<WmMatchVectorInfo> getVectorByHash(
            @RequestBody VecQueryHash vecQueryHash
    ) {
        log.info("unique id is {} ", vecQueryHash.getHash());
        return CommonResponse.data(queryService.getVectorByHash(vecQueryHash.getHash()));
    }

    @PostMapping("/upload")
    @UploadMonitor("upload")
    public CommonResponse<String> handleWaterMarkFileUpload(
            @RequestParam("seqNo") String seqNo,
            @RequestParam("file") MultipartFile file,
            @RequestParam("uniqueId") String uniqueId,
            @RequestParam("fileType") int fileType,
            @RequestParam("algorithm") int algorithm,
            @RequestParam("appId") String appId,
            @RequestParam("uniqueHash") String uniqueHash,
            @RequestParam("fileHash") String fileHash
    ) throws Exception {
        log.info("upload watermark file: seqNo = {}, uniqueId = {}, uniqueHash = {}, algorithm = {}, appId = {}, fileHash = {}"
                , seqNo, uniqueId, uniqueHash, algorithm, appId, fileHash);
        return storeService.uploadFile(file,seqNo,uniqueId,uniqueHash,algorithm,appId,fileHash);
    }


    @PostMapping("/getUniqueId")
    public CommonResponse<List<String>> getUniqueIdByAppIdAndTable(
            @RequestParam("seqNo") String seqNo,
            @RequestParam("appId") String appId,
            @RequestParam("tableNo") String tableNo
    ) {
        log.info("get uniqueId: seqNo = {}, appId = {}, tableNo = {}", seqNo, appId, tableNo);
        List<String> ids = wmVectorHandler.queryUniqueHashByAppIdAndTable(appId, Integer.parseInt(tableNo));
        log.info("Successfully get {} ids", ids.size());
        return CommonResponse.data(ids);
    }

}
