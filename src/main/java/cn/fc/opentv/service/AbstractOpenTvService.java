package cn.fc.opentv.service;

import java.time.Duration;
import java.util.Date;

import cn.fc.opentv.config.OpenTvProperties;
import cn.fc.opentv.domain.dto.IAccessToken;
import cn.fc.opentv.domain.vo.GetTokenVO;
import cn.fc.opentv.domain.vo.IResult;
import cn.fc.opentv.enums.OpenTvApiEnum;
import cn.fc.opentv.utils.IResultAssertValidator;
import cn.fc.opentv.utils.OpenTvTemplate;
import cn.fc.opentv.utils.TokenHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

/**
 * @author fucheng
 * @date 2023/2/8
 */
public abstract class AbstractOpenTvService implements IOpenTvOperateService {
	private final OpenTvProperties openTvProperties;
	private final RestTemplate restTemplate;
	private final ObjectMapper objectMapper;
	private final IResultAssertValidator resultAssertValidator;

	private final TokenHelper tokenHelper;

	public AbstractOpenTvService(OpenTvProperties openTvProperties,
			RestTemplate restTemplate,
			ObjectMapper objectMapper,
			IResultAssertValidator resultAssertValidator) {
		this.openTvProperties = openTvProperties;
		this.restTemplate = restTemplate;
		this.objectMapper = objectMapper;
		this.resultAssertValidator = resultAssertValidator;
		this.tokenHelper = new TokenHelper(this, Duration.ofMillis(openTvProperties.getTokenCacheTime()));
	}

	protected <IN, OUT extends IResult> OUT callOpenTvInterface(OpenTvApiEnum openTvEnum, IN in, Class<OUT> outClazz) {
		if (in instanceof IAccessToken) {
			String accessToken = getToken().getData().getAccessToken();
			((IAccessToken) in).setAccessToken(accessToken);
		}
		BeanUtils.copyProperties(openTvProperties, in);
		OpenTvTemplate<IN, OUT> openTvTool = new OpenTvTemplate<>(restTemplate, objectMapper);
		return openTvTool.getFor(openTvEnum, in, outClazz, resultAssertValidator);
	}

	@Override
	public GetTokenVO getToken() {
		GetTokenVO tokenVO = tokenHelper.getTokenVO();
		if (tokenVO == null){
			tokenHelper.refresh();
		}

		tokenVO = tokenHelper.getTokenVO();
		Assert.notNull(tokenVO, "token is null");
		Assert.notNull(tokenVO.getData(), "token data is null");

		if (tokenVO.getData().getExpireIn().after(new Date())){
			tokenHelper.refresh();
		}
		return tokenHelper.getTokenVO();
	}

	@Override
	public void refreshToken() {
		tokenHelper.refresh();
	}
}
