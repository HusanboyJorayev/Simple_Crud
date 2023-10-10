package com.example.simple_crud.service;

import com.example.simple_crud.dto.CardDto;
import com.example.simple_crud.dto.ResponseDto;
import com.example.simple_crud.dto.SimpleCrud;
import com.example.simple_crud.model.Card;
import com.example.simple_crud.repository.CardRepository;
import com.example.simple_crud.service.mapper.CardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService implements SimpleCrud<Integer, CardDto> {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    @Override
    public ResponseDto<CardDto> create(CardDto dto) {

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
    public ResponseDto<CardDto> update(CardDto dto, Integer id) {
        return null;
    }

    @Override
    public ResponseDto<CardDto> delete(Integer id) {
        return null;
    }
}
