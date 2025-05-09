<template>
  <li>
    <h2>
      {{ friend.name }} {{ friend.isFavorite === "1" ? "(favorite)" : "" }}
    </h2>
    <button @click="toggleDetails">
      {{ detailsAreVisible ? "Hide" : "Show" }} Details
    </button>
    <ul v-show="detailsAreVisible">
      <li><Strong>Phone:</Strong> {{ friend.phone }}</li>
      <li><strong>Email:</strong> {{ friend.email }}</li>
    </ul>
  </li>
</template>
<script>
export default {
  props: {
    friend: Object,
    friend: {
      type: Object,
      required: true,
    },
    friend: {
      type: Object,
      required: false,
      default: {
        name: "jaydeep",
        phone: 123,
        email: "jaydeep@gmail.com",
        isFavorite: "1",
      },
    },
  },
  data() {
    return {
      detailsAreVisible: false,

      // friend:{
      //     id:'manuuel',
      //     name:'Manuel',
      //     phone:'123456789',
      //     email:'manuel@gmail.com'
      // },
    };
  },
  methods: {
    toggleDetails() {
      this.detailsAreVisible = !this.detailsAreVisible;
    },
    toggleFavorite() {
      if (this.isFavorite === "1") {
        this.isFavorite === "0";
      } else {
        this.isFavorite === "1";
      }
    },
  },
};
</script>
