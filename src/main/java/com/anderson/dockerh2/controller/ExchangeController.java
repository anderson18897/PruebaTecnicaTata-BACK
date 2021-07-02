package com.anderson.dockerh2.controller;

import com.anderson.dockerh2.dto.Message;
import com.anderson.dockerh2.model.Exchange;
import com.anderson.dockerh2.model.ExchangeRequest;
import com.anderson.dockerh2.model.ExchangeResponse;
import com.anderson.dockerh2.service.ExchangeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    private static Logger logger = LogManager.getLogger(ExchangeController.class);

    @PostMapping("/exchange")
    public ResponseEntity<ExchangeResponse> exchange(@RequestBody ExchangeRequest exchangeRequest) {

        ExchangeResponse exchangeResponse = new ExchangeResponse();

        exchangeResponse.setMount(exchangeRequest.getMount());

        if (exchangeRequest.getCurrency_origin().equals("USD") || exchangeRequest.getCurrency_destiny().equals("USD")) {

            Optional<Exchange> exchangeRate = exchangeService.findByRate("USD");

            if (!exchangeRate.isPresent()) {
                return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
            }

            Exchange exchangeRateDB = exchangeRate.get();

            if (exchangeRequest.getCurrency_origin().equals("PEN") && exchangeRequest.getCurrency_destiny().equals("USD")) {
                exchangeResponse.setMount_exchange(exchangeResponse.getMount() / exchangeRateDB.getValue());
                exchangeResponse.setExchange_rate("PEN-USD");
            } else if (exchangeRequest.getCurrency_origin().equals("USD") && exchangeRequest.getCurrency_destiny().equals("PEN")) {
                exchangeResponse.setMount_exchange(exchangeResponse.getMount() * exchangeRateDB.getValue());
                exchangeResponse.setExchange_rate("USD-PEN");
            } else if (exchangeRequest.getCurrency_origin().equals("USD") && exchangeRequest.getCurrency_destiny().equals("USD")) {
                exchangeResponse.setMount_exchange(exchangeResponse.getMount());
                exchangeResponse.setExchange_rate("USD-USD");
            }
        }

            if (exchangeRequest.getCurrency_origin().equals("PEN") && exchangeRequest.getCurrency_destiny().equals("PEN")) {
                exchangeResponse.setMount_exchange(exchangeResponse.getMount());
                exchangeResponse.setExchange_rate("PEN-PEN");
            }

            exchangeResponse.setCurrency_origin(exchangeRequest.getCurrency_origin());
            exchangeResponse.setCurrency_destiny(exchangeRequest.getCurrency_destiny());


            return new ResponseEntity(exchangeResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create-exchange")
    public ResponseEntity<?> create(@RequestBody Exchange exchangeRate) {
        if(StringUtils.isBlank(exchangeRate.getDescription()))
            return new ResponseEntity(new Message("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(exchangeRate.getValue()==null || exchangeRate.getValue()<0 )
            return new ResponseEntity(new Message("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        Exchange exchange = new Exchange(exchangeRate.getDescription(), exchangeRate.getValue());
        exchangeService.save(exchangeRate);
        return new ResponseEntity(new Message("producto creado"), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Exchange> getById(@PathVariable("id") Long id){
        if(!exchangeService.existsById(id))
            return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
        Exchange exchangeRate = exchangeService.getOne(id).get();
        return new ResponseEntity(exchangeRate, HttpStatus.OK);
    }

    @GetMapping("/show")
    @ResponseBody
    public ResponseEntity<List<Exchange>> show() {
        List<Exchange> list = exchangeService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update-exchange/{id}")
    public ResponseEntity<?> update(@RequestBody Exchange exchangeRate, @PathVariable Long id) {

        Optional<Exchange> requestOptional = exchangeService.findById(id);

        Exchange exchangeRateDB = requestOptional.get();

        exchangeRateDB.setDescription(exchangeRate.getDescription());
        exchangeRateDB.setValue(exchangeRate.getValue());

        exchangeService.save(exchangeRateDB);

        return new ResponseEntity(new Message("producto actualizado"), HttpStatus.OK);
    }
}
