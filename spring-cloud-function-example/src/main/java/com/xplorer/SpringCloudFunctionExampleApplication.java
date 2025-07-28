package com.xplorer;

import com.xplorer.model.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class SpringCloudFunctionExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudFunctionExampleApplication.class, args);
	}

	@Bean
	public Function<String, String> reverse(){
		return (input) -> new StringBuffer(input).reverse().toString();
	}

	@Bean
	public Supplier<Book> getBook(){
		return () -> new Book(101, "core java");
	}

	@Bean
	public Consumer<String> printMessage(){
		return (input) -> System.out.println(input);
	}

}
