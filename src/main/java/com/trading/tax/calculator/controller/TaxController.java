package com.trading.tax.calculator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trading.tax.calculator.model.CurrencyRate;
import com.trading.tax.calculator.service.TaxService;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Yurii_Suprun
 */
@RestController
public class TaxController {

    @Value("${endpoint.uri}")
    private String endpointUri;

    private final TaxService taxService;

    @Autowired
    public TaxController(TaxService taxService) {
        this.taxService = taxService;
    }

    @GetMapping(path = "/result")
    public double getCurrencyExchangeRate() throws ParseException, JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(restTemplate.getForObject(endpointUri, String.class));
        Object[] currencyRatesArray = jsonArray.toArray();
        ObjectMapper objectMapper = new ObjectMapper();

        double usCurrencyRate = 0;
        for (Object currencyRateObject : currencyRatesArray) {
            // converting json string to object
            CurrencyRate currencyRate = objectMapper.readValue(currencyRateObject.toString(), CurrencyRate.class);
            if (currencyRate.getCc().equals("USD")){
                System.out.println(currencyRate.getCc());
                System.out.println(currencyRate.getRate());
                usCurrencyRate = currencyRate.getRate();
            }
        }
        return usCurrencyRate;
    }
}
