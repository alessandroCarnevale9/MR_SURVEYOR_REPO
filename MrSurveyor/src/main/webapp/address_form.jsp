<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Indirizzo</title>
<link rel="stylesheet" href="styles/form_style.css">
</head>
<body>
	
	

	<div class="main-box registration">
	
	<!--  <div class="flex-container"> -->
	<jsp:include page="header.jsp"></jsp:include>
	<!-- <main>  -->
	
		<div class="child-box address">
			<h1>Aggiungi il tuo Indirizzo</h1>
    		<form action="#" method="POST">
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
        			<input id="submit-btn" type="submit" value="Aggiungi Indirizzo">
        		</div>
    		</form>
    	</div>
    	
    <!-- </main> -->
	<jsp:include page="footer.jsp"></jsp:include>
	<!-- </div> -->
    	
	</div>
	
	
	
</body>
</html>