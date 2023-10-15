package com.example.simple_crud.service.mapper;

import com.example.simple_crud.dto.CardDto;
import com.example.simple_crud.dto.UserDto;
import com.example.simple_crud.model.Card;
import com.example.simple_crud.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-14T22:20:59+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.name( user.getName() );
        userDto.surname( user.getSurname() );
        userDto.email( user.getEmail() );
        userDto.password( user.getPassword() );
        userDto.createdAt( user.getCreatedAt() );
        userDto.updatedAt( user.getUpdatedAt() );
        userDto.deletedAt( user.getDeletedAt() );

        return userDto.build();
    }

    @Override
    public UserDto toDtoWithCard(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.name( user.getName() );
        userDto.surname( user.getSurname() );
        userDto.email( user.getEmail() );
        userDto.password( user.getPassword() );
        userDto.createdAt( user.getCreatedAt() );
        userDto.updatedAt( user.getUpdatedAt() );
        userDto.deletedAt( user.getDeletedAt() );

        userDto.cards( user.getCards().stream().map(this.cardMapper::toDto).toList() );

        return userDto.build();
    }

    @Override
    public User toEntity(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.name( dto.getName() );
        user.surname( dto.getSurname() );
        user.email( dto.getEmail() );
        user.password( dto.getPassword() );
        user.cards( cardDtoListToCardList( dto.getCards() ) );

        return user.build();
    }

    @Override
    public void update(UserDto dto, User user) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            user.setId( dto.getId() );
        }
        if ( dto.getName() != null ) {
            user.setName( dto.getName() );
        }
        if ( dto.getSurname() != null ) {
            user.setSurname( dto.getSurname() );
        }
        if ( dto.getEmail() != null ) {
            user.setEmail( dto.getEmail() );
        }
        if ( dto.getPassword() != null ) {
            user.setPassword( dto.getPassword() );
        }
        if ( dto.getCreatedAt() != null ) {
            user.setCreatedAt( dto.getCreatedAt() );
        }
        if ( dto.getUpdatedAt() != null ) {
            user.setUpdatedAt( dto.getUpdatedAt() );
        }
        if ( dto.getDeletedAt() != null ) {
            user.setDeletedAt( dto.getDeletedAt() );
        }
    }

    protected Card cardDtoToCard(CardDto cardDto) {
        if ( cardDto == null ) {
            return null;
        }

        Card.CardBuilder card = Card.builder();

        card.id( cardDto.getId() );
        card.cardNumber( cardDto.getCardNumber() );
        card.cardCode( cardDto.getCardCode() );
        card.balance( cardDto.getBalance() );
        card.userId( cardDto.getUserId() );
        card.createdAt( cardDto.getCreatedAt() );
        card.updatedAt( cardDto.getUpdatedAt() );
        card.deletedAt( cardDto.getDeletedAt() );

        return card.build();
    }

    protected List<Card> cardDtoListToCardList(List<CardDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Card> list1 = new ArrayList<Card>( list.size() );
        for ( CardDto cardDto : list ) {
            list1.add( cardDtoToCard( cardDto ) );
        }

        return list1;
    }
}
