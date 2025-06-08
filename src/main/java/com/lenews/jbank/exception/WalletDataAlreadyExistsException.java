package com.lenews.jbank.exception;

public class WalletDataAlreadyExistsException extends JBankException {

    public WalletDataAlreadyExistsException(String message) {
        super(message);
    }

    public WalletDataAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}