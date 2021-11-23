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

    public String getUsdCurrencyExchangeRate(String exchangeRateDate) throws ParseException, JsonProcessingException {
        String uri = endpointUri + exchangeRateDate + "&json";
        JSONArray jsonArray = (JSONArray) parser.parse(restTemplate.getForObject(uri, String.class));
        Object[] currencyRatesArray = jsonArray.toArray();

        double usCurrencyRate = 0;
        String usCc = "";
        String usCurrencyExchangedate = "";
        for (Object currencyRateObject : currencyRatesArray) {
            // converting json string to object
            CurrencyRate currencyRate = objectMapper.readValue(currencyRateObject.toString(), CurrencyRate.class);
            if (currencyRate.getCc().equals(usdCc)) {
                usCurrencyRate = currencyRate.getRate();
                usCc = currencyRate.getCc();
                usCurrencyExchangedate = currencyRate.getExchangedate();
            }
        }
        return "{rate: " + usCurrencyRate + ", cc: " + usCc + ", currencyExchangedate: " + usCurrencyExchangedate + "}";
    }
}
