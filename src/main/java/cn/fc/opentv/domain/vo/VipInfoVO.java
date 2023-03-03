package cn.fc.opentv.domain.vo;

import lombok.Data;


/**
 * @Author: fuch
 * @Date: 2021-06-16
 */
@Data
public class VipInfoVO implements IResult{
    private ResultVO result;
    private Data data;

    @lombok.Data
    public static class Data {
        private VipInfo[] vipInfos;
        private String[] nbaInfos;
        private int transferable;   // 受否可迁移
    }

    @lombok.Data
    public static class VipInfo {
        private int start;  // 会员开始时间
        private int end;    // 会员结束时间
        private int vip;    // 是否是会员（1：是；0：否）
        private int vipBid; // 会员包id（平台相关，需要事先约定，如 3：基础包；4：hbo；35：nba）
        private String serviceName;
        private String startDatetime;
        private String endDatetime;
        private int bidType;    // 会员包类型  1：影视会员；2：鼎级剧场；3：腾讯体育；10：NBA权益  13：客厅少儿包
    }
}
