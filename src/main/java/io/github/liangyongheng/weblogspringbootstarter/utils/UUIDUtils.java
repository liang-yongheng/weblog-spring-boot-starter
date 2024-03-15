package io.github.liangyongheng.weblogspringbootstarter.utils;

import java.util.UUID;

/**
 * @author liangyongheng 2024/3/12 20:44
 */
public class UUIDUtils {

    public static String getUuid() {
        return UUID.randomUUID().toString();
    }
}
