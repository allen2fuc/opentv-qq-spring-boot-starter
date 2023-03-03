package cn.fc.opentv.domain.vo;

import lombok.Data;

/**
 * @Author: fuch
 * @Date: 2021-06-16
 */
@Data
public class ResultVO {
    private int ret;
    private int code;
    private String msg;
    private int costtime;
}