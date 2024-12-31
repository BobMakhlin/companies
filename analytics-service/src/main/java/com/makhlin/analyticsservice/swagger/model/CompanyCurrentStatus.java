package com.makhlin.analyticsservice.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;
import java.util.UUID;

/**
 * CompanyCurrentStatus
 */
@Validated


public class CompanyCurrentStatus {
    @JsonProperty("companyId")
    private UUID companyId = null;

    @JsonProperty("status")
    private CompanyStatus status = null;

    public CompanyCurrentStatus companyId(UUID companyId) {
        this.companyId = companyId;
        return this;
    }

    /**
     * Get companyId
     *
     * @return companyId
     **/
    @Schema(description = "")

    @Valid
    public UUID getCompanyId() {
        return companyId;
    }

    public void setCompanyId(UUID companyId) {
        this.companyId = companyId;
    }

    public CompanyCurrentStatus status(CompanyStatus status) {
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
        CompanyCurrentStatus companyCurrentStatus = (CompanyCurrentStatus) o;
        return Objects.equals(this.companyId, companyCurrentStatus.companyId) &&
               Objects.equals(this.status, companyCurrentStatus.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, status);
    }

    @Override
    public String toString() {

        String sb = "class CompanyCurrentStatus {\n" +
                    "    companyId: " + toIndentedString(companyId) + "\n" +
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
