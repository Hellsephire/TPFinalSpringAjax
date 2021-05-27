var app = new Vue({ 
    el: '#app',
    data: {
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