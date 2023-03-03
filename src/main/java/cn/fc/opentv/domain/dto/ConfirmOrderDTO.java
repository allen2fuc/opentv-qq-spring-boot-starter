package cn.fc.opentv.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class ConfirmOrderDTO implements IAccessToken {
    private Integer version;
    private String format;
    private String accessToken;
    @NonNull
    private String vuserid;
    @NonNull
    private String orderId;
}