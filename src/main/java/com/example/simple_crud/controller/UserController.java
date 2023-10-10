package com.example.simple_crud.controller;

import com.example.simple_crud.dto.ResponseDto;
import com.example.simple_crud.dto.SimpleCrud;
import com.example.simple_crud.dto.UserDto;
import com.example.simple_crud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController implements SimpleCrud<Integer, UserDto> {

    private final UserService userService;

    @Override
    @PostMapping("/create")
    public ResponseDto<UserDto> create(@RequestBody UserDto dto) {
        return this.userService.create(dto);
    }

    @Override
    @GetMapping("/get")
    public ResponseDto<UserDto> get(@RequestParam Integer id) {
        return this.userService.get(id);
    }

    @Override
    @PutMapping("/update")
    public ResponseDto<UserDto> update(@RequestBody UserDto dto, @RequestParam Integer id) {
        return this.userService.update(dto, id);
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseDto<UserDto> delete(@RequestParam Integer id) {
        return this.userService.delete(id);
    }
}
