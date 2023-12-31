package com.example.simple_crud.service;

import com.example.simple_crud.dto.CardDto;
import com.example.simple_crud.dto.ErrorDto;
import com.example.simple_crud.dto.ResponseDto;
import com.example.simple_crud.dto.SimpleCrud;
import com.example.simple_crud.model.Card;
import com.example.simple_crud.repository.CardRepository;
import com.example.simple_crud.service.mapper.CardMapper;
import com.example.simple_crud.service.validation.CardValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService implements SimpleCrud<Integer, CardDto> {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final CardValidation cardValidation;

    @Override
    public ResponseDto<CardDto> create(CardDto dto) {

        List<ErrorDto>errors=this.cardValidation.errors(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<CardDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errors(errors)
                    .build();
        }

        Card card=this.cardMapper.toEntity(dto);
        card.setCreatedAt(LocalDateTime.now());
        this.cardRepository.save(card);
        return ResponseDto.<CardDto>builder()
                .success(true)
                .message("Ok")
                .data(this.cardMapper.toDto(card))
                .build();
    }

    @Override
    public ResponseDto<CardDto> get(Integer id) {

        Optional<Card>optional=this.cardRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()){
            return ResponseDto.<CardDto>builder()
                    .code(-1)
                    .message("Card is not found")
                    .build();
        }
        Card card=optional.get();
        return ResponseDto.<CardDto>builder()
                .success(true)
                .message("Ok")
                .data(this.cardMapper.toDto(card))
                .build();
    }

    @Override
    public ResponseDto<CardDto> update(@NonNull CardDto dto, @NonNull Integer id) {

        List<ErrorDto>errors=this.cardValidation.errors(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<CardDto>builder()
                    .code(400)
                    .message("Validation error")
                    .errors(errors)
                    .build();
        }

        Optional<Card> cardOptional = cardRepository.findByIdAndDeletedAtIsNull(id);

        if(cardOptional.isEmpty()) {
            return ResponseDto.<CardDto>builder()
                    .success(false)
                    .code(400)
                    .message("Card does not exist")
                    .build();
        }

        Card cardToUpdate = cardOptional.get();

        cardMapper.update(dto, cardToUpdate);
        cardRepository.save(cardToUpdate);

        return ResponseDto.<CardDto>builder()
                .success(true)
                .code(200)
                .message("Success")
                .build();
    }

    @Override
    public ResponseDto<CardDto> delete(Integer id) {

        Optional<Card> cardOptional = cardRepository.findByIdAndDeletedAtIsNull(id);

        if(cardOptional.isEmpty()) {
            return ResponseDto.<CardDto>builder()
                    .success(false)
                    .code(400)
                    .message("Card does not exist")
                    .build();
        }

        cardRepository.deleteById(id);

        return ResponseDto.<CardDto>builder()
                .success(true)
                .code(200)
                .message("Success")
                .build();
    }
}
