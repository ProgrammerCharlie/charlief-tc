package com.feirui.tc.config;

import com.feirui.tc.adapter.AliyunStorageAdapter;
import com.feirui.tc.adapter.MinioStorageAdapter;
import com.feirui.tc.adapter.StorageAdapter;
import com.feirui.tc.domain.OssType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfiguration {
    @Value("${oss.type}")
    private OssType ossType;

    @Bean
    public StorageAdapter storageAdapter() {
        switch (ossType) {
            case MINIO:
                return new MinioStorageAdapter();
            case ALIYUN:
                return new AliyunStorageAdapter();
            default:
                throw new IllegalArgumentException("Unsupported oss type: " + ossType);
        }
    }
}
