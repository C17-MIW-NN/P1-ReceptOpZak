// const addIngredientButton = document.getElementById("addIngredient");
// const ingredientsContainer = document.getElementById("ingredients");
//
// addIngredientButton.addEventListener("click", function () {
//     const ingredientLine = document.createElement("div");
//     ingredientLine.innerHTML = `
//
//         <label class="form-label">Naam</label>
//         <input type="text" name="ingredientNames[]">
//         <label class="form-label">Hoeveelheid</label>
//         <input type="text" name="quantities[]">
//         <button type="button" class="remove">Verwijder</button>
//     `;
//     ingredientsContainer.appendChild(ingredientLine);
// });
//
// ingredientsContainer.addEventListener("click", removeClick);
//
// function removeClick(e) {
//     if (e.target.classList.contains("remove")) {
//         e.target.parentElement.remove();
//     }
// }