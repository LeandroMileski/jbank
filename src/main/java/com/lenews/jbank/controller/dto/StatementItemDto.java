package com.lenews.jbank.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record StatementItemDto(String transactionId,
                               String type,
                               String literal,
                               BigDecimal value,
                               LocalDateTime dateTime,
                               StatementOperation operation
                               ) {
}
