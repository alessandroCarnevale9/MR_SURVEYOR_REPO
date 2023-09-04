let slideIndex = 1;

const sliderContainer = document.querySelector(".slider-container");
const containerWidth = sliderContainer.offsetWidth;
const sliderWidth = 250;
const values = [];
const allSlides = document.querySelectorAll(".slides");

let moving = false;
let mouseLastPosition = 0;
let debounceInterval = 100; // Intervallo di debounce in millisecondi
let debounceTimeout;

