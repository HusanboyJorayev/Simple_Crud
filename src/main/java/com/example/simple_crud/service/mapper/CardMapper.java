package com.example.simple_crud.service.mapper;

import com.example.simple_crud.dto.CardDto;
import com.example.simple_crud.model.Card;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public abstract class CardMapper {
    public abstract CardDto toDto(Card card);

    @Mapping(ignore = true,target ="id")
    @Mapping(ignore = true,target ="createdAt")
    @Mapping(ignore = true,target ="updatedAt")
    @Mapping(ignore = true,target ="deletedAt")
    public abstract Card toEntity(CardDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(CardDto dto,@MappingTarget Card card);

}
