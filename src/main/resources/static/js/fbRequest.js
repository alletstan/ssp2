'use strict';

var dbRequest = null;
var requestKey = null;
var today = null;
var updatestatus = null;
var requestNum = 0;

var datetimetitle = document.querySelector('#datetimetitle');
var datetimetext = document.querySelector('#datetimetext');
var threatleveltitle = document.querySelector('#threatleveltitle');
var threatleveltext = document.querySelector('#threatleveltext');
var crisistypetitle = document.querySelector('#crisistypetitle');
var crisistypetext = document.querySelector('#crisistypetext');
var affectedareatitle = document.querySelector('#affectedareatitle');
var affectedareatext = document.querySelector('#affectedareatext');
var crisisdetailsthreatleveltitle = document
		.querySelector('#crisisdetailstitle');
var crisisdetailstext = document.querySelector('#crisisdetailstext');
var recommendedactiontitle = document.querySelector('#recommendedactiontitle');
var recommendedactiontext = document.querySelector('#recommendedactiontext');
var forcedeploymenttitle = document.querySelector('#forcedeploymenttitle');
var forcedeploymenttext = document.querySelector('#forcedeploymenttext');
var requestpage = document.querySelector('#request-page');

function connect() {

	// Initialize Firebase
	var config = {
		apiKey : "AIzaSyD-C2KtzNA8_8h84urE2Y8ulk80uit72Ac",
		authDomain : "ssp2ef.firebaseapp.com",
		databaseURL : "https://ssp2ef.firebaseio.com",
		projectId : "ssp2ef",
		storageBucket : "ssp2ef.appspot.com",
		messagingSenderId : "616498327"
	};

	firebase.initializeApp(config);

	// Create references
	dbRequest = firebase.database().ref("request");

	// Add data
	// With moment.js
	// let today = moment(new Date()).format('DD-MM-YYYY HH:mm:SS a');
	//
	// var message = {
	// "datetime" : today,
	// "threatLevel" : 1,
	// "crisisType" : "Test4",
	// "affectedArea" : "West Area",
	// "crisisDetails" : "Zombie Virus has Spread",
	// "recommendedAction" : "Kill every zombie",
	// "deploymentForce" : "Police, Ambulance, SWAT",
	// "isAcknowledged" : "false",
	// "isAcknowledged_datetime" : "false " + today
	// }
	// dbRequest.push(message);

	// Get latest request
	getrequest();
}

function getrequest() {
	dbRequest
			.limitToFirst(1)
			.orderByChild("isAcknowledged_datetime")
			.startAt("false")
			.on(
					"child_added",
					function(snapshot) {
						requestKey = snapshot.key;

						let requestDetails = snapshot.val();

						let requestDateTime = requestDetails.datetime;
						let threatLevel = requestDetails.threatLevel;
						let crisisType = requestDetails.crisisType;
						let affectedArea = requestDetails.affectedArea;
						let crisisDetails = requestDetails.crisisDetails;
						let recommendedAction = requestDetails.recommendedAction;
						let deploymentForce = requestDetails.deploymentForce;
						let isAcknowledged = requestDetails.isAcknowledged;

						if (isAcknowledged == "false") {

							requestNum++;

							datetimetitle.innerHTML = "Date and Time of Request";
							threatleveltitle.innerHTML = "Threat Level";
							crisistypetitle.innerHTML = "Crisis Type";
							affectedareatitle.innerHTML = "Affected Areas";
							crisisdetailstitle.innerHTML = "Crisis Details";
							recommendedactiontitle.innerHTML = "Recommended Course of Action";
							forcedeploymenttitle.innerHTML = "Force to be Deployed";

							datetimetext.innerHTML = requestDateTime;
							threatleveltext.innerHTML = threatLevel;
							crisistypetext.innerHTML = crisisType;
							affectedareatext.innerHTML = affectedArea;
							crisisdetailstext.innerHTML = crisisDetails;
							recommendedactiontext.innerHTML = recommendedAction;
							forcedeploymenttext.innerHTML = deploymentForce;

							window
									.alert("You have a new request from EF sent on "
											+ requestDateTime);
						}

						if (requestNum == 0) {
							requestpage.style.display = 'none';
						}

					}

			)
}

function setAcknowledged() {
	var updates = {};
	let today = moment(new Date()).format('DD-MM-YYYY HH:mm:SS a');

	updates['/' + requestKey + '/isAcknowledged'] = "true";
	updates['/' + requestKey + '/isAcknowledged_datetime'] = "true " + today;

	updatestatus = dbRequest.update(updates);
	if (updatestatus != null) {
		window.alert("Request is acknowledged");
		updatestatus = null;
		requestNum--;
		getrequest();
	}
}

window.addEventListener("load", connect, true)