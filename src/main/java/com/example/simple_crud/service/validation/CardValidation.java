package com.example.simple_crud.service.validation;

import com.example.simple_crud.dto.CardDto;
import com.example.simple_crud.dto.ErrorDto;
import com.example.simple_crud.model.User;
import com.example.simple_crud.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CardValidation {

    @Autowired
    private  UserRepository userRepository;

    public List<ErrorDto> errors(CardDto dto) {
        List<ErrorDto> list = new ArrayList<>();

        Optional<User> users=this.userRepository.findByIdAndDeletedAtIsNull(dto.getUserId());
        if (users.isEmpty()) {
                list.add(new ErrorDto("userId", "you cannot create card"));
        }

        if (StringUtils.isBlank(dto.getCardNumber())) {
            list.add(new ErrorDto("cardNumber", "CardNumber cannot be null or empty"));
        }
        if (StringUtils.isBlank(dto.getCardCode().toString())) {
            list.add(new ErrorDto("cardCode", "cardCode cannot be null or empty"));
        }
        return list;
    }
}
