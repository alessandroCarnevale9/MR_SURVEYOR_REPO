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
	
	if(prcdForm != null) {
		var computedStyle = window.getComputedStyle(prcdForm);
	
		if(computedStyle.display !== 'none') {
			const elem = document.getElementById('prcd');
	
			if(elem != null) {
				elem.style.display = 'none';
			}
		}
	}
}

hideTheProcdBtn();

function displayOrderProducts(orderIndex) {
	 var productsContainer = document.getElementById("products" + orderIndex);

        if (productsContainer.style.display === "none" || productsContainer.style.display === "") {
            productsContainer.style.display = "block";
        } else {
            productsContainer.style.display = "none";
        }
}

function handleSelectChange() {
	var select = document.getElementById("order");
	var selectedOption = select.options[select.selectedIndex].value;
	
	window.location.href = "http://localhost:8080/MrSurveyor/CatalogManagerServlet?cmd=showProducts&ord="+selectedOption;
}

function findSubcategories() {
	var select = document.getElementById("product_category");
	var selectedOption = select.options[select.selectedIndex].value;
	
	$.ajax({
		url: "http://localhost:8080/MrSurveyor/AddProductServlet?param=getSubcategories",
		method: "POST",
		data: {categoryName: selectedOption},
		dataType: "json",
		
		success: function (data) {
            // "data" conterrà la collezione di subcategorie come oggetto JavaScript
            // Popola dinamicamente il menu a discesa con le opzioni ottenute dalla chiamata AJAX
            
            var selectSub = document.getElementById('subcategory-container');
            if (data === null || data.length ==  0) {
				selectSub.style.display = 'none';
			} else {
				selectSub.style.display = 'block';
				populateSubcategoriesDropdown(data);
			}
        }
	});
}

function populateSubcategoriesDropdown(subcategories) {
    var select = document.getElementById("product_subcategory");

    // Pulisci le opzioni esistenti
    select.innerHTML = "";

    // Aggiungi le opzioni basate sulla collezione di subcategorie
    for (var i = 0; i < subcategories.length; i++) {
        var subcategory = subcategories[i];
        var option = document.createElement("option");
        option.value = subcategory.id;
        option.textContent = subcategory.name;
        select.appendChild(option);
    }
}