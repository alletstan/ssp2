'use strict';

var updateDetails = document.querySelector('#updateDetails');
var dbUpdate = null;
var pushedItemId = null;

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
}

function sendupdate() {
	var threatLevel = document.querySelector('#threatLevel').value;
	var areaDeployed = document.querySelector('#areaDeployed').value;
	var deploymentStatus = document.querySelector('#deploymentStatus').value;
	var note = document.querySelector('#note').value;
	
	let today = moment(new Date()).format('DD-MM-YYYY HH:mm:SS a');

	let update = {
			"updateDateTime" : today,
			"threatLevel" : threatLevel,
			"areaDeployed" : areaDeployed,
			"deploymentStatus" : deploymentStatus,
			"note" : note,
			"isAcknowledged" : "false",
	};
	
	var pushedItem = dbUpdate.push(update);
	pushedItemId = pushedItem.key;
	console.log(pushedItem.key);
	console.log(update);
	window.alert("Update Sent");
}

window.addEventListener("load", connect, true)
updateDetails.addEventListener("submit", sendupdate, true)