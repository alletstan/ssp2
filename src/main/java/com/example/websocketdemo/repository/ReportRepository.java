package com.example.websocketdemo.repository;

import java.util.List;

import com.example.websocketdemo.model.Report;

public interface ReportRepository {

	Report findById(long id);

	void saveReport(Report report);

	List<Report> findAllReports();

	boolean isReportExist(Report report);

}
