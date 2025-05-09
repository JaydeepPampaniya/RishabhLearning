<script setup>
import { computed, ref } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { toast } from "vue3-toastify";
import LoadingSpinner from "../components/LoadingSpinner.vue";
import { signInUser } from "../services/apis";
import { jwtDecode } from "jwt-decode";

const store = useStore();
const router = useRouter();
const email = ref("");
const password = ref("");
let loading = computed(() => store.getters["auth/loading"]);

const log = async (e) => {
  e.preventDefault();

  if (!email.value || !password.value) {
    toast.error("Please fill all the fields");
    return;
  }

  try {
    const credentials = {
      email: email.value,
      password: password.value,
    };
    loading = true;
    const data = await signInUser(credentials);

    if (data.status === 200) {
      const user = jwtDecode(data.data.token);
      store.dispatch("auth/login", data.data.token);
      setTimeout(() => {
        toast.success("Login successful!");
      }, 100);

      if (user.role === "CUSTOMER") {
        router.push("/");
      } else {
        router.push("/admin");
      }
    }
  } catch (error) {
    toast.error(error);
  } finally {
    loading = false;
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
              placeholder="Email"
            />
          </div>
          <div class="col-12">
            <input
              type="password"
              v-model="password"
              class="form-control"
              placeholder="Password"
            />
          </div>
          <div class="col-12">
            <button type="submit" class="btn btn-success col-12">
              Sign In
            </button>
          </div>
          <div class="text-white">
            Don't Have an Account?
            <span class="signin" @click="router.push('/SignUp#top')">
              Sign Up
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
