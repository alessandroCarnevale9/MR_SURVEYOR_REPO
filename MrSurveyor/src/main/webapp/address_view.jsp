<%@page import="model.bean.Address"%>
<%@page import="model.bean.RegisteredEndUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%

	if(session == null || session.getAttribute("registeredEndUser") == null) {
		response.sendRedirect(getServletContext().getContextPath()+"/authentication_enduser.jsp");
		return;
	}

	RegisteredEndUser registeredEndUser = (RegisteredEndUser)session.getAttribute("registeredEndUser");
	
	String buttonText = "Aggiungi";
%>

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Il tuo indirizzo</title>
<link rel="stylesheet" type="text/css" href="styles/enduser_home.css">
<link rel="stylesheet" href="styles/form_style.css">
</head>
<body>
	
	<div class="flex-container">
	<jsp:include page="header.jsp"></jsp:include>
	<main>
	
	<jsp:include page="enduser_sidemenu.jsp"></jsp:include>

	<div class="body-info">
	<section class="welcome-message">
		<h2>Indirizzo di spedizione</h2>
		<p>Il seguente indirizzo sarà usato come predefinito nella pagina di riepilogo dell'ordine.</p>
	</section>
	
	<section class="address-info">
	<%
	Address address = registeredEndUser.getAddress();
	if(!address.isValidAddress()) {
	%>
		<p>Ancora nessuno indirizzo di spedizione aggiunto</p>
	<%
	} else {
		buttonText = "Modifica";
	%>
			<div><%=registeredEndUser.getName()+" "+registeredEndUser.getSurname() %></div>
			<div><%=address.getStreet()+" "+address.getHouseNumber() %></div>
			<div><%=address.getCap() %></div>
			<div><%=address.getRegion()+" "+address.getProvince() %></div>
			<div><%=address.getCity() %></div>
	<%
	}
	%>
	
		<%
		if(request.getAttribute("checkout") != null) {
		%>
			<br><small>Aggiungi o modifica l'indirizzo</small>
		<%
		}
		%>
		<a href="#aggiungi_indirizzo" id="addr-funct" class="funct" onclick="showAddressForm(this)"><%=buttonText %></a>
	
		<%
		if(request.getAttribute("checkout") != null) {
		%>
			<small>poi:</small>
			<a href="order_details.jsp" class="funct">Procedi all'acqusisto</a>
		<%
		}
		%>
		
	</section>
	</div>
	
	<div class="child-box address user-home" id="address-form">
		
		<%
		String errorMessage = (String) request.getAttribute("error");

		if (errorMessage != null && !errorMessage.trim().equals("")) {
		%>
			<div id="error-message">
				<%=errorMessage%>
			</div>
		<%
		}
		%>
		
			<h1>Nuovo Indirizzo</h1>
    		<form action="${pageContext.request.contextPath}/ManageAddressServlet" method="POST">
        		
        		<%
				if(request.getAttribute("checkout") != null) {
				%>
				<input type="hidden" name="checkout" value="checkout">
				<%
				}
				%>
        		
        		<label for="region">Regione:</label>
        		<input class="address" type="text" id="region" name="region" required>
        
        		<label for="province">Provincia:</label>
        		<input class="address" type="text" id="province" name="province" required>
        
        		<label for="city">Città:</label>
        		<input class="address" type="text" id="city" name="city" required>
        
        		<label for="street">Strada:</label>
        		<input class="address" type="text" id="street" name="street" required>
        
        		<label for="houseNumber">Numero Civico:</label>
        		<input class="address" type="number" min="1" id="houseNumber" name="houseNumber" required>
        
        		<label for="cap">CAP:</label>
        		<input class="address" type="text" id="cap" name="cap" pattern="[0-9]{5}" maxlength="5" placeholder="12345" required>
        
        		<div class="submit address">
        			<input id="submit-btn" type="submit" value="Aggiungi Indirizzo" onclick="hideAddressForm(this)">
        		</div>
    		</form>
    	</div>
    	
	</main>
	<jsp:include page="footer.jsp"></jsp:include>
	</div>
	
	 <script type="text/javascript" src="js/utils.js"></script>
	
</body>
</html>