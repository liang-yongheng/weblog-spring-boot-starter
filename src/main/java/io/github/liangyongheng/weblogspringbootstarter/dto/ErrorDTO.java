package io.github.liangyongheng.weblogspringbootstarter.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author liangyongheng 2024/3/13 18:54
 */
@Data
public class ErrorDTO {

    private Date timestamp;

    private Integer status;

    private String error;

    private String message;

}
