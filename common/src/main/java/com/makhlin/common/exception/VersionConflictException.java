package com.makhlin.common.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class VersionConflictException extends RuntimeException {
    private final String errorCode = "NotTheLastVersion";

    public VersionConflictException(String message) {
        super(message);
    }
}