const step = document.querySelectorAll('.step');
const prevBtn = document.getElementById('prev-btn');
const nextBtn = document.getElementById('next-btn');
const submitBtn = document.getElementById('submit-btn');

const addIngredientBtn = document.getElementById('add-ingredient');
const addStepBtn = document.getElementById('add-step');

addIngredientBtn.onclick = () => {
  const container = document.getElementById('ingredients-section');
  container.insertAdjacentHTML('beforeend',
    `<div class="input-group mb-3 ingredient-div">
                  <input type="text" class="form-control shadow-none rounded-0 ingredient-in" aria-label="ingredient details"
                    maxlength="120" required>
                  <span class="input-group-text rounded-0" onclick="remove(this)">
                    <i class="bi bi-x"></i>
                  </span>
                </div>`);
  updateIngredientsIndex();
};

addStepBtn.onclick = () => {
  const container = document.getElementById('steps-section');
  container.insertAdjacentHTML('beforeend',
    `<div class="input-group mb-3 step-div">
                  <textarea class="form-control shadow-none rounded-0 steps-in" aria-label="how to prepare step"
                    maxlength="1200" required></textarea>
                  <span class="input-group-text rounded-0" onclick="remove(this, 1)">
                    <i class="bi bi-x"></i>
                  </span>
                </div>`);
  updateStepsIndex();
};

function remove(spanElement, type = 0) {
  const parentDiv = spanElement.parentNode;
  parentDiv.parentNode.removeChild(parentDiv);
  if (type === 0) {
    updateIngredientsIndex();
  } else {
    updateStepsIndex();
  }
}

function updateIngredientsIndex() {
  const ingredients = document.querySelectorAll('.ingredient-in');
  ingredients.forEach((input, index) => {
    input.setAttribute('name', `ingredients[${index}]`);
  });
}

function updateStepsIndex() {
  const steps = document.querySelectorAll('.steps-in');
  steps.forEach((input, index) => {
    input.setAttribute('name', `howToPrepare[${index}]`);
  });
}

// handle form input of type number

const numberInputs = document.querySelectorAll('input[type="number"]');
numberInputs.forEach(input => {
  input.addEventListener('input', function (event) {
    const inputValue = event.target.value;
    const numericValue = inputValue.replace(/[^0-9]/g, '');

    event.target.value = numericValue;

    if (numericValue === '' || numericValue < 1) {
      event.target.value = '1';
    } else if (numericValue > 99 && event.target.id !== 'preparationTime') {
      event.target.value = '99';
    } else if (numericValue > 999 && event.target.id === 'preparationTime') {
      event.target.value = '999';
    } else {
      event.target.value = numericValue;
    }
  });
});

// handle form steps

let current_step = 0;
let stepCount = step.length - 1;

step[current_step].classList.toggle('d-none');
if (current_step === 0) {
  prevBtn.classList.add('d-none');
  submitBtn.classList.add('d-none');
  nextBtn.classList.add('d-inline-block');
}

nextBtn.addEventListener('click', () => {
  current_step++;
  let previous_step = current_step - 1;
  if ((current_step > 0) && (current_step <= stepCount)) {
    prevBtn.classList.remove('d-none');
    prevBtn.classList.add('d-inline-block');

    step[current_step].classList.remove('d-none');
    step[current_step].classList.add('d-block');
    step[previous_step].classList.remove('d-block');
    step[previous_step].classList.add('d-none');
    if (current_step === stepCount) {
      submitBtn.classList.remove('d-none');
      submitBtn.classList.add('d-inline-block');
      nextBtn.classList.remove('d-inline-block');
      nextBtn.classList.add('d-none');
    }
  }
});

prevBtn.addEventListener('click', () => {
  if (current_step > 0) {
    current_step--;
    let previous_step = current_step + 1;
    prevBtn.classList.add('d-none');
    prevBtn.classList.add('d-inline-block');

    step[current_step].classList.remove('d-none');
    step[current_step].classList.add('d-block');
    step[previous_step].classList.remove('d-block');
    step[previous_step].classList.add('d-none');
    if (current_step < stepCount) {
      submitBtn.classList.remove('d-inline-block');
      submitBtn.classList.add('d-none');
      nextBtn.classList.remove('d-none');
      nextBtn.classList.add('d-inline-block');
      prevBtn.classList.remove('d-none');
      prevBtn.classList.add('d-inline-block');
    }
  }

  if (current_step <= 0) {
    prevBtn.classList.remove('d-inline-block');
    prevBtn.classList.add('d-none');
  }
});