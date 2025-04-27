package com.adamkabbara.httpserver.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

public class Json {
    private static final ObjectMapper myObjectMapper = defaultObjectMapper();

    private static ObjectMapper defaultObjectMapper() {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return om;
    }

    public static JsonNode parse(String jsonString) throws JsonProcessingException {
        return myObjectMapper.readTree(jsonString);
    }

    public static <T> T fromJson(JsonNode jsonNode, Class<T> cls) throws JsonProcessingException {
        return myObjectMapper.treeToValue(jsonNode, cls);
    }

    public static JsonNode toJson(Object obj) {
        return myObjectMapper.valueToTree(obj);
    }

    public static String stringify(JsonNode jsonNode) throws JsonProcessingException {
        return generateJson(jsonNode, false);
    }

    public static String stringifyPretty(JsonNode jsonNode) throws JsonProcessingException {
        return generateJson(jsonNode, true);
    }

    private static String generateJson(Object obj, boolean pretty) throws JsonProcessingException {
        ObjectWriter objectWriter = myObjectMapper.writer();
        if (pretty) {
            objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        }
        return objectWriter.writeValueAsString(obj);
    }
}
