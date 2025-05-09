<script setup>
import { defineProps, defineEmits } from "vue";
import { useRouter } from "vue-router";
import cookies from "js-cookie";

// Import icons
import { PiUserListBold } from "vue3-icons/pi";
import { BiCategoryAlt } from "vue3-icons/bi";
import { MdOutlineProductionQuantityLimits } from "vue3-icons/md";
import { TbLogout } from "vue3-icons/tb";

import profile from "../../public/assets/background/profile.png";

// Define props and emit event
const { selectedTab } = defineProps(["selectedTab"]);
const emit = defineEmits(["update:selectedTab"]); // Updated event name

const router = useRouter();

const setSelectedTab = (tab) => {
  emit("update:selectedTab", tab); // Use Vue's standard two-way binding naming
};

const logOut = () => {
  cookies.remove("AdminId");
  router.push("/AdminLogin");
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
          ></button>
        </div>
        <div class="offcanvas-body">
          <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
            <li class="nav-item">
              <a
                class="nav-link"
                :class="{ active: selectedTab === 'UserList' }"
                @click="setSelectedTab('UserList')"
              >
                <PiUserListBold /> &nbsp;User List
              </a>
            </li>
            <li class="nav-item">
              <a
                class="nav-link"
                :class="{ active: selectedTab === 'AddItem' }"
                @click="setSelectedTab('AddItem')"
              >
                <MdOutlineProductionQuantityLimits />
                &nbsp;Add/Remove/Update Product
              </a>
            </li>
            <li class="nav-item">
              <a
                class="nav-link"
                :class="{ active: selectedTab === 'AddPostCategory' }"
                @click="setSelectedTab('AddPostCategory')"
              >
                <BiCategoryAlt /> &nbsp;Add Post Category
              </a>
            </li>
            <li class="nav-item">
              <a
                class="nav-link"
                :class="{ active: selectedTab === 'OrderList' }"
                @click="setSelectedTab('OrderList')"
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

<style scoped>
.nav-link.active {
  font-weight: bold;
  background-color: rgba(255, 255, 255, 0.1);
}
</style>
