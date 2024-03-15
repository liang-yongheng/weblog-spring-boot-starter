package io.github.liangyongheng.weblogspringbootstarter.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author liangyongheng 2024/3/11 17:06
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ResponseDTO extends DataDTO {

    private String requestId;

    private int status;
}
