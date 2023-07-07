package com.xinerjisoft.exchangeRate.controller;

import com.xinerjisoft.exchangeRate.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/exchange-rates")
public class ExchangeRateController {

    @Autowired
    ExchangeRateService exchangeRateService;


    @GetMapping("/getExchangeRates/{date}")
    public ResponseEntity<?> getExchangeRates(@PathVariable String date) {
        return ResponseEntity.ok(exchangeRateService.getExchangeRate(date));
    }
}
