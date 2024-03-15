package io.github.liangyongheng.weblogspringbootstarter.core;

import com.alibaba.fastjson.JSONObject;
import io.github.liangyongheng.weblogspringbootstarter.dto.WebLogDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liangyongheng 2024/3/14 14:45
 */
public class DefaultWebLogHandler implements WebLogHandler {

    private static final Logger logger = LoggerFactory.getLogger(DefaultWebLogHandler.class);

    @Override
    public void handle(WebLogDTO webLog) {
        logger.info("WebLog: {}", JSONObject.toJSONString(webLog));
    }
}
