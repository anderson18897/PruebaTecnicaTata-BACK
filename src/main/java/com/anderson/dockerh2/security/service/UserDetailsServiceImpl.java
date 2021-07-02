package com.anderson.dockerh2.security.service;

import com.anderson.dockerh2.security.entity.User;
import com.anderson.dockerh2.security.entity.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String nameUser) throws UsernameNotFoundException {
        User user = usuarioService.getByNameUser(nameUser).get();
        return UserPrincipal.build(user);
    }
}
