package io.github.liangyongheng.weblogspringbootstarter.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * @author liangyongheng 2024/3/11 17:06
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RequestDTO extends DataDTO {

    private Map<String, Object> parameters;
}
