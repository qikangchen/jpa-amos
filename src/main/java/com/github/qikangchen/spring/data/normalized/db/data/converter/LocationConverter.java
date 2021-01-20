package com.github.qikangchen.spring.data.normalized.db.data.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.qikangchen.spring.data.normalized.db.data.Location;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class LocationConverter implements AttributeConverter<Location, String> {

    private final ObjectMapper objectMapper;

    public LocationConverter(){
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public String convertToDatabaseColumn(Location location) {
        try {
            String json = objectMapper.writeValueAsString(location);
            return json;
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Cant convert: " + location);
        }
    }

    @Override
    public Location convertToEntityAttribute(String s) {
        try {
            return objectMapper.readValue(s, Location.class);
        } catch (JsonProcessingException e) {
            throw  new IllegalStateException("Cant convert: " + s);
        }
    }
}
