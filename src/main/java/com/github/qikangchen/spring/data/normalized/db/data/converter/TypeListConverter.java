package com.github.qikangchen.spring.data.normalized.db.data.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.qikangchen.spring.data.normalized.db.data.Incident;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

@Converter
public class TypeListConverter implements AttributeConverter<List<Incident.Types>, String> {

    private final ObjectMapper objectMapper;

    public TypeListConverter(){
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public String convertToDatabaseColumn(List<Incident.Types> locationList) {
        try {
            String json = objectMapper.writeValueAsString(locationList);
            return json;
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Cant convert: " + locationList);
        }
    }

    @Override
    public List<Incident.Types> convertToEntityAttribute(String s) {
        try {
            return objectMapper.readValue(s,  new TypeReference<List<Incident.Types>>(){});
        } catch (JsonProcessingException e) {
            throw  new IllegalStateException("Cant convert: " + s);
        }
    }
}
