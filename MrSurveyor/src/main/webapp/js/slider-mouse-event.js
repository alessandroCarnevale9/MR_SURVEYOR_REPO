const slides = document.querySelector(".slides");

let moving = false;
let mouseLastPosition = 0;
let debounceInterval = 100; // Intervallo di debounce in millisecondi
let debounceTimeout;

slides.addEventListener("mousedown", (e) => {
  e.preventDefault(); // Impedisce il trascinamento predefinito
  moving = true;
  mouseLastPosition = e.pageX;
});

slides.addEventListener("mousemove", (e) => {
  if(moving) {

    clearTimeout(debounceTimeout);
    debounceTimeout = setTimeout(() => {
      
      const diffX = e.pageX - mouseLastPosition;

      if(diffX < 0) {
        next();
      }
      else {
        prev();
      }
    }, debounceInterval);
  }

});

window.addEventListener("mouseup", () => {
  moving = false;
});
