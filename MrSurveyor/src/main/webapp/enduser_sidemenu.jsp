<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<aside class="left-bar">
		<nav class="side-menu">
			<ul class="side-menu-item">
				<li><a href="${pageContext.request.contextPath}/homepage_enduser.jsp">BACHECA</a></li>
				<li><a href="#">ORDINI</a></li>
				<li><a href="${pageContext.request.contextPath}/address_view.jsp">INDIRIZZO</a></li>
				<li><a href="${pageContext.request.contextPath}/end_user_account_details.jsp">DETTAGLI ACCOUNT</a></li>
				<li><a href="${pageContext.request.contextPath}/AuthenticationEndUserServlet?invalidate">LOGOUT</a></li>
			</ul>
		</nav>
	</aside>
</body>
</html>