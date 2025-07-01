package com.lenews.jbank.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface StatementView {
    String getStatementId();
    String getType();
    BigDecimal getStatementValue();
    String getReceiverWallet();
    String getSenderWallet();
    LocalDateTime getDateTime();

}
