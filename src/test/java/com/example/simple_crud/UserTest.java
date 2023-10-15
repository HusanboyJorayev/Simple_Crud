package com.example.simple_crud;

import com.example.simple_crud.dto.ErrorDto;
import com.example.simple_crud.dto.ResponseDto;
import com.example.simple_crud.dto.UserDto;
import com.example.simple_crud.model.User;
import com.example.simple_crud.repository.UserRepository;
import com.example.simple_crud.service.UserService;
import com.example.simple_crud.service.mapper.UserMapper;
import com.example.simple_crud.service.validation.UserValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserTest {

    private UserRepository userRepository;
    private UserValidation userValidation;
    private UserMapper userMapper;
    private UserService userService;

    @BeforeEach
    void initObject() {
        this.userMapper = Mockito.mock(UserMapper.class);
        this.userRepository = Mockito.mock(UserRepository.class);
        this.userValidation = Mockito.mock(UserValidation.class);
        this.userService = new UserService(userMapper, userRepository, userValidation);
    }

    @Test
    void createValidation() {
        when(this.userValidation.errors(any()))
                .thenReturn(List.of(new ErrorDto("field", "Valiadtion error")));

        ResponseDto<UserDto> response = this.userService.create(any());

        Assertions.assertEquals(response.getCode(), -3);
        Assertions.assertFalse(response.isSuccess());
        Assertions.assertNull(response.getData());
        Assertions.assertEquals(response.getMessage(), "Validation error");

        verify(this.userValidation, times(1)).errors(any());
    }
    @Test
    void createPositive(){
        User user= User.builder()
                .id(1)
                .email("wer@gmail.com")
                .password("1234")
                .surname("alimovo")
                .name("Asadbek")
                .build();


        UserDto dto= UserDto.builder()
                .id(1)
                .email("wer@gmail.com")
                .password("1234")
                .surname("alimovo")
                .name("Asadbek")
                .build();

        when(this.userMapper.toEntity(any()))
                .thenReturn(user);

        when(this.userMapper.toDto(any()))
                .thenReturn(dto);


        ResponseDto<UserDto>response=this.userService.create(any());
        Assertions.assertEquals(response.getCode(),0);
        Assertions.assertNotNull(response.getData());
        Assertions.assertTrue(response.isSuccess());

        verify(this.userMapper,times(1)).toEntity(any());
        verify(this.userMapper,times(1)).toDto(any());
        verify(this.userRepository,times(1)).save(any());
    }


}
