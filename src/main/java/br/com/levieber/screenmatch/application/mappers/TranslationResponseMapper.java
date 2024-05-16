package br.com.levieber.screenmatch.application.mappers;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TranslationResponseMapper {
    @JsonAlias("responseData")
    public ResponseData responseData;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResponseData {
        @JsonAlias("translatedText")
        public String translatedText;
    }
}
