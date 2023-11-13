document.addEventListener("DOMContentLoaded", function () {
  let form = document.getElementById("formulario-contacto");
  let messageContainer = document.getElementById("message-container");
  form.addEventListener("submit", function (event) {
    event.preventDefault();
    messageContainer.textContent = "Mensaje enviado con éxito ";
    form.reset();
  });
});
