package com.makhlin.analyticsservice.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * CompanyAddressStatistics
 */
@Validated


public class CompanyAddressStatistics {
    @JsonProperty("companyId")
    private UUID companyId = null;

    @JsonProperty("addressStatistics")
    @Valid
    private Map<String, Integer> addressStatistics = null;

    public CompanyAddressStatistics companyId(UUID companyId) {
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

    public CompanyAddressStatistics addressStatistics(Map<String, Integer> addressStatistics) {
        this.addressStatistics = addressStatistics;
        return this;
    }

    public CompanyAddressStatistics putAddressStatisticsItem(String key, Integer addressStatisticsItem) {
        if (this.addressStatistics == null) {
            this.addressStatistics = new HashMap<>();
        }
        this.addressStatistics.put(key, addressStatisticsItem);
        return this;
    }

    /**
     * A map where keys are address categories and values are their counts.
     *
     * @return addressStatistics
     **/
    @Schema(example = "{\"warehouse\":3,\"headquarters\":1,\"retail\":5,\"storage\":2}", description = "A map where keys are address categories and values are their counts.")

    public Map<String, Integer> getAddressStatistics() {
        return addressStatistics;
    }

    public void setAddressStatistics(Map<String, Integer> addressStatistics) {
        this.addressStatistics = addressStatistics;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CompanyAddressStatistics companyAddressStatistics = (CompanyAddressStatistics) o;
        return Objects.equals(this.companyId, companyAddressStatistics.companyId) &&
               Objects.equals(this.addressStatistics, companyAddressStatistics.addressStatistics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, addressStatistics);
    }

    @Override
    public String toString() {

        String sb = "class CompanyAddressStatistics {\n" +
                    "    companyId: " + toIndentedString(companyId) + "\n" +
                    "    addressStatistics: " + toIndentedString(addressStatistics) + "\n" +
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
