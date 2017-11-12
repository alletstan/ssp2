var form = document.getElementById('loginForm');
var loginUsername = null;

function signIn() {
	let loginUsername = document.querySelector('#username').value.trim();
	amplify.store("loginUsername", loginUsername);
	if (loginUsername == "efpersonnel") {
		form.action="/home";
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