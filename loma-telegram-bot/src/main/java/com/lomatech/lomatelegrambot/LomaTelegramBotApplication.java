package com.lomatech.lomatelegrambot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class LomaTelegramBotApplication {

	public static void main(String[] args) {

		SpringApplication.run(LomaTelegramBotApplication.class, args);
		/*try {
			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
			botsApi.registerBot(new CasinoTelegramBot());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}*/
	}

}
