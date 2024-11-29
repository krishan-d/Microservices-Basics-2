package com.learn.microservice.currency_exchange_service.controller;

import com.learn.microservice.currency_exchange_service.model.CurrencyExchange;
import com.learn.microservice.currency_exchange_service.repository.CurrencyExchangeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
    @Autowired
    private Environment environment;

    private CurrencyExchangeRepository currencyExchangeRepository;

    public CurrencyExchangeController(CurrencyExchangeRepository currencyExchangeRepository) {
        this.currencyExchangeRepository = currencyExchangeRepository;
    }

    @GetMapping("/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {

        logger.info("retrieveExchangeValue called from {} to {}", from, to);
        // INFO [currency-exchange,c8deea71204296b5d87f9326531fe598,904f8ce1aad4237a] 90951 --- [currency-exchange]

//        CurrencyExchange ce = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(80));
        CurrencyExchange ce = currencyExchangeRepository.findByFromAndTo(from, to);
        if (ce == null) throw new RuntimeException("Data not found!");
        String port = environment.getProperty("local.server.port");
        ce.setEnvironment(port);
        return ce;
    }


}
