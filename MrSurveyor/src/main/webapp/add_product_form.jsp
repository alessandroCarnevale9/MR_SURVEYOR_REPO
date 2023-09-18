<%@page import="model.bean.Subcategory"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    if(session.getAttribute("manager") == null) {
    	response.sendRedirect(getServletContext().getContextPath()+"/authentication_manager.jsp");
    	return;
    }
    
    Collection<?> categories = (Collection<?>)request.getAttribute("categories");
    if(categories == null || categories.isEmpty()) {
    	response.sendRedirect(getServletContext().getContextPath()+"/AddProductServlet?param=getCategories");
    	return;
    }
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="styles/form_style.css">
</head>
<body>

	<div class="flex-container">
	<jsp:include page="header_manager.jsp"></jsp:include>
	<main>

	<div id="add-form-container">
	<h1>Aggiungi Prodotto</h1>
    <form action="${pageContext.request.contextPath}/AddProductServlet" method="POST" enctype="multipart/form-data">

        <label for="product_image">Carica un'immagine:</label>
        <input type="file" id="product_image" name="product_image" size="50" accept="image/*" required>

        <label for="product_name">Nome del prodotto:</label>
        <input type="text" id="product_name" name="product_name" required>

        <label for="product_price">Prezzo:</label>
        <input type="number" id="product_price" name="product_price" step="0.01" required>

        <label for="product_quantity">Quantità:</label>
        <input type="number" id="product_quantity" name="product_quantity" required>

        <label for="product_description">Descrizione:</label>
        <textarea id="product_description" name="product_description" rows="4" required></textarea>

        <!-- Scegli la categoria -->
        <label for="product_category">Categoria:</label>
        <select class="catalog" id="product_category" name="product_category" onchange="findSubcategories()">
        <%
        Iterator<?> it = categories.iterator();
        while(it.hasNext()) {
        	Category c = (Category)it.next();
        %>
            <option value="<%=c.getName()%>"><%=c.getName() %></option>
        <%
        }
        %>    
        </select>

        <!-- Seleziona la sottocategoria (se presente) -->
        <div id="subcategory-container">
        	<label for="product_subcategory">Sottocategoria:</label>
        	<select class="catalog" id="product_subcategory" name="product_subcategory" multiple>
    
       		</select>
       	</div>
        <button class="catalog" type="submit">Inserisci Prodotto</button>
    	</form>
    	</div>
    	
    	</main>
		<jsp:include page="footer.jsp"></jsp:include>
		</div>
		
		<script type="text/javascript" src="js/utils.js"></script>
</body>
</html>