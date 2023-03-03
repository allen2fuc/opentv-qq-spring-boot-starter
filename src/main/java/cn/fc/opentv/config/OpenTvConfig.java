package cn.fc.opentv.config;

import java.net.InetSocketAddress;
import java.net.Proxy;

import cn.fc.opentv.service.IOpenTvOperateService;
import cn.fc.opentv.service.impl.OpenTvOperateServiceImpl;
import cn.fc.opentv.utils.IResultAssertValidator;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import static cn.fc.opentv.config.OpenTvProperties.PREFIX;
import static cn.fc.opentv.config.OpenTvProperties.PROXY;
import static cn.fc.opentv.utils.OpenTvConstants.OPEN_TV_URL_TEMPLATE;

/**
 * @author fucheng
 * @date 2022/6/9
 */
@AllArgsConstructor
@Configuration
@EnableConfigurationProperties(OpenTvProperties.class)
public class OpenTvConfig {

	private OpenTvProperties openTvProperties;

	private ObjectMapper objectMapper;

	/**
	 * 结果验证器
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	IResultAssertValidator resultAssertValidator() {
		return new IResultAssertValidator.SimpleAssertResultValidator();
	}

	@Bean
	@ConditionalOnMissingBean
	public IOpenTvOperateService openTvOperateService(RestTemplate restTemplate){
		return new OpenTvOperateServiceImpl(openTvProperties, restTemplate, objectMapper, resultAssertValidator());
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder, ClientHttpRequestFactory clientHttpRequestFactory) {
		String baseUriTemplate = StrUtil.format(OPEN_TV_URL_TEMPLATE, openTvProperties.getDomain());
		return restTemplateBuilder.requestFactory(() -> clientHttpRequestFactory)
				.uriTemplateHandler(new DefaultUriBuilderFactory(baseUriTemplate))
				.setReadTimeout(openTvProperties.getReadTimeout())
				.setConnectTimeout(openTvProperties.getConnectTimeout())
				.build();
	}

	@Bean
	@ConditionalOnProperty(prefix = PREFIX, name = PROXY)
	public ClientHttpRequestFactory proxyClientHttpRequestFactory() {
		Assert.hasText(openTvProperties.getProxy().getHost(), "open-tv.proxy.host=xxx.xxx.xxx.xxx required");
		Assert.notNull(openTvProperties.getProxy().getPort(), "open-tv.proxy.port=0 required");
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		InetSocketAddress address =
				new InetSocketAddress(openTvProperties.getProxy().getHost(), openTvProperties.getProxy().getPort());
		Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
		requestFactory.setProxy(proxy);
		return requestFactory;
	}

	@Bean
	@ConditionalOnMissingBean(ClientHttpRequestFactory.class)
	public ClientHttpRequestFactory defaultClientHttpRequestFactory() {
		return new SimpleClientHttpRequestFactory();
	}
}
