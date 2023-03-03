package cn.fc.opentv.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTokenDTO {
    private Integer version;
    private String format;
    private String appid;
    private String appkey;
}