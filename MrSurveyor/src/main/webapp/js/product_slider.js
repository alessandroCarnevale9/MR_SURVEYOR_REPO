
allSlides.forEach((slider, i) => {
  values.push({id: i+1, currentPosition: 0, numSlides: slider.querySelectorAll(".slide-item").length});
});


function next(slideID) {
  for(let i = 0; i < values.length; i++) {
    const val = values[i];

    if(val.id == slideID) {
      const maxPosition = -((sliderWidth * val.numSlides) - containerWidth);

      if(val.currentPosition > maxPosition) {
        val.currentPosition -= sliderWidth;
      }
      else if(val.currentPosition === maxPosition) {
        val.currentPosition = 0;
      }
      break;
    }
  }
  updateSliderPosition(slideID);
}

function prev(slideID) {

  for(let i = 0; i < values.length; i++) {
    const val = values[i];

    if(val.id == slideID) {
      const maxPosition = -((sliderWidth * val.numSlides) - containerWidth);

      if(val.currentPosition < 0) {
        val.currentPosition += sliderWidth;
      }
      else if(val.currentPosition === 0) {
        val.currentPosition = maxPosition;
      }
      break;
    }
  }
  updateSliderPosition(slideID);
}

function updateSliderPosition(slideID) {
  const slider = document.getElementById(slideID);

  for(let i = 0; i < values.length; i++) {
    const val = values[i];
    if(val.id == slideID) {
      slider.style.transform = "translateX("+val.currentPosition+"px)";
      break;
    }
  }
}
