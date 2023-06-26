<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrazione</title>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<form action="#" method="post">
		<fieldset>
			<legend>Registrati</legend>

			<label for="name">Nome:</label> <input type="text" id="name"
				name="name" required><br>
			<br> <label for="surname">Cognome:</label> <input type="text"
				id="surname" name="surname" required><br>
			<br> <label for="email">Email:</label> <input type="email"
				id="email" name="email" required><br>
			<br> <label for="password">Password:</label> <input
				type="password" id="password" name="password" required><br>
			<br> <label for="confirm_password">Conferma Password:</label> <input
				type="password" id="confirm_password" name="confirm_password"
				required><br>
			<br> <input type="submit" value="Sign Up">

			<p>Hai già un account? <a href="#">Accedi</a></p>

		</fieldset>
	</form>

	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>