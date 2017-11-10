package com.example.websocketdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.websocketdemo.model.FeedbackReport;
import com.example.websocketdemo.service.FeedbackReportService;

@RestController
public class FeedbackReportController {
	
	@Autowired
	private FeedbackReportService feedbackReportService;
	
	@RequestMapping("/feedbackReport")
	public List<FeedbackReport> getAllFeedbackReports(){
		return feedbackReportService.findAllFeedbackReports();
	}
	
	//curly braces for inputs
	@RequestMapping("/feedbackReport/{id}")
	public FeedbackReport getFeedbackReport(@PathVariable long crisisId){ //need use @pathvariable. convention to keep names same
		return feedbackReportService.findById(crisisId);
	}
	
	//method to save feedbackReport
	@RequestMapping(method=RequestMethod.POST, value="/feedbackReport")
	public void addFeedbackReport(@RequestBody FeedbackReport feedbackReport){
		feedbackReportService.saveFeedbackReport(feedbackReport);
	}
	
	
	@RequestMapping(method=RequestMethod.DELETE, value="/feedbackReport/{feedbackReportID}")
	public void deleteFeedbackReport(@RequestBody FeedbackReport feedbackReport){
		feedbackReportService.deleteFeedbackReport(feedbackReport);
	}

}