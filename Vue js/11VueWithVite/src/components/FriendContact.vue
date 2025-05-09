<template>
    <li>
        <h2>{{ friend.name }}</h2>
        <button @click="toggleDetails">{{ detailsAreVisible ? 'Hide' : 'Show' }} Details</button>
        <ul v-show="detailsAreVisible">
            <li><Strong>Phone:</Strong> {{ friend.phone }}</li>
            <li><strong>Email:</strong> {{ friend.email }}</li>
            
        </ul>
    </li>
</template>
<script>
export default {
    data(){
        return{
            detailsAreVisible:false,
            friend:{
                id:'manuuel',
                name:'Manuel',
                phone:'123456789',
                email:'manuel@gmail.com'
            },
        }
    },
    methods:{
        toggleDetails(){
            this.detailsAreVisible = !this.detailsAreVisible;
        }
    }
}
</script>