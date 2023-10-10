package com.example.simple_crud.controller;

import com.example.simple_crud.dto.CardDto;
import com.example.simple_crud.dto.ResponseDto;
import com.example.simple_crud.dto.SimpleCrud;
import com.example.simple_crud.service.CardService;
import com.example.simple_crud.service.mapper.CardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("card")
@RequiredArgsConstructor
public class CardController implements SimpleCrud<Integer, CardDto> {

    private final CardService cardService;


    @Override
    @PostMapping("/create")
    public ResponseDto<CardDto> create(@RequestBody CardDto dto) {
        return this.cardService.create(dto);
    }

    @Override
    @GetMapping("/get")
    public ResponseDto<CardDto> get(@RequestParam Integer id) {
        return this.cardService.get(id);
    }

    @Override
    @PutMapping("/update")
    public ResponseDto<CardDto> update(@RequestBody CardDto dto, @RequestParam Integer id) {
        return this.cardService.update(dto, id);
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseDto<CardDto> delete(@RequestParam Integer id) {
        return this.cardService.delete(id);
    }
}
