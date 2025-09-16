document.addEventListener("DOMContentLoaded", () => {
    // Referencias a la modal y al botón de cerrar
    const modal = document.getElementById("taskModalUpdate");
    const closeBtn = modal.querySelector(".close");

    // Referencias a los campos del formulario
    const idField = document.getElementById("updateId");
    const titleField = document.getElementById("updateTitle");
    const descField = document.getElementById("updateDescription");
    const statusField = document.getElementById("updateStatus");
    const priorityField = document.getElementById("updatePriority");
    const dueDateField = document.getElementById("updateDueDate");

    // Abrir modal al hacer click en cualquier task-card
    document.querySelectorAll(".task-card").forEach(card => {
        card.addEventListener("click", () => {
            idField.value = card.dataset.id;
            titleField.value = card.dataset.title;
            descField.value = card.dataset.description;
            statusField.value = card.dataset.status;
            priorityField.value = card.dataset.priority;

            // Convertir fecha a formato yyyy-MM-dd
            if (card.dataset.limitdate) {
                dueDateField.value = card.dataset.limitdate.substring(0, 10);
            } else {
                dueDateField.value = "";
            }

            // Mostrar modal
            modal.style.display = "block";
        });
    });

    // Cerrar modal al hacer click en la "x"
    closeBtn.addEventListener("click", () => {
        modal.style.display = "none";
    });

    // Cerrar modal al hacer click fuera del contenido
    window.addEventListener("click", e => {
        if (e.target === modal) {
            modal.style.display = "none";
        }
    });
});
