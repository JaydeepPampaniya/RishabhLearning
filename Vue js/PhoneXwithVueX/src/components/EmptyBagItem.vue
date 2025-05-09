<script setup>
import { useRouter } from "vue-router";

const router = useRouter();
</script>

<template>
  <div
    class="container d-flex flex-column align-items-center justify-content-center mt-5 text-center"
  >
    <img
      src="../assets/background/pato-juan.gif"
      alt="Empty Bag"
      class="mb-4"
      style="max-width: 150px"
    />
    <h2 class="mb-3">Your Bag is Empty 😢</h2>
    <p class="mb-4 text-muted">
      Looks like you haven’t added anything to your bag yet.
    </p>

    <button class="btn btn-danger px-4 py-2" @click="router.push('/')">
      Go to Shopping
    </button>
  </div>
</template>

<style scoped>
/* Add any required styles */
</style>
