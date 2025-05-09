// const btnElement= document.querySelector('button');
// const ulElement = document.querySelector('ul');
// const inputElement = document.querySelector('input')

// btnElement.addEventListener('click',()=>{
//     const inputValue = inputElement.value;
//     const newLi = document.createElement('li');
//     newLi.textContent = inputValue;
//     ulElement.appendChild(newLi);
//     inputElement.value=""
// })



Vue.createApp({
    data() {
        return {
            goals:[],
            enteredValue:''
        }
    },
    methods:{
        addGoal(){
            this.goals.push(this.enteredValue)
        },
        removeGoal(){
            this.goals.pop()
        }
    }
}).mount('#app');