package com.makhlin.companyservice.swagger.api;

import org.springframework.web.bind.annotation.RestController;


@RestController
public class CompaniesApiController implements CompaniesApi {

    private final CompaniesApiDelegate delegate;

    @org.springframework.beans.factory.annotation.Autowired
    public CompaniesApiController(CompaniesApiDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public CompaniesApiDelegate getDelegate() {
        return delegate;
    }
}
