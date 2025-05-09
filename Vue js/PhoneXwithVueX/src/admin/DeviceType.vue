<script setup>
import { computed, onMounted, ref, watch } from "vue";
import { toast } from "vue3-toastify";
import LoadingSpinner from "../components/LoadingSpinner.vue";
import { useStore } from "vuex";

const name = ref("");
const store = useStore();
const loading = computed(() => store.getters["deviceType/loading"]);
const deviceTypes = computed(() => store.getters["deviceType/deviceTypes"]);
const totalPages = computed(() => store.getters["deviceType/totalPages"]);
const page = ref(0);
const size = ref(5);
const submit = async () => {
  if (!name.value.trim()) {
    toast.error("Please enter a category name");
    return;
  }
  const credentials = {
    name: name.value,
  };
  await store.dispatch("deviceType/addDeviceType", credentials);
  await store.dispatch("deviceType/getDeviceTypes", page.value);
  name.value = "";
};
const deleteType = async (id) => {
  await store.dispatch("deviceType/deleteDeviceType", id);
  await store.dispatch("deviceType/getDeviceTypes", page.value);
};
onMounted(async () => {
  await store.dispatch("auth/checkAuth");
  await store.dispatch("deviceType/getDeviceTypes", page.value);
});

watch(page, async () => {
  await store.dispatch("deviceType/getDeviceTypes", page.value);
});
</script>

<style scoped>
.container {
  max-width: 500px;
  margin: auto;
}
</style>

<template>
  <div class="container mt-5">
    <h1 class="text-center p-3">Add Device Type</h1>
    <hr />
    <table class="table container text-center">
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(deviceType, i) in deviceTypes" :key="deviceType.id">
          <td>{{ i + 1 + page * 5 }}</td>

          <td>{{ deviceType.name }}</td>
          <td>
            <button
              type="button"
              class="btn btn-danger"
              @click="deleteType(deviceType.id)"
            >
              DELETE
            </button>
          </td>
        </tr>
      </tbody>
    </table>

    <div class="d-flex justify-content-center my-3">
      <button
        class="btn btn-primary mx-2"
        @click="page = Math.max(page - 1, 0)"
        :disabled="page === 0"
      >
        Previous
      </button>
      <button
        v-for="index in totalPages"
        :key="index"
        class="btn btn-primary mx-1"
        @click="page = index - 1"
        :disabled="page === index - 1"
      >
        {{ index }}
      </button>
      <button
        class="btn btn-primary mx-2"
        @click="page++"
        :disabled="page + 1 >= totalPages"
      >
        Next
      </button>
    </div>
    <div class="my-5">
      <div class="input-group mb-3">
        <span class="input-group-text">Add </span>
        <input
          type="text"
          class="form-control"
          placeholder="Enter Category Name"
          v-model="name"
        />
      </div>
      <button
        class="btn btn-success w-100"
        @click="submit()"
        :disabled="loading"
      >
        {{ loading ? "Posting..." : "Post" }}
      </button>
    </div>
    <LoadingSpinner v-if="loading" />
  </div>
</template>
