const { createApp } = Vue;

const app = createApp({
  data() {
    return {
      seleccionadas: [],
      products: [],
    };
  },

  created() {
    // this.seleccionadas = this.getLocalStorage() ?? [];
    this.productos();
  },

  methods: {
    productos(){
      axios.get("http://localhost:8080/api/products")
      .then(response => {
        this.products = response.data
        console.log("los productos son:", this.products)
      })
      .catch(error =>{
        console.error("el llamado a la api fallo", error)
      })
    },
  },

  computed: {

  },
});

app.mount("#app");
