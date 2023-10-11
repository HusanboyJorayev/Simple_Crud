package com.example.simple_crud.service.validation;

import com.example.simple_crud.dto.CardDto;
import com.example.simple_crud.dto.ErrorDto;
import com.example.simple_crud.dto.UserDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class UserValidation {
    public List<ErrorDto> errors(UserDto dto) {
        List<ErrorDto> list = new ArrayList<>();

        if (StringUtils.isBlank(dto.getEmail())) {
            list.add(new ErrorDto("email", "email cannot be null or empty"));
        }
        if (StringUtils.isBlank(dto.getPassword())) {
            list.add(new ErrorDto("password", "password cannot be null or empty"));
        }
        return list;
    }
}
