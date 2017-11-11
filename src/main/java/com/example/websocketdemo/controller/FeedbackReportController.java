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
@RequestMapping("/EFtoCMO")
public class FeedbackReportController {

	@Autowired
	private FeedbackReportService feedbackReportService;

	@RequestMapping(method = RequestMethod.GET, value = "/feedbackReport")
	public List<FeedbackReport> getAllFeedbackReports() {
		return feedbackReportService.getAllFeedbackReports();
	}

	// curly braces for inputs
	@RequestMapping(method = RequestMethod.GET, value = "/feedbackReport/{crisisID}")
	public FeedbackReport getFeedbackReport(@PathVariable("crisisID") int crisisID) { 
		return feedbackReportService.getFeedbackReport(crisisID);
	}

	// method to save feedbackReport
	@RequestMapping(method = RequestMethod.POST, value = "/feedbackReport")
	public void addFeedbackReport(@RequestBody FeedbackReport feedbackReport) {
		feedbackReportService.addFeedbackReport(feedbackReport);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/feedbackReport/{crisisID}")
	public void updateFeedbackReport(@RequestBody FeedbackReport feedbackReport,
			@PathVariable("crisisID") int crisisID) {
		FeedbackReport savedFeedbackReport = feedbackReportService.updateFeedbackReport(crisisID, feedbackReport);
		System.out.print(savedFeedbackReport);
	}

	public FeedbackReport updateFeedbackReportOfficer(FeedbackReport feedbackReport, int officerID,
			int feedbackReportID) {
		return feedbackReportService.updateFeedbackReport(feedbackReportID, feedbackReport);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/feedbackReport/{crisisID}")
	public void deleteFeedbackReport(@PathVariable("crisisID") String crisisID) {
		feedbackReportService.deleteFeedbackReport(crisisID);
	}

	public List<Integer> getAllFeedbackReportIDs() {
		return feedbackReportService.getAllCrisisIDs();
	}
}