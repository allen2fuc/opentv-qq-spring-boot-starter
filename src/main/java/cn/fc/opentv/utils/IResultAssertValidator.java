package cn.fc.opentv.utils;

import cn.fc.opentv.domain.vo.IResult;
import cn.fc.opentv.exception.OpenApiException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import static cn.fc.opentv.utils.OpenTvConstants.RESULT_CODE_SUCCESS;
import static cn.fc.opentv.utils.OpenTvConstants.RESULT_RET_SUCCESS;

/**
 * @author fucheng
 * @date 2023/2/8
 */

public interface IResultAssertValidator {
	void validate(IResult result) throws OpenApiException;

	@Slf4j
	class SimpleAssertResultValidator implements IResultAssertValidator {

		@Override
		public void validate(@NonNull IResult result) {
			Assert.notNull(result.getResult(), "result should must be null");

			boolean nonResult = result.getResult().getCode() == RESULT_CODE_SUCCESS &&
					result.getResult().getRet() == RESULT_RET_SUCCESS;
			if (!nonResult) {
				throw new OpenApiException(result.getResult().getMsg());
			}
		}
	}

}
