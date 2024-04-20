package com.feirui.tc.adapter;

import com.feirui.tc.domain.OssResp;
import com.feirui.tc.util.MinioUtil;
import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

public class MinioStorageAdapter implements StorageAdapter {
    @Resource
    private MinioUtil minioUtil;

    @Override
    @SneakyThrows
    public void createBucket(String bucket) {
        minioUtil.createBucket(bucket);
    }

    @Override
    @SneakyThrows
    public void uploadFile(MultipartFile uploadFile, String bucket, String objectName) {
        createBucket(bucket);
        if (objectName != null) {
            minioUtil.uploadFile(uploadFile.getInputStream(), bucket, objectName + "/" + uploadFile.getOriginalFilename());
        } else {
            minioUtil.uploadFile(uploadFile.getInputStream(), bucket, uploadFile.getOriginalFilename());
        }
    }

    @Override
    @SneakyThrows
    public List<String> getAllBucket() {
        return minioUtil.getAllBucket();
    }

    @Override
    @SneakyThrows
    public String getUrl(String bucket, String objectName) {
        return minioUtil.getUrl(bucket, objectName);
    }

    @SneakyThrows
    public OssResp getPresignedFileUrl(String bucketName, String objectName) {
        createBucket(bucketName);
        return minioUtil.getPresignedFileUrl(bucketName, objectName);
    }
}
