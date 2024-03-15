package io.github.liangyongheng.weblogspringbootstarter.utils;

/**
 * @author liangyongheng 2024/3/14 18:18
 */
public class StatusCodeUtils {

    public static boolean isStartWith1(int statusCode) {
        if (statusCode >= 100 && statusCode < 200) {
            return true;
        }
        return false;
    }

    public static boolean isStartWith2(int statusCode) {
        if (statusCode >= 200 && statusCode < 300) {
            return true;
        }
        return false;
    }

    public static boolean isStartWith3(int statusCode) {
        if (statusCode >= 300 && statusCode < 400) {
            return true;
        }
        return false;
    }

    public static boolean isStartWith4(int statusCode) {
        if (statusCode >= 400 && statusCode < 500) {
            return true;
        }
        return false;
    }

    public static boolean isStartWith5(int statusCode) {
        if (statusCode >= 500 && statusCode < 600) {
            return true;
        }
        return false;
    }
}
