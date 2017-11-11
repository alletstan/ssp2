package com.example.websocketdemo.repository;

import java.util.List;

import com.example.websocketdemo.model.Order;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends CrudRepository<Order, String>{

	public Order findByCrisisID(long crisisID);
		
	@Modifying
	@Query("delete from Order where crisisID = :crisisID")
	void delete(@Param("crisisID") int crisisID);

	@Query("select DISTINCT(c.crisisID) from Order c where c.crisisID > 0")
	public List<Integer> getCrisisIDs();
}