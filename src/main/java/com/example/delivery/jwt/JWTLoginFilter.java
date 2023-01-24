package com.example.delivery.jwt;

import com.example.delivery.controller.response.DeliveryUserResponse;
import com.example.delivery.domain.user.entity.DeliveryUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();
    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            DeliveryUserLoginForm deliveryUserLoginForm = objectMapper.readValue(request.getInputStream(), DeliveryUserLoginForm.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    deliveryUserLoginForm.getUsername(), deliveryUserLoginForm.getPassword(), null
            );
            return getAuthenticationManager().authenticate(token);
        } catch (IOException e) {
            throw new RuntimeException("입력된 유저 정보를 읽는데 실패하였습니다.");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        DeliveryUser deliveryUser = (DeliveryUser) authResult.getPrincipal();
        DeliveryUserResponse deliveryUserResponse = DeliveryUserResponse.entityToUserResponse(deliveryUser);
        response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + JWTUtil.makeAuthToken(deliveryUser));
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.getOutputStream().write(objectMapper.writeValueAsBytes(deliveryUserResponse));

//        super.successfulAuthentication(request, response, chain, authResult);
    }
}
