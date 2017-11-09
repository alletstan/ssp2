//Client side
//Specify their URI
//Create a method like createReport() to POST to their service
package com.example.client;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.example.websocketdemo.model.FeedbackReport;

public class EFClient {

	public static final String REST_SERVICE_URI = "http://10.27.153.196:8080/EFtoCMO";

	// GET
	@SuppressWarnings({ "unchecked"})
	private static void listLatestFeedbackReport() {
		int i = 1;
		System.out.println("Testing receiving Feedback Report API-----------");

		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> feedbackReportsMap = restTemplate.getForObject(REST_SERVICE_URI + "/feedbackReport/",
				List.class);

		if (feedbackReportsMap != null) {
			for (LinkedHashMap<String, Object> map : feedbackReportsMap) {
				if (i++ == feedbackReportsMap.size()) {
					// end
					
					System.out.println("crisisID=" + map.get("crisisID") + "," + " name=" + map.get("name") + ","
							+ " positionInEF=" + map.get("positionInEF") + "," + " threatLevel=" + map.get("threatLevel") + ","
							+ " casualtiesRescued=" + map.get("casualtiesRescued") + ","
							+ " deploymentStatus=" + map.get("deploymentStatus") + ","
							+ " situationStatus=" + map.get("situationStatus"));
				}
			}
		} else {
			System.out.println("No report exist----------");
		}
	}

	// GET
	@SuppressWarnings({"unchecked" })
	private static void listAllFeedbackReports() {
		System.out.println("Testing receiving Feedback Report API-----------");

		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> feedbackReportsMap = restTemplate.getForObject(REST_SERVICE_URI + "/feedbackReport/",
				List.class);

		if (feedbackReportsMap != null) {
			for (LinkedHashMap<String, Object> map : feedbackReportsMap) {

				System.out.println("crisisID=" + map.get("crisisID") + "," + " name=" + map.get("name") + ","
						+ " positionInEF=" + map.get("positionInEF") + "," + " threatLevel=" + map.get("threatLevel") + ","
						+ " casualtiesRescued=" + map.get("casualtiesRescued") + ","
						+ " deploymentStatus=" + map.get("deploymentStatus") + ","
						+ " situationStatus=" + map.get("situationStatus"));
			}
		} else {
			System.out.println("No report exist----------");
		}
	}

	// GET
	private static void getFeedbackReport() {
		System.out.println("Testing get Feedback Report API----------");

		RestTemplate restTemplate = new RestTemplate();
		FeedbackReport feedbackReport = restTemplate.getForObject(REST_SERVICE_URI + "/feedbackReport/1", FeedbackReport.class);
		System.out.println(feedbackReport);
	}

	// POST 
	private static void createFeedbackReport() {
	System.out.println("Testing create Feedback Report API----------");

	RestTemplate restTemplate = new RestTemplate();
	FeedbackReport feedbackReport = new FeedbackReport(10, "Test10", "EF Liaison Officer" , 4, 10000, "Deployment10", "Situation10");
	URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "/feedbackReport/", feedbackReport,
			FeedbackReport.class);System.out.println("Location : "+uri.toASCIIString());
	}

	public static void main(String args[]) {
		createFeedbackReport();
		getFeedbackReport();
		listAllFeedbackReports();
		listLatestFeedbackReport();
	}
}