<template>
  <div>
    <Navbar />
    <router-view />
    <Subscribe />
    <Footer />
  </div>
</template>

<script setup>
import Navbar from "./Navbar.vue";
import Footer from "./Footer.vue";
import Subscribe from "./Subscribe.vue";
</script>
