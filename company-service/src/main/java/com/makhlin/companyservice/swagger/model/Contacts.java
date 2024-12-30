package com.makhlin.companyservice.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * Contact information of the Company.
 */
@Schema(description = "Contact information of the Company.")
@Validated



public class Contacts   {
  @JsonProperty("email")
  private String email = null;

  @JsonProperty("phone")
  private String phone = null;

  @JsonProperty("fax")
  private String fax = null;

  public Contacts email(String email) {
    this.email = email;
    return this;
  }

  /**
   * An email address of the Company.
   * @return email
   **/
  @Schema(required = true, description = "An email address of the Company.")
      @NotNull

    public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Contacts phone(String phone) {
    this.phone = phone;
    return this;
  }

  /**
   * A phone number of the Company.
   * @return phone
   **/
  @Schema(required = true, description = "A phone number of the Company.")
      @NotNull

    public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Contacts fax(String fax) {
    this.fax = fax;
    return this;
  }

  /**
   * The fax of the Company.
   * @return fax
   **/
  @Schema(description = "The fax of the Company.")
  
    public String getFax() {
    return fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Contacts contacts = (Contacts) o;
    return Objects.equals(this.email, contacts.email) &&
        Objects.equals(this.phone, contacts.phone) &&
        Objects.equals(this.fax, contacts.fax);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, phone, fax);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Contacts {\n");
    
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    fax: ").append(toIndentedString(fax)).append("\n");
    sb.append("}");
    return sb.toString();
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
