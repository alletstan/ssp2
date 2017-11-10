//Server side
package com.example.websocketdemo.controller;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.websocketdemo.model.FeedbackReport;
import com.example.websocketdemo.model.Order;
import com.example.websocketdemo.model.Report;
import com.example.websocketdemo.repository.OrderRepository;
import com.example.websocketdemo.cmoapi.EFClient;
import com.example.websocketdemo.config.CustomErrorType;

@RestController
@RequestMapping("/EFtoCMO")
public class EFApiController {

	public static final Logger logger = LoggerFactory.getLogger(EFApiController.class);

	@Autowired
	ReportController reportController;
	@Autowired
	FeedbackReportController feedbackReportController;
	@Autowired
	OrderController orderController;

	@Autowired
	OrderRepository orderRepository;

	private static final String ERRORPATH = "/error";

	@RequestMapping("/")
	public ModelAndView mainPage(ModelMap model) {
		System.out.println("Here is main page");
		List<Report> reports = reportController.getAllReports();
		model.put("reportList", reports);
		
		return new ModelAndView("efUI");
	}
//
//	@RequestMapping("/home")
//	public ModelAndView homePage(ModelMap model) {
//		System.out.println("Here is the ef page");
//		ResponseEntity<List<Order>> orders = listAllOrders();
//		model.put("orderList", orders);
//		return new ModelAndView("efUI");
//	}



	// -------------------Retrieve All
	// Reports---------------------------------------------

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/order/", method = RequestMethod.GET)
	public ResponseEntity<List<Order>> listAllOrders() {
		List<Order> orders = orderRepository.findAllOrders();
		if (orders.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// Report------------------------------------------

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/order/{crisisID}", method = RequestMethod.GET)
	public ResponseEntity<?> getOrder(@PathVariable("crisisID") long crisisID) {
		logger.info("Fetching Order with crisisID {}", crisisID);
		Order order = orderRepository.findById(crisisID);
		if (order == null) {
			logger.error("Order with crisisID {} not found.", crisisID);
			return new ResponseEntity(new CustomErrorType("Order with crisisID " + crisisID + " not found"),
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}

	// -------------------Create a
	// Report-------------------------------------------

	// @SuppressWarnings({ "unchecked", "rawtypes" })
	// @RequestMapping(value = "/order/", method = RequestMethod.POST)
	// public ResponseEntity<?> createOrder(@RequestBody Order order,
	// UriComponentsBuilder ucBuilder) {
	// logger.info("Creating Order : {}", order);
	//
	// if (orderRepository.isOrderExist(order)) {
	// logger.error("Unable to create. An Order with name {} already exist",
	// order.getCrisisID());
	// return new ResponseEntity(new CustomErrorType("Unable to create. An Order
	// with crisisID " +
	// order.getCrisisID() + " already exist."),HttpStatus.CONFLICT);
	// }
	// orderRepository.saveOrder(order);
	//
	// HttpHeaders headers = new HttpHeaders();
	// headers.setLocation(ucBuilder.path("/CMOtoEF/order/{crisisID}").buildAndExpand(order.getCrisisID()).toUri());
	// return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	// }

	// TODO: Get method and change UI

	@RequestMapping(value = "/addReport", method = RequestMethod.POST)
	public ModelAndView createReport(@RequestParam String datetime, String location, String description,
			String assetsActivated, String timeToFullDeploy, String timelyUpdate, String incidentEndDateTime,
			String casualtyAssessment, String damageAssessment, @RequestParam String action, ModelMap model) {

		System.out.println(description);
		
		Random rand = new Random();
		int reportId = rand.nextInt(99) + 1;
		
		Report temp = new Report();
		temp.setStructuredReportId(reportId);
		temp.setName("Peter");
		temp.setPositionInEf("General");
		temp.setIncidentDateTime(datetime.toString());
		temp.setIncidentLocation(location);
		temp.setIncidentDescription(description);
		temp.setAssetsActivated(assetsActivated);
		temp.setTimeToFullDeploy(timeToFullDeploy);
		temp.setTimelyUpdate(timelyUpdate);
		temp.setEndIncidentDateTime(incidentEndDateTime);
		temp.setCasualtyAssessment(casualtyAssessment);
		temp.setDamageAssesment(damageAssessment);
		
		System.out.println(temp);

		if (action.equals("send")) {
			EFClient.createReport(temp);
		}
		reportController.addReport(temp);
		return new ModelAndView("/");
	}

	@RequestMapping(value = "/addFeedbackReport", method = RequestMethod.POST)
	public ModelAndView createFeedbackReport(@RequestParam int threatLevel, @RequestParam int casualtiesRescued,
			@RequestParam String deploymentStatus, @RequestParam String situationStatus, @RequestParam String action,
			ModelMap model) {
		
		System.out.println(situationStatus);

		Random rand = new Random();
		long crisisId = rand.nextInt() + 1;

		FeedbackReport temp = new FeedbackReport();
		temp.setCrisisID(crisisId);
		temp.setName("Peter");
		temp.setPositionInEF("General");
		temp.setThreatLevel(threatLevel);
		temp.setCasualtiesRescued(casualtiesRescued);
		temp.setDeploymentStatus(deploymentStatus);
		temp.setSituationStatus(situationStatus);
		
		System.out.println(temp);

		if (action.equals("send")) {
			EFClient.createFeedbackReport(temp);
		}
		feedbackReportController.addFeedbackReport(temp);

		return new ModelAndView("/");
	}
	

}