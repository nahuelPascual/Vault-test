package com.vault.test.model.exception;

public class ValidationException extends Exception {

    public ValidationException(){
        super();
    }

    public ValidationException(String message){
        super(message);
    }

    public ValidationException(String message, Throwable e){
        super(message,e);
    }
}
