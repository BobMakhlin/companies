package com.makhlin.companyservice.swagger.api;

import org.springframework.web.bind.annotation.RestController;


@RestController
public class ConfigurationApiController implements ConfigurationApi {

    private final ConfigurationApiDelegate delegate;

    @org.springframework.beans.factory.annotation.Autowired
    public ConfigurationApiController(ConfigurationApiDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public ConfigurationApiDelegate getDelegate() {
        return delegate;
    }
}
