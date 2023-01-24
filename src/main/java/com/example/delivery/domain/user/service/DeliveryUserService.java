package com.example.delivery.domain.user.service;

import com.example.delivery.domain.user.dto.DeliveryUserDto;
import com.example.delivery.domain.user.entity.Authority;
import com.example.delivery.domain.user.entity.DeliveryUser;
import com.example.delivery.domain.user.repository.AuthorityRepository;
import com.example.delivery.domain.user.repository.DeliveryUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@RequiredArgsConstructor
@Service
public class DeliveryUserService implements UserDetailsService {

    private final DeliveryUserRepository deliveryUserRepository;
    private final AuthorityRepository authorityRepository;

    public DeliveryUser signUp(DeliveryUserDto deliveryUserDto) {
        DeliveryUser deliveryUser = DeliveryUser.builder()
                                                .userCustomId(deliveryUserDto.getUserId())
                                                .password(deliveryUserDto.getPassword())
                                                .authorities(new HashSet<>())
                                                .name(deliveryUserDto.getName())
                                                .enabled(true)
                                                .build();

        deliveryUserRepository.save(deliveryUser);
        authorityRepository.save(Authority.builder().authority("USER_ROLE").userId(deliveryUser.getId()).build());

        return deliveryUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return deliveryUserRepository.findByUserCustomId(username).orElseThrow(() -> new UsernameNotFoundException("해당하는 유저 정보가 없습니다."));
    }
}
