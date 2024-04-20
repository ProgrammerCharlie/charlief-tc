package com.feirui.tc.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class OssReq {
    private MultipartFile uploadFile;
    private String bucketName;
    private String objectName;
}
