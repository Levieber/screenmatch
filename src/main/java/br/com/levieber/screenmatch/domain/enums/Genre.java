package br.com.levieber.screenmatch.domain.enums;

import java.util.NoSuchElementException;

public enum Genre {
    ACAO("Action"),
    ROMANCE("Romance"),
    COMEDIA("Comedy"),
    DRAMA("Drama"),
    CRIME("Crime");
    private final String genreOmdb;

    Genre(String genreOmdb) {
        this.genreOmdb = genreOmdb;
    }

    public static Genre fromString(String text) {
        for (Genre genre : Genre.values()) {
            if (genre.genreOmdb.equalsIgnoreCase(text)) {
                return genre;
            }
        }
        throw new NoSuchElementException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
