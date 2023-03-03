package cn.fc.opentv.utils;

import cn.fc.opentv.domain.vo.IResult;
import cn.fc.opentv.enums.OpenTvApiEnum;
import cn.fc.opentv.exception.OpenApiException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.retry.annotation.Retryable;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class OpenTvTemplate<IN, OUT extends IResult> {
	private static final Logger logger = LoggerFactory.getLogger(OpenTvTemplate.class);

	private final RestTemplate restTemplate;
	private final ObjectMapper objectMapper;

	public OpenTvTemplate(RestTemplate restTemplate, ObjectMapper objectMapper) {
		this.restTemplate = restTemplate;
		this.objectMapper = objectMapper;
	}

	@Retryable
	public OUT getFor(OpenTvApiEnum openTvEnum, IN in, Class<OUT> outClazz, IResultAssertValidator assertValidator) {
		String url = acquireUrl(in, openTvEnum);
		if (logger.isDebugEnabled()) {
			logger.debug("Remote request interface [{}]", url);
		}

		try {
			OUT out = restTemplate.getForObject(url, outClazz);
			assertValidator.validate(out);
			return out;
		}
		catch (Exception e) {

			if (logger.isDebugEnabled()) {
				logger.error("Interface call failure ", e);
			}

			throw new OpenApiException(e);
		}
	}

	private String acquireUrl(IN in, OpenTvApiEnum openTvEnum) {
		MultiValueMap<String, String> params = ConvertTool.objToMultiValueMap(objectMapper, in);
		return UriComponentsBuilder.fromUriString(openTvEnum.getValue()).queryParams(params).toUriString();
	}
}