package com.lomatech.lomatelegrambot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class CasinoTelegramBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        //System.out.println(update.getMessage().getText());
        //System.out.println(update.getMessage().getFrom().getFirstName());

        String command = update.getMessage().getText();

        if(command.equals("/run")){
            String message = "hi run command received...";
            SendMessage response = new SendMessage();
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);

            try{
                execute(response);
            }catch (TelegramApiException E){
                E.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "LomaSimpleCasinoBot";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "6553750972:AAGb44XX7aRq1Wd61Bo83T3B0s5ZxiPXvCI";
    }
}
