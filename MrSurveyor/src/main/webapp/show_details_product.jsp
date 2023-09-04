<%@page import="utils.Utlis"%>
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
	
	if(category == null)
		category = subcategory.getRootCategory();
	
	String subcategoryName = "";
	if(subcategory != null)
		subcategoryName = subcategory.getName();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=product.getName() %></title>
<link rel="stylesheet" href="styles/products.css">
</head>
<body>

	<div class="flex-container">
	<jsp:include page="header.jsp"></jsp:include>
	<main>

	<div class="item-prod detail">
		<img class="prod-img detail-img" alt="<%=product.getName() %>" src="images/prod/<%=product.getImagePath() %>">
		<div class="prod-description">
			<div class="prod-category">
				<small><%=Utlis.categoryLoop(category.getName(), subcategoryName)%></small>
			</div>
			<div class="prod-name">
				<h2><%=product.getName() %></h2>
			</div>
			<div class="prod-price">	
				<p><%=product.getPrice() %></p>
			</div>
		</div>
	</div>
	
	<p><%=product.getDescription() %></p>


	<div class="controls">
		<form action="${pageContext.request.contextPath}/CartServlet"
			method="GET">
			<input type="hidden" name="productID" value="<%=product.getId()%>">
			<label for="quantity">Quantità:</label> <select id="quantity"
				name="quantity" class="quantity-select">
				<%
					for(int i = 1; i <= product.getQuantity(); i++) {
				%>
				<option value="<%=i%>"><%=i %></option>
				<%
					}
				%>
			</select> <input type="submit" value="Aggiungi al Carrello">
		</form>
	</div>
	
	</main>
	<jsp:include page="footer.jsp"></jsp:include>
	</div>
	
</body>
</html>