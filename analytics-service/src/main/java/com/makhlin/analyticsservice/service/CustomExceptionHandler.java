package com.makhlin.analyticsservice.service;

import com.makhlin.analyticsservice.swagger.model.ErrorResponse;
import com.makhlin.common.service.CommonExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@ControllerAdvice(assignableTypes = {
})
@Order(1)
public class CustomExceptionHandler extends CommonExceptionHandler<ErrorResponse> {
    @Override
    protected ErrorResponse buildErrorResponse(String code, String message) {
        return new ErrorResponse().code(code).message(message);
    }
}
