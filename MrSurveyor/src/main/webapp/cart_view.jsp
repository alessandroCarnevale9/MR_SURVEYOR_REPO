<%@page import="model.bean.Product"%>
<%@page import="java.util.Collection"%>
<%@page import="model.bean.Cart"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
	Cart userCart = (Cart)session.getAttribute("userCart");
	
	if(userCart == null) {
		response.sendRedirect(getServletContext().getContextPath()+"/CartServlet");
		return;
	}
	
	Collection<Product> products = userCart.getProducts();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Carrello</title>
</head>
<body>
	<div class="cart-item">
	<%
	for(Product p : products) {
	%>
		<div class="cart-img">
			<img alt="<%=p.getName()%>" src="images/prod/<%=p.getImagePath()%>">	
		</div>
		<div class="prod-name">
			<h2><%=p.getName()%></h2>
		</div>
		<div class="funct">
			<form action="#" method="GET">
				<label for="quantity">Quantità:</label>
          		<select id="quantity" class="quantity-select">
          		<%
          		for(int i = 1; i <= p.getQuantity(); i++) {
          		%>
          			<option value="<%=i%>" selected><%=i%></option>
          		<%
          		}
          		%>
          		</select>
          		<input type="submit" value="Rimuovi">
			</form>
		</div>
		<div class="price">
			<h3><%=p.getPrice()%></h3>
		</div>
	<%
	}
	%>
	</div>
</body>
</html>