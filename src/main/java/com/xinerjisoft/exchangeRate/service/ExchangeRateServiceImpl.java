package com.xinerjisoft.exchangeRate.service;

import com.xinerjisoft.exchangeRate.model.Currency;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinerjisoft.exchangeRate.model.Date;
import com.xinerjisoft.exchangeRate.repository.CurrencyRepository;
import com.xinerjisoft.exchangeRate.repository.DateRepository;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {
    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    DateRepository dateRepository;

    public String getExchangeRate(String date) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> dateFormat = convertDate(date);

        String url = "https://www.tcmb.gov.tr/kurlar/" +
                dateFormat.get("year") + dateFormat.get("month") + "/" +
                dateFormat.get("day") + dateFormat.get("month") + dateFormat.get("year") + ".xml";

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

        ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);
        String xmlResponse = response.getBody();


        JSONObject xmlJSONObj = XML.toJSONObject(xmlResponse);
        System.out.println(xmlJSONObj);
        saveCurrenciesFromJson(xmlJSONObj.toString(),date);
        return xmlJSONObj.toString();
    }


    public void saveCurrenciesFromJson(String jsonData, String date) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonData);

            JsonNode currencyNode = rootNode.path("Tarih_Date").path("Currency");

            if (currencyNode.isArray()) {
                List<Currency> currencies = new ArrayList<>();
                Date selectedDate = new Date();
                selectedDate.setDate(date.substring(0, 4) + '.' + date.substring(4, 6) + '.' + date.substring(6, 8));
                dateRepository.save(selectedDate);
                for (JsonNode currency : currencyNode) {

                    Currency currencyObj = new Currency();
                    currencyObj.setCurrencyCode(currency.path("CurrencyCode").asText());
                    currencyObj.setKod(currency.path("Kod").asText());
                    currencyObj.setBanknoteSelling(currency.path("BanknoteSelling").asDouble());
                    currencyObj.setCrossOrder(currency.path("CrossOrder").asInt());
                    currencyObj.setUnit(currency.path("Unit").asInt());
                    currencyObj.setBanknoteBuying(currency.path("BanknoteBuying").asDouble());
                    currencyObj.setForexBuying(currency.path("ForexBuying").asDouble());
                    currencyObj.setForexSelling(currency.path("ForexSelling").asDouble());
                    currencyObj.setCrossRateOther(currency.path("CrossRateOther").asDouble());
                    currencyObj.setCrossRateUSD(currency.path("CrossRateUSD").asDouble());
                    currencyObj.setForexSelling(currency.path("ForexSelling").asDouble());
                    currencyObj.setDate(selectedDate);
                    currencies.add(currencyObj);
                }

                saveCurrencies(currencies);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveCurrencies(List<Currency> currencies) {
        currencyRepository.saveAll(currencies);
    }


    private Map<String, String> convertDate(String date) {
        Map<String, String> dateFormat = new HashMap<>();
        dateFormat.put("year", date.substring(0, 4));
        dateFormat.put("month", date.substring(4, 6));
        dateFormat.put("day", date.substring(6, 8));
        return dateFormat;
    }
}





