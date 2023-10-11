package com.example.simple_crud.service.validation;

import com.example.simple_crud.dto.CardDto;
import com.example.simple_crud.dto.ErrorDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CardValidation {

    public List<ErrorDto> errors(CardDto dto) {
        List<ErrorDto> list = new ArrayList<>();

        if (StringUtils.isBlank(dto.getCardNumber())) {
            list.add(new ErrorDto("cardNumber", "CardNumber cannot be null or empty"));
        }
        if (StringUtils.isBlank(dto.getCardCode().toString())) {
            list.add(new ErrorDto("cardCode", "cardCode cannot be null or empty"));
        }
        return list;
    }
}
