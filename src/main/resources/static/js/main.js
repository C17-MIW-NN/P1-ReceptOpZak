
const scrollBtn = document.getElementById('scrollTopBtn');

window.addEventListener('scroll', () => {
    if (window.scrollY > 200) {
        scrollBtn.style.display = 'block';
    } else {
        scrollBtn.style.display = 'none';
    }
});

scrollBtn.addEventListener('click', () => {
    window.scrollTo({ top: 0, behavior: 'smooth' });
});


    function autoResize(textarea) {
    textarea.style.height = 'auto';
    textarea.style.height = textarea.scrollHeight + 'px';
}
    document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll('.auto-expand').forEach(function (textarea) {
        autoResize(textarea);
    });
});



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
    textarea.oninput = function () { autoResize(this); };

    const removeBtn = document.createElement('button');
    removeBtn.type = 'button';
    removeBtn.className = 'btn btn-outline-danger';
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
    const steps = document.querySelectorAll('#instructionContainer .instruction-step textarea');
    steps.forEach((textarea, index) => {
    textarea.placeholder = 'Stap ' + (index + 1);
});
}

}




