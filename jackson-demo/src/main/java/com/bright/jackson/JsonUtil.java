package com.bright.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

public final class JsonUtil {
    private static final ObjectMapper MAPPER = new JsonMapper();

    private JsonUtil() {
    }

    public static <T> T parse(String json, Class<T> valueType) throws JsonProcessingException {
        return MAPPER.readValue(json, valueType);
    }

    public static String stringify(Object value) throws JsonProcessingException {
        return MAPPER.writeValueAsString(value);
    }
}
