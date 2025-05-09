<script setup>
import FriendContact from "./components/FriendContact.vue";
import "./styles/styles.css";
</script>
<template>
  <section>
    <header>
      <h1>My Friends</h1>
    </header>
    <ul v-for="friend in friends">
      <FriendContact :friend="friend"/>
    </ul>
  </section>
</template>

<script>
export default {
  data() {
    return {
      friends: [
        {
          id:1,
          name: "jaydeep",
          email: "jaydeeppampaniya77@gmail.com",
          phone: 9974177873,
          isFavorite:'1'
        },
        {
          id:2,
          name: "aashsish",
          email: "aashish@gmail.com",
          phone: 9974177874,
          isFavorite:'0'
        },
      ],
    };
  },
};
</script>
