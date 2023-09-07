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

const screenWidth = window.innerWidth;

if (screenWidth <= 1290) {
    const hamburgerHTML = `
        <div class="hamburger-container">
            <div class="hamburger"></div>
            <div class="hamburger"></div>
            <div class="hamburger"></div>
        </div>
    `;
    
    document.getElementById('add_after_me').insertAdjacentHTML('afterend', hamburgerHTML);
    
    const hamburgerContainer = document.querySelector('.hamburger-container');
    const mobileNavbar = document.querySelector('.mobile-navbar');
    hamburgerContainer.addEventListener('click', () => {
        mobileNavbar.classList.toggle('active');
    });
}

if(screenWidth > 1290) {
	mobileNavbar.classList.remove('active');
}
