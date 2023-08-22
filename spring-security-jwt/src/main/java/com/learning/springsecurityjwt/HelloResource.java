package com.learning.springsecurityjwt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloResource {
	
	@RequestMapping(method = RequestMethod.GET, value = "/greetingMessage")
	public String greetMessage() {
		return "hello";
	}
}
