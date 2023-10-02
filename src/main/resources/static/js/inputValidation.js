const emailInput = document.getElementById('floatingInput');
const passwordInput = document.getElementById('floatingPassword');
const confirmPasswordInput = document.getElementById('floatingConfirmPassword');

function validateInput(inputElement, validationFunction) {
  inputElement.addEventListener('input', function (event) {
    const inputValue = event.target.value;
    const isValid = validationFunction(inputValue);

    if (!isValid) {
      event.target.classList.remove('is-valid');
      event.target.classList.add('is-invalid');
    } else {
      event.target.classList.remove('is-invalid');
      event.target.classList.add('is-valid');
    }
  });
}

function validateEmail(email) {
  const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
  return emailPattern.test(email);
}

function validatePassword(password) {
  // when change the password field check if the confirmation password matches
  if (confirmPasswordInput) {
    if (validateConfirmPassword(confirmPasswordInput.value)) {
      confirmPasswordInput.classList.remove('is-invalid');
      confirmPasswordInput.classList.add('is-valid');
    } else {
      confirmPasswordInput.classList.remove('is-valid');
      confirmPasswordInput.classList.add('is-invalid');
    }
  }
  return password !== '';
}

function validateConfirmPassword(confirmPassword) {
  return confirmPassword === passwordInput.value && confirmPassword !== '';
}

validateInput(emailInput, validateEmail);
validateInput(passwordInput, validatePassword);
if (confirmPasswordInput) {
  validateInput(confirmPasswordInput, validateConfirmPassword);
}