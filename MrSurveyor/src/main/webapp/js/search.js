var searchForms = document.querySelectorAll(".search-form");

searchForms.forEach(function(form) {
	form.addEventListener("submit", function(event) {
		event.preventDefault();

		// Trova l'input di ricerca all'interno del form
		var searchInput = form.querySelector(".live-search");

		// Ottieni il valore dell'input di ricerca
		var inputValue = searchInput.value.trim();

		// Verifica se il campo di ricerca è vuoto
		if (inputValue != null && inputValue !== "") {
			// Se il campo di ricerca non è vuoto, procedi con il submit del modulo
			// (rimuovi il codice seguente se non è necessario)
			
			// Crea il campo nascosto "isManualSubmit" e imposta gli attributi
			var isManualSubmitInput = document.createElement("input");
			isManualSubmitInput.type = "hidden";
			isManualSubmitInput.name = "isManualSubmit";
			isManualSubmitInput.value = "true";

			// Trova il div con classe "input-container" all'interno del form
			var inputContainer = form.querySelector(".input-container");

			// Aggiungi il campo nascosto al div "input-container"
			inputContainer.appendChild(isManualSubmitInput);

			// Esegui il submit del form
			form.submit();

			setTimeout(function() {
				// Rimuovi l'elemento nascosto
				inputContainer.removeChild(isManualSubmitInput);
			}, 1000);
		}
	});
});

$(document).ready(function() {
	$('.live-search').keyup(function(event) {
		var input = $(this).val();

		if (input.trim() != "") {
			$.ajax({
				url: "http://localhost:8080/MrSurveyor/SearchServlet",
				method: "GET",
				data: { search: input },

				success: function(data) {
					$('#search-results').css("display", "grid");
					$('#search-results').css("grid-template-rows", "repeat(auto-fit, minmax(200px, 1fr))");
					$('#search-results').css("gap", "20px");
					$('#search-results').html(data);
					$('#search-results').css("border", "1px solid silver");
					if (data.length !== 0) {
						$('#search-results').css("border-top", "none");
					} else {
						$('#search-results').html('<h3 style="text-align: center; margin:auto;">Nessun risultato</h3>');
					}
				},
				error: function(err) {
					console.error(err);
				}
			});
		} else {
			$('#search-results').css("display", "none");
		}

		// Aggiungi un gestore di eventi keydown per la freccia giù e la freccia su
		$(this).keydown(function(event) {
			var searchResults = $('#search-results');
			var searchItems = searchResults.find('.product');

			if (event.keyCode === 40 || event.keyCode === 38) { // Codice delle frecce giù (40) e su (38)
				event.preventDefault();
				if (searchItems.length > 0) {
					var focusedItem = document.activeElement;
					var index = searchItems.index(focusedItem);
					if (event.keyCode === 40) {
						// Freccia giù, passa al prodotto successivo
						index = (index + 1) % searchItems.length;
					} else {
						// Freccia su, passa al prodotto precedente
						index = (index - 1 + searchItems.length) % searchItems.length;
					}
					searchItems.eq(index).focus();
				}
			}
		});
	});

	// Aggiungi un gestore di eventi keydown agli elementi dei risultati
	$('#search-results').on('keydown', '.product', function(event) {
		var searchItems = $('#search-results').find('.product');
		var index = searchItems.index(this);

		if (event.keyCode === 40) { // Codice della freccia giù
			event.preventDefault();
			if (index < searchItems.length - 1) {
				// Passa il focus all'elemento successivo
				searchItems.eq(index + 1).focus();
			}
		} else if (event.keyCode === 38) { // Codice della freccia su
			event.preventDefault();
			if (index > 0) {
				// Passa il focus all'elemento precedente
				searchItems.eq(index - 1).focus();
			}
		}
	});

	$(window).click(function(event) {
		var searchResults = $('#search-results');
		var liveSearchInputs = $('.live-search'); // Seleziona tutti gli input con classe 'live-search'

		// Controlla se l'elemento cliccato è il div #search-results o un input di ricerca
		if (!searchResults.is(event.target) && searchResults.has(event.target).length === 0 &&
			!liveSearchInputs.is(event.target) && liveSearchInputs.has(event.target).length === 0) {
			searchResults.css("display", "none");
		}
	});
});

function redirectToDetatils(url) {
	window.location.href = url;
}
