<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Crisis Management System | Warning</title>
		<!-- jQuery -->
		<script src="static/js/jquery-3.2.1.min.js"></script>
		<script>
			var message="${message}";
			var redirect="${redirect}";
			alert(message);
			window.location.href = redirect;
		</script>
	</head>
</html>