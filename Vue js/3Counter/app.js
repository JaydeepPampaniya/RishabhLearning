const app =Vue.createApp({
    data(){
        return{
            counter:0,
            inputNumber:0,
            name:'',
            email:'',
            password:'',
            fruits:[]
        };
    },
    methods:{
        increaseCounterByOne(){
            return this.counter++;
        },
        decreaseCounterByOne(){
            return this.counter--;
        },
        addInputNumber(){
            return this.counter += this.inputNumber;
        },
        subInputNumber(){
            return this.counter -= this.inputNumber;
        },
        setName(e){
            return this.name = e.target.value;
        },
        submitForm(){
        
            alert(`submitted email and password is : ${this.email} And ${this.password}`);
        },
        setDescription(event){
            return this.fruits.push(event.target.value);
        }
    }
});

app.mount('#counter');