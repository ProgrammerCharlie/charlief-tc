package com.feirui.tc.domain;

import lombok.Data;

/**
 * 文件类
 */
@Data
public class FileInfo {
    private String fileName;

    private Boolean directoryFlag;

    private String etag;
}
