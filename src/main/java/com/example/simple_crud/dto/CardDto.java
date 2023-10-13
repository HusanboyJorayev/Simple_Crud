package com.example.simple_crud.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardDto {

    @NotNull(message = "card id cannot be null")
    private Integer id;

    @NotBlank(message = "cardNumber cannot be null and empty")
    private String cardNumber;
    @NotNull(message = "cardCode cannot be null")
    private Integer cardCode;
    @NotNull(message = "card balance cannot be null")
    private Double balance;

    @NotNull(message = "user id cannot be null")
    private Integer userId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
