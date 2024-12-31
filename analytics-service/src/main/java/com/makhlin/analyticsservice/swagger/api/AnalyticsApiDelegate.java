package com.makhlin.analyticsservice.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.makhlin.analyticsservice.swagger.model.CompanyAddressStatistics;
import com.makhlin.analyticsservice.swagger.model.CompanyCurrentStatus;
import com.makhlin.analyticsservice.swagger.model.CompanyName;
import com.makhlin.analyticsservice.swagger.model.CompanyPreviousNames;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

/**
 * A delegate to be called by the {@link AnalyticsApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */

public interface AnalyticsApiDelegate {

    Logger log = LoggerFactory.getLogger(AnalyticsApi.class);

    default Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    default Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    default Optional<String> getAcceptHeader() {
        return getRequest().map(r -> r.getHeader("Accept"));
    }

    /**
     * @see AnalyticsApi#getCompanyAddresses
     */
    default ResponseEntity<CompanyAddressStatistics> getCompanyAddresses(UUID companyId) {
        if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{\r\n  \"companyId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\r\n  \"addressStatistics\" : {\r\n    \"warehouse\" : 3,\r\n    \"headquarters\" : 1,\r\n    \"retail\" : 5,\r\n    \"storage\" : 2\r\n  }\r\n}", CompanyAddressStatistics.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default AnalyticsApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * @see AnalyticsApi#getCompanyName
     */
    default ResponseEntity<CompanyName> getCompanyName(UUID companyId) {
        if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{\r\n  \"companyId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\r\n  \"currentName\" : \"Tech Solutions Inc.\"\r\n}", CompanyName.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default AnalyticsApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * @see AnalyticsApi#getCompanyPreviousNames
     */
    default ResponseEntity<CompanyPreviousNames> getCompanyPreviousNames(UUID companyId) {
        if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{\r\n  \"companyId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\r\n  \"previousNames\" : [ \"Tech Innovations LLC\", \"Advanced Tech Systems\" ]\r\n}", CompanyPreviousNames.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default AnalyticsApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * @see AnalyticsApi#getCompanyStatus
     */
    default ResponseEntity<CompanyCurrentStatus> getCompanyStatus(UUID companyId) {
        if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{\r\n  \"companyId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\r\n  \"status\" : \"active\"\r\n}", CompanyCurrentStatus.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default AnalyticsApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
