package com.sky
/**
 * Created by tm1c14 on 15/04/2016.
 */

class BusinessException extends RuntimeException {


    public BusinessException(String message) {
        this(message, new Throwable())
    }

    public BusinessException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
