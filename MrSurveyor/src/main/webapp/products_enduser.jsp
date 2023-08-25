<%@page import="java.util.Iterator"%>
<%@page import="model.bean.Product"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

	<%
	Collection<?> products = (Collection<?>)request.getAttribute("products");
	String errorMessage = (String)request.getAttribute("error");
	
	if(products == null && errorMessage == null) {
		response.sendRedirect(getServletContext().getContextPath()+"/CatalogServlet");
		return;
	}
	%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><%=request.getParameter("category")%></title>
<link rel="stylesheet" href="styles/grid_style.css">
</head>
<body>
	<div class="grid-container">
	<%
	if(products != null && !products.isEmpty()) {
		Iterator<?> it = products.iterator();
		while(it.hasNext()) {
			Product productBean = (Product)it.next();
	%>
	<div class="product">
		<img alt="<%=productBean.getName()%>" src="images/prod/<%=productBean.getImagePath()%>">
		<div class="prod-description">
			<small>categoria, sottocategoria</small>
			<h2><%=productBean.getName()%></h2>
			<p><%=productBean.getPrice() %></p>
		</div>
	</div>
	<%
		}
	}
		%>
	</div>
</body>
</html>