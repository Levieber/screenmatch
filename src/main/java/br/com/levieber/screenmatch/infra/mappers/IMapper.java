package br.com.levieber.screenmatch.infra.mappers;

public interface IMapper {
    <T> T map(String json, Class<T> toConvert);
}
