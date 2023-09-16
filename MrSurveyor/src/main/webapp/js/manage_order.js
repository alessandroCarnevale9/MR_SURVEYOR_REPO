function toggleOrderForm(orderId) {
	var orderForm = document.getElementById(orderId + "-form");
	if (orderForm.style.display === "none" || orderForm.style.display === "") {
		orderForm.style.display = "block";
	}
}

function validaECompletaOrdine(orderId) {
	
	var courier = document.getElementById("courier").value;
	var trackingNumber = document.getElementById("trackingNumber").value;
	var orderDate = document.getElementById("orderDate").value;
	var shipmentDate = document.getElementById("shipmentDate").value;
	
	var regexAlphanumeric = /^[0-9a-zA-Z]+$/;
	if (!regexAlphanumeric.test(trackingNumber)) {
		document.getElementById("tracking-number-error").textContent = "Il tracking number può contenere solo numeri e lettere.";
		return;
	}

	var orderDateValueDate = new Date(orderDate);
	var shipmentDateValueDate = new Date(shipmentDate);
	if (shipmentDateValueDate < orderDateValueDate) {
		document.getElementById("shipment-date-error").textContent = "La data di spedizione non può essere inferiore alla data dell'ordine.";
		return;
	}

	// Se i dati sono validi, puoi inviarli al sistema
	inviaDatiAlSistema(orderId, courier, trackingNumber, shipmentDate);
}

function inviaDatiAlSistema(orderId, courier, trackingNumber, shipmentDate) {
	
	$.ajax({
		url: "http://localhost:8080/MrSurveyor/OrderServlet?cmd=manageOrder",
		method: "POST",
		data: { orderId: orderId,
        		courier: courier,
        		trackingNumber: trackingNumber,
        		shipmentDate: shipmentDate },
        
        success: function() {
        	window.location.href = "http://localhost:8080/MrSurveyor/homepage_manager.jsp";
    	}
	});
}