package com.makhlin.companyservice.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * BusinessConfiguration
 */
@Validated


public class BusinessConfiguration {
    @JsonProperty("industries")
    @Valid
    private List<String> industries = null;

    @JsonProperty("addressCategories")
    @Valid
    private List<String> addressCategories = null;

    public BusinessConfiguration industries(List<String> industries) {
        this.industries = industries;
        return this;
    }

    public BusinessConfiguration addIndustriesItem(String industriesItem) {
        if (this.industries == null) {
            this.industries = new ArrayList<>();
        }
        this.industries.add(industriesItem);
        return this;
    }

    /**
     * Get industries
     *
     * @return industries
     **/
    @Schema(description = "")

    public List<String> getIndustries() {
        return industries;
    }

    public void setIndustries(List<String> industries) {
        this.industries = industries;
    }

    public BusinessConfiguration addressCategories(List<String> addressCategories) {
        this.addressCategories = addressCategories;
        return this;
    }

    public BusinessConfiguration addAddressCategoriesItem(String addressCategoriesItem) {
        if (this.addressCategories == null) {
            this.addressCategories = new ArrayList<>();
        }
        this.addressCategories.add(addressCategoriesItem);
        return this;
    }

    /**
     * Get addressCategories
     *
     * @return addressCategories
     **/
    @Schema(description = "")

    public List<String> getAddressCategories() {
        return addressCategories;
    }

    public void setAddressCategories(List<String> addressCategories) {
        this.addressCategories = addressCategories;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BusinessConfiguration businessConfiguration = (BusinessConfiguration) o;
        return Objects.equals(this.industries, businessConfiguration.industries) &&
               Objects.equals(this.addressCategories, businessConfiguration.addressCategories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(industries, addressCategories);
    }

    @Override
    public String toString() {

        String sb = "class BusinessConfiguration {\n" +
                    "    industries: " + toIndentedString(industries) + "\n" +
                    "    addressCategories: " + toIndentedString(addressCategories) + "\n" +
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
