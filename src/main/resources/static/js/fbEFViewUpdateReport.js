'use strict';

var dbUpdate = null;
var updateKey = null;
var today = null;
var updatestatus = null;
var updateNum = 0;

var datetimetitle = document.querySelector('#datetimetitle');
var datetimetext = document.querySelector('#datetimetext');
var nametitle = document.querySelector('#nametitle');
var nametext = document.querySelector('#nametext');
var positiontitle = document.querySelector('#positiontitle');
var positiontext = document.querySelector('#positiontext');
var threatleveltitle = document.querySelector('#threatleveltitle');
var threatleveltext = document.querySelector('#threatleveltext');
var areadeployedtitle = document.querySelector('#areadeployedtitle');
var areadeployedtext = document.querySelector('#areadeployedtext');
var deploymentstatustitle = document.querySelector('#deploymentstatustitle');
var deploymentstatustext = document.querySelector('#deploymentstatustext');
var notestitle = document.querySelector('#notestitle');
var notestext = document.querySelector('#notestext');

var updatepage = document.querySelector('#update-page');
var reportpage = document.querySelector('#report-page');

var dbReport = null;
var reportKey = null;
var reportstatus = null;
var reportNum = 0;

var reportdatetimetitle = document.querySelector('#reportdatetimetitle');
var reportdatetimetext = document.querySelector('#reportdatetimetext');
var incidentdatetimetitle = document.querySelector('#incidentdatetimetitle');
var incidentdatetimetext = document.querySelector('#incidentdatetimetext');
var locationtitle = document.querySelector('#locationtitle');
var locationtext = document.querySelector('#locationtext');
var descriptiontitle = document.querySelector('#descriptiontitle');
var descriptiontext = document.querySelector('#descriptiontext');
var assetactivatedtitle = document.querySelector('#assetactivatedtitle');
var assetactivatedtext = document.querySelector('#assetactivatedtext');
var timetakenfulldeploytitle = document.querySelector('#timetakenfulldeploytitle');
var timetakenfulldeploytext = document.querySelector('#timetakenfulldeploytext');
var timelyupdatetitle = document.querySelector('#timelyupdatetitle');
var timelyupdatetext = document.querySelector('#timelyupdatetext');
var incidentenddatetimetitle = document.querySelector('#incidentenddatetimetitle');
var incidentenddatetimetext = document.querySelector('#incidentenddatetimetext');
var casualtyassessmenttitle = document.querySelector('#casualtyassessmenttitle');
var casualtyassessmenttext = document.querySelector('#casualtyassessmenttext');
var damageassessmenttitle = document.querySelector('#damageassessmenttitle');
var damageassessmenttext = document.querySelector('#damageassessmenttext');

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
	dbUpdate = firebase.database().ref("update");
	dbReport = firebase.database().ref("report");

	// Get latest update
	getupdate();
	getreport();
}

function getupdate() {
	dbUpdate
			.limitToFirst(1)
			.orderByChild("isAcknowledged_datetime")
			.startAt("false")
			.on(
					"child_added",
					function(snapshot) {
						updateKey = snapshot.key;

						let updateDetails = snapshot.val();

						let datetime = updateDetails.datetime;
						let IFname = updateDetails.ifName;
						let position = updateDetails.position;
						let threatLevel = updateDetails.threatLevel;
						let areaDeployed = updateDetails.areaDeployed;
						let deploymentStatus = updateDetails.deploymentStatus;
						let notes = updateDetails.notes;
						let isAcknowledged = updateDetails.isAcknowledged;

						if (isAcknowledged == "false") {
							window.alert("You have a update from IF sent on "
									+ datetime);
							
							updateNum++;

							datetimetitle.innerHTML = "Date and Time of Update"
							nametitle.innerHTML = "Name";
							positiontitle.innerHTML = "Position in IF"
							threatleveltitle.innerHTML = "Threat Level";
							areadeployedtitle.innerHTML = "Area Deployed";
							deploymentstatustitle.innerHTML = "Deployment Status";
							notestitle.innerHTML = "Notes";

							datetimetext.innerHTML = datetime;
							nametext.innerHTML = IFname;
							positiontext.innerHTML = position;
							threatleveltext.innerHTML = threatLevel;
							areadeployedtext.innerHTML = areaDeployed;
							deploymentstatustext.innerHTML = deploymentStatus;
							notestext.innerHTML = notes;

						}

						if (updateNum == 0) {
							updatepage.style.visibility = 'hidden';
						}

					}

			)
}

