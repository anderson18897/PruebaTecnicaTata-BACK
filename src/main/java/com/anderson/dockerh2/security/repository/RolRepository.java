package com.anderson.dockerh2.security.repository;

import com.anderson.dockerh2.security.entity.Rol;
import com.anderson.dockerh2.security.enums.RolName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

    @Query("select r from Rol r where r.rolName=?1")
    Optional<Rol> findByRolName(RolName rolName);

}
