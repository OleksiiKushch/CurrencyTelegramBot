package com.example.currencytelegrambot.bot;

import com.example.currencytelegrambot.facade.impl.DefaultCurrencyFacade;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Log4j2
@Component
public class CurrencyTelegramBot extends TelegramLongPollingBot {

    private final BotConfig botConfig;
    private final DefaultCurrencyFacade defaultCurrencyFacade;

    public CurrencyTelegramBot(BotConfig botConfig, DefaultCurrencyFacade defaultCurrencyFacade) {
        super(botConfig.getBotToken());
        this.botConfig = botConfig;
        this.defaultCurrencyFacade = defaultCurrencyFacade;
    }

    /**
     * Called every time the user sends a message.
     * @param update using it we can get the text of the message, chat id for sending a response message and other information.
     */
    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (messageText.equals("/start")) {
                sendMessage(chatId, getStartMessage(update));
            } else if (messageText.equals("/all")) {
                sendMessage(chatId, defaultCurrencyFacade.getAllCurrencies());
            } else {
                sendMessage(chatId, defaultCurrencyFacade.getCurrencyByCc(messageText));
            }
        }
    }

    private String getStartMessage(Update update) {
        return "Hello, " + update.getMessage().getChat().getFirstName() + "!\n" +
                "Do you want to find out the official exchange rate of a specific currency against UAH (грн)?\n" +
                "Enter the currency in the following format, for example: USD";
    }

    /**
     * Sends a telegram message to the user via the chat id.
     */
    private void sendMessage(Long chatId, String textToSend){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);
        try {
            execute(sendMessage);
        } catch (TelegramApiException exception) {
            log.error(exception.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotUsername();
    }
}
