document.addEventListener("DOMContentLoaded", () => {
    fetch("/listarCanchas")
        .then(response => {
            if (!response.ok) {
                throw new Error("Error en la red: " + response.status);
            }
            return response.json();
        })
        .then(data => {
            mostrarCanchas(data);
        })
        .catch(error => {
            console.error("Hubo un problema con la petición Fetch:", error);
        });
});

function mostrarCanchas(canchas) {
    const contenedor = document.getElementById("contenedorCanchas"); // Cambia esto según tu estructura HTML
    contenedor.innerHTML = ""; // Limpiar el contenedor

    canchas.forEach(cancha => {
        const div = document.createElement("div");
        div.className = "cancha";
        div.innerHTML = `
            <h3>Cancha ${cancha.nro_cancha}</h3>
            <p>Precio Día: ${cancha.precio_dia}</p>
            <p>Precio Noche: ${cancha.precio_noche}</p>
        `;
        contenedor.appendChild(div);
    });
}
