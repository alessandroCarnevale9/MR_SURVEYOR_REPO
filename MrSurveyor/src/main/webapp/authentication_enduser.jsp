<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Autenticazione</title>
</head>
<body>
	
	<jsp:include page="header.jsp"></jsp:include>
	
	<form action="#" method="post">
		<fieldset>
			<legend>Accedi</legend>
		
			<label for="email">Email:</label>
			<input type="email" id="email" name="email" required><br><br>
		
			<label for="password">Password:</label>
			<input type="password" id="password" name="password" required>
		
			<input type="submit" value="Log In">
		
			<div><a href="#">Password dimenticata?</a></div>
			<div>Non hai un account?<a href="#">Registrati</a></div>
		</fieldset>
	</form>	
	
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>