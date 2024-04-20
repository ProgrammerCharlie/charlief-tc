package com.feirui.tc.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AliOssUtil {
    @Resource
    private OSS ossClient;

    @Value("${oss.endpoint}")
    private String url;

    /**
     * 创建桶
     */
    public void createBucket(String bucket) {
        boolean exist = ossClient.doesBucketExist(bucket);
        if (!exist) {
            ossClient.createBucket(bucket);
        }
    }

    /**
     * 获取文件路径
     */
    public String getUrl(String bucketName, String objectName) {
        return "https://" +
                bucketName +
                "." +
                url +
                "/" +
                objectName;
    }

    /**
     * 上传文件
     */
    public void uploadFile(byte[] bytes, String bucketName, String objectName) {
        try {
            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(bytes));
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    /**
     * 列出所有的桶
     */
    public List<String> getAllBucket() {
        return ossClient.listBuckets().stream()
                .map(Bucket::getName)
                .collect(Collectors.toList());
    }
}
