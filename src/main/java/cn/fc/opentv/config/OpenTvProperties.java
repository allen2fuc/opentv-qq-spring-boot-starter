package cn.fc.opentv.config;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import static cn.fc.opentv.config.OpenTvProperties.PREFIX;


@ConfigurationProperties(PREFIX)
@Validated
@Data
public class OpenTvProperties {
    public static final String PREFIX = "open-tv";
    public static final String PROXY = "proxy";

    /**
     * 域名
     */
    @NotBlank
    private String domain;

    /**
     * APP_ID
     */
    @NotBlank
    private String appid;

    /**
     * APP_KEY
     */
    @NotBlank
    private String appkey;

    /**
     * 创建订单时的qua
     */
    @NotBlank
    private String qua;


    @NotBlank
    private String vendorPlatform;

    @NotBlank
    private String vippkg;

    @NotNull
    private int version = 1;

    @NotBlank
    private String format = "json";

    private Duration connectTimeout = Duration.ofMinutes(3);

    private Duration readTimeout = Duration.ofMinutes(3);;

    /**
     * 代理配置
     */
    private ProxyConfig proxy;

    /**
     * Token刷新间隔时间
     */
    private long tokenRefreshInterval = 30 * 60 * 1000;
    private long tokenCacheTime = 60 * 60 * 1000;

    @Data
    public static class ProxyConfig {
        private String host;
        private Integer port;
    }
}