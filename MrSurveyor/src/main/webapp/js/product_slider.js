const sliderContainer = document.querySelector(".slider-container");
const containerWidth = sliderContainer.offsetWidth;

const slider = document.querySelector(".slides");

const slideWidth = 250;

const numSlides = document.querySelectorAll(".slide-item").length;

let currentPosition = 0;
const maxPosition = -((slideWidth * numSlides) - containerWidth);

function next() {
  if(currentPosition > maxPosition) {
    currentPosition -= slideWidth;
  }
  else if (currentPosition === maxPosition) {
    currentPosition = 0;
  }
  updateSliderPosition();
}

function prev() {
  if(currentPosition < 0) {
    currentPosition += slideWidth;
  }
  else if (currentPosition === 0) {
    currentPosition = maxPosition;
  }
  updateSliderPosition();
}

function updateSliderPosition() {
  slider.style.transform = "translateX("+currentPosition+"px)";
}
