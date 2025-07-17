package com.explorer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AwsExplorerApplication {

	@GetMapping("/")
	public String home(){
		return "Welcome to Aws first deployment...!!";
	}

	public static void main(String[] args) {
		SpringApplication.run(AwsExplorerApplication.class, args);
	}

}
