package com.trading.tax.calculator.model;

import java.io.Serializable;

/**
 * Created by Yurii_Suprun
 */
public class CurrencyRate implements Serializable {

    String cc;
    String txt;
    double rate;
    int r030;
    String exchangeDate;

    public CurrencyRate() {
    }

    public CurrencyRate(String cc, String txt, double rate, int r030, String exchangeDate) {
        this.cc = cc;
        this.txt = txt;
        this.rate = rate;
        this.r030 = r030;
        this.exchangeDate = exchangeDate;
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

    public String getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(String exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    @Override
    public String toString() {
        return "CurrencyRate{" +
                "cc='" + cc + '\'' +
                ", txt='" + txt + '\'' +
                ", rate=" + rate +
                ", r030=" + r030 +
                ", exchangedate=" + exchangeDate +
                '}';
    }
}
