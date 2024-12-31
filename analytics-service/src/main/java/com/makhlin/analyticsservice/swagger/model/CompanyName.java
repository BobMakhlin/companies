package com.makhlin.analyticsservice.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;
import java.util.UUID;

/**
 * CompanyName
 */
@Validated


public class CompanyName {
    @JsonProperty("companyId")
    private UUID companyId = null;

    @JsonProperty("currentName")
    private String currentName = null;

    public CompanyName companyId(UUID companyId) {
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

    public CompanyName currentName(String currentName) {
        this.currentName = currentName;
        return this;
    }

    /**
     * Get currentName
     *
     * @return currentName
     **/
    @Schema(example = "Tech Solutions Inc.", description = "")

    public String getCurrentName() {
        return currentName;
    }

    public void setCurrentName(String currentName) {
        this.currentName = currentName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CompanyName companyName = (CompanyName) o;
        return Objects.equals(this.companyId, companyName.companyId) &&
               Objects.equals(this.currentName, companyName.currentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, currentName);
    }

    @Override
    public String toString() {

        String sb = "class CompanyName {\n" +
                    "    companyId: " + toIndentedString(companyId) + "\n" +
                    "    currentName: " + toIndentedString(currentName) + "\n" +
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
