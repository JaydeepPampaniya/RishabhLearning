<script setup>
import { ref, shallowRef } from "vue";
import AdminNav from "./AdminNav.vue";
import UserList from "./UserList.vue";
import AddItem from "./AddItem.vue";
import AddPostCategory from "./AddPostCategory.vue";

// Define reactive state for selectedTab
const selectedTab = ref("UserList");

// Map tab names to component references
const components = {
  UserList,
  AddItem,
  AddPostCategory,
};

// Update selected tab
const setSelectedTab = (tab) => {
  selectedTab.value = tab;
};
</script>

<template>
  <div>
    <AdminNav :selectedTab="selectedTab" @update:selectedTab="setSelectedTab" />
    <div>
      <keep-alive>
        <component :is="components[selectedTab]" />
      </keep-alive>
    </div>
  </div>
</template>

<style scoped>
/* Add styles if needed */
</style>
