package com.makhlin.analyticsservice.swagger.api;

import org.springframework.web.bind.annotation.RestController;


@RestController
public class AnalyticsApiController implements AnalyticsApi {

    private final AnalyticsApiDelegate delegate;

    @org.springframework.beans.factory.annotation.Autowired
    public AnalyticsApiController(AnalyticsApiDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public AnalyticsApiDelegate getDelegate() {
        return delegate;
    }
}
