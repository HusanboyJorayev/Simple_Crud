package com.example.simple_crud.service.mapper;

import com.example.simple_crud.dto.UserDto;
import com.example.simple_crud.model.User;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring")
public abstract   class UserMapper {

    @Autowired
    protected CardMapper cardMapper;

    @Mapping(ignore = true,target = "cards")
    public abstract UserDto toDto(User user);

    @Mapping(target = "cards",expression = "java(user.getCards().stream().map(this.cardMapper::toDto).toList())")
    public abstract UserDto toDtoWithCard(User user);

    @Mapping(ignore = true,target ="id")
    @Mapping(ignore = true,target ="createdAt")
    @Mapping(ignore = true,target ="updatedAt")
    @Mapping(ignore = true,target ="deletedAt")
    public abstract User toEntity(UserDto dto);

    @Mapping(ignore = true,target = "cards")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(UserDto dto,@MappingTarget User user);

    public void view(){
        User user=new User();
        UserDto dto=new UserDto();
        dto.setCards(user.getCards().stream().map(this.cardMapper::toDto).toList());
    }


}
