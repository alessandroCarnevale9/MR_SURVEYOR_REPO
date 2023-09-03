
allSlides.forEach((slides, i) => {
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
          next(i+1);
        }
        else {
          prev(i+1);
        }
      }, debounceInterval);
    }

  });
});

window.addEventListener("mouseup", () => {
  moving = false;
});
