package com.lynsite.blog.enums;

/**
 * @Description: TODO
 * @Author: 刘亚楠
 * @Date: 2019/4/13 21:08
 * @Version: 1.0
 */

public enum ResultEnums {

    RESULT_ERROR(500, "查询结果失败"),

    RESULT_NOT_FOUND(404, "查询结果不存在"),

    ;

    private Integer code;
    private String message;

    ResultEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}
