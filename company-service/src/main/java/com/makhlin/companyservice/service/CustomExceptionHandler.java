package com.makhlin.companyservice.service;

import com.makhlin.companyservice.service.exception.BadParamsException;
import com.makhlin.companyservice.swagger.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(assignableTypes = {
        com.makhlin.companyservice.swagger.api.CompaniesApiController.class,
        com.makhlin.companyservice.swagger.api.ConfigurationApiController.class,
})
@Order(1)
public class CustomExceptionHandler {
    @ExceptionHandler(value = {BadParamsException.class})
    public ResponseEntity<Object> handleBadParamsException(BadParamsException ex) {
        log.error("Bad params exception: {}", ex.getMessage());
        var body = new ErrorResponse()
                .code(ex.getCode())
                .message(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);
    }
}
