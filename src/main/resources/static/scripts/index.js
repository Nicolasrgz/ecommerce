function vaciarCampos() {
  document.getElementById("nombre").value = "";
  document.getElementById("email").value = "";
}
function validarFormulario() {
  const nombre = document.getElementById("nombre").value;
  const email = document.getElementById("email").value;
  if (nombre === "" || email === "") {
    alert("Por favor, rellena todos los campos del formulario.");
  } else if (!email.includes("@")) {
    alert("Por favor, introduce una dirección de correo electrónico válida.");
  } else {
    let modal = new bootstrap.Modal(document.getElementById("staticBackdrop"));
    modal.show();
    modal.addEventListener("hidden.bs.modal", vaciarCampos);
  }
}
