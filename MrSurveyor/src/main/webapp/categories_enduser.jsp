<%@page import="java.util.Iterator"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	Collection<?> categories = (Collection<?>)request.getAttribute("categories");
	String errorMessage = (String)request.getAttribute("error");
	
	if(categories == null && errorMessage == null) {
		response.sendRedirect(getServletContext().getContextPath()+"/CatalogServlet");
		return;
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Prodotti</title>
<link rel="stylesheet" href="styles/categories_grid_style.css">
</head>
<body>
	<div class="grid-container">
		<%
		if(categories != null && !categories.isEmpty()) {
			Iterator<?> it = categories.iterator();
			while(it.hasNext()) {
				Category categoryBean = (Category)it.next();
		%>
		<div class="category">
			<img alt="<%=categoryBean.getName()%>" src="images/categ/<%=categoryBean.getPathImage()%>">
			<h3><%=categoryBean.getName()%></h3>
		</div>
		<%
			}
		} else if(errorMessage != null) {
		%>
			<p id="error-message"><%=errorMessage%></p>
		<%
		}
		%>
	</div>
</body>
</html>