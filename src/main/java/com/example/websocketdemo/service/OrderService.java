package com.example.websocketdemo.service;
 
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.websocketdemo.model.Order;
import com.example.websocketdemo.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	public List<Order> getAllOrders(){
		List<Order> orders = new ArrayList<>();
		orderRepository.findAll().forEach(orders::add); //query all orders in DB, and add each of them into orders
		return orders;
	}

	public Order getOrder(int crisisID){
		return orderRepository.findByCrisisID(crisisID);
	}
	
	public void addOrder(Order operator){
		orderRepository.save(operator);
	}
	
	public Order updateOrder(int crisisID, Order order){
		return orderRepository.save(order); //repository smart enough to find tuple with stated id
	}

	public void deleteOrder(String id){
		orderRepository.delete(id);
	}
	
	public List<Integer> getAllOrderIDs(){
		return orderRepository.getCrisisIDs();
	}
	
}