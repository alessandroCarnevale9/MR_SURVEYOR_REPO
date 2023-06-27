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
			
			<label for="username">Username:</label>
			<input type="text" id="username" name="username" required>
			
			<label for="password">Password:</label>
			<input type="password" id="password" name="password" required>
			
			<div id="role">
			
			<label for="role1">Gestore catalogo:</label>
			<input type="radio" id="role1" name="role" value="catalog_manager" checked="checked">
			
			<label for="role2">Gestore ordini:</label>
			<input type="radio" id="role2" name="role" value="order_manager">
			</div>
			
			<input type="submit" value="Log In">
			
			<div><a href="#">Password dimenticata?</a></div>
			
		</fieldset>
	</form>
	
	<%
	String errorMessage = (String) request.getAttribute("error");

	if (errorMessage != null && !errorMessage.trim().equals("")) {
	%>
	<div id="error_message">
		<%=errorMessage%>
	</div>
	<%
	}
	%>
		
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>