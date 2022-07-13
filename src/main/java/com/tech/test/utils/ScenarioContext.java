package com.tech.test.utils;

import static com.tech.test.utils.TestConstants.HTTP_RESPONSE;

import io.restassured.response.Response;
import lombok.Data;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class ScenarioContext {

    private Map<String, Object> params = new HashMap<>();

    public Object getParam(String key) {
        return params.get(key);
    }

    public void addParam(String key, Object object) {
        params.put(key, object);
    }

    public Response getResponse() {
        return (Response) (getParam(HTTP_RESPONSE));
    }

    public void refreshContext() {
        params.clear();
    }
}
