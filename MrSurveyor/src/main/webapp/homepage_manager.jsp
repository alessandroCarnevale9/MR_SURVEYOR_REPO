<%@page import="model.bean.Product"%>
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
    
    Collection<?> catalogProducts = (Collection<?>)request.getAttribute("catalogProducts");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%
if(manager.getRole().equals(Role.CATALOG_MANAGER)) {
	
	if(catalogProducts == null || catalogProducts.isEmpty()) {
		response.sendRedirect(getServletContext().getContextPath()+"/CatalogManagerServlet?cmd=showProducts");
		return;
	}
%>
<title>Home page Gestore Catalogo</title>
<link rel="stylesheet" href="styles/catalog_manager_view.css">
</head>
<body>

	<div class="flex-container">
	<jsp:include page="header_manager.jsp"></jsp:include>
	<main>
	
	<div class="order-container">
        <div>
            <label for="order">Ordina per:</label>
            <select id="order" name="order" onchange="handleSelectChange()">
            <%
            String selected = (String)request.getAttribute("selected");
            if(selected != null && selected.equals("product_id")) {
            %>
            <option value="product_id" selected>ID</option>
            <%
            } else {
            %>
                <option value="product_id">ID</option>
                
            <%
            } if(selected != null && selected.equals("product_name")) {
            %>
            	<option value="product_name" selected>Nome</option>
            <%
            } else {
            %>
            	<option value="product_name">Nome</option>
            <%
            } if(selected != null && selected.equals("product_price")) {
            %>
            	<option value="product_price" selected>Prezzo</option>
            <%
            } else {
            %>
                <option value="product_price">Prezzo</option>
            <%
            }
            %>
            </select>
        </div>
        <a class="btn-add-product" href="add_product_form.jsp">Aggiungi Prodotto</a>
    </div>

    <div class="product-container">
        <%
        Iterator<?> it = catalogProducts.iterator();
        while(it.hasNext()) {
        	Product p = (Product)it.next();
        %>
        <div class="product-row">
            <img src="images/prod/<%=p.getImagePath() %>" alt="<%=p.getName() %>">
            <div class="product-details">
                <p><b>ID:</b> <%=p.getId() %></p>
                <p><b>Nome:</b> <%=p.getName() %></p>
                <p><b>Prezzo:</b> <%=df.format(p.getPrice()) %></p>
                <p><b>Quantità:</b> <%=p.getQuantity() %></p>
            </div>
            <div class="product-actions">
                <a class="btn-edit">Modifica</a>
                <a href="${pageContext.request.contextPath}/CatalogManagerServlet?cmd=removeProduct&id=<%=p.getId() %>" class="btn-remove">Rimuovi</a>
            </div>
        </div>
        
        <%
        }
        %>
        
    </div>
	
	</main>
	<jsp:include page="footer.jsp"></jsp:include>
	</div>
	
	<script type="text/javascript" src="js/utils.js"></script>
	
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