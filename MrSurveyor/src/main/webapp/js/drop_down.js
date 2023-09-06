// Ottieni riferimenti agli elementi HTML
var userDropdownToggle = document.getElementById("user-dropdown-toggle");
var userDropdown = document.getElementById("user-dropdown");

// Aggiungi un gestore di eventi per il clic sull'icona utente
userDropdownToggle.addEventListener("click", function(e) {
    e.preventDefault(); // Evita il comportamento predefinito del link
    if (userDropdown.style.display === "block") {
        userDropdown.style.display = "none"; // Chiudi il dropdown se è già aperto
    } else {
        userDropdown.style.display = "block"; // Apri il dropdown se è chiuso
    }
});

// Chiudi il dropdown se l'utente fa clic altrove nella pagina
window.addEventListener("click", function(e) {
    if (e.target !== userDropdownToggle && e.target !== userDropdown) {
        userDropdown.style.display = "none";
    }
});

