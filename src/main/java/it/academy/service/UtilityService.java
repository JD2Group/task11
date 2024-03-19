package it.academy.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class UtilityService {

    private UtilityService() {

    }

    public static Map<String, String> urlencodedParamExtractor(String str) {
        String[] strArr = str.split("&");
        Map<String, String> params = new HashMap<>();
        Arrays.stream(strArr)
                .forEach(s -> {
                    String[] arr = s.split("=");
                    params.put(arr[0], arr[1]);
                });
        return params;
    }
}
