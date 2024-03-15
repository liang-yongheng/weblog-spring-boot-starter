package io.github.liangyongheng.weblogspringbootstarter.core;

import com.alibaba.fastjson.JSONObject;
import io.github.liangyongheng.weblogspringbootstarter.dto.ErrorDTO;
import io.github.liangyongheng.weblogspringbootstarter.utils.StatusCodeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Locale;

/**
 * @author liangyongheng 2024/3/11 17:16
 */
public class WebCachingResponseWrapper extends ContentCachingResponseWrapper implements ServletWrapper {

    /**
     * Create a new ContentCachingResponseWrapper for the given servlet response.
     *
     * @param response the original servlet response
     */
    public WebCachingResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public Object getBody() {
        try {
            int status = getStatus();
            if (StatusCodeUtils.isStartWith4(status) || StatusCodeUtils.isStartWith5(status)) {
                ErrorDTO errorDTO = new ErrorDTO();
                errorDTO.setTimestamp(new Date());
                errorDTO.setStatus(status);
                errorDTO.setError(HttpStatus.valueOf(status).getReasonPhrase());
                errorDTO.setMessage(new String(getContentAsByteArray(), getCharacterEncoding()));
                return errorDTO;
            }
            if (StatusCodeUtils.isStartWith2(status)) {
                String contentType = getContentType().toLowerCase(Locale.ROOT).trim();
                if (contentType.contains("json")) {
                    return JSONObject.parse(new String(getContentAsByteArray(), getCharacterEncoding()));
                }
                return new String(getContentAsByteArray(), getCharacterEncoding());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
