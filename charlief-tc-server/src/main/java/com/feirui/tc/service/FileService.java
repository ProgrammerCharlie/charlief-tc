package com.feirui.tc.service;

import com.feirui.tc.adapter.StorageAdapter;
import com.feirui.tc.domain.OssResp;
import com.feirui.tc.domain.OssType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileService {
    private final StorageAdapter storageAdapter;

    @Value("${oss.type}")
    private OssType ossType;

    public FileService(StorageAdapter storageAdapter) {
        this.storageAdapter = storageAdapter;
    }

    /**
     * 列出所有桶
     */
    public List<String> getAllBucket() {
        return storageAdapter.getAllBucket();
    }

    /**
     * 获取文件路径
     */
    public String getUrl(String bucketName, String objectName) {
        return storageAdapter.getUrl(bucketName, objectName);
    }

    /**
     * 上传文件
     *
     * @param uploadFile 文件对象
     * @param bucket     桶
     * @param objectName 文件名
     * @return 文件url
     */
    public String uploadFile(MultipartFile uploadFile, String bucket, String objectName) {
        storageAdapter.uploadFile(uploadFile, bucket, objectName);
        objectName = objectName + "/" + uploadFile.getOriginalFilename();
        return storageAdapter.getUrl(bucket, objectName);
    }

    /**
     * 获取临时文件url，过期时间为1天
     */
    public OssResp getPresignedFileUrl(String bucketName, String objectName) {
        if (ossType.getType() != OssType.MINIO.getType()) return null;
        return storageAdapter.getPresignedFileUrl(bucketName, objectName);
    }
}
