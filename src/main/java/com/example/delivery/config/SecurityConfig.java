package com.example.delivery.config;

import com.example.delivery.domain.user.service.DeliveryUserService;
import com.example.delivery.jwt.JWTCheckFilter;
import com.example.delivery.jwt.JWTLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Autowired
    DeliveryUserService deliveryUserService;

    private static final String[] PERMIT_URL_ARRAY = {
            /* swagger v3 */
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        JWTLoginFilter loginFilter = new JWTLoginFilter(authenticationManager);
        JWTCheckFilter checkFilter = new JWTCheckFilter(authenticationManager, deliveryUserService);

        http
                .csrf().disable()
                .sessionManagement(
                        httpSecuritySessionManagementConfigurer ->
                                httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(checkFilter, BasicAuthenticationFilter.class)

                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(PERMIT_URL_ARRAY).permitAll()
                        .requestMatchers("/api/user").permitAll()
                .anyRequest().authenticated()
        );
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }
}
