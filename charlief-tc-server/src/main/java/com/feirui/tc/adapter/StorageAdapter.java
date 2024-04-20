package com.feirui.tc.adapter;

import com.feirui.tc.domain.FileInfo;
import com.feirui.tc.domain.OssResp;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * 文件存储适配器
 */
public interface StorageAdapter {
    /**
     * 创建bucket桶
     */
    void createBucket(String bucket);

    /**
     * 上传文件
     */
    void uploadFile(MultipartFile uploadFile, String bucket, String objectName);

    /**
     * 列出所有桶
     */
    List<String> getAllBucket();

    /**
     * 根据文件桶和对象名获取url
     */
    String getUrl(String bucket, String objectName);

    /**
     * 获取临时文件url，过期时间为1天
     */
    OssResp getPresignedFileUrl(String bucketName, String objectName);
}
