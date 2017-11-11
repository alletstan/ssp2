package com.example.websocketdemo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.websocketdemo.model.FeedbackReport;
import com.example.websocketdemo.repository.FeedbackReportRepository;

@Service
public class FeedbackReportService {

	@Autowired
	private FeedbackReportRepository feedbackReportRepository;

	public List<FeedbackReport> getAllFeedbackReports() {
		List<FeedbackReport> feedbackReports = new ArrayList<>();
		feedbackReportRepository.findAll().forEach(feedbackReports::add); 
		return feedbackReports;
	}

	public FeedbackReport getFeedbackReport(long crisisID) {
		return feedbackReportRepository.findByCrisisID(crisisID);
	}

	public void addFeedbackReport(FeedbackReport feedbackReport) {
		FeedbackReport savedFeedbackReport = feedbackReportRepository.save(feedbackReport);
		FeedbackReport success = feedbackReportRepository.findByCrisisID(savedFeedbackReport.getCrisisID());
		System.out.print(success);
	}

	public FeedbackReport updateFeedbackReport(int crisisID, FeedbackReport feedbackReport) {
		return feedbackReportRepository.save(feedbackReport); 
	}

	public void deleteFeedbackReport(String crisisID) {
		feedbackReportRepository.delete(crisisID);
	}

	public List<Integer> getAllCrisisIDs() {
		return feedbackReportRepository.getCrisisIDs();
	}

}