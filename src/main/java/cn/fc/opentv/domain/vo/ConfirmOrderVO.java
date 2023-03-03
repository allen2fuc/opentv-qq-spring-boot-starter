package cn.fc.opentv.domain.vo;

import java.util.Date;

import cn.fc.opentv.utils.TimeStampSecondsToDate;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;


/**
 * @Author: fuch
 * @Date: 2021-06-16
 */
@Data
public class ConfirmOrderVO implements IResult {
	private ResultVO result;
	private Data data;

	@lombok.Data
	public static class Data {
		@JsonDeserialize(using = TimeStampSecondsToDate.class)
		private Date orderConfirmtime;   //订单发货时间
		@JsonDeserialize(using = TimeStampSecondsToDate.class)
		private Date orderCreatetime;    //订单创建时间
		private String orderId; //订单号
		private int orderPrice; //订单参考价格，单位：分
		private int orderStatus;    //订单状态；0，待发货；1，已发货
		private String vuid;    //历史字段，不一定返回
		private String openOpenid;
		private String[] service;   //订单开通服务内容
	}
}
