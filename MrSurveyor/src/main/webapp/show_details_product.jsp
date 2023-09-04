<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.Subcategory"%>
<%@page import="model.bean.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Product product = (Product)request.getAttribute("showProduct");
	String errorMessage = (String)request.getAttribute("error");

	if(product == null && errorMessage == null) {
		response.sendRedirect(getServletContext().getContextPath()+"/CatalogServlet");
		return;
	}
	
	ArrayList<Category> categories = product.getCategories();
	
	Category category = null;
	if(!categories.isEmpty())
		category = categories.get(0);
	
	ArrayList<Subcategory> subcategories = product.getSubcategories();

	Subcategory subcategory = null;	
	if(!subcategories.isEmpty())
		subcategory = subcategories.get(0);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=product.getName() %></title>
</head>
<body>
	<div class="product-container">
		<img class="product-image" alt="<%=product.getName() %>" src="images/prod/<%=product.getImagePath() %>">
		<div class="product-details">
		<%
		if(subcategory != null) {
		%>
			<a href="${pageContext.request.contextPath}/CatalogServlet?category=<%=subcategory.getRootCategory().getName()%>">
			<small><%=subcategory.getRootCategory().getName() %></small>
			</a>
			<small>
			<a href="${pageContext.request.contextPath}/CatalogServlet?subcategory=<%=subcategory.getName()%>">
			, <%=subcategory.getName() %>
			</a>
			</small>
		<%
		} else if(category != null){
		%>
			<a href="${pageContext.request.contextPath}/CatalogServlet?category=<%=category.getName()%>">
			<small><%=category.getName() %></small>
			</a>
		<%
		}
		%>
			<h1><%=product.getName() %></h1>
			<p><%=product.getDescription() %></p>
			<p><%=product.getPrice() %></p>
			<div class="controls">
				<form action="${pageContext.request.contextPath}/CartServlet" method="GET">
					<input type="hidden" name="productID" value="<%=product.getId()%>">
					<label for="quantity">Quantità:</label>
					<select id="quantity" name="quantity" class="quantity-select">
					<%
					for(int i = 1; i <= product.getQuantity(); i++) {
					%>
						<option value="<%=i%>"><%=i %></option>
					<%
					}
					%>
					</select>
					<input type="submit" value="Aggiungi al Carrello">
				</form>
			</div>
		</div>
	</div>
</body>
</html>