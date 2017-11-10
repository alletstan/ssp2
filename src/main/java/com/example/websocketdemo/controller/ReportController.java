package com.example.websocketdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.websocketdemo.model.Report;
import com.example.websocketdemo.service.ReportService;

@RestController
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@RequestMapping("/report")
	public List<Report> getAllReports(){
		return reportService.findAllReports();
	}
	
	//curly braces for inputs
	@RequestMapping("/report/{id}")
	public Report getReport(@PathVariable int reportID){ //need use @pathvariable. convention to keep names same
		return reportService.findById(reportID);
	}
	
	//method to save report
	@RequestMapping(method=RequestMethod.POST, value="/report")
	public void addReport(@RequestBody Report report){
		reportService.saveReport(report);
	}
	
	
	@RequestMapping(method=RequestMethod.DELETE, value="/report/{reportID}")
	public void deleteReport(@RequestBody Report report){
		reportService.deleteReport(report);
	}

}