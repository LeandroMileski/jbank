package com.lenews.jbank.controller.dto;

import com.lenews.jbank.entities.Wallet;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record TransferRequestDto(@NotNull UUID sender,
                                 @NotNull UUID receiver,
                                 @NotNull @DecimalMin("10.00") BigDecimal amountTransferred){
}
