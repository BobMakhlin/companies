package com.makhlin.common.utils;


import java.util.Objects;

public final class IfMatchUtil {
    private IfMatchUtil() {
    }

    public static boolean isEqualVersion(String ifMatch, Integer currentVersion) {
        return Objects.equals(Integer.parseInt(ifMatch), currentVersion);
    }
}
