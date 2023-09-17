<%@page import="java.text.DecimalFormat"%>
<%@page import="model.bean.Order"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="model.bean.Manager.Role"%>
<%@page import="model.bean.Manager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    Manager manager = (Manager)session.getAttribute("manager");
    
    DecimalFormat df = new DecimalFormat("###,##0.00 €");
    
    if(manager == null) {
    	response.sendRedirect(getServletContext().getContextPath()+"/authentication_manager.jsp");
    	return;
    }
    
    Collection<?> ordersToManage = (Collection<?>)request.getAttribute("toManage");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%
if(manager.getRole().equals(Role.CATALOG_MANAGER)) {
%>
<title>Home page Gestore Catalogo</title>
</head>
<body>
	<jsp:include page="header_manager.jsp"></jsp:include>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
<%
} else if(manager.getRole().equals(Role.ORDER_MANAGER)){
	
	if(ordersToManage == null) {
		response.sendRedirect(getServletContext().getContextPath()+"/OrderServlet?cmd=showAssigned");
		return;
	}
%>
<title>Home page Gestore Ordini</title>
<link rel="stylesheet" href="styles/orders_manager_view.css">
</head>
<body>
	
	<div class="flex-container">
	<jsp:include page="header_manager.jsp"></jsp:include>
	<main>
	
	<div class="container">
        <h1>Lista degli Ordini</h1>
        
        <%
        
        if(ordersToManage != null && !ordersToManage.isEmpty()) {
        
        Iterator<?> it = ordersToManage.iterator();
        while(it.hasNext()) {
        	Order o = (Order)it.next();
        %>
        <div class="order" onclick="toggleOrderForm('order-<%=o.getId() %>')">
            <p><span class="label"><b>ID ordine:</b></span> <%=o.getId() %></p>
            <p><span class="label"><b>ID cliente:</b></span> <%=o.getEndUser().getId() %></p>
            <p><span class="label"><b>Data dell'ordine:</b></span> <%=o.getOrderDate() %></p>
            <p><span class="label"><b>Prezzo totale:</b></span> <%=df.format(o.getTotalPrice()) %></p>
            <div class="order-form" id="order-<%=o.getId() %>-form">
                <!-- Form per la gestione dell'ordine -->
                <form>
                    <div class="form-group">
                        <label for="courier">Seleziona un corriere:</label>
                        <select id="courier" name="courier">
                            <option value="BRT">BRT</option>
                            <option value="DHL">DHL</option>
                            <option value="GLS">GLS</option>
                            <option value="UPS">UPS</option>
                            <option value="TNT">TNT</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="trackingNumber">Tracking number:</label>
                        <input type="text" id="trackingNumber" name="trackingNumber" maxlength="7" placeholder="Inserisci il tracking number" required>
                        <small class="error-message" id="tracking-number-error"></small>
                    </div>
                    <div class="form-group">
                    	<input type="hidden" id="orderDate" name="orderDate" value="<%=o.getOrderDate() %>">
                        <label for="shipmentDate">Data di spedizione:</label>
                        <input type="date" id="shipmentDate" name="shipmentDate" required>
                        <small class="error-message" id="shipment-date-error"></small>
                    </div>
                    <button type="button" onclick="validaECompletaOrdine('<%=o.getId() %>')">Completa</button>
                </form>
            </div>
        </div>
        <%
        	}
        } else {
        %>
        	<h3>Nessun ordine da gestire</h3>
        <%
        }
        %>
        
    </div>
	
	</main>
	<jsp:include page="footer.jsp"></jsp:include>
	</div>
	
	<script type="text/javascript" src="js/manage_order.js"></script>
	
</body>
<%
}
%>
</html>