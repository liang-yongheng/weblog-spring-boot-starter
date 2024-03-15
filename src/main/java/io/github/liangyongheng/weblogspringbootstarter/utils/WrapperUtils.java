package io.github.liangyongheng.weblogspringbootstarter.utils;

import io.github.liangyongheng.weblogspringbootstarter.core.ServletWrapper;
import io.github.liangyongheng.weblogspringbootstarter.core.WebCachingRequestWrapper;
import io.github.liangyongheng.weblogspringbootstarter.core.WebCachingResponseWrapper;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liangyongheng 2024/3/11 17:40
 */
public class WrapperUtils {

    public static Map<String, String> getRequestHeaders(WebCachingRequestWrapper requestWrapper) {
        Enumeration<String> headerKeys = requestWrapper.getHeaderNames();
        Map<String, String> headers = new HashMap<>();
        while (headerKeys.hasMoreElements()) {
            String key = headerKeys.nextElement();
            headers.put(key, requestWrapper.getHeader(key));
        }
        return headers;
    }

    public static Map<String, String> getResponseHeaders(WebCachingResponseWrapper responseWrapper) {
        Collection<String> headerNames = responseWrapper.getHeaderNames();
        Map<String, String> headers = new HashMap<>();
        headerNames.forEach(name -> headers.put(name, responseWrapper.getHeader(name)));
        return headers;
    }

    public static Map<String, Object> getRequestParameters(WebCachingRequestWrapper requestWrapper) {
        Map<String, String[]> parameterMap = requestWrapper.getParameterMap();
        Map<String, Object> parameters = new HashMap<>();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            if (entry.getValue().length == 1)
                parameters.put(entry.getKey(), entry.getValue()[0]);
            else if (entry.getValue().length > 1)
                parameters.put(entry.getKey(), entry.getValue());
        }
        return parameters;
    }

    public static Object getBody(ServletWrapper wrapper) {
        return wrapper.getBody();
    }
}
