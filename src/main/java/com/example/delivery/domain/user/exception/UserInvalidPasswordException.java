package com.example.delivery.domain.user.exception;

public class UserInvalidPasswordException extends RuntimeException{

    public UserInvalidPasswordException() {

    }

    public UserInvalidPasswordException(String message) {
        super(message);
    }
}
