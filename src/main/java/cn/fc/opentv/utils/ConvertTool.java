package cn.fc.opentv.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.util.MultiValueMap;

public class ConvertTool {

    public static MultiValueMap<String, String> objToMultiValueMap(ObjectMapper objectMapper, Object obj) {
        return new ObjectToMultiValueMapConverter(objectMapper).convert(obj);
    }
}