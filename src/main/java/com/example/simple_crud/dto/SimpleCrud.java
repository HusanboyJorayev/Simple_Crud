package com.example.simple_crud.dto;

public interface SimpleCrud <K,V>{
    ResponseDto<V>create(V dto);
    ResponseDto<V>get(K id);
    ResponseDto<V>update(V dto,K id);
    ResponseDto<V>delete(K id);
}
