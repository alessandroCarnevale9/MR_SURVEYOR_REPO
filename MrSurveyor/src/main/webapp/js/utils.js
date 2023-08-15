const form = document.getElementById("registration-form");
const button = document.getElementById("submit-btn");
	
form.addEventListener("submit", (e) => {
		
	e.preventDefault();
	
	const currentText = button.textContent;
	
	button.disabled = true;
	button.textContent = "";
	button.parentElement.className = "submit loading";
		
	setTimeout(function() {
		button.disabled = false;
		button.textContent = currentText;
		button.parentElement.className = "submit";
		form.submit();
	}, 2000);
});