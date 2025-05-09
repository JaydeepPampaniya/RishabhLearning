<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";
import Cookies from "js-cookie";
import { useToast } from "vue-toastification";
import { jwtDecode } from "jwt-decode";
import LoadingSpinner from "../components/LoadingSpinner.vue";
import "../CSS/Admin.css";

const email = ref("");
const password = ref("");
const loading = ref(false);
const router = useRouter();
const toast = useToast();

const log = async (e) => {
  e.preventDefault();
  if (!email.value || !password.value) {
    toast.error("Please fill all the fields");
    return;
  }

  const data = { email: email.value, password: password.value };
  loading.value = true;

  try {
    const response = await axios.post("http://localhost:8080/user/login", data);
    if (response.status === 200) {
      const tokenDecoded = jwtDecode(response.data.token);
      if (tokenDecoded.role === "ADMIN" || tokenDecoded.role === "OWNER") {
        Cookies.set(
          "AdminId",
          JSON.stringify({
            id: response.data.userId,
            token: response.data.token,
          }),
          { secure: true, sameSite: "Strict" }
        );
        toast.success(response.data.message);
        setTimeout(() => router.push("/Admin/AdminMiddle"), 1500);
      } else {
        toast.error("You are not an admin or owner");
      }
    } else {
      toast.error(response.data.message);
    }
  } catch (error) {
    toast.error(error.response?.data || error.message);
  } finally {
    loading.value = false;
  }
};
</script>

<template>
  <div class="background" id="top">
    <div class="container" style="padding: 4.8rem 0rem">
      <div class="admin">
        <div class="login">
          <h2 class="text-center text-white">Sign In</h2>
          <form class="row g-3 text-start mt-3">
            <div>
              <input
                type="email"
                v-model="email"
                class="form-control"
                id="inputEmail4"
                placeholder="Email"
              />
            </div>
            <div>
              <input
                type="password"
                v-model="password"
                class="form-control"
                id="inputPassword4"
                placeholder="Password"
              />
            </div>
            <div>
              <button
                type="submit"
                class="btn btn-secondary col-12"
                id="btn"
                @click="log"
              >
                Sign In
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
  <LoadingSpinner v-if="loading" />
</template>

<style scoped>
@import "../CSS/Admin.css";
</style>
