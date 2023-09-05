<%@page import="java.text.DecimalFormat"%>
<%@page import="utils.Utlis"%>
<%@page import="com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.bean.Product"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%
	Collection<?> products = (Collection<?>)request.getAttribute("products");
	String errorMessage = (String)request.getAttribute("error");
	
	if(products == null && errorMessage == null) {
		response.sendRedirect(getServletContext().getContextPath()+"/CatalogServlet");
		return;
	}
	
	String category = request.getParameter("category");
	String subcategory = request.getParameter("subcategory");
	
	String title = category != null ? category : subcategory;
	
	DecimalFormat df = new DecimalFormat("###,##0.00 €");
	
	%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=title%></title>
<link rel="stylesheet" href="styles/grid_style.css">
<link rel="stylesheet" href="styles/products.css">
</head>
<body>

	<div class="flex-container">
	<jsp:include page="header.jsp"></jsp:include>
	<main>

	<div class="grid-container">
	<%
	if(products != null && !products.isEmpty()) {
		Iterator<?> it = products.iterator();
		while(it.hasNext()) {
			Product productBean = (Product)it.next();
			
			if(category != null && !category.trim().equals("")) {
				session.setAttribute("category", category);
	%>
	
			<div class="item-prod">
			<a href="${pageContext.request.contextPath}/CatalogServlet?detailProductID=<%=productBean.getId()%>">
				<img class="prod-img detail-img" alt="<%=productBean.getName()%>" src="images/prod/<%=productBean.getImagePath()%>">
			</a>	
				<div class="prod-description">
					<div class="prod-category">
						<small><%=Utlis.categoryLoop(category, productBean.getSubcategories().get(0).getName())%></small>
					</div>
					<div class="prod-name">
						<h2>
						<a href="${pageContext.request.contextPath}/CatalogServlet?detailProductID=<%=productBean.getId()%>"><%=productBean.getName()%></a>
						</h2>
					</div>
					<div class="prod-price">
						<p><%=df.format(productBean.getPrice()) %></p>
					</div>
				</div>
			</div>
	<%
			} else if(subcategory != null && !subcategory.trim().equals("")) {
	%>
			<div class="item-prod">
			<a href="${pageContext.request.contextPath}/CatalogServlet?detailProductID=<%=productBean.getId()%>">
				<img class="prod-img detail-img" alt="<%=productBean.getName()%>" src="images/prod/<%=productBean.getImagePath()%>">
			</a>
				<div class="prod-description">
					<div class="prod-category">
						<small>
						<a href="${pageContext.request.contextPath}/CatalogServlet?subcategory=<%=subcategory %>">
						<%=subcategory %>
						</a>
						</small>
					</div>
					<div class="prod-name">
						<h2>
						<a href="${pageContext.request.contextPath}/CatalogServlet?detailProductID=<%=productBean.getId()%>">
						<%=productBean.getName()%>
						</a>
						</h2>
					</div>	
					<div class="prod-price">
						<p><%=df.format(productBean.getPrice()) %></p>
					</div>
				</div>
			</div>
	<%
			}
		}
	}
	%>
	</div>
	
	</main>
	<jsp:include page="footer.jsp"></jsp:include>
	</div>
	
</body>
</html>