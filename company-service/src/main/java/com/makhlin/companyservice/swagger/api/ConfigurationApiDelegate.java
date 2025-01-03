package com.makhlin.companyservice.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.makhlin.companyservice.swagger.model.BusinessConfiguration;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Optional;

/**
 * A delegate to be called by the {@link ConfigurationApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */

public interface ConfigurationApiDelegate {

    Logger log = LoggerFactory.getLogger(ConfigurationApi.class);

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
     * @see ConfigurationApi#getConfiguration
     */
    default ResponseEntity<BusinessConfiguration> getConfiguration() {
        if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{\r\n  \"addressCategories\" : [ \"addressCategories\", \"addressCategories\" ],\r\n  \"industries\" : [ \"industries\", \"industries\" ]\r\n}", BusinessConfiguration.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default ConfigurationApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
