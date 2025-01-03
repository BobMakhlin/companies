package com.makhlin.common.service;

import com.makhlin.common.exception.BadParamsException;
import com.makhlin.common.exception.ItemNotFoundException;
import com.makhlin.common.exception.VersionConflictException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
public abstract class CommonExceptionHandler<T> {
    @ExceptionHandler(value = {BadParamsException.class})
    public ResponseEntity<Object> handleBadParamsException(BadParamsException ex) {
        log.error("Bad params exception: {}", ex.getMessage());
        var body = buildErrorResponse(ex.getCode(), ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);
    }

    @ExceptionHandler(value = {ItemNotFoundException.class})
    public ResponseEntity<Object> handleItemNotFoundException(ItemNotFoundException ex) {
        log.error("Item not found exception {}", ex.getMessage());
        var body = buildErrorResponse("NotFound", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);
    }

    @ExceptionHandler({VersionConflictException.class})
    public ResponseEntity<Object> handleVersionConflictException(VersionConflictException ex) {
        var body = buildErrorResponse(ex.getErrorCode(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);
    }

    protected abstract T buildErrorResponse(String code, String message);

}
