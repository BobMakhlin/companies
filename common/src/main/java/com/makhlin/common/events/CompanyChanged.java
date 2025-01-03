package com.makhlin.common.events;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record CompanyChanged(UUID companyId, String name, String status, Instant modifiedAt, List<Address> addresses) {
    public record Address(String category, String country, String city, String zip, String street) {
    }
}
