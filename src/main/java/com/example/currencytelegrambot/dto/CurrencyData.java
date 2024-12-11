package com.example.currencytelegrambot.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CurrencyData {
    private int r030;
    private String txt;
    private BigDecimal rate;
    private String cc;
    private LocalDate exchangeDate;
}
