package com.example.repository;

import java.util.List;

import com.example.websocketdemo.model.Order;
 
public interface OrderRepository {
     
	Order findById(long id);
    
    void saveOrder(Order order);
 
    List<Order> findAllOrders();
     
    boolean isOrderExist(Order order);
     
}