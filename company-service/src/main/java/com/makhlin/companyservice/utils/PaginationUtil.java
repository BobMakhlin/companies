package com.makhlin.companyservice.utils;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public final class PaginationUtil {
    private PaginationUtil() {
    }

    public static MultiValueMap<String, String> getPaginationResponseHeaders(long totalElementsCount, long totalPages) {
        LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap();
        headers.add("x-total", String.valueOf(totalElementsCount));
        headers.add("x-total-pages", String.valueOf(totalPages));
        return headers;
    }
}