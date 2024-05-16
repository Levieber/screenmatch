package br.com.levieber.screenmatch.infra.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapper implements IMapper {
    private final ObjectMapper mapper = new ObjectMapper();

    public <T> T map(String json, Class<T> toConvert) {
        try {
            return mapper.readValue(json, toConvert);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
