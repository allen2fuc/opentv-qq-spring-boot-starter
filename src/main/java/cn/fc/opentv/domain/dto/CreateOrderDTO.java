package cn.fc.opentv.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class CreateOrderDTO implements IAccessToken {
    private Integer version;
    private String format;
    private String accessToken;
    private String vippkg;
    @NonNull
    private String vuserid;
    @JsonProperty("Q-UA")
    private String qua;
}