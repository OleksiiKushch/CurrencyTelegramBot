package com.example.currencytelegrambot.converter;

import com.example.currencytelegrambot.dto.CurrencyData;

public interface CurrencyConverter<SOURCE> {

    CurrencyData convert(SOURCE source);
}
