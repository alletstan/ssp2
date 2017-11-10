package com.example.websocketdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.websocketdemo.model.Order;
import com.example.websocketdemo.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/order")
	public List<Order> getAllOrders(){
		return orderService.findAllOrders();
	}
	
	//curly braces for inputs
	@RequestMapping("/order/{id}")
	public Order getOrder(@PathVariable int orderID){ //need use @pathvariable. convention to keep names same
		return orderService.findById(orderID);
	}
	
	//method to save order
	@RequestMapping(method=RequestMethod.POST, value="/order")
	public void addOrder(@RequestBody Order order){
		orderService.saveOrder(order);
	}
	
	
	@RequestMapping(method=RequestMethod.DELETE, value="/order/{orderID}")
	public void deleteOrder(@PathVariable Order order){
		orderService.deleteOrder(order);
	}

}