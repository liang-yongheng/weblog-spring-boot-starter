package io.github.liangyongheng.weblogspringbootstarter.core;

import io.github.liangyongheng.weblogspringbootstarter.dto.WebLogDTO;

/**
 * @author liangyongheng 2024/3/14 14:44
 */
public interface WebLogHandler {

    void handle(WebLogDTO webLog);
}
