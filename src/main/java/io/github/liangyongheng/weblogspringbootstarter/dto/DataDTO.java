package io.github.liangyongheng.weblogspringbootstarter.dto;

import lombok.Data;

import java.util.Map;

/**
 * @author liangyongheng 2024/3/11 17:23
 */
@Data
public class DataDTO {

    private Map<String, String> headers;

    private Object body;
}
