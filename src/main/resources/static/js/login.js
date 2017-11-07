var form = document.getElementById('loginForm');
var loginUsername = null;

function signIn() {
	let loginUsername = document.querySelector('#username').value.trim();
	if (loginUsername == "efpersonnel") {
		form.action="/efUI.html";
		form.submit();
	}
	else if (loginUsername) {
		form.action="/ifUI.html";
		form.submit();
	}
	else {
		window.alert("Have you input your username and password?");
	}
}