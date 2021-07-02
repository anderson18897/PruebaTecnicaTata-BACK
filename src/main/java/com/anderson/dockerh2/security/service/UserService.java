package com.anderson.dockerh2.security.service;

import com.anderson.dockerh2.security.entity.User;
import com.anderson.dockerh2.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<User> getByNameUser(String nameUser){
        return userRepository.findByNameUser(nameUser);
    }

    public boolean existsByNameUser(String nameUser){
        return userRepository.existsByNameUser(nameUser);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public void save(User user){
        userRepository.save(user);
    }
}