function getreport() {
	dbReport.limitToFirst(1).orderByChild("isAcknowledged_datetime").startAt(
			"false").on("child_added", function(snapshot) {
		reportKey = snapshot.key;

		let reportDetails = snapshot.val();

		let reportDateTime = reportDetails.reportDateTime;
		let incidentDateTime = reportDetails.incidentDateTime;
		let incidentLocation = reportDetails.incidentLocation;
		let incidentDescription = reportDetails.incidentDescription;
		let assetActivated = reportDetails.assetActivated;
		let timeTakenFullDeploy = reportDetails.timeTakenFullDeploy;
		let timelyUpdate = reportDetails.timelyUpdate;
		let incidentEndDateTime = reportDetails.incidentEndDateTime;
		let casualtyAssessment = reportDetails.casualtyAssessment;
		let damageAssessment = reportDetails.damageAssessment;
		let isAcknowledged = reportDetails.isAcknowledged;

		if (isAcknowledged == "false") {
			window.alert("You have a report from IF sent on " + reportDateTime);

			reportNum++;

			reportdatetimetitle.innerHTML = "Date and Time of Report";
			incidentdatetimetitle.innerHTML = "Date and Time of Incident";
			locationtitle.innerHTML = "Location of Incident";
			descriptiontitle.innerHTML = "Incident Description";
			assetactivatedtitle.innerHTML = "Asset(s) Activated";
			timetakenfulldeploytitle.innerHTML = "Time Taken for Full Deployment in Minutes";
			timelyupdatetitle.innerHTML = "Timely Update";
			incidentenddatetimetitle.innerHTML = "End Date and Time of Incident";
			casualtyassessmenttitle.innerHTML = "Casualty Assessment";
			damageassessmenttitle.innerHTML = "Damage Assessment";

			reportdatetimetext.innerHTML = reportDateTime;
			incidentdatetimetext.innerHTML = incidentDateTime;
			locationtext.innerHTML = incidentLocation;
			descriptiontext.innerHTML = incidentDescription;
			assetactivatedtext.innerHTML = assetActivated;
			timetakenfulldeploytext.innerHTML = timeTakenFullDeploy;
			timelyupdatetext.innerHTML = timelyUpdate;
			incidentenddatetimetext.innerHTML = incidentEndDateTime;
			casualtyassessmenttext.innerHTML = casualtyAssessment;
			damageassessmenttext.innerHTML = damageAssessment;

		}

		if (reportNum == 0) {
			reportpage.style.visibility = 'hidden';
		}

	}

	)
}


function updateAcknowledged() {
	var updates = {};
	let today = moment(new Date()).format('DD-MM-YYYY HH:mm:SS a');

	updates['/' + updateKey + '/isAcknowledged'] = "true";
	updates['/' + updateKey + '/isAcknowledged_datetime'] = "true " + today;

	updatestatus = dbUpdate.update(updates);
	if (updatestatus != null) {
		window.alert("Update is acknowledged");
		updatestatus = null;
		updateNum--;
		getupdate();
	}
}

function reportAcknowledged() {
	var reports = {};
	let today = moment(new Date()).format('DD-MM-YYYY HH:mm:SS a');

	reports['/' + reportKey + '/isAcknowledged'] = "true";
	reports['/' + reportKey + '/isAcknowledged_datetime'] = "true " + today;

	reportstatus = dbReport.update(reports);
	if (reportstatus != null) {
		window.alert("Report is acknowledged");
		reportstatus = null;
		reportNum--;
		getreport();
	}
}

window.addEventListener("load", connect, true)