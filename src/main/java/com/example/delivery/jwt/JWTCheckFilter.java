package com.example.delivery.jwt;

import com.example.delivery.domain.user.service.DeliveryUserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTCheckFilter extends BasicAuthenticationFilter {
    public JWTCheckFilter(AuthenticationManager authenticationManager, DeliveryUserService deliveryUserService) {
        super(authenticationManager);
    }
}
