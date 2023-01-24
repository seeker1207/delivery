package com.example.delivery.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.delivery.domain.user.entity.DeliveryUser;

import java.time.Instant;

public class JWTUtil {

    private static final Algorithm ALGORITHM = Algorithm.HMAC256("jihoon");
    private static final long AUTH_TIME = 20 * 60;
    private static final long REFRESH_TIME = 60 * 60 * 24 * 7;

    public static String makeAuthToken(DeliveryUser deliveryUser){
        return JWT.create()
                .withSubject(deliveryUser.getUsername())
                .withClaim("exp", Instant.now().getEpochSecond() + AUTH_TIME)
                .sign(ALGORITHM);
    }

    public static String makeRefreshToken(DeliveryUser deliveryUser){
        return JWT.create()
                .withSubject(deliveryUser.getUsername())
                .withClaim("exp", Instant.now().getEpochSecond() + REFRESH_TIME)
                .sign(ALGORITHM);
    }

    public static JWTResult verify(String token) {
        try {
            DecodedJWT verifiedJWT = JWT.require(ALGORITHM).build().verify(token);
            return JWTResult.builder().success(true).username(verifiedJWT.getSubject()).build();
        }catch (Exception ex) {
            DecodedJWT decodedJWT = JWT.decode(token);
            return JWTResult.builder().success(false).username(decodedJWT.getSubject()).build();
        }
    }
}
