
showSlides(slideIndex);


function plusSlides(n) {
  showSlides(slideIndex += n);
}

function currentSlide(n) {
  showSlides(slideIndex = n);
}

function showSlides(n) {
  let i;
  let slides = document.getElementsByClassName("hero-slides");
  let dots = document.getElementsByClassName("dot");

  if(n > slides.length) {
    slideIndex = 1;
  }

  if(n < 1) {
    slideIndex = slides.length;
  }

  for(i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";
    dots[i].className = dots[i].className.replace(" active", "");
  }

  slides[slideIndex-1].style.display = "block";
  dots[slideIndex-1].className += " active";
}

setInterval(function() {
  plusSlides(1);
}, 3300);
