package com.explorer.aws.lambda.controller;

import com.explorer.aws.lambda.domain.Order;
import com.explorer.aws.lambda.repository.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderDao orderRepository;

    @GetMapping("/orders")
    public List<Order> orderList(){
        return orderRepository.buildOrders();
    }
}
