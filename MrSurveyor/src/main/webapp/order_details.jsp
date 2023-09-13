<%@page import="java.time.LocalDate"%>
<%@page import="java.util.Date"%>
<%@page import="model.bean.Order"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="model.bean.CartProduct"%>
<%@page import="java.util.Collection"%>
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
	
	Collection<CartProduct> cartProducts = cart.getProducts();
	
	DecimalFormat df = new DecimalFormat("###,##0.00 €");
	
	
	String value = "Aggiungi Carta";
	if(request.getAttribute("successCard") != null) {
		value = "Modifica Carta";
	}
	
	String prevNumber = null;
	if(request.getAttribute("prevNumber") != null) {
		prevNumber = (String)request.getAttribute("prevNumber");
	}
	
	String prevCVC = null;
	if(request.getAttribute("prevCVC") != null) {
		prevCVC = (String)request.getAttribute("prevCVC");
	}
	
	String prevExp = null;
	if(request.getAttribute("prevExp") != null) {
		prevExp = (String)request.getAttribute("prevExp");
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Dettaglio ordine</title>
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
            
            <%
            Order order = new Order();
            for(CartProduct p : cartProducts) {
            %>
            
                <li>
                    <img src="images/prod/<%=p.getImagePath() %>" alt="<%=p.getName() %>">
                    <div class="product-info">
                        <h4><%=p.getName() %></h4>
                        <p><%=p.getPrice() %></p>
                        <p><%=p.getQuantity() %></p>
                    </div>
                </li>
            <%
            
            order.addOrderProduct(p);
            
            }
            %>
            </ul>
            <h3>Totale dell'Ordine: <%= df.format((double)session.getAttribute("totalPrice"))  %></h3>
            
            <%
            order.setOrderDate(new Date());
            order.setTotalPrice((double)session.getAttribute("totalPrice"));
            session.setAttribute("order", order);
            %>
            
        </section>
    </div>
	
	<%
	if(request.getAttribute("errorCard") == null) {
	%>
	
    <div class="footer">
        <a id="back-to-cart" href="cart_view.jsp">Torna al Carrello</a>
        <a href="#checkout" id="prcd" onclick="display(this, '.final-check')">Procedi</a>
    </div>
    
    <%
    String cls = "final-check";
    if(value.equals("Modifica Carta")) {
    	cls = "final-check display";
    }
    %>
    
    <div class="<%=cls %>">
    	<section class="address-info">
    	<%
        Address address = registeredEndUser.getAddress();
        if(!address.isValidAddress()) {
        %>
        	<h2 id="addr-header">Nessun indirizzo associato</h2>
        	<a class="check-btn" href="${pageContext.request.contextPath}/ManageAddressServlet?checkout">Aggiungi Indirizzo</a>
        <%
        } else {
        %>
    	<h2 id="addr-header">Il tuo Indirizzo</h2>
    		<div><%=registeredEndUser.getName()+" "+registeredEndUser.getSurname() %></div>
			<div><%=address.getStreet()+" "+address.getHouseNumber() %></div>
			<div><%=address.getCap() %></div>
			<div><%=address.getRegion()+" "+address.getProvince() %></div>
			<div><%=address.getCity() %></div>
			
			<a class="check-btn liks" href="${pageContext.request.contextPath}/ManageAddressServlet?checkout">Modifica</a>
		<%
        }
		%>	
		</section>
    			<form id="card-form" action="${pageContext.request.contextPath}/CreditCardServlet" method="POST">
        		<section class="credit-card-form">
        			<h2>Inserisci Carta di Credito</h2>
        			
        			<label for="card_number">Numero carta:</label>
        			<%
        			if(prevNumber != null) {
        			%>
        			<input id="card_number" type="text" name="card_number" placeholder="AAAA BBBB CCCC DDD" required value="<%=prevNumber %>">
        			<%
        			} else {
        			%>
        			<input id="card_number" type="text" name="card_number" placeholder="AAAA BBBB CCCC DDD" required>
        			<%
        			}
        			%>
        			<label for="cvc">CVC:</label>
        			<%
        			if(prevCVC != null) {
        			%>
        			<input id="cvc" type="number" name="cvc" placeholder="AAA" required value="<%=prevCVC %>">
        			<%
        			} else {
        			%>
        			<input id="cvc" type="number" name="cvc" placeholder="AAA" required>
        			<%
        			}
        			%>
        			<label for="expire">Scadenza:</label>
        			<%
        			if(prevExp != null) {
        			%>
        			<input id="expire" type="text" name="expire" placeholder="MM/AA" required value="<%=prevExp %>">
        			<%
        			} else {
        			%>
        			<input id="expire" type="text" name="expire" placeholder="MM/AA" required>
        			<%
        			}
        			%>
        
        			<div class="btns-container">
        				<input id="submit-btn" class="check-btn" type="submit" value="<%=value %>" onclick="hideAddressForm(this)">
        				<%
        				if(value.equals("Modifica Carta")) {
        				%>
        				<a href="${pageContext.request.contextPath}/OrderServlet?cmd=addOrder" id="order-btn" class="<%=cls%>" type="submit">Ordina</a>
        				<%
        				}
        				%>
        			</div>
        			</section>
    			</form>
    </div>
    
    <%
	} else {
		
		String errorMessage = (String)request.getAttribute("errorCard");
    %>
    
    <div class="footer">
        <a id="back-to-cart" href="cart_view.jsp">Torna al Carrello</a>
        <a class="hide" id="prcd" href="#checkout" onclick="display(this, '.final-check')">Procedi</a>
    </div>
    
    <div class="final-check display">
    	<section class="address-info">
    	<%
        Address address = registeredEndUser.getAddress();
        if(!address.isValidAddress()) {
        %>
        	<h2 id="addr-header">Nessun indirizzo associato</h2>
        	<a class="check-btn" href="${pageContext.request.contextPath}/ManageAddressServlet?checkout">Aggiungi Indirizzo</a>
        <%
        } else {
        %>
    	<h2 id="addr-header">Il tuo Indirizzo</h2>
    		<div><%=registeredEndUser.getName()+" "+registeredEndUser.getSurname() %></div>
			<div><%=address.getStreet()+" "+address.getHouseNumber() %></div>
			<div><%=address.getCap() %></div>
			<div><%=address.getRegion()+" "+address.getProvince() %></div>
			<div><%=address.getCity() %></div>
			
			<a class="check-btn liks" href="${pageContext.request.contextPath}/ManageAddressServlet?checkout">Modifica</a>
		<%
        }
		%>	
		</section>
    			<form id="card-form" action="${pageContext.request.contextPath}/CreditCardServlet" method="POST">
        		<section class="credit-card-form">
        		
        			<small id=error-message><%=errorMessage %></small>
        		
        			<h2>Inserisci Carta di Credito</h2>
        			
        			<label for="card_number">Numero carta:</label>
        			<input id="card_number" type="text" name="card_number" placeholder="AAAA BBBB CCCC DDD" required>
        
        			<label for="cvc">CVC:</label>
        			<input id="cvc" type="number" name="cvc" placeholder="AAA" required>
        
        			<label for="expire">Scadenza:</label>
        			<input id="expire" type="text" name="expire" placeholder="MM/AA" required>
        
        			<div class="submit cart">
        				<input id="submit-btn" class="check-btn" type="submit" value="Aggiungi Carta" onclick="hideAddressForm(this)">
        			</div>
        			</section>
    			</form>
    </div>
    
    <%
	}
    %>
    </main>
    
	<jsp:include page="footer.jsp"></jsp:include>
	</div>
    
    <script type="text/javascript" src="js/utils.js"></script>
    
</body>
</html>