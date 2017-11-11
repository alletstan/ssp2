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
		return orderService.getAllOrders();
	}
	
	//curly braces for inputs
	@RequestMapping("/order/{id}")
	public Order getOrder(@PathVariable("id") int orderID){ //need use @pathvariable. convention to keep names same
		return orderService.getOrder(orderID);
	}
	
	//method to save order
	@RequestMapping(method=RequestMethod.POST, value="/order")
	public void addOrder(@RequestBody Order order){
		orderService.addOrder(order);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/order/{orderID}")
	public void updateOrder(@RequestBody Order order, @PathVariable("orderID") int orderID){
		Order savedOrder = orderService.updateOrder(orderID, order);
		System.out.print(savedOrder);
	}
	
	public Order updateOrderOfficer(Order order, int officerID, int orderID){
		return orderService.updateOrder(orderID, order);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/order/{orderID}")
	public void deleteOrder(@PathVariable("orderID") String orderID){
		orderService.deleteOrder(orderID);
	}

	public List<Integer> getAllOrderIDs(){
		return orderService.getAllOrderIDs();
	}
}