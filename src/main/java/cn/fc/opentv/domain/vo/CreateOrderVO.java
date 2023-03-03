package cn.fc.opentv.domain.vo;

import java.util.Date;

import cn.fc.opentv.utils.TimeStampSecondsToDate;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

/**
 * @Author: fuch
 * @Date: 2021-06-16a
 */
@Data
public class CreateOrderVO implements IResult {
	private ResultVO result;
	private Data data;

	@lombok.Data
	public static class Data {
		@JsonDeserialize(using = TimeStampSecondsToDate.class)
		private Date orderConfirmtime;
		@JsonDeserialize(using = TimeStampSecondsToDate.class)
		private Date orderCreatetime;
		private String orderId;
		private int orderPrice; //单位：分
		private int orderStatus; //0，待发货；1，已发货
		private Long vuid;
		private String extReserved;
	}
}
