package com.feirui.tc;

import com.feirui.tc.adapter.MinioStorageAdapter;
import com.feirui.tc.properties.OssProperties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class ApplicationTests {
    @Resource
    MinioStorageAdapter minioStorageAdapter;
    @Resource
    OssProperties ossProperties;

    @Test
    public void contextLoads() {
        // url格式: http://ip/bucketName/objectName
        // http://192.168.1.108:9000/charlief/test.jpg
        System.out.println(minioStorageAdapter.getPresignedFileUrl(ossProperties.getBucketName(), "test.jpg"));
    }
}
