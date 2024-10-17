package com.webank.dca.store.service;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.webank.dca.store.ImageStoreApplicationTests;
import com.webank.dca.store.cache.AppCache;
import com.webank.dca.store.db.mapper.ComputeTaskMapper;
import com.webank.dca.store.db.mapper.FileVectorMapper;
import com.webank.dca.store.handler.VectorHandler;
import com.webank.dca.store.model.ComputeTask;
import com.webank.dca.store.model.FileInfo;
import com.webank.dca.store.model.FileVectorInfo;
import com.webank.dca.store.utils.JsonUtils;
import com.webank.dca.store.vo.CommonResponse;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StoreServiceTest extends ImageStoreApplicationTests {

    @Autowired
    private StoreService storeService;
    @Autowired
    private ComputeTaskMapper mapper;
    @Autowired
    private VectorHandler vectorHandler;

    @Autowired
    private FileVectorMapper fileVectorMapper;

    @Test
    public void test() throws Exception {
        List<Long> ids = new ArrayList<>();
        for(long i = 501012 ; i < 501022 ; i++){
            ids.add(i);
        }
        System.out.println("ids size = " + ids.size());
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("ids", ids);
//        paramMap.put("appId", "123");
//        paramMap.put("batch", 200);
        String jsonBody = JsonUtils.toJson(paramMap);
        System.out.println("jsonBody: " + jsonBody);
        long count = 0;
        for(int i = 0; i < 10; i++) {
            long startTime = System.currentTimeMillis();
            String result = HttpUtil.post("127.0.0.1:8080/store/getVectorsByIds", paramMap);
//            System.out.println(result);
            long endTime = System.currentTimeMillis();
            count += endTime-startTime;
        }
        System.out.println("time = " + (count/10));
    }

    @Test
    public void testGetVec(){
        List<Integer> tables = new ArrayList<Integer>();
        tables.add(0);
        tables.add(1);
        tables.add(2);
        tables.add(3);
        List<Long> ids = new ArrayList<>();
        for(long i = 501012 ; i < 501022 ; i++){
            ids.add(i);
        }

        long count = 0;
        for(int i = 0; i < 10; i++) {
            long startTime = System.currentTimeMillis();
            fileVectorMapper.getVectorsBatch(tables,"DG",501012,200);
            long endTime = System.currentTimeMillis();
            count += endTime-startTime;
        }
        System.out.println("time = " + (count/5));
    }

    @Test
    public void testGetApps(){
        HashMap<String, Object> paramMap = new HashMap<>();
        long startTime = System.currentTimeMillis();

        String result = HttpUtil.post("127.0.0.1:8080/store/getApps",paramMap);
        long time = System.currentTimeMillis() - startTime;
        System.out.println(time);
        System.out.println(result);
    }
}
