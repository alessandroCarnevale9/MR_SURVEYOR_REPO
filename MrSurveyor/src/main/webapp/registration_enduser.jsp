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
		
		<%
		if(request.getAttribute("error") != null) {
		%>
			<small id="error-message">${error}</small>
		<%
		}
		%>
			
			<h1 id="header">Registrati</h1>
			<form id="registration-form" action="${pageContext.request.contextPath}/RegistrationEndUserServlet" method="POST">
			
				<div class="form-control">					
					<%
					if(request.getAttribute("prevName") != null) {
					%>
						<input id="name" type="text" name="name" placeholder="Nome" value="${prevName}">
					<%
					} else {
					%>
						<input id="name" type="text" name="name" placeholder="Nome">
					<%
					}
					%>
					<span class="material-symbols-outlined check-icon">check_circle</span>
					<span class="material-symbols-outlined error-icon">error</span>
					<small>Error message</small>
				</div>

				<div class="form-control">
					<%
					if(request.getAttribute("prevSurname") != null) {
					%>
					<input id="surname" type="text" name="surname" placeholder="Cognome" value="${prevSurname}">
					<%
					} else {
					%>
						<input id="surname" type="text" name="surname" placeholder="Cognome">
					<%
					}
					%>
					<span class="material-symbols-outlined check-icon">check_circle</span>
					<span class="material-symbols-outlined error-icon">error</span>
					<small>Error message</small>
				</div>

				<div class="form-control">
					<%
					if(request.getAttribute("prevEmail") != null) {
					%>
						<input id="email" type="text" name="email" placeholder="Email" value="${prevEmail}">
					<%
					} else {
					%>
						<input id="email" type="text" name="email" placeholder="Email">
					<%
					}
					%>
					<span class="material-symbols-outlined check-icon">check_circle</span>
					<span class="material-symbols-outlined error-icon">error</span>
					<small>Error message</small>
				</div>

				<div class="form-control">
					<%
					if(request.getAttribute("prevPasswd") != null) {
					%>
						<input id="password" type="password" name="password" placeholder="Password" value="${prevPasswd}">
					<%
					} else {
					%>
					<input id="password" type="password" name="password" placeholder="Password">
					<%
					}
					%>
					<span class="material-symbols-outlined check-icon">check_circle</span>
					<span class="material-symbols-outlined error-icon">error</span>
					<small>Error message</small>
				</div>

				<div class="form-control">
					<%
					if(request.getAttribute("prevConfirmPasswd") != null) {
					%>
						<input id="confirm-password" type="password" name="confirm_password" placeholder="Conferma Password" value="${prevConfirmPasswd}">
					<%
					} else {
					%>
						<input id="confirm-password" type="password" name="confirm_password" placeholder="Conferma Password">
					<%
					}
					%>
					<span class="material-symbols-outlined check-icon">check_circle</span>
					<span class="material-symbols-outlined error-icon">error</span>
					<small>Error message</small>
				</div>

				<div class="submit">
					<input id="submit-btn" type="submit" value="Sign Up">
				</div>

				<div class="help-info">
					<div>Hai gi&#xE0; un account&#x3F; <a href="${pageContext.request.contextPath}/authentication_enduser.jsp">Accedi</a></div>
				</div>
			</form>
		</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
	
	<script type="text/javascript" src="js/validation_form.js"></script>
	
</body>
</html>