package com.example.currencytelegrambot.facade.impl;

import com.example.currencytelegrambot.dto.CurrencyData;
import com.example.currencytelegrambot.facade.CurrencyFacade;
import com.example.currencytelegrambot.service.impl.DefaultCurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Component
public class DefaultCurrencyFacade implements CurrencyFacade {

    private final DefaultCurrencyService defaultCurrencyService;

    @Override
    public String getCurrencyByCc(String cc) {
        try {
            CurrencyData currency = defaultCurrencyService.getCurrencyByCc(cc.toUpperCase());
            return "The official exchange rate of UAH (грн) to " + currency.getCc() +
                    "\nat the moment (" + currency.getExchangeDate() + ") is: " + currency.getRate();
        } catch (NoSuchElementException exception) {
            return "Sorry, I don't know that currency.";
        } catch (IOException exception) {
            return "Oh, something went wrong.";
        }
    }

    @Override
    public String getAllCurrencies() {
        List<CurrencyData> currencies;
        try {
            currencies = defaultCurrencyService.getAllCurrencies();
        } catch (IOException exception) {
            return "Oh, something went wrong.";
        }
        StringBuilder sb = new StringBuilder();
        for (CurrencyData currency : currencies) {
            sb.append(currency.getCc())
                    .append(" (")
                    .append(currency.getTxt())
                    .append(")\n");
        }
        return sb.toString();
    }
}
