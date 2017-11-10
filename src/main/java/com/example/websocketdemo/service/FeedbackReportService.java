package com.example.websocketdemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.example.websocketdemo.model.FeedbackReport;
import com.example.websocketdemo.model.Order;
import com.example.websocketdemo.repository.FeedbackReportRepository;
import com.example.websocketdemo.repository.OrderRepository;

@Service("FeedbackReportService")
public class FeedbackReportService implements FeedbackReportRepository {

	private static final AtomicLong counter = new AtomicLong();

	private static List<FeedbackReport> feedbackreports;

//	static{
//			feedbackreports= populateDummyReports();
//	}

	public List<FeedbackReport> findAllFeedbackReports() {
			return feedbackreports;
	}

	public FeedbackReport findById(long id) {
			for(FeedbackReport feedbackreport : feedbackreports){
					if(feedbackreport.getCrisisID() == id){
							return feedbackreport;
					}
			}
			return null;
	}

	public void saveFeedbackReport(FeedbackReport feedbackreport) {
		feedbackreports.add(feedbackreport);
	}

	public boolean isFeedbackReportExist(FeedbackReport feedbackreport) {
			return findById(feedbackreport.getCrisisID())!=null;
	}

	public void deleteFeedbackReport(FeedbackReport feedbackreport) {
		feedbackreports.remove(feedbackreport);
	}

//	private static List<FeedbackReport> populateDummyReports(){
//			List<FeedbackReport> feedbackreport = new ArrayList<FeedbackReport>();
//			feedbackreports.add(new Feedbackreport(counter.incrementAndGet(), "Test1", "Analyst" , 4, "Type1", "Area1", "Detail1", "Action1"));
//			feedbackreports.add(new Feedbackreport(counter.incrementAndGet(), "Test2", "General" , 5, "Type2", "Area2", "Detail2", "Action2"));
//			feedbackreports.add(new Feedbackreport(counter.incrementAndGet(), "Test3", "General" , 4, "Type3", "Area3", "Detail3", "Action3"));
//			feedbackreports.add(new Feedbackreport(counter.incrementAndGet(), "Test4", "General" , 5, "Type4", "Area4", "Detail4", "Action4"));
//			return feedbackreports;
//	}
	
}
