<template>
  <div
    class="spinner-main spinner animate__animated animate__fadeIn animate__fast text-center"
  >
    <div class="spinner-grow text-success" role="status">
      <span class="visually-hidden">Loading...</span>
    </div>
    <div class="spinner-grow text-danger" role="status">
      <span class="visually-hidden">Loading...</span>
    </div>
  </div>
</template>

<style setup>
@import "../CSS/Loading.module.css";
</style>
