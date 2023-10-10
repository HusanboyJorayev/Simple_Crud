package com.example.simple_crud.service;

import com.example.simple_crud.dto.ResponseDto;
import com.example.simple_crud.dto.SimpleCrud;
import com.example.simple_crud.dto.UserDto;
import com.example.simple_crud.model.User;
import com.example.simple_crud.repository.UserRepository;
import com.example.simple_crud.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements SimpleCrud<Integer, UserDto> {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public ResponseDto<UserDto> create(UserDto dto) {
        User user = this.userMapper.toEntity(dto);
        user.setCreatedAt(LocalDateTime.now());
        this.userRepository.save(user);

        return ResponseDto.<UserDto>builder()
                .success(true)
                .message("Ok")
                .data(this.userMapper.toDto(user))
                .build();
    }

    @Override
    public ResponseDto<UserDto> get(Integer id) {

        Optional<User>optional=this.userRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()){
            return ResponseDto.<UserDto>builder()
                    .code(-1)
                    .message("User is not found")
                    .build();
        }
        User user=optional.get();

        return ResponseDto.<UserDto>builder()
                .success(true)
                .message("Ok")
                .data(this.userMapper.toDtoWithCard(user))
                .build();
    }

    @Override
    public ResponseDto<UserDto> update(UserDto dto, Integer id) {

        Optional<User>optional=this.userRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()){
            return ResponseDto.<UserDto>builder()
                    .code(-1)
                    .message("user is not found")
                    .build();
        }
        User user=optional.get();
        user.setUpdatedAt(LocalDateTime.now());
        this.userMapper.update(dto,user);
        this.userRepository.save(user);

        return ResponseDto.<UserDto>builder()
                .success(true)
                .message("Ok")
                .data(this.userMapper.toDto(user))
                .build();
    }

    @Override
    public ResponseDto<UserDto> delete(Integer id) {
        return null;
    }
}
