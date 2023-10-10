package com.example.simple_crud.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<T> {

    private  int code;
    private String message;
    private boolean success;

    private T data;

}
