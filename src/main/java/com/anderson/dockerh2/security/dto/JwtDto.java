package com.anderson.dockerh2.security.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@NoArgsConstructor
public class JwtDto {

    private String token;
    private String bearer = "Bearer";
    private String nameUser;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtDto(String token, String nameUser, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.nameUser = nameUser;
        this.authorities = authorities;
    }

}
