<%@page import="model.bean.RegisteredEndUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	RegisteredEndUser endUser = (RegisteredEndUser)session.getAttribute("registeredEndUser");

	if(endUser == null) {
		response.sendRedirect(getServletContext().getContextPath()+"/authentication_enduser.jsp");
		return;
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Dettagli Account</title>
<link rel="stylesheet" type="text/css" href="styles/enduser_home.css">
<link rel="stylesheet" href="styles/form_style.css">
</head>
<body>
	
	<div class="flex-container">
	<jsp:include page="header.jsp"></jsp:include>
	<main>
	
	<jsp:include page="enduser_sidemenu.jsp"></jsp:include>
	
		<div class="child-box user-home">
		<%
		if(request.getAttribute("error") != null) {
		%>
			<small id="error-message">${error}</small>
		<%
		} else if(request.getAttribute("success") != null) {
		%>
			<small id="success-message">${success}</small>
		<%
		}
		%>
			
			<h3>Le tue informazioni</h3>
			<form id="registration-form" action="${pageContext.request.contextPath}/SetEndUserInfoServlet" method="POST">
			
				<div class="form-control">					
					<input id="name" type="text" name="name" placeholder="Nome" value="<%=endUser.getName()%>" autocomplete="off">
					<span class="material-symbols-outlined check-icon">check_circle</span>
					<span class="material-symbols-outlined error-icon">error</span>
					<small>Error message</small>
				</div>

				<div class="form-control">
					<input id="surname" type="text" name="surname" placeholder="Cognome" value="<%=endUser.getSurname()%>" autocomplete="off">
					<span class="material-symbols-outlined check-icon">check_circle</span>
					<span class="material-symbols-outlined error-icon">error</span>
					<small>Error message</small>
				</div>

				<div class="form-control">
					<input id="password" type="password" name="password" placeholder="Password" autocomplete="off">
					<span class="material-symbols-outlined check-icon">check_circle</span>
					<span class="material-symbols-outlined error-icon">error</span>
					<small>Error message</small>
				</div>

				<div class="form-control">
					<input id="confirm-password" type="password" name="confirm_password" placeholder="Conferma Password" autocomplete="off">
					<span class="material-symbols-outlined check-icon">check_circle</span>
					<span class="material-symbols-outlined error-icon">error</span>
					<small>Error message</small>
				</div>

				<div class="submit">
					<input id="submit-btn" type="submit" value="Modifica">
				</div>
			</form>
		</div>
		
		<section id="details-enduser-msg"><p>Per modificare le informazioni del tuo account<br>
		edita tutti i campi.</p><p>Se inserirai delle informazioni differenti da quelle già
		registrate, esse saranno aggiornate</p></section>
		
	</main>
	<jsp:include page="footer.jsp"></jsp:include>
	</div>
	
	<script type="text/javascript" src="js/validation_form.js"></script>
	
</body>
</html>