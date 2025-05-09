<script setup>
import { ref, onMounted, watch } from "vue";
import { useRouter } from "vue-router";
import Cookies from "js-cookie";
import UserDetail from "./UserDetail.vue";
import LoadingSpinner from "./LoadingSpinner.vue";
import { FiUserCheck } from "vue3-icons/fi";
import { MdOutlineLogin } from "vue3-icons/md";
import { FaListUl, FaSearch, FaShoppingCart } from "vue3-icons/fa";

const loading = ref(false);
const seeUserDetails = ref(false);
const router = useRouter();
const close = ref(false);

const userCookie = ref(Cookies.get("UserCookie"));
const id = ref(userCookie.value ? JSON.parse(userCookie.value).id : null);

watch(userCookie, (newValue) => {
  id.value = newValue ? JSON.parse(newValue).id : null;
});

const checkUserCookie = () => {
  setInterval(() => {
    const newCookie = Cookies.get("UserCookie");
    if (newCookie !== userCookie.value) {
      userCookie.value = newCookie;
    }
  }, 1000); // Check every second
};

const handleOnClick = () => {
  if (id.value) {
    router.push("/Bag#top");
  } else {
    router.push("/SignIn#top");
  }
};
const handleNavigate = (path) => {
  close.value.click();
  router.push(path);
};

onMounted(() => {
  checkUserCookie();
});
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

      <!-- Sidebar Menu -->
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
          <button type="button" class="btn btn-outline-light">
            <FaSearch />
          </button>

          <button
            v-if="id"
            type="button"
            class="btn btn-outline-light"
            @click="seeUserDetails = true"
          >
            <FiUserCheck />
          </button>

          <button v-else type="button" class="btn" style="margin: -15px">
            <div class="dropdown">
              <button
                class="btn dropdown-toggle text-white"
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
                    @click.prevent="router.push('/SignIn')"
                  >
                    Sign In
                  </a>
                </li>
                <li>
                  <a
                    class="signUp dropdown-item text-danger fw-bold"
                    href="#"
                    @click.prevent="router.push('/SignUp')"
                  >
                    Sign Up
                  </a>
                </li>
              </ul>
            </div>
          </button>

          <button
            type="button"
            class="btn btn-outline-light"
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
</template>

<style scoped>
@import "../CSS/Navbar.module.css";
</style>
