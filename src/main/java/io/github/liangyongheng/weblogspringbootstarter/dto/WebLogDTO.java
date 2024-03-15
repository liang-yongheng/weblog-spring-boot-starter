package io.github.liangyongheng.weblogspringbootstarter.dto;

import lombok.Data;

/**
 * @author liangyongheng 2024/3/8 18:15
 */
@Data
public class WebLogDTO {

    private String uri;

    private Long useTime;

    private RequestDTO request;

    private ResponseDTO response;
}
