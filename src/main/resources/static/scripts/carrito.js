const { createApp } = Vue;

const app = createApp({
  data() {
    return {
      allEvents: [],
      seleccionadas: [],
      mostrarImagen: false,
      imagenBorrar: false,
      totalCompra: 0,
    };
  },
  created() {
    this.seleccionadas = JSON.parse(localStorage.getItem("compras")) ?? [];
  },
  computed: {
    resultado() {
      this.totalCompra = this.seleccionadas.reduce(
        (total, articulo) => total + articulo.precio * articulo.__v,
        0
      );
      const json = JSON.stringify(this.totalCompra);
      localStorage.setItem("resultado", json);
    },
  },
  methods: {
    comprar() {
      localStorage.removeItem("compras");
      this.seleccionadas = [];
      this.mostrarImagen = true;
    },
    deleteCompras() {
      localStorage.removeItem("compras");
      this.seleccionadas = [];
      this.imagenBorrar = true;
    },
    restar(articulo) {
      if (articulo.__v > 0) {
        articulo.__v--;
      }
    },
    sumar(articulo) {
      if (articulo.__v < articulo.disponibles) {
        articulo.__v++;
      }
    },
  },
});

app.mount("#app");
