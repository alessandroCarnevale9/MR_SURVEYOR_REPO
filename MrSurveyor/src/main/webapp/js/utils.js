const form = document.getElementById("registration-form");
const button = document.getElementById("submit-btn");

submitForm();

function submitForm() {
	
	form.addEventListener("submit", (e) => {
		
		e.preventDefault();
		
		button.disabled = true;
		button.textContent = "";
		button.parentElement.className = "submit loading";
		
		setTimeout(function() {
			button.disabled = false;
			form.submit();
		}, 2000);
	});
}