package com.lomatech.lomatelegrambot.service;

import org.springframework.stereotype.Service;

@Service
public class TelegramCommandProcessingService {
	
	public String sendHelloText() {
		return "Hello";
	}

}
