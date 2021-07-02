package com.anderson.dockerh2.service;

import com.anderson.dockerh2.model.Exchange;
import com.anderson.dockerh2.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExchangeService {

    @Autowired
    ExchangeRepository exchangeRepository;

    public void  save(Exchange exchangeRate){
        exchangeRepository.save(exchangeRate);
    }

    public List<Exchange> list(){
        return exchangeRepository.findAll();
    }

    public Optional findById(Long id){
        return exchangeRepository.findById(id);
    }

    public boolean existsById(Long id){
        return exchangeRepository.existsById(id);
    }

    public Optional<Exchange> getOne(Long id){
        return exchangeRepository.findById(id);
    }

    public Optional findByRate(String description){
        return exchangeRepository.findByRate(description);
    }

}
