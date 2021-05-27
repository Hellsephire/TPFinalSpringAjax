var app = new Vue({ 
    el: '#app',
    vuetify: new Vuetify(),    
    data: {
        page:1,
        loading: true,
        headers: [
            { text: "Code", value: "code" },
            { text: "Libellé", value: "libelle" },
            { text: "Description", value: "description" },
          ],
        json: undefined
    },
    methods: {
        doAjax() {
            fetch("api/categories")
                .then(response => response.json())
                .then(json => { this.json = json})
                .catch(error => alert(error));            
        }
    }
});