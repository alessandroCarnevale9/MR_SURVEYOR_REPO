<%@page import="model.bean.Address"%>
<%@page import="model.bean.Cart"%>
<%@page import="model.bean.RegisteredEndUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	RegisteredEndUser registeredEndUser = (RegisteredEndUser)session.getAttribute("registeredEndUser");
	Cart cart = (Cart)session.getAttribute("userCart");
	
	if(registeredEndUser == null) {
		request.setAttribute("error", "Per acquistare devi essere autenticato");
		request.getRequestDispatcher("authentication_enduser.jsp").forward(request, response);
		return;
	}
	
	if(cart == null || cart.isEmpty()) {
		request.getRequestDispatcher("cart_view.jsp").forward(request, response);
		return;
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Dettaglio prdine</title>
<link rel="stylesheet" href="styles/order_details_style.css">
</head>
<body>

	<div class="flex-container">
	<jsp:include page="header.jsp"></jsp:include>
	<main>

	<div class="header">
        <h1>Dettaglio dell'Ordine</h1>
    </div>

    <div class="main">
        <section class="order-details">
            <h2>Riepilogo dell'Ordine</h2>
            <ul>
                <li>
                    <img src="product-image.jpg" alt="Nome Prodotto">
                    <div class="product-info">
                        <h3>Nome Prodotto</h3>
                        <p>Descrizione del prodotto.</p>
                        <p>Prezzo: $XX.XX</p>
                        <p>Quantità: 1</p>
                    </div>
                </li>
                <!-- Ripeti questa struttura per ogni prodotto nell'ordine -->
            </ul>
            <p>Totale dell'Ordine: $XX.XX</p>
        </section>
    </div>

    <div class="footer">
        <a id="back-to-cart" href="cart_view.jsp">Torna al Carrello</a>
        <%
        if(!registeredEndUser.getAddress().isValidAddress()) {
        %>
        	<a href="${pageContext.request.contextPath}/ManageAddressServlet?checkout">Procedi</a>
        <%
        } else {
        %>
        	<a href="#">Procedi</a>
        <%
        }
        %>
    </div>
        
    </main>
    
	<jsp:include page="footer.jsp"></jsp:include>
	</div>
    
</body>
</html>