function addIngredientInput() {
    const container = document.getElementById("ingredients-section");

    // count how many inputs have in the ingredients sections
    let newIndex = container.querySelectorAll('.ingredient-div').length;

    // append the new input at the bottom
    container.insertAdjacentHTML('beforeend', `
    <div class="ingredient-div">
     <input name="ingredients[${newIndex}]" placeholder="200 grams chicken breast" type="text" class="form-control mb-2" required>
    </div>
    `);
}

function addStepInput() {
    const container = document.getElementById("step-section");

    // count how many inputs have in the ingredients sections
    let newIndex = container.querySelectorAll('.step-by-step-div').length;

    // append the new input at the bottom
    container.insertAdjacentHTML('beforeend', `
    <div class="step-by-step-div">
     <textarea name="howToPrepare[${newIndex}]" class="form-control mb-2" required></textarea>
    </div>
    `);
}