package com.feirui.tc.controller;

import com.feirui.tc.domain.OssReq;
import com.feirui.tc.domain.OssResp;
import com.feirui.tc.domain.Result;
import com.feirui.tc.service.FileService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/charlief/oss")
public class FileController {
    @Resource
    private FileService fileService;

    @GetMapping("/test")
    public Result<List<String>> test() {
        return Result.ok(fileService.getAllBucket());
    }

    @GetMapping
    public Result<String> getUrl(String bucketName, String objectName) {
        return Result.ok(fileService.getUrl(bucketName, objectName));
    }

    @PostMapping
    public Result<String> upload(@RequestBody OssReq ossReq) {
        String url = fileService.uploadFile(ossReq.getUploadFile(),
                ossReq.getBucketName(), ossReq.getObjectName());
        return Result.ok(url);
    }

    @GetMapping("/upload")
    public Result<OssResp> getUploadUrl(String bucketName, String objectName) {
        return Result.ok(fileService.getPresignedFileUrl(bucketName, objectName));
    }
}
