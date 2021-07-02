package com.anderson.dockerh2.repository;

import com.anderson.dockerh2.model.Exchange;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long> {

    @Query("select e from Exchange e where e.description=?1")
    Optional<Exchange> findByRate(String description);

}
