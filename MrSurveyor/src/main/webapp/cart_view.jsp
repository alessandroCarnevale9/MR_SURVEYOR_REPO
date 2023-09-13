<%@page import="java.text.DecimalFormat"%>
<%@page import="model.bean.CartProduct"%>
<%@page import="java.util.Collection"%>
<%@page import="model.bean.Cart"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String errorMessage = (String)request.getAttribute("error");	

	Cart userCart = (Cart)session.getAttribute("userCart");

	if(userCart == null) {
		response.sendRedirect(getServletContext().getContextPath()+"/CartServlet");
		return;
	}
	
	Collection<CartProduct> products = userCart.getProducts();
	
	DecimalFormat df = new DecimalFormat("###,##0.00 €");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Carrello</title>
<link rel="stylesheet" href="styles/cart.css">
</head>
<body>
	<div class="flex-container">
	<jsp:include page="header.jsp"></jsp:include>
	<main>
	<%
	if(userCart.isEmpty()) {
	%>
		<div><h1>Carrello vuoto</h1></div>
	<%
	} else {
		
		double totalPrice = 0;
		double partialTotalPrice = 0;
	%>
	
	<div><h1>I tuoi prodotti</h1></div>
	
	<div class="grid-container">
	<%
	for(CartProduct p : products) {
		partialTotalPrice = p.getPrice();
	%>
		<div class="cart-item">
		<div class="cart-img">
			<img alt="<%=p.getName()%>" src="images/prod/<%=p.getImagePath()%>">	
		</div>
		<div class="prod-name">
			<h2><%=p.getName()%></h2>
		</div>
		<div class="funct">
			<form action="${pageContext.request.contextPath}/CartServlet" method="GET">
				<label for="quantity">Quantità:</label>
				<input type="hidden" name="quantityID" value="<%=p.getId()%>">
          		<select id="quantity" name="cart_quantity" class="quantity-select">
          		<%
          		for(int i = 1; i <= p.getMaxQuantity(); i++) {
          			if(i == p.getQuantity()) {
          				
          				partialTotalPrice *= i;
          		%>
          				<option value="<%=i%>" selected><%=i%></option>
          		<%
          			} else {
                 %>
              			<option value="<%=i%>"><%=i%></option>
              	 <%
          			}
          		}
          		%>
          		</select>
          		<input type="submit" value="Imposta">
			</form>
			<%
				if(errorMessage != null) {
			%>
					<small class="error"><%=errorMessage %></small>
			<%
				}
			%>
			
			<form action="${pageContext.request.contextPath}/CartServlet" method="GET">
        		<input type="hidden" name="removeID" value="<%=p.getId()%>">
        		<input type="submit" value="Rimuovi">
    		</form>
		</div>
		<div class="price">
			<h3><%=df.format(p.getPrice())%></h3>
		</div>
		</div>
	<%
		totalPrice += partialTotalPrice;	
	}
	%>
		<div class="total-price">
			<h2>Prezzo totale: <%=df.format(totalPrice) %></h2>
			<%session.setAttribute("totalPrice", df.format(totalPrice)); %>
			<a href="order_details.jsp">Ordina</a>
		</div>
	<%
	}
	%>
	</div>
	</main>
	<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>