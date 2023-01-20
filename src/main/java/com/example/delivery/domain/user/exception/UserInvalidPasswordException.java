package com.example.delivery.domain.user.exception;

public class UserInvalidPasswordException extends RuntimeException{

    public UserInvalidPasswordException() {
        super("비밀번호 형식이 잘못 되었습니다. 영어 대문자, 영어 소문자, 숫자, 특수문자 중 3종류 이상이고 12자리 이상이어야 합니다.");
    }

    public UserInvalidPasswordException(String message) {
        super(message);
    }
}
