package com.makhlin.companyservice.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * Address of the Company
 */
@Schema(description = "Address of the Company")
@Validated



public class Address   {
  @JsonProperty("country")
  private String country = null;

  @JsonProperty("city")
  private String city = null;

  @JsonProperty("street")
  private String street = null;

  @JsonProperty("zip")
  private String zip = null;

  @JsonProperty("category")
  private String category = null;

  public Address country(String country) {
    this.country = country;
    return this;
  }

  /**
   * Get country
   * @return country
   **/
  @Schema(description = "")
  
    public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Address city(String city) {
    this.city = city;
    return this;
  }

  /**
   * Get city
   * @return city
   **/
  @Schema(description = "")
  
    public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Address street(String street) {
    this.street = street;
    return this;
  }

  /**
   * Get street
   * @return street
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public Address zip(String zip) {
    this.zip = zip;
    return this;
  }

  /**
   * Get zip
   * @return zip
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public Address category(String category) {
    this.category = category;
    return this;
  }

  /**
   * The category of the address. Must be one of the available categories.
   * @return category
   **/
  @Schema(required = true, description = "The category of the address. Must be one of the available categories.")
      @NotNull

    public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Address address = (Address) o;
    return Objects.equals(this.country, address.country) &&
        Objects.equals(this.city, address.city) &&
        Objects.equals(this.street, address.street) &&
        Objects.equals(this.zip, address.zip) &&
        Objects.equals(this.category, address.category);
  }

  @Override
  public int hashCode() {
    return Objects.hash(country, city, street, zip, category);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Address {\n");
    
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    street: ").append(toIndentedString(street)).append("\n");
    sb.append("    zip: ").append(toIndentedString(zip)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
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
