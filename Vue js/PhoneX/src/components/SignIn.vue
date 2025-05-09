<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { useToast } from "vue-toastification";
import axios from "axios";
import cookies from "js-cookie";

import { jwtDecode } from "jwt-decode";
import LoadingSpinner from "./LoadingSpinner.vue";

const toast = useToast();
const router = useRouter();
const email = ref("");
const password = ref("");
const loading = ref(false);

const log = async (e) => {
  e.preventDefault();
  if (email.value && password.value) {
    try {
      loading.value = true;
      const data = { email: email.value, password: password.value };
      const response = await axios.post(
        "http://localhost:8080/user/login",
        data
      );

      if (response.status === 200) {
        const tokenDecoded = jwtDecode(response.data.token);
        if (tokenDecoded.role === "CUSTOMER") {
          cookies.set(
            "UserCookie",
            JSON.stringify({
              id: response.data.userId,
              gender: response.data.gender,
              token: response.data.token,
            }),
            { secure: true, sameSite: "Strict" }
          );
          toast.success(response.data.message);
          setTimeout(() => {
            router.push("/");
          }, 1500);
        } else {
          toast.error("You are not a customer");
        }
      } else {
        toast.error(response.data.message);
      }
    } catch (error) {
      if (error.response?.status) {
        toast.error(error.response.data);
      } else {
        toast.error(error.message);
      }
    } finally {
      loading.value = false;
    }
  } else {
    toast.error("Please fill all the fields");
  }
};
</script>

<template>
  <div class="hero p-5" id="top">
    <div class="container py-5">
      <div class="col-md-6">
        <h2 class="text-center text-white">Sign In</h2>
        <form class="row g-3 text-start mt-3" @submit="log">
          <div class="col-12">
            <input
              type="email"
              v-model="email"
              class="form-control"
              id="inputEmail4"
              placeholder="Email"
            />
          </div>
          <div class="col-12">
            <input
              type="password"
              v-model="password"
              class="form-control"
              id="inputPassword4"
              placeholder="Password"
            />
          </div>
          <div class="col-12">
            <button type="submit" class="btn btn-success col-12" id="btn">
              Sign In
            </button>
          </div>
          <div class="text-white">
            Don't Have an Account?
            <span class="signin" @click="router.push('/SignUp#top')">
              SignUp
            </span>
          </div>
        </form>
      </div>
    </div>
  </div>

  <LoadingSpinner v-if="loading" />
</template>

<style scoped>
@import "../CSS/SignIn.module.css";
</style>
