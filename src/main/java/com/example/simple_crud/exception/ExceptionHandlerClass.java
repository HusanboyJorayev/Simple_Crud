package com.example.simple_crud.exception;

import com.example.simple_crud.dto.ErrorDto;
import com.example.simple_crud.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerClass {
    @ExceptionHandler
    public ResponseEntity<ResponseDto<Void>>exception(MethodArgumentNotValidException e){
        return ResponseEntity.badRequest().body(
                ResponseDto.<Void>builder()
                        .code(-3)
                        .message("Validation error")
                        .errors(
                                e.getBindingResult()
                                        .getFieldErrors()
                                        .stream()
                                        .map(fieldError -> {
                                            String field= fieldError.getField();
                                            String message=fieldError.getDefaultMessage();
                                            String rejectionValue=String.valueOf(fieldError.getRejectedValue());

                                            return new ErrorDto(field,String.format("message::%s,rejactionValue::%s",message,rejectionValue));
                                        }).toList()
                        )
                        .build()

        );

    }

}
