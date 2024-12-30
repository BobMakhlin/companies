package com.makhlin.companyservice.service.exception;

import lombok.Getter;

@Getter
public enum ErrorTypeEnum {

    // BAD REQUEST 400
    INVALID_ADDRESS_CATEGORY("Address category must a value from the available categories list.", "InvalidAddressCategory"),
    INVALID_INDUSTRY("Industry must be a value from the available industries list.", "InvalidIndustry"),

    // NOT FOUND 404
    ITEM_NOT_FOUND("Item with id %s not found.", "NotFound");

    private final String message;
    private final String code;

    ErrorTypeEnum(String message, String code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String toString() {
        return this.message;
    }

}
