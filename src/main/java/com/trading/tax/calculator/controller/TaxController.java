package com.trading.tax.calculator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.trading.tax.calculator.model.CurrencyRate;
import com.trading.tax.calculator.service.TaxService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Yurii_Suprun
 */
@RestController
public class TaxController {

    private final TaxService taxService;

    @Autowired
    public TaxController(TaxService taxService) {
        this.taxService = taxService;
    }

    @GetMapping(path = "{exchangeRateDate}")
    public CurrencyRate getUsdCurrencyExchangeRate(@PathVariable String exchangeRateDate) throws ParseException, JsonProcessingException {
        return taxService.getUsdCurrencyExchangeRate(exchangeRateDate);
    }

    @GetMapping(path = "{exchangeRateDate}/{usdPrice}")
    public double getUahValueForUsdPrice(@PathVariable String exchangeRateDate, @PathVariable double usdPrice) throws ParseException, JsonProcessingException {
        return taxService.calculateCurrencyRate(exchangeRateDate, usdPrice);
    }
}
