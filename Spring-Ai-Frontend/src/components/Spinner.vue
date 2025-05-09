<template>
  <div
    class="spinner-main spinner animate__animated animate__fadeIn animate__fast text-center"
  >
    <div class="spinner-grow text-danger" role="status">
      <span class="visually-hidden">Loading...</span>
    </div>
    <div class="spinner-grow text-light" role="status">
      <span class="visually-hidden">Loading...</span>
    </div>
    <div class="spinner-grow text-success" role="status">
      <span class="visually-hidden">Loading...</span>
    </div>
  </div>
</template>
<style scoped>
.spinner-main {
  position: fixed;
  top: 40%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 500;
  background-color: #0000006c;
  font-size: 40px;
  border: 0px;
  width: 100%;
  padding: 80vw 0;
}
</style>
