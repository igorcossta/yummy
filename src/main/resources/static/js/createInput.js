// create input for new ingredient
function addIngredientInput() {
    const inputContainer = document.getElementById('recipe-ingredients');
    const newInput = document.createElement('input');

    newInput.className = 'form-control mt-3';
    newInput.name = 'ingredients';
    newInput.placeholder = '...';
    newInput.required = true;
    newInput.setAttribute('th:field', '${ingredients}')

    inputContainer.appendChild(newInput);
}

// Attach the addIngredientInput function to a button click event
document.getElementById('add-ingredient').addEventListener('click', addIngredientInput);


// create input for new step
function addStepInput() {
    const stepContainer = document.getElementById('recipe-steps');
    const newInput = document.createElement('textarea');

    newInput.className = 'form-control mt-3';
    newInput.name = 'howToPrepare';
    newInput.placeholder = '...';
    newInput.required = true;
    newInput.rows = 5;
    newInput.setAttribute('th:field', '${howToPrepare}')

    stepContainer.appendChild(newInput);
}

// Attach the addStepInput function to a button click event
document.getElementById('add-step').addEventListener('click', addStepInput);