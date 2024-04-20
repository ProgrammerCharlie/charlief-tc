package com.feirui.tc.adapter;

import com.feirui.tc.domain.OssResp;
import com.feirui.tc.util.AliOssUtil;
import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

public class AliyunStorageAdapter implements StorageAdapter {
    @Resource
    private AliOssUtil aliOssUtil;

    @Override
    public void createBucket(String bucket) {
        aliOssUtil.createBucket(bucket);
    }

    @SneakyThrows
    @Override
    public void uploadFile(MultipartFile uploadFile, String bucket, String objectName) {
        createBucket(bucket);
        if (objectName != null)
            aliOssUtil.uploadFile(uploadFile.getBytes(), bucket, objectName + "/" + uploadFile.getOriginalFilename());
        else
            aliOssUtil.uploadFile(uploadFile.getBytes(), bucket, uploadFile.getOriginalFilename());
    }

    @Override
    public List<String> getAllBucket() {
        return aliOssUtil.getAllBucket();
    }

    @Override
    public String getUrl(String bucket, String objectName) {
        return aliOssUtil.getUrl(bucket, objectName);
    }

    @Override
    public OssResp getPresignedFileUrl(String bucketName, String objectName) {
        return null;
    }
}
