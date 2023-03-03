package cn.fc.opentv.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OpenTvApiEnum {
  GET_TOKEN("get_token"),
  GET_VUID("get_vuid"),
  CREATE_ORDER("create_order"),
  CONFIRM_ORDER("confirm_order"),
  VIP_INFO("vip_info");
  private final String value;
}