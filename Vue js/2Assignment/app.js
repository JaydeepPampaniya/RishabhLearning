const app =Vue.createApp({
    data(){
        return{
            fullName:'Jaydeep Pampaniya',
            age:Math.round(Math.random()*100),
            imageUrl:"https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.bmwgroup.com%2Fen%2Fcompany.html&psig=AOvVaw0nF7CYD0B2VlrwHsVqRga6&ust=1739943395693000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCJjm7rHAzIsDFQAAAAAdAAAAABAE",
        };
    },
    methods:{
        addFiveToAge(){
            return this.age +4;
        },
        randomNumber(){
            return Math.random() *100
        },

    }
});

app.mount('#user-goal');