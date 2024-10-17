package com.webank.dca.store.service;

import cn.hutool.http.HttpUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.Bucket;
import com.qcloud.cos.model.CreateBucketRequest;
import com.webank.dca.store.ImageStoreApplicationTests;
import com.webank.dca.store.config.CosConfig;
import com.webank.dca.store.utils.JsonUtils;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

public class WmStoreServiceTest extends ImageStoreApplicationTests {

    @Autowired
    private WmStoreService wmStoreService;

    @Autowired
    private COSClient cosClient;

    @Autowired
    private CosConfig cosConfig;

    @Test
    public void createBuket() throws Exception {
        String bucket = "dca-" + "appid"; //存储桶名称，格式：BucketName-APPID
        CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucket);
        Bucket bucketResult = cosClient.createBucket(createBucketRequest);
        System.out.println(JsonUtils.toJson(bucketResult));
    }

    @Test
    public void delTest() throws Exception {
        wmStoreService.delFile(
                "123",
                "123");

    }

    @Test
    public void uploadTest() throws Exception {
        // 构建一个测试文件
        File file = new File("31187603.png");
        FileInputStream input = new FileInputStream(file);

        // 构建一个 MultipartFile 文件对象
        MultipartFile multipartFile = new MockMultipartFile("file", file.getName(),
                "text/plain", input);
//        wmStoreService.uploadFile(
//                multipartFile,
//                "123",
//                "123",
//                "asdasdasd",
//                0,
//                "123");

    }

    @Test
    public void getVecTest() throws Exception {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("hash", "123");
        String jsonBody = JsonUtils.toJson(paramMap);
        System.out.println("jsonBody: " + jsonBody);
        long count = 0;
        for (int i = 0; i < 10; i++) {
            long startTime = System.currentTimeMillis();
            String result = HttpUtil.post("127.0.0.1:8080/store/wm/getVectorByHash", paramMap);
            System.out.println(result);
            long endTime = System.currentTimeMillis();
            count += endTime - startTime;
        }
    }
}
