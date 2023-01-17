package com.example.delivery.domain.user.entity;

import com.example.delivery.domain.user.exception.UserInvalidPasswordException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class DeliveryUser {

    @Id
    @GeneratedValue
    public Long id;
    private String userId;
    private String password;
    private String username;


    public DeliveryUser(Long id, String userId, String password, String username) {
        this.id = id;
        this.userId = Objects.requireNonNull(userId);

        validatePassword(password);
        this.password = Objects.requireNonNull(password);
        this.username = Objects.requireNonNull(username);
    }

    void validatePassword(String id){
        // 영어 대문자, 영어 소문자, 숫자, 특수문자 중 3종류 이상 and 12자리 이상
//        String regex = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&-+=()]).{12,}$";
        int i = 0;
        if (id.matches(".*[0-9].*")) i++;
        if (id.matches(".*[a-z].*")) i++;
        if (id.matches(".*[A-Z].*")) i++;
        if (id.matches(".*[@#$%^&-+=()].*")) i++;

        if (i < 3 || id.length() < 12) {
            throw new UserInvalidPasswordException("비밀번호 형식이 잘못 되었습니다.");
        }
    }
}