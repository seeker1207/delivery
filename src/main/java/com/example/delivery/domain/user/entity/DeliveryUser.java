package com.example.delivery.domain.user.entity;

import com.example.delivery.domain.deliveryorder.entity.DeliveryOrder;
import com.example.delivery.domain.user.exception.UserInvalidPasswordException;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

//@ToString
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class DeliveryUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToMany(mappedBy = "deliveryUser")
    private List<DeliveryOrder> deliveryOrderList = new ArrayList<>();
    @NonNull
    private String userId;
    @NonNull
    private String password;
    @NonNull
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "user_id"))
    private Set<Authority> authorities;
    private boolean enabled;

    public DeliveryUser(Long id, List<DeliveryOrder> deliveryOrderList, String userId, String password, String name, Set<Authority> authorities, boolean enabled) {
        this.id = id;
        this.userId = userId;
        if (deliveryOrderList != null && deliveryOrderList.size() > 0) {
            this.deliveryOrderList.addAll(deliveryOrderList);
        }

        validatePassword(password);

        this.password = password;
        this.name = name;
        this.authorities = authorities;
        this.enabled = enabled;
    }

    void validatePassword(String id){
        // 영어 대문자, 영어 소문자, 숫자, 특수문자 중 3종류 이상 and 12자리 이상
//        String regex = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&-+=()]).{12,}$";
        var i = 0;
        if (id.matches(".*[0-9].*")) i++;
        if (id.matches(".*[a-z].*")) i++;
        if (id.matches(".*[A-Z].*")) i++;
        if (id.matches(".*[@#$%^&-+=()].*")) i++;

        if (i < 3 || id.length() < 12) {
            throw new UserInvalidPasswordException();
        }
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }
}
