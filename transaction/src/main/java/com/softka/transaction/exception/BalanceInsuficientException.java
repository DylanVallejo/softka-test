package com.softka.transaction.exception;

public class BalanceInsuficientException extends Exception {

    public BalanceInsuficientException(String message){
        super(message);
    }

}
