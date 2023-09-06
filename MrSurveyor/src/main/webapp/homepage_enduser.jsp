<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%
	
	String userEmail = (String)session.getAttribute("userEmail");
	
	if(userEmail == null) {
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
	
	<aside class="left-bar">
		<nav class="side-menu">
			<ul class="side-menu-item">
				<li><a href="${pageContext.request.contextPath}/homepage_enduser.jsp">BACHECA</a></li>
				<li><a href="#">ORDINI</a></li>
				<li><a href="#">INDIRIZZO</a></li>
				<li><a href="#">DETTAGLI ACCOUNT</a></li>
				<li><a href="${pageContext.request.contextPath}/AuthenticationEndUserServlet?invalidate">LOGOUT</a></li>
			</ul>
		</nav>
	</aside>
	
	<section class="welcome-message">
		<h2>Benvenuto <%=userEmail %></h2>
		<p>Qui puoi visualizzare e modificare le informazioni relative al tuo account.</p>
	</section>
	
	</main>
	<jsp:include page="footer.jsp"></jsp:include>
	</div>
	
</body>
</html>