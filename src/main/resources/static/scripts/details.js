const { createApp } = Vue;
const app = createApp({
  data() {
    return {
      arrayProductos: [],
      arrayProductos: [],
      detail: [],
    };
  },
  created() {
    const url = "https://mindhub-xj03.onrender.com/api/petshop";
    fetch(url)
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        this.arrayProductos = data;
        console.log(this.arrayProductos);
        this.arrayNombreProducto = this.arrayProductos.map(
          (elemento) => elemento.producto
        );
        const locationSearch = location.search;
        const params = new URLSearchParams(locationSearch);
        const id = params.get("id");
        this.detail = this.arrayProductos.find(
          (elemento) => elemento._id == id
        );
        console.log(this.detail);
        /*   document.title = `Detalle | ${this.detail.producto}`; */
      })
      .catch((error) => console.log(error));
  },
});
app.mount("#app");
