package cn.fc.opentv.utils;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

public class ObjectToMultiValueMapConverter implements Converter<Object, MultiValueMap<String, String>> {
    private final ObjectMapper objectMapper;

    public ObjectToMultiValueMapConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public MultiValueMap<String, String> convert(Object source) {
        Map<String, String> map = objectMapper.convertValue(source, new TypeReference<Map<String, String>>() { });
        MultiValueMap<String, String> multiValueMapAdapter = new MultiValueMapAdapter<>(new HashMap<>());
        multiValueMapAdapter.setAll(map);
        return multiValueMapAdapter;
    }
}