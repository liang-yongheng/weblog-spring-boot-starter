package io.github.liangyongheng.weblogspringbootstarter.core;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * @author liangyongheng 2024/3/8 17:05
 */
public class WebCachingRequestWrapper extends ContentCachingRequestWrapper implements ServletWrapper {


    /**
     * Create a new ContentCachingRequestWrapper for the given servlet request.
     *
     * @param request the original servlet request
     */
    public WebCachingRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    /**
     * Create a new ContentCachingRequestWrapper for the given servlet request.
     *
     * @param request the original servlet request
     * @param contentCacheLimit the maximum number of bytes to cache per request
     */
    public WebCachingRequestWrapper(HttpServletRequest request, int contentCacheLimit) {
        super(request, contentCacheLimit);
    }

    @Override
    public Object getBody() {
        try {
            String contentType = getContentType();
            if (contentType != null && contentType.contains("json")) {
                return JSONObject.parse(new String(getContentAsByteArray(), getCharacterEncoding()));
            }
            return new String(getContentAsByteArray(), getCharacterEncoding());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
