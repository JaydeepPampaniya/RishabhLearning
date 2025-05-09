<script setup>
import { computed, ref } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import UserDetail from "../pages/CustomerDetails.vue";
import LoadingSpinner from "./LoadingSpinner.vue";
import { FiUserCheck } from "vue3-icons/fi";
import { MdOutlineLogin } from "vue3-icons/md";
import { FaListUl, FaSearch, FaShoppingCart } from "vue3-icons/fa";
import SearchProduct from "./SearchProduct.vue";

const store = useStore();
const loading = ref(false);
const seeUserDetails = ref(false);
const seeSearchComponent = ref(false);
const router = useRouter();
const close = ref(null);

const userId = computed(() => store.getters["auth/user"]);

const handleOnClick = () => {
  if (userId.value) {
    router.push("/bag");
  } else {
    router.push("/signIn");
  }
};

const handleNavigate = (path) => {
  if (close.value) close.value.click();
  router.push(path);
};
</script>

<template>
  <nav class="navbar navbar-expand-lg sticky-top navbar-dark bg-dark">
    <div class="container">
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="offcanvas"
        data-bs-target="#offcanvasNavbar"
        aria-controls="offcanvasNavbar"
        aria-label="Toggle navigation"
      >
        <FaListUl
          class="navbar-toggler-icon text-white"
          style="font-size: 20px"
        />
      </button>
      <div
        class="offcanvas offcanvas-start bg-dark text-white"
        tabindex="-1"
        id="offcanvasNavbar"
      >
        <div class="offcanvas-header">
          <h5 class="offcanvas-title">
            Phone-<span class="text-danger">X</span>
          </h5>
          <button
            type="button"
            class="btn-close btn-close-white"
            data-bs-dismiss="offcanvas"
            aria-label="Close"
            ref="close"
          ></button>
        </div>
        <div class="offcanvas-body">
          <ul class="navbar-nav me-auto">
            <li class="nav-item">
              <a
                class="home nav-link text-white"
                href="#"
                @click.prevent="handleNavigate('/')"
                >HOME</a
              >
            </li>
            <li class="nav-item">
              <a
                class="about nav-link text-white"
                href="#"
                @click.prevent="handleNavigate('/About')"
                >ABOUT</a
              >
            </li>
          </ul>
        </div>
      </div>

      <a class="logo navbar-brand text-white fw-bold" href="#"
        >Phone<span class="text-danger">X</span></a
      >

      <div class="btn-group">
        <form role="search">
          <button
            type="button"
            class="btn btn-outline-light nav-buttons"
            @click="seeSearchComponent = true && userId"
          >
            <FaSearch />
          </button>

          <button
            v-if="userId"
            type="button"
            class="btn btn-outline-light nav-buttons"
            @click="seeUserDetails = true"
          >
            <FiUserCheck />
          </button>

          <button v-else type="button" class="btn" style="margin: -15px">
            <div class="dropdown">
              <button
                class="btn dropdown-toggle text-white nav-buttons"
                type="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
                style="border: 0px solid green"
              >
                <MdOutlineLogin
                  style="font-size: 18px; border: 0px solid green"
                />
              </button>
              <ul class="dropdown-menu dropdown-menu-dark">
                <li>
                  <a
                    class="signUp dropdown-item text-info fw-bold"
                    href="#"
                    @click.prevent="router.push('/signIn')"
                  >
                    Sign In
                  </a>
                </li>
                <li>
                  <a
                    class="signUp dropdown-item text-danger fw-bold"
                    href="#"
                    @click.prevent="router.push('/signUp')"
                  >
                    Sign Up
                  </a>
                </li>
              </ul>
            </div>
          </button>

          <button
            type="button"
            class="btn btn-outline-light nav-buttons"
            @click="handleOnClick"
          >
            <FaShoppingCart />
          </button>
        </form>
      </div>
    </div>
  </nav>

  <LoadingSpinner v-if="loading" />
  <UserDetail v-if="seeUserDetails" @closePopup="seeUserDetails = false" />
  <SearchProduct
    v-if="seeSearchComponent"
    @closePopup="seeSearchComponent = false"
  />
</template>

<style scoped>
@import "../CSS/Navbar.module.css";
</style>
