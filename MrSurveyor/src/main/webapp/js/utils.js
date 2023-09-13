const form = document.getElementById("registration-form");
const button = document.getElementById("submit-btn");

if(form != null) {
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
}

function showAddressForm(button) {
	const addressMenu = document.getElementById('address-form');
	addressMenu.style.display = 'block';
	button.style.display = 'none';
}

function validateAddressForm() {
    
    var region = document.getElementById('region');
    var province = document.getElementById('province');
    var city = document.getElementById('city');
    var street = document.getElementById('street');
    var houseNumber = document.getElementById('houseNumber');
    var cap = document.getElementById('cap');

    if (
        region.value.trim() === '' ||
        province.value.trim() === '' ||
        city.value.trim() === '' ||
        street.value.trim() === '' ||
        houseNumber.value.trim() === '' ||
        cap.value.trim() === ''
    ) {
        return false;
    }
    
    return true;
}


function hideAddressForm() {
	
	 if (!validateAddressForm()) {
        return;
    }
	
	const addressMenu = document.getElementById('address-form');
	const addrButton = document.getElementById('addr-funct');
	
	addrButton.style.display = 'block';
	addressMenu.style.display = 'none';
}

function display(btn, el) {
	const element = document.querySelector(el);
	
	btn.style.display = 'none';
	element.style.display = 'flex';
}

function isValidCreditCard() {
    var today = new Date();

    var expireInput = document.getElementById("expire").value;
    var expireDate = new Date("20" + expireInput.substring(3), expireInput.substring(0, 2) - 1, 1);

    if (expireDate <= today) {
        return false;
    }

    var cardNumber = document.getElementById("card_number").value.replace(/\s/g, '');
    if (cardNumber.length !== 16 || isNaN(cardNumber)) {
        return false;
    }

    var cvc = document.getElementById("cvc").value.trim();
    if (cvc.length !== 3 || isNaN(cvc)) {
        return false;
    }

    return true;
}

function handleSubmitCreditCard() {
    if (isValidCreditCard()) {
        var submitBtn = document.getElementById("submit-btn");
        submitBtn.value = "Modifica Carta";
    }
}

function hideTheProcdBtn() {
	
	const prcdForm = document.querySelector('.final-check');
	
	var computedStyle = window.getComputedStyle(prcdForm);
	
	if(computedStyle.display !== 'none') {
		const elem = document.getElementById('prcd');
	
		if(elem != null) {
			elem.style.display = 'none';
		}
	}
}

hideTheProcdBtn();