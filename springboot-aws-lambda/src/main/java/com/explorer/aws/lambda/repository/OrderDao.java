package com.explorer.aws.lambda.repository;

import com.explorer.aws.lambda.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class OrderDao {

    public List<Order>  buildOrders(){
        return Stream.of(
                new Order(101, "Mobile", 52201, 1),
                new Order(102, "Book", 4031, 1),
                new Order(103, "Book", 2000, 3),
                new Order(104, "Shoes", 4000, 2),
                new Order(105, "Laptop", 60000, 1)
        ).collect(Collectors.toList());
    }
}
