<%@page import="model.bean.Order.State"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="model.bean.Product"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.bean.Order"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    Collection<?> enduserOrders = (Collection<?>)request.getAttribute("orders");
    String title = "Dettagli Ordini";
    
    DecimalFormat df = new DecimalFormat("###,##0.00 €");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Oridini effettuati</title>
<link rel="stylesheet" href="styles/orders_enduser_view.css">
</head>
<body>
	<div class="flex-container">
	<jsp:include page="header.jsp"></jsp:include>
	<main>
	
	<% 
	if(enduserOrders == null || enduserOrders.isEmpty()) {
    	title = "Ancora nessun ordine effettuato";
	%>
	
	<div id="empty-orders">
		<h1><%=title %></h1>
	</div>
	
	<%
	} else {
	%>
	<div class="container">
        <h1><%=title %></h1>
	<%
	Iterator<?> it = enduserOrders.iterator();
	int orderIndex = 0;
	while(it.hasNext()) {
		Order o = (Order)it.next();
	%>
		<div class="order" onclick="displayOrderProducts(<%=orderIndex %>)">
            <div class="order-details">
                <p><span class="label">ID dell'ordine:</span> <%=o.getId() %></p>
                <p><span class="label">Data dell'ordine:</span> <%=o.getOrderDate() %></p>
                <p><span class="label">Totale dell'ordine:</span> <%=df.format(o.getTotalPrice()) %></p>
                <p><span class="label">Indirizzo di spedizione:</span> <%=o.getOrderAddress() %></p>
                <%
                String state = "Da spedire";
                if(o.getState().equals(State.SENT)) {
                	state = "Spedito";
                }
                %>
                <p><span class="label">Stato dell'ordine:</span> <%=state %></p>
                <%
                if(o.getTrackingNumber() != null) {
                %>
                <p><span class="label">Numero di tracciamento:</span> <%=o.getTrackingNumber() %></p>
                <%
                } else {
                %>
                <p><span class="label">Numero di tracciamento:</span> Da definire</p>
                <%
                }
                %>
                <%
                if(o.getShipmentDate() != null) {
                %>
                <p><span class="label">Data di spedizione:</span> <%=o.getShipmentDate() %></p>
                <%
                } else {
                %>
                <p><span class="label">Data di spedizione:</span> Da definire</p>
                <%
                }
                %>
            </div>
            
            <div class="products" id="products<%= orderIndex %>">
            <%
            Collection<Product> orderProducts = o.getOrderProducts();
            
            for(Product p : orderProducts) {
            %>
                <div class="product">
                    <img src="images/prod/<%=p.getImagePath() %>" alt="<%=p.getName()%>">
                    <div class="product-details">
                        <p><strong><%=p.getName() %></strong></p>
                        <p><%=df.format(p.getPrice()) %></p>
                        <small>Quantità: <%=p.getQuantity() %></small>
                    </div>
                </div>
            <%
            }
            %>
            </div>
        </div>
        <%
        
        orderIndex++;
	}
        %>
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