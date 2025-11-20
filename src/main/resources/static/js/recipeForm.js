const addIngredientButton = document.getElementById("addIngredient");
const ingredientsContainer = document.getElementById("ingredients");

addIngredientButton.addEventListener("click", function () {
    const ingredientLine = document.createElement("div");
    ingredientLine.className = "row g-3 mb-2 align-items-end";
    ingredientLine.innerHTML = `

         <div class="col-md-5">
            <label class="form-label">Naam</label>
            <input type="text" name="ingredientNames[]" class="form-control">
        </div>
        <div class="col-md-4">
            <label class="form-label">Hoeveelheid</label>
            <input type="text" name="quantities[]" class="form-control">
        </div>
        <div class="col-md-3 d-flex">
            <button type="button" class="btn btn-danger w-100 remove">Verwijder</button>
        </div>
    `;
    ingredientsContainer.appendChild(ingredientLine);
});

ingredientsContainer.addEventListener("click", removeClick);

function removeClick(e) {

    const removeButton = e.target.closest("button.remove");

    if (removeButton) {
        removeButton.closest(".row").remove();
    }
}