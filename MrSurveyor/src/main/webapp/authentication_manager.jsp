<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Autenticazione</title>
<link rel="stylesheet" href="styles/form_style.css">
</head>
<body>
	
	<jsp:include page="header.jsp"></jsp:include>
	
	<div class="main-box">
	
		<div class="child-box">
			<h1 id="header">Accedi</h1>
			<form class="auth-form" action="${pageContext.request.contextPath}/AuthenticationManagerServlet" method="POST">
			
			<input type="text" name="username" placeholder="Username" required>
			<input type="password" name="password" placeholder="Password" required>
			
			<div class="radio-btns">
            <div class="r-btn">
              <label for="role1">Gestore catalogo</label>
  			      <input type="radio" id="role1" name="role" value="catalog_manager" checked="checked">
            </div>

            <div class="r-btn">
              <label for="role2">Gestore ordini</label>
              <input type="radio" id="role2" name="role" value="order_manager">
            </div>
          </div>
          
          <div class="submit">
            <input type="submit" value="Log In">
          </div>

          <div class="help-info">
            <a href="#">Password dimenticata&#x3F;</a>
          </div>
			
			</form>
		</div>
		
	</div>
	
	<!-- <form action="${pageContext.request.contextPath}/AuthenticationManagerServlet" method="post">
		<fieldset>
			<legend>Accedi</legend>
			
			<label for="username">Username:</label>
			<input type="text" id="username" name="username" required>
			
			<label for="password">Password:</label>
			<input type="password" id="password" name="password" required>
			
			<div id="role">
			
			<label for="role1">Gestore catalogo:</label>
			<input type="radio" id="role1" name="role" value="catalog_manager" checked="checked">
			
			<label for="role2">Gestore ordini:</label>
			<input type="radio" id="role2" name="role" value="order_manager">
			</div>
			
			<input type="submit" value="Log In">
			
			<div><a href="#">Password dimenticata?</a></div>
			
		</fieldset>
	</form> -->
	
	<%
	String errorMessage = (String) request.getAttribute("error");

	if (errorMessage != null && !errorMessage.trim().equals("")) {
	%>
	<div id="error_message">
		<%=errorMessage%>
	</div>
	<%
	}
	%>
		
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>