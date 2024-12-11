package com.example.currencytelegrambot.service;

import com.example.currencytelegrambot.dto.CurrencyData;

import java.io.IOException;
import java.util.List;

public interface CurrencyService {

    List<CurrencyData> getAllCurrencies() throws IOException;
    CurrencyData getCurrencyByCc(String cc) throws IOException;
}
