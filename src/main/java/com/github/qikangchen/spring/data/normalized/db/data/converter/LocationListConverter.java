package com.github.qikangchen.spring.data.normalized.db.data.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.qikangchen.spring.data.normalized.db.data.Location;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

@Converter
public class LocationListConverter implements AttributeConverter<List<Location>, String> {

    private final ObjectMapper objectMapper;

    public LocationListConverter(){
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public String convertToDatabaseColumn(List<Location> locationList) {
        try {
            String json = objectMapper.writeValueAsString(locationList);
            return json;
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Cant convert: " + locationList);
        }
    }

    @Override
    public List<Location> convertToEntityAttribute(String s) {
        try {
            return objectMapper.readValue(s,  new TypeReference<List<Location>>(){});
        } catch (JsonProcessingException e) {
            throw  new IllegalStateException("Cant convert: " + s);
        }
    }
}
