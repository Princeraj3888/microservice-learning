package com.explorer.aws.lambda;

import com.explorer.aws.lambda.domain.Order;
import com.explorer.aws.lambda.repository.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class SpringbootAwsLambdaApplication {

	@Autowired
	private OrderDao orderDao;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAwsLambdaApplication.class, args);
	}

	@Bean
	public Supplier<List<Order>> orders(){
		return ()-> orderDao.buildOrders();
	}

	@Bean
	public Function<String, List<Order>> findOrderByName(){
		return (input)-> orderDao.buildOrders().stream()
				.filter(ele -> ele.getName().equals(input)).toList();
	}
}
