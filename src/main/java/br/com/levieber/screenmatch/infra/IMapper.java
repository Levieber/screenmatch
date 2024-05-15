package br.com.levieber.screenmatch.infra;

public interface IMapper {
    <T> T map(String json, Class<T> toConvert);
}
