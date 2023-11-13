const { createApp } = Vue;
const app = createApp({
  data() {
    return {
      seleccionadas: [],
      totalCompra: null,
    };
  },
  created() {
    this.totalCompra = JSON.parse(localStorage.getItem("resultado"));
  },
  methods: {
    deleteCompras() {
      localStorage.removeItem("compras");
      this.seleccionadas = [];
    },
  },
});
app.mount("#app");
let cardDrop = document.getElementById("card-dropdown");
let activeDropdown;
cardDrop.addEventListener("click", function () {
  let node;
  for (let i = 0; i < this.childNodes.length - 1; i++)
    node = this.childNodes[i];
  if (node.className === "dropdown-select") {
    node.classList.add("visible");
    activeDropdown = node;
  }
});

window.onclick = function (e) {
  if (e.target.tagName === "LI" && activeDropdown) {
    if (e.target.innerHTML === "Master Card") {
      document.getElementById("credit-card-image").src =
        "https://dl.dropboxusercontent.com/s/2vbqk5lcpi7hjoc/MasterCard_Logo.svg.png";
      activeDropdown.classList.remove("visible");
      activeDropdown = null;
      e.target.innerHTML = document.getElementById("current-card").innerHTML;
      document.getElementById("current-card").innerHTML = "Master Card";
    } else if (e.target.innerHTML === "American Express") {
      document.getElementById("credit-card-image").src =
        "https://dl.dropboxusercontent.com/s/f5hyn6u05ktql8d/amex-icon-6902.png";
      activeDropdown.classList.remove("visible");
      activeDropdown = null;
      e.target.innerHTML = document.getElementById("current-card").innerHTML;
      document.getElementById("current-card").innerHTML = "American Express";
    } else if (e.target.innerHTML === "Visa") {
      document.getElementById("credit-card-image").src =
        "https://dl.dropboxusercontent.com/s/ubamyu6mzov5c80/visa_logo%20%281%29.png";
      activeDropdown.classList.remove("visible");
      activeDropdown = null;
      e.target.innerHTML = document.getElementById("current-card").innerHTML;
      document.getElementById("current-card").innerHTML = "Visa";
    }
  } else if (e.target.className !== "dropdown-btn" && activeDropdown) {
    activeDropdown.classList.remove("visible");
    activeDropdown = null;
  }
};

/* PARA FUNCION DEL BOTON COMPRAR */
document.getElementById("confirm-btn").addEventListener("click", function () {
  let cardNumber = document.getElementById("card-number").value;
  let cardHolder = document.getElementById("card-holder").value;
  let expirationDate = document.getElementById("expiration-date").value;
  let securityCode = document.getElementById("security-code").value;
  let fullName = document.getElementById("full-name").value;
  let email = document.getElementById("email").value;
  let phone = document.getElementById("phone").value;
  let address = document.getElementById("address").value;

  if (
    cardNumber !== "" &&
    cardHolder !== "" &&
    expirationDate !== "" &&
    securityCode !== "" &&
    fullName !== "" &&
    email !== "" &&
    phone !== "" &&
    address !== ""
  ) {
    // Verificar si el correo electrónico tiene el formato correcto
    if (email.includes("@")) {
      // Todos los campos están completos y el correo electrónico es válido, mostrar el modal
      let modal = document.getElementById("staticBackdrop");
      modal.classList.add("show");
      modal.style.display = "block";
      document.body.classList.add("modal-open");

      // Limpiar los campos del formulario
      document.getElementById("card-number").value = "";
      document.getElementById("card-holder").value = "";
      document.getElementById("expiration-date").value = "";
      document.getElementById("security-code").value = "";
      document.getElementById("full-name").value = "";
      document.getElementById("email").value = "";
      document.getElementById("phone").value = "";
      document.getElementById("address").value = "";
    } else {
      // El correo electrónico no tiene el formato correcto, mostrar alerta
      alert("Ingrese un correo electrónico válido.");
    }
  } else {
    // Alguno de los campos está vacío, mostrar alerta
    alert("Por favor, complete todos los campos del formulario.");
  }
});
