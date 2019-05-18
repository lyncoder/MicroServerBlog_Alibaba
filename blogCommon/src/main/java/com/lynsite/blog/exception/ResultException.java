package com.lynsite.blog.exception;

import com.lynsite.blog.enums.ResultEnums;

/**
 * @Description: TODO
 * @Author: 刘亚楠
 * @Date: 2019/4/13 21:15
 * @Version: 1.0
 */
public class ResultException extends RuntimeException {

    private Integer code;

    public ResultException(ResultEnums resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public ResultException(Integer code, String message){
        super(message);
        this.code = code;
    }

}
