package com.makhlin.companyservice.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CompanyDetails
 */
@Validated


public class CompanyDetails {
    @JsonProperty("name")
    private String name = null;

    @JsonProperty("registrationNumber")
    private String registrationNumber = null;

    @JsonProperty("contacts")
    private Contacts contacts = null;

    @JsonProperty("industry")
    private String industry = null;

    @JsonProperty("addresses")
    @Valid
    private List<Address> addresses = null;

    public CompanyDetails name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Parameter representing the name of the Company.
     *
     * @return name
     **/
    @Schema(required = true, description = "Parameter representing the name of the Company.")
    @NotNull

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CompanyDetails registrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
        return this;
    }

    /**
     * Parameter representing the registration number of the Company.
     *
     * @return registrationNumber
     **/
    @Schema(required = true, description = "Parameter representing the registration number of the Company.")
    @NotNull

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public CompanyDetails contacts(Contacts contacts) {
        this.contacts = contacts;
        return this;
    }

    /**
     * Get contacts
     *
     * @return contacts
     **/
    @Schema(required = true, description = "")
    @NotNull

    @Valid
    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public CompanyDetails industry(String industry) {
        this.industry = industry;
        return this;
    }

    /**
     * The industry of the Company. Must be one of the available industries.
     *
     * @return industry
     **/
    @Schema(required = true, description = "The industry of the Company. Must be one of the available industries.")
    @NotNull

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public CompanyDetails addresses(List<Address> addresses) {
        this.addresses = addresses;
        return this;
    }

    public CompanyDetails addAddressesItem(Address addressesItem) {
        if (this.addresses == null) {
            this.addresses = new ArrayList<>();
        }
        this.addresses.add(addressesItem);
        return this;
    }

    /**
     * Addresses of the company buildings.
     *
     * @return addresses
     **/
    @Schema(description = "Addresses of the company buildings.")
    @Valid
    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CompanyDetails companyDetails = (CompanyDetails) o;
        return Objects.equals(this.name, companyDetails.name) &&
               Objects.equals(this.registrationNumber, companyDetails.registrationNumber) &&
               Objects.equals(this.contacts, companyDetails.contacts) &&
               Objects.equals(this.industry, companyDetails.industry) &&
               Objects.equals(this.addresses, companyDetails.addresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, registrationNumber, contacts, industry, addresses);
    }

    @Override
    public String toString() {

        String sb = "class CompanyDetails {\n" +
                    "    name: " + toIndentedString(name) + "\n" +
                    "    registrationNumber: " + toIndentedString(registrationNumber) + "\n" +
                    "    contacts: " + toIndentedString(contacts) + "\n" +
                    "    industry: " + toIndentedString(industry) + "\n" +
                    "    addresses: " + toIndentedString(addresses) + "\n" +
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
