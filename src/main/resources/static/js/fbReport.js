'use strict';

var reportDetails = document.querySelector('#reportDetails');
var dbReport = null;
var pushedItemId = null;

function connect() {

	// Initialize Firebase
	const config = {
		apiKey : "AIzaSyD-C2KtzNA8_8h84urE2Y8ulk80uit72Ac",
		authDomain : "ssp2ef.firebaseapp.com",
		databaseURL : "https://ssp2ef.firebaseio.com",
		projectId : "ssp2ef",
		storageBucket : "ssp2ef.appspot.com",
		messagingSenderId : "616498327"
	};
	firebase.initializeApp(config);

	// Create references
	dbReport = firebase.database().ref("report");
	console.log(dbReport);
}

function sendreport() {
	// Get elements
	var incidentDate = document.querySelector('#incidentDate').value;
	var incidentLocation = document.querySelector('#incidentLocation').value;
	var incidentDescription = document.querySelector('#incidentDescription').value;
	var assetActivated = document.querySelector('#assetActivated').value;
	var timeTakenFullDeploy = document.querySelector('#timeTakenFullDeploy').value;
	var timelyReport = document.querySelector('#timelyReport').value;
	var incidentEndDate = document.querySelector('#incidentEndDate').value;
	var casualtyAssessment = document.querySelector('#casualtyAssessment').value;
	var damageAssessment = document.querySelector('#damageAssessment').value;


	let today = moment(new Date()).format('DD-MM-YYYY HH:mm:SS a');

	let report = {
		"reportDateTime" : today,
		"incidentDate" : incidentDate,
		"incidentLocation" : incidentLocation,
		"incidentDescription" : incidentDescription,
		"assetActivated" : assetActivated,
		"timeTakenFullDeploy" : timeTakenFullDeploy,
		"timelyReport" : timelyReport,
		"incidentEndDate" : incidentEndDate,
		"casualtyAssessment" : casualtyAssessment,
		"damageAssessment" : damageAssessment,
		"isAcknowledged" : "false"
	};
	console.log(report);
	var pushedItem = dbReport.push(report);
	pushedItemId = pushedItem.key;
	console.log(pushedItem.key);
	window.alert("Report Sent");
};

window.addEventListener("load", connect, true)
reportDetails.addEventListener('submit', sendreport, true)