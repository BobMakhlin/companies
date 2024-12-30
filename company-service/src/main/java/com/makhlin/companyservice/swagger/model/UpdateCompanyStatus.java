package com.makhlin.companyservice.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * UpdateCompanyStatus
 */
@Validated


public class UpdateCompanyStatus {
    @JsonProperty("status")
    private CompanyStatus status = null;

    public UpdateCompanyStatus status(CompanyStatus status) {
        this.status = status;
        return this;
    }

    /**
     * Get status
     *
     * @return status
     **/
    @Schema(description = "")

    @Valid
    public CompanyStatus getStatus() {
        return status;
    }

    public void setStatus(CompanyStatus status) {
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UpdateCompanyStatus updateCompanyStatus = (UpdateCompanyStatus) o;
        return Objects.equals(this.status, updateCompanyStatus.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    @Override
    public String toString() {

        String sb = "class UpdateCompanyStatus {\n" +
                    "    status: " + toIndentedString(status) + "\n" +
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
