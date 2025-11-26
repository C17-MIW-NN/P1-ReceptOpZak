// RecipeHome scrollButton

const scrollBtn = document.getElementById('scrollTopBtn');

if (scrollBtn) {

    window.addEventListener('scroll', () => {
        scrollBtn.style.display = window.scrollY > 200 ? 'block' : 'none';
    });

    scrollBtn.addEventListener('click', () => {
        window.scrollTo({ top: 0, behavior: 'smooth' });
    });

}

// RecipeForm ingredients

const addIngredientButton = document.getElementById("addIngredient");
const ingredientsContainer = document.getElementById("ingredients");

addIngredientButton.addEventListener("click", function () {
    const ingredientLine = document.createElement("div");
    ingredientLine.className = "row g-3 mb-2 align-items-end";
    ingredientLine.innerHTML = `
        <div class="col-md-2">
            <input type="text" name="ingredientNames[]" class="form-control">
        </div>
        <div class="col-md-2">
            <input type="text" name="quantities[]" class="form-control">
        </div>
        <div class="col-md-2">
            <input type="number" step="1" name="carbs[]" class="form-control">
        </div>
        <div class="col-md-2">
            <input type="number" step="1" name="fats[]" class="form-control">
        </div>
        <div class="col-md-2">
            <input type="number" step="1" name="proteins[]" class="form-control">
        </div>
        <div class="col-md-2 d-flex">
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


// RecipeForm instructions

function addInstruction() {
    const container = document.getElementById('instructionContainer');
    const stepCount = container.children.length + 1;

    const div = document.createElement('div');
    div.className = 'instruction-step mt-2';

    const inputGroup = document.createElement('div');
    inputGroup.className = 'input-group';

    const textarea = document.createElement('textarea');
    textarea.className = 'form-control auto-expand';
    textarea.name = 'instructionTexts';
    textarea.rows = 1;
    textarea.placeholder = 'Stap ' + stepCount;
    textarea.oninput = function () {
        autoResize(this);
    };

    const removeBtn = document.createElement('button');
    removeBtn.type = 'button';
    removeBtn.className = 'btn btn-danger';
    removeBtn.innerText = '✕';
    removeBtn.onclick = function () {
        div.remove();
        updatePlaceholders();
    };

    inputGroup.appendChild(textarea);
    inputGroup.appendChild(removeBtn);
    div.appendChild(textarea);
    container.appendChild(div);

    function updatePlaceholders() {
        const steps = document.querySelectorAll
        ('#instructionContainer .instruction-step textarea');
        steps.forEach((textarea, index) => {
            textarea.placeholder = 'Stap ' + (index + 1);
        });
    }

}

function autoResize(textarea) {
    textarea.style.height = 'auto';
    textarea.style.height = textarea.scrollHeight + 'px';
}

document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll('.auto-expand').forEach(function (textarea) {
        autoResize(textarea);
    });
});


// RecipeDetail strikethrough

function toggleStrikeTable(checkbox) {
    const row = checkbox.closest('tr');
    const cells = row.querySelectorAll('.ingredient-text'); // alleen de divs met tekst

    cells.forEach(cell => {
        cell.classList.toggle('strikethrough', checkbox.checked);
    });
}

function toggleStrikeList(checkbox) {
    const label = checkbox.nextElementSibling;
    label.classList.toggle('strikethrough', checkbox.checked);


}






