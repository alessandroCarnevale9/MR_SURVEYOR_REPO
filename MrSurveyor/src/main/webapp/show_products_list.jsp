<%@page import="java.text.DecimalFormat"%>
<%@page import="model.bean.Product"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    Collection<?> searchedProducts = (Collection<?>)request.getAttribute("searchedProducts");
	Collection<?> cartProducts = (Collection<?>)request.getAttribute("cartProducts");
    
	if(searchedProducts == null && cartProducts == null) {
		response.sendRedirect(getServletContext().getContextPath()+"/CatalogServlet");
		return;
	}
	
	String title = searchedProducts != null ? "Risultati ricerca" : "Riepilogo prodotti";
	
	DecimalFormat df = new DecimalFormat("###,##0.00 €");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=title %></title>
<link rel="stylesheet" href="styles/products.css">
</head>
<body>
	<div class="flex-container">
	<jsp:include page="header.jsp"></jsp:include>
	<main>
		<div class="product-grid">
			<%
			Iterator<?> it = searchedProducts.iterator();
				while(it.hasNext()) {
					Product productBean = (Product)it.next();
			%>
				<div class="product-grid-item" onclick="redirectToDetatils('${pageContext.request.contextPath}/CatalogServlet?detailProductID=<%=productBean.getId()%>')">
					<img src="images/prod/<%=productBean.getImagePath() %>" alt="<%=productBean.getName() %>">
					<h3><%=productBean.getName() %></h3>
					<p><%=df.format(productBean.getPrice()) %></p>
				</div>
			<%
				}
			%>
		</div>
	</main>
	<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>