window.onload = connect;

var dbRequest = null;
var requestDateTime = null;

var firstpage = document.querySelector("#firstpage");
var requestpage = document.querySelector('#secondpage');

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
var acknowledgedtitle = document.querySelector('#acknowledgedtitle');
var acknowledgedtext = document.querySelector('#acknowledgedtext');

function putLink(requestDateTime) {
	var el = document.getElementById("list");
	var node = document.createElement("li");
	var link = document.createElement("a");
	link.innerText = requestDateTime;
	link.setAttribute('href', 'javascript:readData("' + requestDateTime + '")');
	node.appendChild(link);
	el.appendChild(node);
}

function readData(requestDateTime){
	 firstpage.classList.add('hidden');
     secondpage.classList.remove('hidden');
	dbRequest.orderByChild("datetime").equalTo(requestDateTime).on("child_added", function(snapshot){

		let requestDetails = snapshot.val();

		let requestDateTime = requestDetails.datetime;
		let threatLevel = requestDetails.threatLevel;
		let crisisType = requestDetails.crisisType;
		let affectedArea = requestDetails.affectedArea;
		let crisisDetails = requestDetails.crisisDetails;
		let recommendedAction = requestDetails.recommendedAction;
		let deploymentForce = requestDetails.deploymentForce;
		let isAcknowledged = requestDetails.isAcknowledged;

		datetimetitle.innerHTML = "Date and Time of Request";
		threatleveltitle.innerHTML = "Threat Level";
		crisistypetitle.innerHTML = "Crisis Type";
		affectedareatitle.innerHTML = "Affected Areas";
		crisisdetailstitle.innerHTML = "Crisis Details";
		recommendedactiontitle.innerHTML = "Recommended Course of Action";
		forcedeploymenttitle.innerHTML = "Force to be Deployed";
		acknowledgedtitle.innerHTML = "Acknowledged";
		

		datetimetext.innerHTML = requestDateTime;
		threatleveltext.innerHTML = threatLevel;
		crisistypetext.innerHTML = crisisType;
		affectedareatext.innerHTML = affectedArea;
		crisisdetailstext.innerHTML = crisisDetails;
		recommendedactiontext.innerHTML = recommendedAction;
		forcedeploymenttext.innerHTML = deploymentForce;
		acknowledgedtext.innerHTML = isAcknowledged;
		
		
	})
}

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

	// Get request list
	getrequest();
}

function getrequest() {
	dbRequest.orderByChild("datetime").on("child_added", function(snapshot) {

		let requestDetails = snapshot.val();
		let requestDateTime = requestDetails.datetime;
		putLink(requestDateTime);

	}
	)
}

function goBack(){
	firstpage.classList.remove('hidden');
    secondpage.classList.add('hidden');
}