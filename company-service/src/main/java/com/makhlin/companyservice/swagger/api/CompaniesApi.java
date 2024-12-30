/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.46).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.makhlin.companyservice.swagger.api;

import com.makhlin.companyservice.swagger.model.Company;
import com.makhlin.companyservice.swagger.model.ErrorResponse;
import com.makhlin.companyservice.swagger.model.UpdateCompany;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Validated
public interface CompaniesApi {

    CompaniesApiDelegate getDelegate();

    @Operation(summary = "Add new Company", description = "Allows to create a company by providing company details.", tags = {"COMPANIES"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Company.class))),

            @ApiResponse(responseCode = "400", description = "Bad Request <br><br> **Code descriptions:** <br> - BadParams: Were passed bad params; <br> - InvalidIndustry: Industry must be a value from the available industries list; <br> - InvalidAddressCategory: Address category must a value from the available categories list. <br> ", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),

            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))})
    @RequestMapping(value = "/v1/companies",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    default ResponseEntity<Company> addCompany(@Parameter(in = ParameterIn.DEFAULT, description = "Company that needs to be added", schema = @Schema()) @Valid @RequestBody UpdateCompany body) {
        return getDelegate().addCompany(body);
    }


    @Operation(summary = "Delete an existing Company", description = "Allows to delete existing Company.", tags = {"COMPANIES"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),

            @ApiResponse(responseCode = "400", description = "Bad Request <br><br> **Code descriptions:** <br> - BadParams: Were passed bad params. <br> ", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),

            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),

            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))})
    @RequestMapping(value = "/v1/companies/{companyId}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteCompany(@Parameter(in = ParameterIn.PATH, description = "Unique identifier of Company", required = true, schema = @Schema()) @PathVariable("companyId") UUID companyId) {
        return getDelegate().deleteCompany(companyId);
    }


    @Operation(summary = "Get list of Companies", description = "Returns list of Companies.", tags = {"COMPANIES"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Company.class)))),

            @ApiResponse(responseCode = "400", description = "Bad Request <br><br> **Code descriptions:** <br> - BadParams: Were passed bad params. ", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),

            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))})
    @RequestMapping(value = "/v1/companies",
            produces = {"application/json"},
            method = RequestMethod.GET)
    default ResponseEntity<List<Company>> getCompanies(@Min(0) @Parameter(in = ParameterIn.QUERY, description = "Items offset (minimum 0). Default is 0", schema = @Schema(allowableValues = {"0"}
            , defaultValue = "0")) @Valid @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset, @Min(20) @Max(1500) @Parameter(in = ParameterIn.QUERY, description = "Number of items to list (minimum 20, maximum 500)", schema = @Schema(allowableValues = {"20", "1500"}, minimum = "20", maximum = "1500"
            , defaultValue = "20")) @Valid @RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit) {
        return getDelegate().getCompanies(offset, limit);
    }


    @Operation(summary = "Retrieves the details of specific Company", description = "Returns the single Company object.", tags = {"COMPANIES"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Company.class))),

            @ApiResponse(responseCode = "400", description = "Bad Request <br><br> **Code descriptions:** <br> - BadParams: Were passed bad params. ", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),

            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),

            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))})
    @RequestMapping(value = "/v1/companies/{companyId}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    default ResponseEntity<Company> getCompany(@Parameter(in = ParameterIn.PATH, description = "Unique identifier of Company", required = true, schema = @Schema()) @PathVariable("companyId") UUID companyId) {
        return getDelegate().getCompany(companyId);
    }


    @Operation(summary = "Updates existing Company", description = "Allows to update existing company by providing parameters of the company.", tags = {"COMPANIES"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Company.class))),

            @ApiResponse(responseCode = "400", description = "Bad Request <br><br> **Code descriptions:** <br> - BadParams: Were passed bad params; <br> - InvalidIndustry: Industry must be a value from the available industries list; <br> - InvalidAddressCategory: Address category must a value from the available categories list. <br> ", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),

            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),

            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))})
    @RequestMapping(value = "/v1/companies/{companyId}",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    default ResponseEntity<Company> updateCompany(@Parameter(in = ParameterIn.PATH, description = "Unique identifier of Company", required = true, schema = @Schema()) @PathVariable("companyId") UUID companyId, @Parameter(in = ParameterIn.DEFAULT, description = "Parameters of the company that must be updated", required = true, schema = @Schema()) @Valid @RequestBody UpdateCompany body) {
        return getDelegate().updateCompany(companyId, body);
    }

}
