<script setup>
import { ref } from "vue";
import axios from "axios";
import { toast } from "vue3-toastify";
import cookies from "js-cookie";
import LoadingSpinner from "./LoadingSpinner.vue"; // Import spinner component

const oldPassword = ref("");
const newPassword = ref("");
const confirmNewPassword = ref("");
const loading = ref(false);

const userCookie = cookies.get("UserCookie");
const token = userCookie ? JSON.parse(userCookie).token : null;
const id = userCookie ? JSON.parse(userCookie).id : null;
const gender = userCookie ? JSON.parse(userCookie).gender : null;

// Update password function
const updatePassword = async () => {
  if (!oldPassword.value || !newPassword.value || !confirmNewPassword.value) {
    toast.error("Please fill all the details");
    return;
  }
  if (newPassword.value !== confirmNewPassword.value) {
    toast.error("New password and confirm password must be the same");
    return;
  }

  const data = {
    id,
    oldPassword: oldPassword.value,
    newPassword: newPassword.value,
  };

  try {
    loading.value = true;
    const response = await axios.patch(
      "http://localhost:8080/customer/changePassword",
      data,
      {
        headers: { Authorization: `Bearer ${token}` },
      }
    );

    if (response.status === 200) {
      toast.success(response.data);
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
  <div class="card text-center container my-5">
    <div class="card-header">Edit User Detail</div>
    <div class="card-body">
      <p class="card-text">Change Account Details</p>

      <!-- Avatar Selection Based on Gender -->
      <p style="font-size: '3rem'">
        <img
          v-if="gender === 'Male'"
          src="../assets/background/manavtar.png"
          style="width: 10rem; border-radius: 50%"
          class="card-img-top"
          alt="avatar"
        />
        <img
          v-else-if="gender === 'Female'"
          src="../assets/background/womenavtar.jpg"
          style="width: 10rem; border-radius: 50%"
          class="card-img-top"
          alt="avatar"
        />
        <FcDecision v-else />
      </p>

      <!-- Password Inputs -->
      <div class="input-group mb-3">
        <span class="input-group-text">Old Password</span>
        <input
          type="password"
          class="form-control"
          v-model="oldPassword"
          placeholder="Old Password"
        />
      </div>

      <div class="input-group mb-3">
        <span class="input-group-text">New Password</span>
        <input
          type="password"
          class="form-control"
          v-model="newPassword"
          placeholder="New Password"
        />
      </div>

      <div class="input-group mb-3">
        <span class="input-group-text">Confirm Password</span>
        <input
          type="password"
          class="form-control"
          v-model="confirmNewPassword"
          placeholder="Confirm New Password"
        />
      </div>

      <!-- Update Button -->
      <button class="btn btn-info w-100 text-white" @click="updatePassword">
        Update
      </button>
    </div>
  </div>

  <!-- Loading Spinner -->
  <LoadingSpinner v-if="loading" />
</template>

<style scoped>
/* Add any required styles */
</style>
