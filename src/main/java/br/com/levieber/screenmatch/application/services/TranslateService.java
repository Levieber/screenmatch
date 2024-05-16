package br.com.levieber.screenmatch.application.services;

import br.com.levieber.screenmatch.application.mappers.TranslationResponseMapper;
import br.com.levieber.screenmatch.domain.enums.Language;
import br.com.levieber.screenmatch.infra.api.ApiClient;
import br.com.levieber.screenmatch.infra.mappers.JsonMapper;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class TranslateService {
    private static final JsonMapper jsonMapper = new JsonMapper();
    private static final ApiClient apiClient = new ApiClient();

    public static String fromEnglishToPortuguese(String text) {
        String json = apiClient.get(buildApiUrl(text, Language.ENGLISH, Language.PORTUGUESE));

        var translation = jsonMapper.map(json, TranslationResponseMapper.class);

        translation.responseData.translatedText = URLDecoder.decode(
                translation.responseData.translatedText, StandardCharsets.UTF_8
        );

        return translation.responseData.translatedText;
    }

    private static String buildApiUrl(String textToTranslate, Language lang1, Language lang2) {
        String text = URLEncoder.encode(textToTranslate, StandardCharsets.UTF_8);
        String langPair = URLEncoder.encode(lang1.languageAcronym + "|"+ lang2.languageAcronym, StandardCharsets.UTF_8);

        return "https://api.mymemory.translated.net/get?q=%s&langpair=%s".formatted(text, langPair);
    }
}
