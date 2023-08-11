<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Autenticazione</title>
<link rel="stylesheet" href="styles/form_style.css">
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>
	
	<div class="main-box">
		<div class="child-box">
			<h1 id="header">Accedi</h1>
			<form class="auth-form" action="${pageContext.request.contextPath}/AuthenticationEndUserServlet" method="POST">
				<input type="email" name="email" placeholder="Email" required>
				<input type="password" name="password" placeholder="Password" required>
				
				<div class="submit">
            <input type="submit" value="Log In">
          </div>

          <div class="help-info">
            <a href="#">Password dimenticata&#x3F;</a><br>
            <div>Non hai un account&#x3F; <a href="#">Registrati</a></div>
          </div>
          
			</form>
		</div>
	</div>
	
	<p>PROVA</p>
	
	<!-- <form action="${pageContext.request.contextPath}/AuthenticationEndUserServlet" method="post">
		<fieldset>
			<legend>Accedi</legend>

			<label for="email">Email:</label> <input type="email" id="email"
				name="email" required><br>
			<br> <label for="password">Password:</label> <input
				type="password" id="password" name="password" required> <input
				type="submit" value="Log In">

			<div>
				<a href="#">Password dimenticata?</a>
			</div>
			<div>
				Non hai un account?<a href="#">Registrati</a>
			</div>
		</fieldset>
	</form> -->

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