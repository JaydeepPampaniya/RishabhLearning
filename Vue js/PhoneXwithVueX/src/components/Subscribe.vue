<script setup>
import { ref } from "vue";
import { BiLogoLinkedin } from "vue3-icons/bi";
import { FaFacebookF, FaTwitter, FaInstagram } from "vue3-icons/fa";
import { toast } from "vue3-toastify";

const email = ref("");

const subscribe = () => {
  if (email.value.trim() === "") {
    toast.error("Please enter a valid email address.");
    return;
  }
  alert(`Subscribed successfully with ${email.value}`);
  email.value = "";
};
</script>

<template>
  <div class="subscribeImage">
    <div class="new container text-center">
      <div>
        <p class="text-secondary fs-5">NEWSLETTER</p>
        <h2>SUBSCRIBE FOR LATEST UPDATE AND OFFERS</h2>
      </div>
      <div class="email">
        <div class="in">
          <input
            v-model="email"
            type="email"
            class="form-control input"
            placeholder="Email"
          />
        </div>
        <div>
          <button
            type="button"
            class="btn btn-info subscribe-button mt-3"
            @click="subscribe"
          >
            Subscribe
          </button>
        </div>
      </div>
      <div>
        <p class="text-secondary mt-3">
          Will be used in accordance with our Privacy Policy
        </p>
      </div>
      <div class="icon mt-2">
        <a href="#"><BiLogoLinkedin /></a>
        <a href="#"><FaFacebookF /></a>
        <a href="#"><FaTwitter /></a>
        <a href="#"><FaInstagram /></a>
      </div>
    </div>
  </div>
</template>

<style scoped>
@import "../CSS/Subscribe.module.css";
</style>
