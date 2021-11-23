package com.trading.tax.calculator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trading.tax.calculator.model.CurrencyRate;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JSONParser parser;

    @Autowired
    private ObjectMapper objectMapper;

    public CurrencyRate getUsdCurrencyExchangeRate(String exchangeRateDate) throws ParseException, JsonProcessingException {
        String uri = endpointUri + exchangeRateDate + "&json";

        JSONArray currencyRateJsonArray = (JSONArray) parser.parse(restTemplate.getForObject(uri, String.class));
        return findUsCurrencyRate(currencyRateJsonArray);
    }

    public double calculateCurrencyRate(String exchangeRateDate, double usdPrice) throws ParseException, JsonProcessingException {
        double rate = getUsdCurrencyExchangeRate(exchangeRateDate).getRate();
        return rate * usdPrice;
    }

    private CurrencyRate findUsCurrencyRate(JSONArray currencyRateJsonArray) throws JsonProcessingException {
        CurrencyRate currencyRate = new CurrencyRate();
        for (Object currencyRateObject : currencyRateJsonArray) {
            // converting json string to object
            currencyRate = objectMapper.readValue(currencyRateObject.toString(), CurrencyRate.class);
            if (currencyRate.getCc().equals(usdCc)) {
                return currencyRate;
            }
        }
        return currencyRate;
    }
}
