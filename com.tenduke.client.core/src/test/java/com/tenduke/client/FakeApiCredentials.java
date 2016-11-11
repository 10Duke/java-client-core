package com.tenduke.client;

import java.util.HashMap;
import java.util.Map;

class FakeApiCredentials implements ApiCredentials {
    
    private final Map<String, String> _headers;

    public FakeApiCredentials(boolean headers) {
        //
        if (headers) {
            _headers = new HashMap<>();
            _headers.put("AUTHORIZATION", "Fake zombie/undead");
        } else {
            _headers = null;
        }
    } //

    @Override
    public Map<String, String> getHeaders() {
        return _headers;
    }
    
}
