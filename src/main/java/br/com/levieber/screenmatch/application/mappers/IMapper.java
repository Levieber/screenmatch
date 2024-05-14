package br.com.levieber.screenmatch.application.mappers;

public interface IMapper {
    <T> T map(String json, Class<T> toConvert);
}
