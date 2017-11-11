//Client side
//Specify their URI
//Create a method like createReport() to POST to their service
package com.example.websocketdemo.cmoapi;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.websocketdemo.model.FeedbackReport;
import com.example.websocketdemo.model.Order;

public class EFClient {

	// public static final String REST_SERVICE_URI =
	// "http://10.27.113.59:8080/EFtoCMO";
	public static final String REST_SERVICE_URI = "http://localhost:8080/EFtoCMO";

	// GET
	@SuppressWarnings({ "unchecked" })
	public static void listLatestFeedbackReport() {
		int i = 1;
		System.out.println("Testing receiving Feedback Report API-----------");

		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> feedbackReportsMap = restTemplate
				.getForObject(REST_SERVICE_URI + "/feedbackReport/", List.class);

		if (feedbackReportsMap != null) {
			for (LinkedHashMap<String, Object> map : feedbackReportsMap) {
				if (i++ == feedbackReportsMap.size()) {
					// end

					System.out.println("crisisID=" + map.get("crisisID") + "," + " name=" + map.get("name") + ","
							+ " positionInEF=" + map.get("positionInEF") + "," + " threatLevel="
							+ map.get("threatLevel") + "," + " casualtiesRescued=" + map.get("casualtiesRescued") + ","
							+ " deploymentStatus=" + map.get("deploymentStatus") + "," + " situationStatus="
							+ map.get("situationStatus"));
				}
			}
		} else {
			System.out.println("No report exist----------");
		}
	}

	// GET
	@SuppressWarnings({ "unchecked" })
	public static ResponseEntity<List<Order>> listLatestOrders() {
		int i = 1;
		System.out.println("Testing receiving Order API-----------");

		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> ordersMap = restTemplate.getForObject(REST_SERVICE_URI + "/order/",
				List.class);

		if (ordersMap != null) {
			for (LinkedHashMap<String, Object> map : ordersMap) {
				if (i++ == ordersMap.size()) {
					// end

					System.out.println("crisisID=" + map.get("crisisID") + "," + " name=" + map.get("name") + ","
							+ " positionInCMO=" + map.get("positionInCMO") + "," + " threatLevel="
							+ map.get("threatLevel") + "," + " affectedArea=" + map.get("affectedArea") + ","
							+ " crisisDetails=" + map.get("crisisDetails") + "," + " courseofAction="
							+ map.get("courseofAction"));
				}
				return (ResponseEntity<List<Order>>) ordersMap;
			}
		} else {
			System.out.println("No order exist----------");
		}
		return null;
	}

	// GET
	@SuppressWarnings({ "unchecked" })
	public static void listAllFeedbackReports() {
		System.out.println("Testing receiving Feedback Report API-----------");

		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> feedbackReportsMap = restTemplate
				.getForObject(REST_SERVICE_URI + "/feedbackReport/", List.class);

		if (feedbackReportsMap != null) {
			for (LinkedHashMap<String, Object> map : feedbackReportsMap) {

				System.out.println("crisisID=" + map.get("crisisID") + "," + " name=" + map.get("name") + ","
						+ " positionInEF=" + map.get("positionInEF") + "," + " threatLevel=" + map.get("threatLevel")
						+ "," + " casualtiesRescued=" + map.get("casualtiesRescued") + "," + " deploymentStatus="
						+ map.get("deploymentStatus") + "," + " situationStatus=" + map.get("situationStatus"));
			}
		} else {
			System.out.println("No report exist----------");
		}
	}

	// GET
	@SuppressWarnings({ "unchecked" })
	public static void listAllOrders() {
		System.out.println("Testing receiving Order API-----------");

		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> ordersMap = restTemplate.getForObject(REST_SERVICE_URI + "/order/",
				List.class);

		if (ordersMap != null) {
			for (LinkedHashMap<String, Object> map : ordersMap) {
				System.out.println("crisisID=" + map.get("crisisID") + "," + " name=" + map.get("name") + ","
						+ " positionInCMO=" + map.get("positionInCMO") + "," + " threatLevel=" + map.get("threatLevel")
						+ "," + " affectedArea=" + map.get("affectedArea") + "," + " crisisDetails="
						+ map.get("crisisDetails") + "," + " courseofAction=" + map.get("courseofAction"));
			}
		} else {
			System.out.println("No order exist----------");
		}
	}

	// GET
	public static void getFeedbackReport() {
		System.out.println("Testing get Feedback Report API----------");

		RestTemplate restTemplate = new RestTemplate();
		FeedbackReport feedbackReport = restTemplate.getForObject(REST_SERVICE_URI + "/feedbackReport/1",
				FeedbackReport.class);
		System.out.println(feedbackReport);
	}
	
	// GET
	public static void getOrder() {
		System.out.println("Testing get Order API----------");

		RestTemplate restTemplate = new RestTemplate();
		Order order = restTemplate.getForObject(REST_SERVICE_URI + "/order/1", Order.class);
		System.out.println(order);
	}

	// POST Feedback Report
	public static boolean createFeedbackReport(FeedbackReport feedbackReport) {
		System.out.println("Testing create Feedback Report API----------");
		System.out.println(feedbackReport);

		boolean success;

		RestTemplate restTemplate = new RestTemplate();
		try {
			success = restTemplate
					.postForEntity(REST_SERVICE_URI + "/feedbackReport/", feedbackReport, FeedbackReport.class)
					.getStatusCode().is2xxSuccessful();
		} catch (Exception e) {
			success = false;
		}
		System.out.println(success);
		return success;
		// System.out.println("Location : " + uri.toASCIIString());
	}
	
	// POST Feedback Report
		public static boolean createOrder(Order order) {
			System.out.println("Testing create Order API----------");
			System.out.println(order);

			boolean success;

			RestTemplate restTemplate = new RestTemplate();
			try {
				success = restTemplate
						.postForEntity(REST_SERVICE_URI + "/order/", order, Order.class)
						.getStatusCode().is2xxSuccessful();
			} catch (Exception e) {
				success = false;
			}
			System.out.println(success);
			return success;
			// System.out.println("Location : " + uri.toASCIIString());
		}

}