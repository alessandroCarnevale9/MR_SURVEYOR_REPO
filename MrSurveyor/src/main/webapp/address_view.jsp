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
<title>Il tuo indirizzo</title>
<link rel="stylesheet" type="text/css" href="styles/enduser_home.css">
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
	
		<a href="address_form.jsp" class="funct"><%=buttonText %></a>
	
	</section>
	</div>
	</main>
	<jsp:include page="footer.jsp"></jsp:include>
	</div>
	
</body>
</html>