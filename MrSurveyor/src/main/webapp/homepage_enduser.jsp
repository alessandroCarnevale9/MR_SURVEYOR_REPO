<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page End User</title>
<link rel="stylesheet" type="text/css" href="styles/enduser_home.css">
</head>
<body>
	
	<div class="flex-container">
	<jsp:include page="header.jsp"></jsp:include>
	<main>
	
	<aside class="left-bar">
		<nav class="links">
			<ul>
				<li><a href="#">BACHECA</a></li>
				<li><a href="#">ORDINI</a></li>
				<li><a href="#">INDIRIZZO</a></li>
				<li><a href="#">DETTAGLI ACCOUNT</a></li>
				<li><a href="#">LOGOUT</a></li>
			</ul>
		</nav>
	</aside>
	
	</main>
	<jsp:include page="footer.jsp"></jsp:include>
	</div>
	
</body>
</html>