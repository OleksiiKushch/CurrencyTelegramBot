package com.example.currencytelegrambot.converter.impl;

import com.example.currencytelegrambot.converter.CurrencyConverter;
import com.example.currencytelegrambot.dto.CurrencyData;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class JsonCurrencyConverter implements CurrencyConverter<JSONObject> {

    private static final String R030 = "r030";
    private static final String TXT = "txt";
    private static final String RATE = "rate";
    private static final String CC = "cc";
    private static final String EXCHANGE_DATE = "exchangedate";
    public static final DateTimeFormatter API_DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    public CurrencyData convert(JSONObject source) {
        CurrencyData target = new CurrencyData();

        target.setR030(source.getInt(R030));
        target.setTxt(source.getString(TXT));
        target.setRate(source.getBigDecimal(RATE));
        target.setCc(source.getString(CC));
        target.setExchangeDate(LocalDate.parse(source.getString(EXCHANGE_DATE), API_DATE_FORMAT));

        return target;
    }
}
