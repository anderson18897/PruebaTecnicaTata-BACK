package com.anderson.dockerh2.security.repository;

import com.anderson.dockerh2.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByNameUser(String nameUser);
    boolean existsByNameUser(String nameUser);
    boolean existsByEmail(String email);

}
