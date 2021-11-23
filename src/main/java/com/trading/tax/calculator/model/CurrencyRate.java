package com.trading.tax.calculator.model;

import java.util.Date;

/**
 * Created by Yurii_Suprun
 */
public class CurrencyRate {

    String cc;
    String txt;
    double rate;
    int r030;
    Date exchangedate;

    public CurrencyRate() {
    }

    public CurrencyRate(String cc, String txt, double rate, int r030, Date exchangedate) {
        this.cc = cc;
        this.txt = txt;
        this.rate = rate;
        this.r030 = r030;
        this.exchangedate = exchangedate;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getR030() {
        return r030;
    }

    public void setR030(int r030) {
        this.r030 = r030;
    }

    public Date getExchangedate() {
        return exchangedate;
    }

    public void setExchangedate(Date exchangedate) {
        this.exchangedate = exchangedate;
    }

    @Override
    public String toString() {
        return "CurrencyRate{" +
                "cc='" + cc + '\'' +
                ", txt='" + txt + '\'' +
                ", rate=" + rate +
                ", r030=" + r030 +
                ", exchangedate=" + exchangedate +
                '}';
    }
}
