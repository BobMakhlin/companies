package com.makhlin.companyservice.service;

import com.makhlin.common.service.CommonExceptionHandler;
import com.makhlin.companyservice.swagger.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@ControllerAdvice(assignableTypes = {
        com.makhlin.companyservice.swagger.api.CompaniesApiController.class,
        com.makhlin.companyservice.swagger.api.ConfigurationApiController.class,
})
@Order(1)
public class CustomExceptionHandler extends CommonExceptionHandler<ErrorResponse> {
    @Override
    protected ErrorResponse buildErrorResponse(String code, String message) {
        return new ErrorResponse().code(code).message(message);
    }
}
