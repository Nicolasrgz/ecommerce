const { createApp } = Vue;

const app = createApp({
  data() {
    return {
      allEvents: [],
      jugueteria: [],
      busqueda: "",
      seleccionadas: [],
      filtroTitulo: [],
    };
  },

  created() {
    this.seleccionadas = this.getLocalStorage() ?? [];
    fetch("https://mindhub-xj03.onrender.com/api/petshop")
      .then((res) => res.json())
      .then((data) => {
        this.allEvents = data;
        this.jugueteria = this.allEvents.filter(
          (fil) => fil.categoria == "jugueteria"
        );
      })
      .catch((error) => console.error(error));
  },
  methods: {
    filtrarPorTitulo() {
      return this.jugueteria.filter((e) =>
        e.producto.toLowerCase().includes(this.busqueda.toLowerCase())
      );
    },
    toggleSeleccion(_id) {
      if (this.seleccionadas.find((e) => e._id == _id)) {
        console.log("ya");
        this.seleccionadas = this.seleccionadas.filter((e) => e._id != _id);
      } else {
        const aux = this.allEvents.find((e) => e._id == _id);
        this.seleccionadas.push(aux);
      }
      const json = JSON.stringify(this.seleccionadas);
      localStorage.setItem("compras", json);
    },
    getLocalStorage() {
      return JSON.parse(localStorage.getItem("compras"));
    },
  },
  computed: {
    filtrarPorTitulo() {
      this.productosFiltrados = this.jugueteria.filter((e) =>
        e.producto.toLowerCase().includes(this.busqueda.toLowerCase())
      );
    },
  },
});

app.mount("#app");
