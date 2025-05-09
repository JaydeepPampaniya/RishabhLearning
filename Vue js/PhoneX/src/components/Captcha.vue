<script setup>
import { ref, watchEffect } from "vue";
import { useRouter } from "vue-router";
import { useToast } from "vue-toastification";

const toast = useToast();
// Generate Random Captcha Code
const generateRandomCode = () => {
  const characters =
    "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
  let code = "";
  for (let i = 0; i < 6; i++) {
    code += characters.charAt(Math.floor(Math.random() * characters.length));
  }
  return code;
};

const router = useRouter();
const captchaCode = ref(generateRandomCode());
const captchaInput = ref("");

// Refresh Captcha
const handleRefreshCaptcha = () => {
  captchaCode.value = generateRandomCode();
  captchaInput.value = "";
};

// Submit Captcha Validation
const submitCaptcha = () => {
  if (captchaCode.value === captchaInput.value) {
    router.push("/EditUser");
  } else {
    toast.error("Enter Correct Captcha");
  }
};
</script>

<template>
  <div class="container my-5 text-center captcha">
    <h3>Captcha</h3>
    <img
      src="../assets/background/captcha.png"
      class="img-fluid"
      alt="captcha"
    />

    <div class="my-3 captcha-button">
      <button class="btn text-white my-3" style="background-color: blueviolet">
        CAPTCHA Code: {{ captchaCode }}
      </button>
      <button
        type="button"
        class="btn btn-danger my-3"
        @click="handleRefreshCaptcha"
      >
        Refresh CAPTCHA
      </button>
    </div>

    <div class="captcha-input">
      <div class="input-group mb-3 text-center">
        <span class="input-group-text" id="basic-addon1">👉</span>
        <input
          type="password"
          class="form-control"
          placeholder="Enter Captcha"
          v-model="captchaInput"
          aria-label="Captcha"
          aria-describedby="basic-addon1"
        />
      </div>
      <br />
    </div>

    <button type="button" class="btn btn-success" @click="submitCaptcha">
      Submit
    </button>
  </div>
</template>

<style scoped>
/* Add any required styles */
</style>
