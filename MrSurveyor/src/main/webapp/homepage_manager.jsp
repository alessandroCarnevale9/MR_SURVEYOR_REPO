<%@page import="model.bean.Order"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="model.bean.Manager.Role"%>
<%@page import="model.bean.Manager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    Manager manager = (Manager)session.getAttribute("manager");
    
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
	<jsp:include page="header_manager.jsp"></jsp:include>
	
	<div class="container">
        <h1>Lista degli Ordini</h1>
        
        <%
        Iterator<?> it = ordersToManage.iterator();
        while(it.hasNext()) {
        	Order o = (Order)it.next();
        %>
        <div class="order" onclick="toggleOrderForm('order-<%=o.getId() %>')"> <!-- Aggiungi un ID unico per ogni ordine, ad esempio "order-1" -->
            <p><span class="label">ID ordine:</span> <%=o.getId() %></p>
            <p><span class="label">ID cliente:</span> <%=o.getEndUser().getId() %></p>
            <p><span class="label">Data dell'ordine:</span> <%=o.getOrderDate() %></p>
            <p><span class="label">Prezzo totale:</span> <%=o.getTotalPrice() %></p>
            <div class="order-form" id="order-<%=o.getId() %>-form"> <!-- Aggiungi un ID unico per ogni form, ad esempio "order-1-form" -->
                <!-- Form per la gestione dell'ordine -->
                <form>
                    <div class="form-group">
                        <label for="corriere">Seleziona un corriere:</label>
                        <select id="corriere" name="corriere">
                            <option value="BRT">BRT</option>
                            <!-- Altre opzioni del corriere possono essere aggiunte qui -->
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="tracking-number">Tracking number:</label>
                        <input type="text" id="tracking-number" name="tracking-number" placeholder="Inserisci il tracking number" required>
                    </div>
                    <div class="form-group">
                        <label for="data-spedizione">Data di spedizione:</label>
                        <input type="date" id="data-spedizione" name="data-spedizione" required>
                    </div>
                    <button type="button" onclick="validaECompletaOrdine('order-<%=o.getId() %>')">Completa</button>
                </form>
            </div>
        </div>
        <%
        }
        %>
    </div>
	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
<%
}
%>
</html>