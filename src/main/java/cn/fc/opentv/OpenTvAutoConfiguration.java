package cn.fc.opentv;

import cn.fc.opentv.config.OpenTvConfig;

import org.springframework.context.annotation.Import;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author fucheng
 * @date 2023/3/3
 */
@EnableRetry
@EnableScheduling
@Import(OpenTvConfig.class)
public class OpenTvAutoConfiguration {
}