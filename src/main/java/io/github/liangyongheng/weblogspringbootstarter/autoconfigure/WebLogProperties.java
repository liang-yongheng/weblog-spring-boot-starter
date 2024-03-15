package io.github.liangyongheng.weblogspringbootstarter.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author liangyongheng 2024/3/14 11:54
 */
@Data
@ConfigurationProperties(prefix = "weblog")
public class WebLogProperties {

    private Boolean enabled;

    private Integer order;

    public WebLogProperties() {
    }
}
