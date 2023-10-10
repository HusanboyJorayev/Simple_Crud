package com.example.simple_crud.dto;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {

    private Integer id;

    private String cardNumber;
    private Integer cardCode;
    private Double balance;

    private Integer userId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
