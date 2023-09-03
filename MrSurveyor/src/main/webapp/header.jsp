<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="styles/header_style.css">
</head>
<body>
	
	<header>
		<div class="logo">
			<a href="homepage.jsp"><img src="images/logo.png" alt="Logo del sito"></a>
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
                <form action="#" method="GET">
                <div class="input-container">
                    <input type="text" placeholder="Cerca...">
                    <button type="submit"><img alt="search_img" src="images/search.png"></button>
                </div>
                </form>
                </div>
                
                <div class="cart-icon">
                <a href="cart_view.jsp"><img alt="cart_img" src="images/shopping_cart.png"></a>
                </div>
                <div class="user-icon">
                <a href="homepage_enduser.jsp"><img alt="user_img" src="images/user.png"></a>
                </div>
            </div>    
    </header>
    
</body>
</html>