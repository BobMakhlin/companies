package com.makhlin.companyservice.swagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets companyStatus
 */
public enum CompanyStatus {
    ACTIVE("active"),
    INACTIVE("inactive"),
    BLOCKED("blocked");

    private final String value;

    CompanyStatus(String value) {
        this.value = value;
    }

    @JsonCreator
    public static CompanyStatus fromValue(String text) {
        for (CompanyStatus b : CompanyStatus.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }
}
