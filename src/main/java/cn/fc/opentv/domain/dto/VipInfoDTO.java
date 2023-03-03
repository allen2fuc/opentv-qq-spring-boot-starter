package cn.fc.opentv.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class VipInfoDTO implements IAccessToken{
    private Integer version;
    private String format;
    private String accessToken;
    private String vendorPlatform;
    @NonNull
    private String vuserid;
}