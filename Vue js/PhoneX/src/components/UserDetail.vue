<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import cookies from "js-cookie";
import { jwtDecode } from "jwt-decode";
import manavtar from "../assets/background/manavtar.png";
import womenavtar from "../assets/background/womenavtar.jpg";

const router = useRouter();

// Reactive variables
const gender = ref(null);
const userName = ref("");
const email = ref("");
const emit = defineEmits(["closePopup"]);
// Fetch user details from cookie
onMounted(() => {
  try {
    const userCookie = JSON.parse(cookies.get("UserCookie"));
    if (userCookie) {
      gender.value = userCookie.gender;
      const decodedToken = jwtDecode(userCookie.token);
      userName.value = decodedToken.sub;
      email.value = decodedToken.email;
    }
  } catch (error) {
    console.error("Error decoding token:", error);
  }
});

// Logout function
const handleLogout = () => {
  cookies.remove("UserCookie");
  router.push("/SignIn#top");
  emit("closePopup");
};
</script>

<template>
  <div class="userMain animate__animated animate__fadeInUp">
    <div class="d-flex justify-content-between align-items-center mb-3">
      <strong class="fs-4">User Profile</strong>
      <button
        type="button"
        class="btn-close"
        aria-label="Close"
        @click="emit('closePopup')"
      ></button>
    </div>
    <div class="card mb-3 p-3">
      <div class="text-center">
        <p>
          <img
            v-if="gender === 'Male'"
            :src="manavtar"
            style="width: 6rem; border-radius: 50px"
            alt="avatar"
          />
          <img
            v-else-if="gender === 'Female'"
            :src="womenavtar"
            style="width: 6rem; border-radius: 50px"
            alt="avatar"
          />
          <FcDecision v-else style="font-size: 6rem" />
        </p>
        <p class="fs-5 mb-1">{{ userName }}</p>
        <p class="text-secondary">{{ email }}</p>
        <button
          type="button"
          class="btn btn-danger mt-4 me-2"
          @click="handleLogout"
        >
          Log out
        </button>
        <button
          type="button"
          class="btn btn-success mt-4 me-2"
          @click="router.push('/Captcha#top')"
        >
          Edit User Detail
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped>
@import "../CSS/UserDetails.module.css";
</style>
