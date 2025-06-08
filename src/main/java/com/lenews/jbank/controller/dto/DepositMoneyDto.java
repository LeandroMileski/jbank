package com.lenews.jbank.controller.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record DepositMoneyDto(@NotNull @DecimalMin("10.00") BigDecimal amountDeposited) {
}
