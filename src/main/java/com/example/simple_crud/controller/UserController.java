package com.example.simple_crud.controller;

import com.example.simple_crud.dto.ResponseDto;
import com.example.simple_crud.dto.SimpleCrud;
import com.example.simple_crud.dto.UserDto;
import com.example.simple_crud.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController implements SimpleCrud<Integer, UserDto> {

    private final UserService userService;

    @Override
    @PostMapping("/create")
    public ResponseDto<UserDto> create(@RequestBody @Valid UserDto dto) {
        return this.userService.create(dto);
    }

    @Override
    @GetMapping("/get")
    public ResponseDto<UserDto> get(@RequestParam Integer id) {
        return this.userService.get(id);
    }

    @Override
    @PutMapping("/update")
    public ResponseDto<UserDto> update(@RequestBody @Valid UserDto dto, @RequestParam Integer id) {
        return this.userService.update(dto, id);
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseDto<UserDto> delete(@RequestParam Integer id) {
        return this.userService.delete(id);
    }

    @GetMapping("/getPage")
    public ResponseDto<Page<UserDto>> getByPage(@RequestParam Integer page, @RequestParam Integer size) {
        return this.userService.getByPage(page, size);
    }
    @GetMapping("/getAllUsersByQuery")
    public ResponseDto<List<UserDto>>getAllUsersByQuery(){
        return this.userService.getAllUsersByQuery();
    }
}
