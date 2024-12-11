package com.example.currencytelegrambot.service.impl;

import com.example.currencytelegrambot.converter.impl.JsonCurrencyConverter;
import com.example.currencytelegrambot.dto.CurrencyData;
import com.example.currencytelegrambot.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
@Service
public class DefaultCurrencyService implements CurrencyService {

    private static final String JSON_FORMAT = "json";
    private static final String ALL_CURRENT_CURRENCIES_API_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange";

    private final JsonCurrencyConverter jsonCurrencyConverter;

    @Override
    public List<CurrencyData> getAllCurrencies() throws IOException {
        URL url = new URL(ALL_CURRENT_CURRENCIES_API_URL + "?" + JSON_FORMAT);

        Scanner scanner = new Scanner((InputStream) url.getContent());
        StringBuilder temp = new StringBuilder();
        while (scanner.hasNext()){
            temp.append(scanner.nextLine());
        }
        JSONArray jsonArray = new JSONArray(temp.toString());

        List<CurrencyData> result = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            result.add(jsonCurrencyConverter.convert(jsonArray.getJSONObject(i)));
        }
        return result;
    }

    @Override
    public CurrencyData getCurrencyByCc(String cc) throws IOException {
        return getAllCurrencies().stream()
                .filter(c -> c.getCc().equals(cc))
                .findFirst().orElseThrow();
    }
}
