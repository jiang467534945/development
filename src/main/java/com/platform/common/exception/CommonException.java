package com.platform.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonException extends Exception {

    private static Logger logger = LoggerFactory.getLogger(CommonException.class);

    private String message;

    public CommonException() {
        super();
    }

    public CommonException(String message) {
        super(message);
        this.message = message;
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
