const app =Vue.createApp({
    data(){
        return{
            goal:[],
            plan:''
        };
    },
    methods:{
        addTask(event){
            const existTask = this.goal.find((task) => task === event.target.value);
            if(existTask){
                alert('task already exists');
            }else{
                this.goal.push(event.target.value);
            }
            
        },
        removeTask(removedTask){
            return this.goal = this.goal.filter((task)=> task !== removedTask);
        }
    }
});

app.mount('#todos');