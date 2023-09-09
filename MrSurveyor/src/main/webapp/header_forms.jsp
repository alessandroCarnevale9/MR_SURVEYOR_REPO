<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="styles/header_style.css">
</head>
<body>
	<header>
		<div class="logo">
			<a id="add_after_me" href="homepage.jsp"><img src="images/logo.png" alt="Logo del sito"></a>
        </div>
        
        <nav class="navbar-container">
            <ul class="nav-links">
                <li><a href="homepage.jsp">HOME</a></li>
                <li><a href="#">CHI SIAMO</a></li>
                <li><a href="categories_enduser.jsp">PRODOTTI</a></li>
                <li><a href="#">CONTATTI</a></li>
            </ul>
        </nav>
        
        <div class="nav-right">
			<div class="cart-icon">
				<a href="cart_view.jsp"><span id="shopping-cart" class="material-symbols-outlined">shopping_cart</span></a>
			</div>
			<div class="user-icon">
				<span id="user-dropdown-toggle" class="material-symbols-outlined">person</span>
				<%
				if (session.getAttribute("userEmail") == null) {
				%>
				<div id="user-dropdown" class="user-dropdown-content">
					<a href="homepage_enduser.jsp">Accedi</a>
					<a href="registration_enduser.jsp">Registrati</a>
				</div>
				<%
				} else {
				%>
				<div id="user-dropdown" class="user-dropdown-content">
					<ul class="header-menu">
						<li><a href="${pageContext.request.contextPath}/homepage_enduser.jsp">BACHECA</a></li>
						<li><a href="#">ORDINI</a></li>
						<li><a href="#">INDIRIZZO</a></li>
						<li><a href="#">DETTAGLI ACCOUNT</a></li>
						<li><a href="${pageContext.request.contextPath}/AuthenticationEndUserServlet?invalidate">LOGOUT</a></li>
					</ul>
				</div>
				<%
				}
				%>

			</div>
		</div>    
    </header>
    
    <nav id="mobile-navbar" class="mobile-navbar">
        <ul class="nav-links">
            <li><a href="homepage.jsp">HOME</a></li>
            <li><a href="#">CHI SIAMO</a></li>
            <li><a href="categories_enduser.jsp">PRODOTTI</a></li>
            <li><a href="#">CONTATTI</a></li>
        </ul>
    </nav>
    
    <script type="text/javascript" src="js/drop_down.js"></script>
    
</body>
</html>