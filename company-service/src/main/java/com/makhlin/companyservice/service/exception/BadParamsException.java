package com.makhlin.companyservice.service.exception;

import lombok.Getter;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Getter
public class BadParamsException extends RuntimeException {

    private static final String DEFAULT_CODE = "BadParams";
    private final String code;

    public BadParamsException(ErrorTypeEnum error) {
        super(error.getMessage());
        this.code = isEmpty(error.getCode()) ? DEFAULT_CODE : error.getCode();
    }

}
