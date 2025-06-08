package com.lenews.jbank.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;

public class DepositException extends JBankException {
    private final String detail;

    public DepositException(String detail) {
        super("Deposit Error");
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(422), detail);
        problemDetail.setTitle("Deposit Error");

        return problemDetail;
    }
}
