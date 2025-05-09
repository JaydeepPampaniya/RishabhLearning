<script setup>
import { ref, onMounted } from "vue";
import cookies from "js-cookie";
import AdminMain from "./AdminMain.vue";
import AdminLogin from "./AdminLogin.vue";

const isLogin = ref(false);

onMounted(() => {
  const id = cookies.get("AdminId");
  isLogin.value = !!id;
});
</script>

<template>
  <div>
    <AdminMain v-if="isLogin" />
    <AdminLogin v-else />
  </div>
</template>
