package com.vault.test.model.exception;

public class DaoException extends Exception {

    public DaoException(){
        super();
    }

    public DaoException(String message){
        super(message);
    }

    public DaoException(String message, Throwable e){
        super(message,e);
    }
}
