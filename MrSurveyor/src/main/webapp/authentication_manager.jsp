<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Autenticazione</title>
<link rel="stylesheet" href="styles/form_style.css">
</head>
<body>
	
	
	<div class="main-box">
	
	<jsp:include page="header_manager.jsp"></jsp:include>
	
		<div class="child-box">
		
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
			<h1 id="header">Accedi</h1>
			<form id="registration-form" action="${pageContext.request.contextPath}/AuthenticationManagerServlet" method="POST">
			
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
            <input id="submit-btn" type="submit" value="Log In">
          </div>

          <div class="help-info">
            <a href="#">Password dimenticata&#x3F;</a>
          </div>
			
			</form>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
		
	<script type="text/javascript" src="js/utils.js"></script>

</body>
</html>