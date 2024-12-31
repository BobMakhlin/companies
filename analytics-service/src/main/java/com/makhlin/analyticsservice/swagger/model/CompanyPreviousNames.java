package com.makhlin.analyticsservice.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * CompanyPreviousNames
 */
@Validated


public class CompanyPreviousNames {
    @JsonProperty("companyId")
    private UUID companyId = null;

    @JsonProperty("previousNames")
    @Valid
    private List<String> previousNames = null;

    public CompanyPreviousNames companyId(UUID companyId) {
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

    public CompanyPreviousNames previousNames(List<String> previousNames) {
        this.previousNames = previousNames;
        return this;
    }

    public CompanyPreviousNames addPreviousNamesItem(String previousNamesItem) {
        if (this.previousNames == null) {
            this.previousNames = new ArrayList<>();
        }
        this.previousNames.add(previousNamesItem);
        return this;
    }

    /**
     * Get previousNames
     *
     * @return previousNames
     **/
    @Schema(example = "[\"Tech Innovations LLC\",\"Advanced Tech Systems\"]", description = "")

    public List<String> getPreviousNames() {
        return previousNames;
    }

    public void setPreviousNames(List<String> previousNames) {
        this.previousNames = previousNames;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CompanyPreviousNames companyPreviousNames = (CompanyPreviousNames) o;
        return Objects.equals(this.companyId, companyPreviousNames.companyId) &&
               Objects.equals(this.previousNames, companyPreviousNames.previousNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, previousNames);
    }

    @Override
    public String toString() {

        String sb = "class CompanyPreviousNames {\n" +
                    "    companyId: " + toIndentedString(companyId) + "\n" +
                    "    previousNames: " + toIndentedString(previousNames) + "\n" +
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
