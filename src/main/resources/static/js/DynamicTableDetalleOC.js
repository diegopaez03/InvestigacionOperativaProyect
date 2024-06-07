let dataTable;
let isDataTableInitialized=false;

let DataTableOptions = {
    pageLength: 3
};

function initDataTable() {
    if (isDataTableInitialized) {
        dataTable.destroy();
    }
    generarDetallesOC();

    dataTable=$("#detallesOrdenDeCompra").DataTable(DataTableOptions);
    isDataTableInitialized = true;
}

function generarDetallesOC() {

    let content = "";
    for (let index = 0; index < 5; index++) {
        content += `
        <tr>
            <td>${index + 1}</td>
        </tr>
        `
    };
    tableBody_detalles.innerHTML = content;
}

// Llama a la función 'saludar' cuando el documento HTML está completamente cargado
document.addEventListener("DOMContentLoaded", function() {
    initDataTable();
});
