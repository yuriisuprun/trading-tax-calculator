package com.trading.tax.calculator.controller;

import com.trading.tax.calculator.service.TaxService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Yurii_Suprun
 */
@RestController
//@RequestMapping("tax/")
public class TaxController {

    private final TaxService taxService;

    @Autowired
    public TaxController(TaxService taxService) {
        this.taxService = taxService;
    }

    @GetMapping(path = "/result")
    public JSONArray getRecipeById() throws ParseException {
        String uri = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
        RestTemplate restTemplate = new RestTemplate();
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(restTemplate.getForObject(uri, String.class));
        Object[] currencyRatesArray = jsonArray.toArray();
        Object o3 = currencyRatesArray[3];
        System.out.println(o3);
        Optional<Object> any = Arrays.stream(currencyRatesArray).filter(o -> o.["cc"] == "USD").findAny();
        return (JSONArray) parser.parse(restTemplate.getForObject(uri, String.class));
//        return (JSONObject) parser.parse(result);
    }
}
