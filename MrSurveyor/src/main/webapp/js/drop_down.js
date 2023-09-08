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




let isHamburgerMenuCreato = false; // Variabile di stato per tenere traccia della creazione dell'hamburger menu
const mobileNavbar = document.querySelector('.mobile-navbar');

function creaHamburgerMenu() {
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

    isHamburgerMenuCreato = true; // Imposta la variabile di stato a true dopo la creazione
}

function gestisciHamburgerMenu() {
    const larghezzaFinestra = window.innerWidth;
    const larghezzaMinimaPerHamburger = 1290; // Imposta la larghezza a cui l'hamburger menu dovrebbe apparire

    // Se l'hamburger menu non è stato creato e la finestra è abbastanza stretta, crea l'hamburger menu
    if (!isHamburgerMenuCreato && larghezzaFinestra <= larghezzaMinimaPerHamburger) {
        creaHamburgerMenu();
    }

    // Controlla se mobileNavbar è aperto e chiudilo se la finestra è abbastanza larga
    if (larghezzaFinestra > larghezzaMinimaPerHamburger && mobileNavbar.classList.contains('active')) {
        mobileNavbar.classList.remove('active');
    }
}

// Chiama la funzione per inizializzare la gestione dell'hamburger menu
gestisciHamburgerMenu();

// Aggiungi un listener per l'evento di ridimensionamento della finestra
window.addEventListener('resize', gestisciHamburgerMenu);
