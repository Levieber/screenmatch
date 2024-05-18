package br.com.levieber.screenmatch.domain.enums;

import java.util.NoSuchElementException;

public enum Genre {
    ACAO("Action", "Ação"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Comédia"),
    DRAMA("Drama", "Drama"),
    CRIME("Crime", "Crime");

    private final String genreOmdb;
    private final String portugueseGenre;

    Genre(String genreOmdb, String portugueseGenre) {
        this.genreOmdb = genreOmdb;
        this.portugueseGenre = portugueseGenre;
    }

    public static Genre fromString(String text) {
        for (Genre genre : Genre.values()) {
            if (genre.genreOmdb.equalsIgnoreCase(text)) {
                return genre;
            }
        }
        throw new NoSuchElementException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

    public static Genre fromPortugueseString(String text) {
        for (Genre genre : Genre.values()) {
            if (genre.portugueseGenre.equalsIgnoreCase(text)) {
                return genre;
            }
        }
        throw new NoSuchElementException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
