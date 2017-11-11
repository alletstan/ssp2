package com.example.websocketdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.websocketdemo.model.FeedbackReport;

public interface FeedbackReportRepository extends CrudRepository<FeedbackReport, String>{

	public FeedbackReport findByCrisisID(long crisisID);
		
	@Modifying
	@Query("delete from FeedbackReport where crisisID = :crisisID")
	void delete(@Param("crisisID") int crisisID);
	
	@Query("select DISTINCT(c.crisisID) from FeedbackReport c where c.crisisID > 0")
	public List<Integer> getCrisisIDs();
}
