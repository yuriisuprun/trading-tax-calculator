package com.trading.tax.calculator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trading.tax.calculator.model.CurrencyRate;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Yurii_Suprun
 */
@Service
public class TaxService {

    @Value("${endpoint.uri}")
    private String endpointUri;

    @Value("${cc.usd}")
    private String usdCc;

    public double getUsdCurrencyExchangeRate(String exchangeRateDate) throws ParseException, JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        JSONParser parser = new JSONParser();
        String uri = endpointUri + exchangeRateDate + "&json";
        System.out.println("==========================");
        System.out.println(uri);
        System.out.println("==========================");
        JSONArray jsonArray = (JSONArray) parser.parse(restTemplate.getForObject(uri, String.class));
        Object[] currencyRatesArray = jsonArray.toArray();
        ObjectMapper objectMapper = new ObjectMapper();

        double usCurrencyRate = 0;
        for (Object currencyRateObject : currencyRatesArray) {
            // converting json string to object
            CurrencyRate currencyRate = objectMapper.readValue(currencyRateObject.toString(), CurrencyRate.class);
            if (currencyRate.getCc().equals(usdCc)) {
                System.out.println(currencyRate.getCc());
                System.out.println(currencyRate.getRate());
                System.out.println(currencyRate.getExchangedate());
                usCurrencyRate = currencyRate.getRate();
            }
        }
        return usCurrencyRate;
    }
}
