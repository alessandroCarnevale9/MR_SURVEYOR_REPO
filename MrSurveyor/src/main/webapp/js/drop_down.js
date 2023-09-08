var userDropdownToggle = document.getElementById("user-dropdown-toggle");
var userDropdown = document.getElementById("user-dropdown");

userDropdownToggle.addEventListener("click", function(e) {
	e.preventDefault();
	if (userDropdown.style.display === "block") {
		userDropdown.style.display = "none";
	} else {
		userDropdown.style.display = "block";
	}
});

window.addEventListener("click", function(e) {
	if (e.target !== userDropdownToggle && e.target !== userDropdown) {
		userDropdown.style.display = "none";
	}
});



let isHamburgerMenuCreated = false; // Variabile di stato per tenere traccia della creazione dell'hamburger menu
const mobileNavbar = document.querySelector('.mobile-navbar');

function createHamburgerMenu() {
	const hamburgerHTML = `
        <div class="hamburger-container">
            <div class="hamburger"></div>
            <div class="hamburger"></div>
            <div class="hamburger"></div>
        </div>
    `;

	document.getElementById('add_after_me').insertAdjacentHTML('afterend', hamburgerHTML);

	const hamburgerContainer = document.querySelector('.hamburger-container');

	hamburgerContainer.addEventListener('click', () => {
		mobileNavbar.classList.toggle('active');
	});

	isHamburgerMenuCreated = true; // Imposta la variabile di stato a true dopo la creazione
}

function manageHamburgerMenu() {
	const windowWidth = window.innerWidth;
	const minWidthForHamburger = 1290; // Imposta la larghezza a cui l'hamburger menu dovrebbe apparire

	// Se l'hamburger menu non è stato creato e la finestra è abbastanza stretta, crea l'hamburger menu
	if (!isHamburgerMenuCreated && windowWidth <= minWidthForHamburger) {
		createHamburgerMenu();
	}

	// Controlla se mobileNavbar è aperto e chiudilo se la finestra è abbastanza larga
	if (windowWidth > minWidthForHamburger && mobileNavbar.classList.contains('active')) {
		mobileNavbar.classList.remove('active');
	}
}

// Funzione per chiudere il side menu
function closeSideMenu() {
	if (mobileNavbar.classList.contains('active')) {
		mobileNavbar.classList.remove('active');
	}
}

// Aggiungi un listener all'oggetto window per gestire il clic al di fuori del side menu
window.addEventListener('click', function(event) {
	const target = event.target;

	// Verifica se il clic è avvenuto al di fuori del mobileNavbar e del pulsante hamburger
	if (!mobileNavbar.contains(target) && !document.querySelector('.hamburger-container').contains(target)) {
		closeSideMenu();
	}
});

// Chiama la funzione per inizializzare la gestione dell'hamburger menu
manageHamburgerMenu();

// Aggiungi un listener per l'evento di ridimensionamento della finestra
window.addEventListener('resize', manageHamburgerMenu);


// Funzione per mantenere i risultati coerenti tra i campi di ricerca
// Seleziona gli elementi <input> con classe 'live-search' nella barra di ricerca desktop e mobile
const desktopLiveSearchInput = document.querySelector('.search-bar .live-search');
const mobileLiveSearchInput = document.querySelector('.mobile-search .live-search');

// Aggiungi un evento di ascolto all'input della barra di ricerca desktop
desktopLiveSearchInput.addEventListener('input', function() {
	// Aggiorna il valore dell'input della barra di ricerca mobile
	mobileLiveSearchInput.value = this.value;
});

// Aggiungi un evento di ascolto all'input della barra di ricerca mobile
mobileLiveSearchInput.addEventListener('input', function() {
	// Aggiorna il valore dell'input della barra di ricerca desktop
	desktopLiveSearchInput.value = this.value;
});
