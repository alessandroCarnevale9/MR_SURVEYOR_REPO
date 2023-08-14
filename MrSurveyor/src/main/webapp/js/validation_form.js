const form = document.getElementById("registration-form");
const button = document.getElementById("submit-btn");

const name = document.getElementById("name");
const surname = document.getElementById("surname");
const email = document.getElementById("email");
const password = document.getElementById("password");
const confirmPassword = document.getElementById("confirm-password");

form.addEventListener("submit", (e) => {

  e.preventDefault();

  if(checkInputs()) {
    button.disabled = true;
    button.textContent = "";
    button.parentElement.className = "submit loading";

    setTimeout(function() {
		button.disabled = false;
		form.submit();
    }, 2000);
  }
});

function checkInputs() {

  result = false;

  if(name != null) {
    const nameValue = name.value.trim();

    if(nameValue === "") {
      setErrorFor(name, "Inserisci il tuo nome");
      result = false;
    }
    else if(!isValid(nameValue)) {
      setErrorFor(name, "Sono consetite solo lettere maiuscole, minuscole e spazi");
      result = false;
    }
    else {
      setSuccessFor(name);
      result = true;
    }
  }

  if(surname != null) {
    const surnameValue = surname.value.trim();

    if(surnameValue === "") {
      setErrorFor(surname, "Inserisci il tuo cognome");
      result = false;
    }
    else if(!isValid(surnameValue)) {
      setErrorFor(surname, "Sono consetite solo lettere maiuscole, minuscole e spazi");
      result = false;
    }
    else {
      setSuccessFor(surname);
      result = true;
    }
  }

  if(email != null) {
    const emailValue = email.value.trim();

    if(emailValue === "") {
      setErrorFor(email, "Inserisci un indirizzo email");
      result = false;
    }
    else if (!isEmail(emailValue)) {
      setErrorFor(email, "Inserito indirizzo email non valido");
      result = false;
    }
    else {
      setSuccessFor(email);
      result = true;
    }
  }

  if(password != null) {
    const passwordValue = password.value;

    if(passwordValue === "") {
      setErrorFor(password, "Inserisci la password");
      result = false;
    }
    else if (!isSecure(passwordValue)) {
      setErrorFor(password, "La password deve contenere almeno 8 caratteri e deve includere almeno un numero e un carattere speciale");
      result = false;
    }
    else {
      setSuccessFor(password);

      if(confirmPassword != null) {
        const confirmPasswordValue = confirmPassword.value;

        if(confirmPasswordValue === "") {
          setErrorFor(confirmPassword, "Digita nuovamente la password");
          result = false;
        }

        else if(passwordValue !== confirmPasswordValue) {
          setErrorFor(confirmPassword, "Le password inserite non corrispondono");
          result = false;
        }
        else {
          setSuccessFor(confirmPassword);
          result = true;
        }
      }
    }
  }

  return result;
}

function isValid(name) {
  return /^[a-zA-Z ]+$/; // Solo maiuscole, minuscole e spazi
}

function isEmail(email) {
  return /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(email);
}

function isSecure(password) {
  // La password deve contenere almeno 8 caratteri e deve includere almeno un numero e un carattere speciale.
  return /^(?=.*\d)(?=.*[\W_]).{8,}$/.test(password);
}

function setErrorFor(input, message) {
  const formControl = input.parentElement;
  const small = formControl.querySelector("small");

  // scrivo il messaggio di errore
  small.innerText = message;

  // aggiungo la classe error
  formControl.className = "form-control error";
}

function setSuccessFor(input) {
  const formControl = input.parentElement;

  formControl.className = "form-control success";
}
