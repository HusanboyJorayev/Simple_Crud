package com.example.simple_crud.service;

import com.example.simple_crud.dto.*;
import com.example.simple_crud.model.User;
import com.example.simple_crud.repository.UserRepository;
import com.example.simple_crud.service.mapper.UserMapper;
import com.example.simple_crud.service.validation.UserValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements SimpleCrud<Integer, UserDto> {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UserValidation userValidation;

    @Override
    public ResponseDto<UserDto> create(UserDto dto) {

        List<ErrorDto> errors = this.userValidation.errors(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<UserDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errors(errors)
                    .build();
        }

        try {
            User user = this.userMapper.toEntity(dto);
            user.setCreatedAt(LocalDateTime.now());
            this.userRepository.save(user);

            return ResponseDto.<UserDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.userMapper.toDto(user))
                    .build();
        }
        catch (Exception e) {
            return ResponseDto.<UserDto>builder()
                    .code(-2)
                    .message("User while saving error")
                    .build();
        }

    }

    @Override
    public ResponseDto<UserDto> get(Integer id) {

        return this.userRepository.findByIdAndDeletedAtIsNull(id)
                .map(user -> ResponseDto.<UserDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.userMapper.toDtoWithCard(user))
                        .build())

                .orElse(ResponseDto.<UserDto>builder()
                        .code(-1)
                        .message("User is not found")
                        .build());
    }

    @Override
    public ResponseDto<UserDto> update(UserDto dto, Integer id) {

        List<ErrorDto> errors = this.userValidation.errors(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<UserDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errors(errors)
                    .build();
        }

        Optional<User> optional = this.userRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<UserDto>builder()
                    .code(-1)
                    .message("user is not found")
                    .build();
        }
        User user = optional.get();
        user.setUpdatedAt(LocalDateTime.now());
        this.userMapper.update(dto, user);
        this.userRepository.save(user);

        return ResponseDto.<UserDto>builder()
                .success(true)
                .message("Ok")
                .data(this.userMapper.toDto(user))
                .build();
    }

    @Override
    public ResponseDto<UserDto> delete(@NonNull Integer id) {

        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            return ResponseDto.<UserDto>builder()
                    .success(false)
                    .code(400)
                    .message("User with this id does not exist")
                    .build();
        }

        userRepository.deleteById(id);

        return ResponseDto.<UserDto>builder()
                .success(true)
                .message("Success")
                .data(userMapper.toDto(userOptional.get()))
                .build();
    }

    public ResponseDto<Page<UserDto>> getByPage(Integer page, Integer size) {

        Page<User> userPage = this.userRepository.findAllByDeletedAtIsNull(PageRequest.of(page, size));
        if (userPage.isEmpty()) {
            return ResponseDto.<Page<UserDto>>builder()
                    .code(-1)
                    .message("Users are not found")
                    .build();
        }
        return ResponseDto.<Page<UserDto>>builder()
                .success(true)
                .message("OK")
                .data(userPage.map(this.userMapper::toDto))
                .build();
    }

    public ResponseDto<List<UserDto>> getAllUsersByQuery() {
        List<User> userList = this.userRepository.getAllUsersByQuery();
        if (userList.isEmpty()) {
            return ResponseDto.<List<UserDto>>builder()
                    .code(-1)
                    .message("Users are not found")
                    .build();
        }
        return ResponseDto.<List<UserDto>>builder()
                .success(true)
                .message("OK")
                .data(userList.stream().map(this.userMapper::toDto).toList())
                .build();
    }
}
