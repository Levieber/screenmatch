package br.com.levieber.screenmatch.domain.enums;

public enum Language {
    PORTUGUESE("pt"),
    ENGLISH("en");

    public final String languageAcronym;

    Language(String lang) {
        this.languageAcronym = lang;
    }
}
