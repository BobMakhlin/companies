package com.makhlin.companyservice.swagger.model;

import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * UpdateCompany
 */
@Validated


public class UpdateCompany extends CompanyDetails {

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        return o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }

    @Override
    public String toString() {
        String sb = "class UpdateCompany {\n" +
                    "    " + toIndentedString(super.toString()) + "\n" +
                    "}";
        return sb;
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
