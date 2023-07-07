package com.xinerjisoft.exchangeRate.service;

public interface ExchangeRateService {
    void saveCurrenciesFromJson(String jsonData,String date);

    String getExchangeRate(String date);
}
