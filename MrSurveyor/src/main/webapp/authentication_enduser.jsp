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

	<div class="flex-container">
	<jsp:include page="header.jsp"></jsp:include>
	<main>
	
	<div class="main-box">
		<div class="child-box">
			<h1 id="header">Accedi</h1>
			<form id="registration-form" action="${pageContext.request.contextPath}/AuthenticationEndUserServlet" method="POST">
				<input type="email" name="email" placeholder="Email" required>
				<input type="password" name="password" placeholder="Password" required>

				<div class="submit">
					<input id="submit-btn" type="submit" value="Log In">
				</div>

				<div class="help-info">
					<a href="#">Password dimenticata&#x3F;</a>
					<div>Non hai un account&#x3F; <a href="${pageContext.request.contextPath}/registration_enduser.jsp">Registrati</a></div>
				</div>

			</form>
		</div>
	</div>
	
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

	</main>
	<jsp:include page="footer.jsp"></jsp:include>
	</div>
	
	<script type="text/javascript" src="js/utils.js"></script>

</body>
</html>