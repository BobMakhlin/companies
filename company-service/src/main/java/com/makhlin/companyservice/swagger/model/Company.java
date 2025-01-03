package com.makhlin.companyservice.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;
import java.util.UUID;

/**
 * Company
 */
@Validated


public class Company extends CompanyDetails {
    @JsonProperty("id")
    private UUID id = null;

    @JsonProperty("status")
    private CompanyStatus status = null;

    public Company id(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     **/
    @Schema(description = "")

    @Valid
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Company status(CompanyStatus status) {
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
        Company company = (Company) o;
        return Objects.equals(this.id, company.id) &&
               Objects.equals(this.status, company.status) &&
               super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, super.hashCode());
    }

    @Override
    public String toString() {
        String sb = "class Company {\n" +
                    "    " + toIndentedString(super.toString()) + "\n" +
                    "    id: " + toIndentedString(id) + "\n" +
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
