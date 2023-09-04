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
	
	%>
	
	<%!
	String contextPath = "/MrSurveyor";
	
	String categoryLoop(String category, String subcategory) {
		
		String result = "";
		
		String subcategoryCpy = subcategory;
		
		/*
		if(((category+subcategory).length() > 20)) {
			subcategoryCpy = subcategory.substring(0, subcategory.length()-subcategory.length()+4);
			subcategoryCpy += ".";
		}
		*/
		
		result += (subcategory != null && !subcategory.trim().equals(""))
				? "<a href=\""+contextPath+"/CatalogServlet?category=" + category + "\">"
						+ category + "</a>" + ", "
						+ "<a href=\""+contextPath+"/CatalogServlet?subcategory=" + subcategory
						+ "\">" + subcategoryCpy + "</a>"
				: "<a href=\""+contextPath+"/CatalogServlet?category=" + category + "\">" + category
						+ "</a>";
						
		return result;
	}
	%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=title%></title>
<link rel="stylesheet" href="styles/grid_style.css">
</head>
<body>
	<div class="grid-container">
	<%
	if(products != null && !products.isEmpty()) {
		Iterator<?> it = products.iterator();
		while(it.hasNext()) {
			Product productBean = (Product)it.next();
			
			if(category != null && !category.trim().equals("")) {
				session.setAttribute("category", category);
	%>
	
			<div class="product">
			<a href="${pageContext.request.contextPath}/CatalogServlet?detailProductID=<%=productBean.getId()%>">
				<img alt="<%=productBean.getName()%>" src="images/prod/<%=productBean.getImagePath()%>">
			</a>	
				<div class="prod-description">
					<small><%=categoryLoop(category, productBean.getSubcategories().get(0).getName())%></small>
					<h2>
					<a href="${pageContext.request.contextPath}/CatalogServlet?detailProductID=<%=productBean.getId()%>"><%=productBean.getName()%></a>
					</h2>
					<p><%=productBean.getPrice() %></p>
				</div>
			</div>
	<%
			} else if(subcategory != null && !subcategory.trim().equals("")) {
	%>
			<div class="product">
			<a href="${pageContext.request.contextPath}/CatalogServlet?detailProductID=<%=productBean.getId()%>">
				<img alt="<%=productBean.getName()%>" src="images/prod/<%=productBean.getImagePath()%>">
			</a>
				<div class="prod-description">
					<small>
					<a href="${pageContext.request.contextPath}/CatalogServlet?subcategory=<%=subcategory %>">
					<%=subcategory %>
					</a>
					</small>
					<h2>
					<a href="${pageContext.request.contextPath}/CatalogServlet?detailProductID=<%=productBean.getId()%>">
					<%=productBean.getName()%>
					</a>
					</h2>
					<p><%=productBean.getPrice() %></p>
				</div>
			</div>
	<%
			}
		}
	}
	%>
	</div>
</body>
</html>