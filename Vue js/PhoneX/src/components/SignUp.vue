<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";
import { useToast } from "vue-toastification";

import LoadingSpinner from "./LoadingSpinner.vue";

const router = useRouter();
const username = ref("");
const email = ref("");
const password = ref("");
const confirmPassword = ref("");
const gender = ref("");
const age = ref("");
const address = ref("");
const contactNo = ref("");
const loading = ref(false);
const role = "CUSTOMER";

const toast = useToast();

const reg = async (e) => {
  e.preventDefault();

  if (
    username.value &&
    email.value &&
    password.value &&
    confirmPassword.value &&
    gender.value &&
    password.value === confirmPassword.value &&
    age.value &&
    address.value &&
    contactNo.value
  ) {
    try {
      loading.value = true;
      const data = {
        username: username.value,
        email: email.value,
        password: password.value,
        gender: gender.value,
        age: age.value,
        contactNo: contactNo.value,
        address: address.value,
        role,
      };

      const response = await axios.post(
        "http://localhost:8080/customer/register",
        data
      );
      if (response.status === 201) {
        toast.success(response.data);
        setTimeout(() => {
          router.push("/SignIn#top");
        }, 1500);
      } else {
        toast.error(response.message);
      }
    } catch (error) {
      if (error.response?.status) {
        toast.error(error.response.data || error.response.data.message);
      } else {
        toast.error("An error occurred. Please try again later.");
      }
    } finally {
      loading.value = false;
    }
  } else {
    toast.error("Please fill in all fields and ensure passwords match.");
  }
};
</script>

<template>
  <div class="hero" id="top">
    <div class="container py-5">
      <div class="col-md-6">
        <h2 class="text-center text-white">Sign Up</h2>
        <form class="row g-3 text-start" @submit="reg">
          <div class="col-12">
            <input
              type="text"
              v-model="username"
              class="form-control"
              placeholder="Name"
            />
          </div>
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
            <input
              type="password"
              v-model="confirmPassword"
              class="form-control"
              placeholder="Confirm Password"
            />
          </div>
          <div class="col-12">
            <input
              type="number"
              v-model="age"
              class="form-control"
              placeholder="Age"
            />
          </div>
          <div class="col-12">
            <input
              type="number"
              v-model="contactNo"
              class="form-control"
              placeholder="Contact No"
            />
          </div>
          <div class="col-12">
            <input
              type="text"
              v-model="address"
              class="form-control"
              placeholder="Address"
            />
          </div>
          <div class="my-3 mx-1 col-12">
            <div class="radio form-check">
              <input
                v-model="gender"
                value="Male"
                class="form-check-input"
                type="radio"
                name="gender"
              />
              <label class="form-check-label">Male</label>
            </div>
            <div class="radio form-check">
              <input
                v-model="gender"
                value="Female"
                class="form-check-input"
                type="radio"
                name="gender"
              />
              <label class="form-check-label">Female</label>
            </div>
            <div class="radio form-check">
              <input
                v-model="gender"
                value="Other"
                class="form-check-input"
                type="radio"
                name="gender"
              />
              <label class="form-check-label">Other</label>
            </div>
          </div>
          <div class="col-12">
            <button type="submit" class="btn btn-success col-12">
              Sign Up
            </button>
          </div>
          <div class="text-white">
            Already Have an Account?
            <span class="signin" @click="router.push('/SignIn#top')"
              >Sign In</span
            >
          </div>
        </form>
      </div>
    </div>
  </div>

  <LoadingSpinner v-if="loading" />
</template>

<style scoped>
@import "../CSS/SignUp.module.css";
</style>
