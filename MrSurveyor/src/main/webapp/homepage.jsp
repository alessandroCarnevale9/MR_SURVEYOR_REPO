<%@page import="java.util.LinkedList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.bean.Product"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	Collection<?> productsCollection = (Collection<?>)request.getAttribute("homepage_products");

	if(productsCollection == null) {
		response.sendRedirect(getServletContext().getContextPath()+"/CatalogServlet?homepage");
		return;
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
<link rel="stylesheet" href="styles/slider.css">
<link rel="stylesheet" href="styles/slider_product.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<div class="flex-container">
	<jsp:include page="header.jsp"></jsp:include>
	
	<div class="hero-slider-container">

      <div class="hero-slides hero-fade">
        <div class="numbertext">1 / 3</div>
        <img class="hero-slide-img" src="images/slider/1.jpg" alt="image1">
        <div class="text" id="text1">Scopri la nostra vasta gamma di strumenti topografici 
        di alta precisione, perfetti per<br>professionisti 
        del settore e appassionati di topografia.</div>
      </div>

      <div class="hero-slides hero-fade">
        <div class="numbertext">2 / 3</div>
        <img class="hero-slide-img" src="images/slider/2.jpg" alt="image2">
        <div class="text" id="text2">Misura e mappa il territorio con facilità 
        grazie ai nostri innovativi strumenti topografici,<br>
        progettati per garantire risultati accurati e affidabili.</div>
      </div>

      <div class="hero-slides hero-fade">
        <div class="numbertext">3 / 3</div>
        <img class="hero-slide-img" src="images/slider/3.jpg" alt="image3">
        <div class="text" id="text3">Affronta qualsiasi sfida topografica con
         fiducia grazie alla nostra selezione di strumenti<br>ad alta precisione 
         e tecnologicamente avanzati, pronti a soddisfare le tue esigenze.</div>
      </div>

      <a class="hero-prev" onclick="plusSlides(-1)">&#10094;</a>
      <a class="hero-next" onclick="plusSlides(1)">&#10095;</a>

    </div>
    <br>

    <div id="dots">
      <span class="dot" onclick="currentSlide(1)"></span>
      <span class="dot" onclick="currentSlide(2)"></span>
      <span class="dot" onclick="currentSlide(3)"></span>
    </div>
    
    
    <div class="prducts">
    
    <%
    int slideID = 0;
    if(productsCollection != null && productsCollection.size() != 0) {
    	Iterator<?> it = productsCollection.iterator();
    	while(it.hasNext()) {
    		
    		slideID++;
    		
    		LinkedList<?> products = (LinkedList<?>)it.next();
    		Product first = (Product)products.get(0);
    		String rootCategory = first.getCategories().get(0).getName();
    %>
    		<div class="head-slider">
    		<h2><%=rootCategory %></h2>
    		<div class="arrows">
    			<a class="prev" onclick="prev('<%=slideID%>')">&#10094;</a>
    			<a class="next" onclick="next('<%=slideID%>')">&#10095;</a>
    		</div>
    		</div>
   
    		<div class="slider-container">
    		<div class="slides fade" id="<%=slideID %>">
    		 <%
    			Iterator<?> prodIterator = products.iterator();
    			while(prodIterator.hasNext()) {
    				Product p = (Product)prodIterator.next();
    		%>
    				<div class="slide-item">
    					<img class="slide-img" alt="<%=p.getName() %>" src="images/prod/<%=p.getImagePath() %>">
    					<div class="prod-description">
    					<div class="prod-category">
    						<a href="#"><%=rootCategory%>
    						<%
    						String subcategory = p.getSubcategories().get(0).getName();
    						if(subcategory != null && !subcategory.trim().equals("")) {
    						%>
    						, <%=subcategory %></a>
    						<%
    						}
    						%>
    					</div>
    					<div class="prod-name">
    						<h2><a href="#"><%=p.getName() %></a></h2>
    					</div>
    					<div class="prod-price"><%=p.getPrice() %></div>
    					</div>
    				</div>
    			<%
    			}
    			%>
    			</div>
    			</div>
    <%
    		}
    }
    %>
    </div>

	<script type="text/javascript" src="js/global_vars.js"></script>
    <script type="text/javascript" src="js/slider.js"></script>
    <script type="text/javascript" src="js/product_slider.js"></script>
    <script type="text/javascript" src="js/slider-mouse-event.js"></script>
    
    
	<jsp:include page="footer.jsp"></jsp:include>
	
	</div>
	
	<script type="text/javascript" src="js/slider.js"></script>
    <script type="text/javascript" src="js/product_slider.js"></script>
    <script type="text/javascript" src="js/slider-mouse-event.js"></script>
</body>
</html>