package com.example.delivery.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Entity
@IdClass(Authority.class)
public class Authority implements GrantedAuthority {

    @Id
    @Column(name = "user_id")
    private String userId;
    @Id
    private String authority;

}
