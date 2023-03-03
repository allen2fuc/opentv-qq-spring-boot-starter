package cn.fc.opentv.domain.vo;

import java.util.Date;

import cn.fc.opentv.utils.TimeStampSecondsToDate;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

/**
 * @Author: fuch
 * @Date: 2021-06-15
 */
@Data
public class GetTokenVO implements IResult {
	private ResultVO result;
	private Data data;

	@lombok.Data
	public static class Data {
		private String accessToken;
		@JsonDeserialize(using = TimeStampSecondsToDate.class)
		private Date expireIn;
	}
}
