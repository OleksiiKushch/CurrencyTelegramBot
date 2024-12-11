package com.example.currencytelegrambot.bot;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Data
@Configuration
public class BotConfig {

    @Value("${currency.telegram.bot.username}")
    private String botUsername;
    @Value("${currency.telegram.bot.token}")
    private String botToken;
}
