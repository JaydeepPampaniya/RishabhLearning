<script setup>
import { ref, onMounted, computed, watch } from "vue";
import LoadingSpinner from "../components/LoadingSpinner.vue";
import { FcBusinessman, FcBusinesswoman, FcDecision } from "vue3-icons/fc";
import { useStore } from "vuex";

const page = ref(0);

const customers = computed(() => store.getters["auth/customers"]);
const loading = computed(() => store.getters["auth/loading"]);
const totalPages = computed(() => store.getters["auth/totalPages"]);
const store = useStore();
const fetchCustomers = async () => {
  await store.dispatch("auth/customerList", page.value);
};
onMounted(async () => {
  await store.dispatch("auth/checkAuth");
  fetchCustomers();
});
watch(page, fetchCustomers);
const deleteCustomer = async (id) => {
  await store.dispatch("auth/deleteCustomer", id, page.value);
};
</script>

<template>
  <div>
    <h1 class="text-center py-1 cl mt-5">User List</h1>
    <table class="table container text-center">
      <thead>
        <tr>
          <th>ID</th>
          <th>Profile</th>
          <th>Name</th>
          <th>Email</th>
          <th>Gender</th>
          <th>Created At</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(user, i) in customers" :key="user.id">
          <td>{{ i + 1 + page * 5 }}</td>
          <td class="fs-4 text-success">
            <component
              :is="
                user.gender === 'Male'
                  ? FcBusinessman
                  : user.gender === 'Female'
                  ? FcBusinesswoman
                  : FcDecision
              "
            />
          </td>
          <td>{{ user.username }}</td>
          <td>{{ user.user.email || "N/A" }}</td>
          <td>{{ user.gender }}</td>
          <td>{{ user.user.createdTime }}</td>
          <td>
            <button
              type="button"
              class="btn btn-danger"
              @click="deleteCustomer(user.user?.id)"
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

    <LoadingSpinner v-if="loading" />
  </div>
</template>
