const app =Vue.createApp({
    data(){
        return{
            courseGoalA:'Finish the course and learn vue!',
            courseGoalB:"Master in vue",
            courseGoalC:"<h1>Get a job in vue</h1>",
            vueLink:'https://google.com'
        };
    },
    methods:{
        outputGoal(){
            const randomNumber=Math.random();
            if(randomNumber>0.5){
                return this.courseGoalC;
            }else if(randomNumber<0.5 && randomNumber>0.1){
                return this.courseGoalB;
            }else{
                return this.courseGoalA
            }
        }
    }
});

app.mount('#user-goal');