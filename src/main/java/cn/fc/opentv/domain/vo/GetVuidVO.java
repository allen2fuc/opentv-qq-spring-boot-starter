package cn.fc.opentv.domain.vo;


import lombok.Data;

/**
 * @Author: fuch
 * @Date: 2021-06-16
 */
@Data
public class GetVuidVO implements IResult{
    private ResultVO result;
    private Data data;

    @lombok.Data
    public static class Data{
        private Long vuid;
        private String vtoken;
    }
}
