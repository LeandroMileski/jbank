package com.lenews.jbank.exception;

import com.lenews.jbank.exception.dto.InvalidParamDto;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JBankException.class)
    public ProblemDetail handleJBankException(JBankException e) {
        return e.toProblemDetail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
            var invalidParams = e.getFieldErrors()
                    .stream()
                    .map(fe -> new InvalidParamDto(fe.getField(), fe.getDefaultMessage()))
                    .toList();

            var pd = ProblemDetail.forStatus(400);

            pd.setTitle("Invalid parameters");
            pd.setDetail("The request contains invalid parameters.");
            pd.setProperty("invalidParams", invalidParams);


        return pd;
    }

}
