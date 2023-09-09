<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<script src="js/jquery-3.7.1.js"></script>

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
                <div class="search-bar">
                <form class="search-form" action="${pageContext.request.contextPath}/SearchServlet" method="GET">
                <div class="input-container">
                    <input class="live-search" type="text" name="search" placeholder="Cerca..." autocomplete="off">
                </div>
                <button type="submit"><span id="search-icon" class="material-symbols-outlined">search</span></button>
                </form>
                </div>
                
                <div class="cart-icon">
                <a href="cart_view.jsp"><span id="shopping-cart" class="material-symbols-outlined">shopping_cart</span></a>
                </div>
                <div class="user-icon">
                <span id="user-dropdown-toggle" class="material-symbols-outlined">person</span>
                <%
                if(session.getAttribute("userEmail") == null) {
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
    
    <div class="search-bar mobile-search">
    	<form class="search-form" action="${pageContext.request.contextPath}/SearchServlet" method="GET">
    	<div class="input-container">
    		<input class="live-search" type="text" name="search" placeholder="Cerca..." autocomplete="off">
    	</div>
    	<button type="submit"><span id="search-icon" class="material-symbols-outlined">search</span></button>
    	</form>
    </div>
    
    <script type="text/javascript" src="js/drop_down.js"></script>
    <script type="text/javascript" src="js/search.js"></script>
    
    <div id="search-results"></div>
    
</body>
</html>