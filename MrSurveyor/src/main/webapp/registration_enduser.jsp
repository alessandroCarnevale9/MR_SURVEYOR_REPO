<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrazione</title>
<link rel="stylesheet" href="styles/form_style.css">
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>
	
	<div class="main-box">
		<div class="child-box">
			<h1 id="header">Registrati</h1>
			<form class="auth-form" action="${pageContext.request.contextPath}/RegistrationEndUserServlet" method="POST">
				<input type="text" name="name" placeholder="Nome" required>
				<input type="text" name="surname" placeholder="Cognome" required>
				<input type="email" name="email" placeholder="Email" required>
				<input type="password" name="password" placeholder="Password" required>
				<input type="password" name="confirm-password" placeholder="Conferma Password" required>

				<div class="submit">
					<input type="submit" value="Sign Up">
				</div>

				<div class="help-info">
					<div>Hai gi&#xE0; un account&#x3F; <a href="#">Accedi</a></div>
				</div>
			</form>
		</div>
	</div>

	<!-- <form action="${pageContext.request.contextPath}/RegistrationEndUserServlet"
		method="post">
		<fieldset>
			<legend>Registrati</legend>

			<label for="name">Nome:</label> <input type="text" id="name"
				name="name" required><br>
			<br> <label for="surname">Cognome:</label> <input type="text"
				id="surname" name="surname" required><br> <br> <label
				for="email">Email:</label> <input type="email" id="email"
				name="email" required><br>
			<br> <label for="password">Password:</label> <input
				type="password" id="password" name="password" required><br>
			<br> <label for="confirm_password">Conferma Password:</label> <input
				type="password" id="confirm_password" name="confirm_password"
				required><br>
			<br> <input type="submit" value="Sign Up">

			<div>
				Hai già un account? <a href="#">Accedi</a>
			</div>

		</fieldset>
	</form>  -->
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