<%@page import="model.bean.RegisteredEndUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%
	
	RegisteredEndUser registeredEndUser = (RegisteredEndUser)session.getAttribute("registeredEndUser");
	
	if(registeredEndUser == null) {
		response.sendRedirect(getServletContext().getContextPath()+"/authentication_enduser.jsp");
		return;
	}
	%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home Page End User</title>
<link rel="stylesheet" type="text/css" href="styles/enduser_home.css">
</head>
<body>
	
	<div class="flex-container">
	<jsp:include page="header.jsp"></jsp:include>
	<main>
	
	<jsp:include page="enduser_sidemenu.jsp"></jsp:include>
	
	<section class="welcome-message">
		<h2>Benvenuto <%=registeredEndUser.getName()+" "+registeredEndUser.getSurname() %></h2>
		<p>Qui puoi visualizzare e modificare le informazioni relative al tuo account.</p>
	</section>
	
	</main>
	<jsp:include page="footer.jsp"></jsp:include>
	</div>
	
</body>
</html>