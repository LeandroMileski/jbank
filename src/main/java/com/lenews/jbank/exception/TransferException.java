package com.lenews.jbank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TransferException extends JBankException {
    private final String detail;

    public TransferException(String detail) {
        super(detail);
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        //UNPROCESSABLE_ENTITY for business logic errors
        pd.setTitle("Transfer Error");
        pd.setDetail(detail);
        return pd;
    }
}
