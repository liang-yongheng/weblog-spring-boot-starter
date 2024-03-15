package io.github.liangyongheng.weblogspringbootstarter.autoconfigure;

import io.github.liangyongheng.weblogspringbootstarter.core.DefaultWebLogHandler;
import io.github.liangyongheng.weblogspringbootstarter.core.WebLogHandler;
import io.github.liangyongheng.weblogspringbootstarter.filters.WebLogFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liangyongheng 2024/3/14 11:49
 */
@EnableConfigurationProperties({WebLogProperties.class})
@Configuration
public class WebLogAutoConfiguration {

    private final WebLogProperties properties;

    public WebLogAutoConfiguration(WebLogProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnProperty(value = "weblog.enabled", havingValue = "true", matchIfMissing = true)
    public FilterRegistrationBean<WebLogFilter> webLogFilter() {
        FilterRegistrationBean<WebLogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(getWebLogFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("webLogFilter");
        registrationBean.setOrder(properties.getOrder() == null ? 1 : properties.getOrder());
        return registrationBean;
    }

    @Bean
    @ConditionalOnMissingBean
    public WebLogHandler webLogHandler() {
        return new DefaultWebLogHandler();
    }

    private WebLogFilter getWebLogFilter() {
        WebLogFilter webLogFilter = new WebLogFilter();
        WebLogHandler webLogHandler = webLogHandler();
        webLogFilter.setWebLogHandler(webLogHandler);
        return webLogFilter;
    }
}
