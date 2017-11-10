package com.example.websocketdemo.service;
 
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
 
import org.springframework.stereotype.Service;

import com.example.websocketdemo.model.Order;
import com.example.websocketdemo.repository.OrderRepository;

@Service("OrderService")
public class OrderService implements OrderRepository{
     
    private static final AtomicLong counter = new AtomicLong();
     
    private static List<Order> orders;
     
    static{
        orders= populateDummyReports();
    }
 
    public List<Order> findAllOrders() {
        return orders;
    }
     
    public Order findById(long id) {
        for(Order order : orders){
            if(order.getCrisisID() == id){
                return order;
            }
        }
        return null;
    }
 
    public void saveOrder(Order order) {
    	orders.add(order);
    }
    
    public boolean isOrderExist(Order order) {
        return findById(order.getCrisisID())!=null;
    }
    
    public void deleteOrder(Order order) {
    	orders.remove(order);
    }
 
    private static List<Order> populateDummyReports(){
        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(counter.incrementAndGet(), "Test1", "Analyst" , 4, "Type1", "Area1", "Detail1", "Action1"));
        orders.add(new Order(counter.incrementAndGet(), "Test2", "General" , 5, "Type2", "Area2", "Detail2", "Action2"));
        orders.add(new Order(counter.incrementAndGet(), "Test3", "General" , 4, "Type3", "Area3", "Detail3", "Action3"));
        orders.add(new Order(counter.incrementAndGet(), "Test4", "General" , 5, "Type4", "Area4", "Detail4", "Action4"));
        return orders;
    }

}