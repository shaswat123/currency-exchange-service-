package com.currency.exchange.controllers;

import com.currency.exchange.domain.ExchangeValue;
import com.currency.exchange.repo.ExchangeValueRepository;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeControllers {

    @Autowired
    private Environment env;

    @Autowired
    private ExchangeValueRepository exchangeValueRepository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from , @PathVariable String to){

        ExchangeValue exchangeValue=exchangeValueRepository.findByFromAndTo(from,to);
        exchangeValue.setPort(Integer.parseInt(env.getProperty("local.server.port")));
        return exchangeValue;
    }
}
