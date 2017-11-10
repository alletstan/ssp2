package com.example.websocketdemo.repository;

import java.util.List;

import com.example.websocketdemo.model.FeedbackReport;

public interface FeedbackReportRepository {

	FeedbackReport findById(long id);

	void saveFeedbackReport(FeedbackReport feedbackreport);

	List<FeedbackReport> findAllFeedbackReports();

	boolean isFeedbackReportExist(FeedbackReport feedbackreport);

}
