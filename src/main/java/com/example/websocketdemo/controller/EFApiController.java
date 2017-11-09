//Server side
package com.example.websocketdemo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.websocketdemo.model.Order;
import com.example.repository.OrderRepository;
import com.example.websocketdemo.config.CustomErrorType;
 
@RestController
@RequestMapping("/CMOtoEF")
public class EFApiController {
 
    public static final Logger logger = LoggerFactory.getLogger(EFApiController.class);
 
    @Autowired
    OrderRepository orderRepository; //Service which will do all data retrieval/manipulation work
 
    // -------------------Retrieve All Reports---------------------------------------------
 
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/order/", method = RequestMethod.GET)
    public ResponseEntity<List<Order>> listAllOrders() {
        List<Order> orders = orderRepository.findAllOrders();
        if (orders.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
    }
 
    // -------------------Retrieve Single Report------------------------------------------
 
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/order/{crisisID}", method = RequestMethod.GET)
    public ResponseEntity<?> getOrder(@PathVariable("crisisID") long crisisID) {
        logger.info("Fetching Order with crisisID {}", crisisID);
        Order order = orderRepository.findById(crisisID);
        if (order == null) {
            logger.error("Order with crisisID {} not found.", crisisID);
            return new ResponseEntity(new CustomErrorType("Order with crisisID " + crisisID 
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
 
    // -------------------Create a Report-------------------------------------------
 
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value = "/order/", method = RequestMethod.POST)
    public ResponseEntity<?> createOrder(@RequestBody Order order, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Order : {}", order);
 
        if (orderRepository.isOrderExist(order)) {
            logger.error("Unable to create. An Order with name {} already exist", order.getCrisisID());
            return new ResponseEntity(new CustomErrorType("Unable to create. An Order with crisisID " + 
            order.getCrisisID() + " already exist."),HttpStatus.CONFLICT);
        }
        orderRepository.saveOrder(order);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/CMOtoEF/order/{crisisID}").buildAndExpand(order.getCrisisID()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
 
}