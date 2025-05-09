<script setup>
import { useRouter } from "vue-router";

const router = useRouter();

const navigate = (path) => {
  router.push(path);
};
</script>

<template>
  <footer class="footer py-5 row">
    <div class="col-sm-12 col-md-3 px-2 mt-4">
      <h4>About</h4>
      <p class="text-secondary">
        We PhoneX Developer provide you best mobile phones and accessories based
        on your needs. Thank you for visiting our website.
      </p>
    </div>

    <div class="contact col-sm-12 col-md-3 px-2 mt-4">
      <h4>Contact</h4>
      <a
        href="https://maps.app.goo.gl/jouiWzjKgNDnKSAo6"
        target="_blank"
        class="text-secondary"
      >
        <i class="pi pi-location" style="color: red; font-size: 1rem"></i>
        &nbsp; Gota, Ahmedabad-382481
      </a>
      <a href="tel:+919974177873" class="text-secondary">
        <i class="pi pi-location" style="color: red; font-size: 1rem"></i>
        &nbsp; Phone: +91 9974177873
      </a>
      <a href="mailto:phonex@gmail.com" class="text-secondary">
        <i class="pi pi-location" style="color: red; font-size: 1rem"></i>
        &nbsp; Email: phonex@gmail.com
      </a>
    </div>

    <div class="contact col-sm-12 col-md-3 px-2 mt-4">
      <h4>Categories</h4>
      <a href="#" class="text-secondary">Phones</a>
      <a href="#" class="text-secondary">Headphones</a>
      <a href="#" class="text-secondary">Airpods</a>
      <a href="#" class="text-secondary">AR-VR</a>
    </div>

    <div class="contact col-sm-12 col-md-3 px-2 mt-4">
      <h4>Pages</h4>
      <button class="btn btn-link text-secondary" @click="navigate('/')">
        Home
      </button>
      <button class="btn btn-link text-secondary" @click="navigate('/about')">
        About
      </button>
      <button
        class="btn btn-link text-secondary"
        @click="navigate('/privacyPolicy')"
      >
        Privacy Policy
      </button>
      <button class="btn btn-link text-secondary" @click="navigate('/return')">
        Returns
      </button>
      <button class="btn btn-link text-secondary" @click="navigate('/term')">
        Terms & Conditions
      </button>
    </div>
  </footer>

  <hr />

  <div class="payment px-3">
    <div class="logo">
      <a class="text-start text-secondary fs-5" href="#">
        Phone<span style="color: red">X</span>
      </a>
    </div>
    <div class="paymentcards">
      <a href="#">
        <img src="../assets/Payment/visa.png" alt="Visa Payment" />
      </a>
      <a href="#">
        <img src="../assets/Payment/mastercard.png" alt="MasterCard Payment" />
      </a>
      <a href="#">
        <img src="../assets/Payment/paypal.png" alt="PayPal Payment" />
      </a>
      <a href="#">
        <img src="../assets/Payment/Maestro.png" alt="Maestro Payment" />
      </a>
      <a href="#">
        <img
          src="../assets/Payment/visa elecron.png"
          alt="Visa Electron Payment"
        />
      </a>
    </div>
  </div>
</template>

<style scoped>
@import "../CSS//Footer.module.css";
</style>
