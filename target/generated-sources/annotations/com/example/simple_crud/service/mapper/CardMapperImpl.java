package com.example.simple_crud.service.mapper;

import com.example.simple_crud.dto.CardDto;
import com.example.simple_crud.model.Card;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-14T22:20:58+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class CardMapperImpl extends CardMapper {

    @Override
    public CardDto toDto(Card card) {
        if ( card == null ) {
            return null;
        }

        CardDto.CardDtoBuilder cardDto = CardDto.builder();

        cardDto.id( card.getId() );
        cardDto.cardNumber( card.getCardNumber() );
        cardDto.cardCode( card.getCardCode() );
        cardDto.balance( card.getBalance() );
        cardDto.userId( card.getUserId() );
        cardDto.createdAt( card.getCreatedAt() );
        cardDto.updatedAt( card.getUpdatedAt() );
        cardDto.deletedAt( card.getDeletedAt() );

        return cardDto.build();
    }

    @Override
    public Card toEntity(CardDto dto) {
        if ( dto == null ) {
            return null;
        }

        Card.CardBuilder card = Card.builder();

        card.cardNumber( dto.getCardNumber() );
        card.cardCode( dto.getCardCode() );
        card.balance( dto.getBalance() );
        card.userId( dto.getUserId() );

        return card.build();
    }

    @Override
    public void update(CardDto dto, Card card) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            card.setId( dto.getId() );
        }
        if ( dto.getCardNumber() != null ) {
            card.setCardNumber( dto.getCardNumber() );
        }
        if ( dto.getCardCode() != null ) {
            card.setCardCode( dto.getCardCode() );
        }
        if ( dto.getBalance() != null ) {
            card.setBalance( dto.getBalance() );
        }
        if ( dto.getUserId() != null ) {
            card.setUserId( dto.getUserId() );
        }
        if ( dto.getCreatedAt() != null ) {
            card.setCreatedAt( dto.getCreatedAt() );
        }
        if ( dto.getUpdatedAt() != null ) {
            card.setUpdatedAt( dto.getUpdatedAt() );
        }
        if ( dto.getDeletedAt() != null ) {
            card.setDeletedAt( dto.getDeletedAt() );
        }
    }
}
