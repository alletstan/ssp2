//Server side
package com.example.websocketdemo.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.example.websocketdemo.cmoapi.EFClient;
import com.example.websocketdemo.model.FeedbackReport;
import com.example.websocketdemo.model.Order;

@RestController
public class EFApiController {

	public static final Logger logger = LoggerFactory.getLogger(EFApiController.class);

	@Autowired
	FeedbackReportController feedbackReportController;
	@Autowired
	OrderController orderController;

	// private static final String ERRORPATH = "/error";

	@RequestMapping("/")
	public ModelAndView mainPage(ModelMap model) {
		String userInSession = null;

		if (model.get("username") != null) {
			userInSession = (String) model.get("username");
		}
		if (userInSession == null)
			return new ModelAndView("redirect:/index.html");
		else if (userInSession == "efpersonnel"){
			return new ModelAndView("redirect:/home");
		}
		else {
			return new ModelAndView("redirect:/ifUI.html");
		}
	}

	@RequestMapping("/home")
	public ModelAndView homePage(ModelMap model) {
//		if (model.get("username") == null) {
//			model.put("message", "Please log in first.");
//			model.put("redirect", "/");
//			return new ModelAndView("redirect:/message");
//		} else {
			List<LinkedHashMap<String, Object>> orders = EFClient.listLatestOrders();
			model.put("orderList", orders);
			return new ModelAndView("redirect:/efUI.html");
//		}

	}
	
	@RequestMapping(value = "/EFtoCMO/order/latest", method = RequestMethod.GET)
	public @ResponseBody List<LinkedHashMap<String, Object>> getLatestOrders(ModelMap model){
		List<LinkedHashMap<String, Object>> orders = EFClient.listLatestOrders();
		model.put("orderList", orders);
		return orders;
	}



	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView handleLoginRequest(@RequestParam String username, ModelMap model) {

		if (username == null) {
			model.put("message", "Invalid Credentials. Please re-login again.");
			model.put("redirect", "/");
			return new ModelAndView("/message");
		} else if (username.contentEquals("efpersonnel")){
			model.put("username", username);
			return new ModelAndView("redirect:/home");
		}
		else {
			model.put("username", username);
			return new ModelAndView("redirect:/ifUI.html");
		}
	}

	// -------------------Retrieve All
	// Reports---------------------------------------------

	// @SuppressWarnings({ "unchecked", "rawtypes" })
	// @RequestMapping(value = "/order/", method = RequestMethod.GET)
	// public ResponseEntity<List<Order>> listAllOrders() {
	// List<Order> orders = orderRepository.findAllOrders();
	// if (orders.isEmpty()) {
	// return new ResponseEntity(HttpStatus.NO_CONTENT);
	// // You many decide to return HttpStatus.NOT_FOUND
	// }
	// return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	// }

	// -------------------Retrieve Single
	// Report------------------------------------------

	// @SuppressWarnings({ "unchecked", "rawtypes" })
	// @RequestMapping(value = "/order/{crisisID}", method = RequestMethod.GET)
	// public ResponseEntity<?> getOrder(@PathVariable("crisisID") long
	// crisisID) {
	// logger.info("Fetching Order with crisisID {}", crisisID);
	// Order order = orderRepository.findById(crisisID);
	// if (order == null) {
	// logger.error("Order with crisisID {} not found.", crisisID);
	// return new ResponseEntity(new CustomErrorType("Order with crisisID " +
	// crisisID + " not found"),
	// HttpStatus.NOT_FOUND);
	// }
	//
	// return new ResponseEntity<Order>(order, HttpStatus.OK);
	// }

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

	// @RequestMapping(value = "/addReport", method = RequestMethod.POST)
	// public ModelAndView createReport(@RequestParam String datetime, String
	// location, String description,
	// String assetsActivated, String timeToFullDeploy, String timelyUpdate,
	// String incidentEndDateTime,
	// String casualtyAssessment, String damageAssessment, @RequestParam String
	// action, ModelMap model) {
	//
	// System.out.println(description);
	//
	// Random rand = new Random();
	// int reportId = rand.nextInt(99) + 1;
	//
	// Report temp = new Report();
	// temp.setStructuredReportId(reportId);
	// temp.setName("Peter");
	// temp.setPositionInEf("General");
	// temp.setIncidentDateTime(datetime.toString());
	// temp.setIncidentLocation(location);
	// temp.setIncidentDescription(description);
	// temp.setAssetsActivated(assetsActivated);
	// temp.setTimeToFullDeploy(timeToFullDeploy);
	// temp.setTimelyUpdate(timelyUpdate);
	// temp.setEndIncidentDateTime(incidentEndDateTime);
	// temp.setCasualtyAssessment(casualtyAssessment);
	// temp.setDamageAssesment(damageAssessment);
	//
	// System.out.println(temp);
	//
	// if (action.equals("send")) {
	// EFClient.createReport(temp);
	// }
	// reportController.addReport(temp);
	// return new ModelAndView("/");
	// }

	@RequestMapping(value = "/EFtoCMO/feedbackReport/new", method = RequestMethod.POST)
	public ModelAndView createFeedbackReport(@RequestParam String threatLevel, @RequestParam String casualtiesRescued,
			@RequestParam String deploymentStatus, @RequestParam String situationStatus, @RequestParam String action) {

		System.out.println(situationStatus);

		Random rand = new Random();
		long crisisID = rand.nextInt(99) + 1;

		FeedbackReport temp = new FeedbackReport(crisisID, "Peter", "General", Integer.valueOf(threatLevel),
				Integer.valueOf(casualtiesRescued), deploymentStatus, situationStatus);

		System.out.println("Report to be sent is " + temp);

		feedbackReportController.addFeedbackReport(temp);
		boolean success = EFClient.createFeedbackReport(temp);

		return new ModelAndView("redirect:/reportmessage.html");
	}

	@RequestMapping(value = "/EFtoCMO/order/new", method = RequestMethod.POST)
	public ModelAndView createFeedbackReport() {

		Order temp = new Order(1, "Peter", "General", 1, "Test1", "Test1", "Test1", "Test1");

		System.out.println("Order to be sent is " + temp);

		orderController.addOrder(temp);
		boolean success = EFClient.createOrder(temp);

		return new ModelAndView("redirect:/reportmessage.html");
	}

}