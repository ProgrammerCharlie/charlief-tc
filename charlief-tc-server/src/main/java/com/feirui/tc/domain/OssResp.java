package com.feirui.tc.domain;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OssResp {
    /**
     * 上传的临时url
     */
    private String uploadUrl;

    /**
     * 成功后能够下载的url
     */
    private String downloadUrl;
}
