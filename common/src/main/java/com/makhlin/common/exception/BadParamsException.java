package com.makhlin.common.exception;

import lombok.Getter;

@Getter
public class BadParamsException extends RuntimeException {

    private static final String DEFAULT_CODE = "BadParams";
    private final String code;

    public BadParamsException(ErrorType error) {
        super(error.getMessage());
        this.code = error.getCode() == null ? DEFAULT_CODE : error.getCode();
    }

}
