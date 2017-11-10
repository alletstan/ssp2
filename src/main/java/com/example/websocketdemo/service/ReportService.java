package com.example.websocketdemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.example.websocketdemo.model.Order;
import com.example.websocketdemo.model.Report;
import com.example.websocketdemo.repository.OrderRepository;
import com.example.websocketdemo.repository.ReportRepository;

@Service("ReportService")
public class ReportService implements ReportRepository {

	private static final AtomicLong counter = new AtomicLong();

	private static List<Report> reports;

//	static{
//		reports=populateDummyReports();
//	}

	public List<Report> findAllReports() {
			return reports;
	}

	public Report findById(long id) {
			for(Report report : reports){
					if(report.getStructuredReportId() == id){
							return report;
					}
			}
			return null;
	}

	public void saveReport(Report report) {
		reports.add(report);
	}

	public boolean isReportExist(Report report) {
			return findById(report.getStructuredReportId())!=null;
	}

	public void deleteReport(Report report) {
		reports.remove(report);
	}

//	private static List<Report> populateDummyReports(){
//			List<Report> reports = new ArrayList<Report>();
//			reports.add(new Report(counter.incrementAndGet(), "10/11/2017", "Analyst" , 4, "Type1", "Area1", "Detail1", "Action1"));
//			reports.add(new Report(counter.incrementAndGet(), "Test2", "General" , 5, "Type2", "Area2", "Detail2", "Action2"));
//			reports.add(new Report(counter.incrementAndGet(), "Test3", "General" , 4, "Type3", "Area3", "Detail3", "Action3"));
//			reports.add(new Report(counter.incrementAndGet(), "Test4", "General" , 5, "Type4", "Area4", "Detail4", "Action4"));
//			return reports;
//	}

}
