var userDropdownToggle = document.getElementById("user-dropdown-toggle");
var userDropdown = document.getElementById("user-dropdown");

userDropdownToggle.addEventListener("click", function(e) {
    e.preventDefault(); // Evita il comportamento predefinito del link
    if (userDropdown.style.display === "block") {
        userDropdown.style.display = "none"; // Chiudi il dropdown se è già aperto
    } else {
        userDropdown.style.display = "block"; // Apri il dropdown se è chiuso
    }
});

window.addEventListener("click", function(e) {
    if (e.target !== userDropdownToggle && e.target !== userDropdown) {
        userDropdown.style.display = "none";
    }
});