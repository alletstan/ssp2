package com.example.websocketdemo.repository;

import java.util.List;

import com.example.websocketdemo.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository {

	Order findById(long id);

	void saveOrder(Order order);

	List<Order> findAllOrders();

	boolean isOrderExist(Order order);

}