<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { PiUserListBold } from "vue3-icons/pi";
import { BiCategoryAlt } from "vue3-icons/bi";
import { MdOutlineProductionQuantityLimits } from "vue3-icons/md";
import { TbLogout } from "vue3-icons/tb";
import { useStore } from "vuex";
import profile from "../assets/background/profile.png";

const store = useStore();
const router = useRouter();
const close = ref(null);

const logOut = () => {
  store.dispatch("auth/logout");
  close.value.click();
  router.push("/signIn");
};

const navigate = (path) => {
  close.value.click();
  router.push(path);
};
</script>
<template>
  <nav class="navbar navbar-dark bg-dark fixed-top">
    <div class="container">
      <a class="navbar-brand" href="#">
        Phone<span style="color: red">X</span> Admin
      </a>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="offcanvas"
        data-bs-target="#offcanvasDarkNavbar"
        aria-controls="offcanvasDarkNavbar"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div
        class="offcanvas offcanvas-start text-bg-dark"
        style="width: 300px"
        tabindex="-1"
        id="offcanvasDarkNavbar"
        aria-labelledby="offcanvasDarkNavbarLabel"
      >
        <div class="offcanvas-header gap-3">
          <img :src="profile" alt="Admin Profile" width="30" height="30" />
          <h5 class="offcanvas-title" id="offcanvasDarkNavbarLabel">
            Administrator
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
          <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
            <li class="nav-item">
              <a class="nav-link" href="#" @click.prevent="navigate('/admin')">
                <PiUserListBold /> &nbsp;User List
              </a>
            </li>
            <li class="nav-item">
              <a
                class="nav-link"
                href="#"
                @click.prevent="navigate('/admin/productsList')"
              >
                <MdOutlineProductionQuantityLimits /> &nbsp; Product List
              </a>
            </li>
            <li class="nav-item">
              <a
                class="nav-link"
                href="#"
                @click.prevent="navigate('/admin/addProducts')"
              >
                <PiUserListBold /> &nbsp;Add Products
              </a>
            </li>
            <li class="nav-item">
              <a
                class="nav-link"
                href="#"
                @click.prevent="navigate('/admin/deviceType')"
              >
                <BiCategoryAlt /> &nbsp;Device Type
              </a>
            </li>
            <li class="nav-item">
              <a
                class="nav-link"
                href="#"
                @click.prevent="navigate('/admin/orderList')"
              >
                <BiCategoryAlt /> &nbsp;Order List
              </a>
            </li>
            <hr />
            <li class="nav-item">
              <a class="nav-link" href="#" @click.prevent="logOut">
                <TbLogout /> &nbsp;Log Out
              </a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </nav>
</template>
