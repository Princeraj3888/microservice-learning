package com.lomatech.lomatelegrambot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import com.lomatech.lomatelegrambot.CasinoTelegramBot;

@Configuration
public class TelegramBotConfiguration {

	
	@Bean
	public TelegramBotsApi telegramBotApi(CasinoTelegramBot casinoTelegramBot) throws TelegramApiException {
		var api = new TelegramBotsApi(DefaultBotSession.class);
		api.registerBot(casinoTelegramBot);
		return api;
	}
}
