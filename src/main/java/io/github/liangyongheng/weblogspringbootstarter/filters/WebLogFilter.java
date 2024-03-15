package io.github.liangyongheng.weblogspringbootstarter.filters;

import io.github.liangyongheng.weblogspringbootstarter.core.WebCachingRequestWrapper;
import io.github.liangyongheng.weblogspringbootstarter.core.WebCachingResponseWrapper;
import io.github.liangyongheng.weblogspringbootstarter.core.WebLogHandler;
import io.github.liangyongheng.weblogspringbootstarter.dto.RequestDTO;
import io.github.liangyongheng.weblogspringbootstarter.dto.ResponseDTO;
import io.github.liangyongheng.weblogspringbootstarter.dto.WebLogDTO;
import io.github.liangyongheng.weblogspringbootstarter.utils.UUIDUtils;
import io.github.liangyongheng.weblogspringbootstarter.utils.WrapperUtils;
import lombok.Setter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liangyongheng 2024/3/8 16:12
 */
@Setter
public class WebLogFilter extends OncePerRequestFilter {

    private WebLogHandler webLogHandler;

    @Override
    public void afterPropertiesSet() {
        // do nothing and so that initFilterBean() will be called once!
    }

    @Override
    protected void initFilterBean() {
        System.out.println("\033[1;32mWebLogFilter Is Init!\033[0m");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) {
        try {
            WebCachingRequestWrapper requestWrapper = new WebCachingRequestWrapper(httpServletRequest);
            WebCachingResponseWrapper responseWrapper = new WebCachingResponseWrapper(httpServletResponse);
            WebLogDTO webLog = new WebLogDTO();
            webLog.setUri(requestWrapper.getRequestURI());
            long startTime = System.currentTimeMillis();
            filterChain.doFilter(requestWrapper, responseWrapper);
            long endTime = System.currentTimeMillis();
            RequestDTO request = new RequestDTO();
            request.setBody(WrapperUtils.getBody(requestWrapper));
            request.setHeaders(WrapperUtils.getRequestHeaders(requestWrapper));
            request.setParameters(WrapperUtils.getRequestParameters(requestWrapper));
            webLog.setRequest(request);
            ResponseDTO response = new ResponseDTO();
            response.setRequestId(UUIDUtils.getUuid());
            response.setStatus(responseWrapper.getStatus());
            // Since the responseWrapper copyBodyToResponse method clears the content, we need to get the body before calling the copyBodyToResponse method
            response.setBody(WrapperUtils.getBody(responseWrapper));
            // need to copy the body to the response
            responseWrapper.copyBodyToResponse();
            response.setHeaders(WrapperUtils.getResponseHeaders(responseWrapper));
            webLog.setResponse(response);
            webLog.setUseTime(endTime - startTime);
            if (webLogHandler != null) {
                webLogHandler.handle(webLog);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
