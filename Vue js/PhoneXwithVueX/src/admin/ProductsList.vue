<script setup>
import { ref, onMounted, watch, computed } from "vue";
import LoadingSpinner from "../components/LoadingSpinner.vue";
import UpdateItem from "./UpdateProduct.vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";

const router = useRouter();
const store = useStore();
const products = computed(() => store.getters["products/products"]);
const loading = computed(() => store.getters["products/loading"]);
const deviceId = ref(null);
const deviceTypes = computed(() => store.getters["deviceType/deviceTypes"]);
const totalPages = computed(() => store.getters["products/totalPages"]);
const deviceType = ref("");
const page = ref(0);
const size = ref(5);
const productAll = ref([]);

const handleDelete = async (id) => {
  handleSize(5);
  await store.dispatch("products/deleteProduct", id);
  await store.dispatch("products/fetchProducts", {
    page: page.value,
    size: size.value,
    deviceType: deviceType.value,
  });
};

const handleSize = (value) => {
  size.value = value;
  page.value = 0;
};
const navigate = (path) => {
  router.push(path);
};
watch([page, size, deviceType], async () => {
  await store.dispatch("products/fetchProducts", {
    page: page.value,
    size: size.value,
    deviceType: deviceType.value,
  });
});
onMounted(async () => {
  await store.dispatch("auth/checkAuth");
  await store.dispatch("products/fetchProducts", {
    page: page.value,
    size: size.value,
    deviceType: deviceType.value,
  });
  await store.dispatch("deviceType/getDeviceTypes");
});
</script>

<template>
  <div class="container mt-5">
    <h1 class="text-center py-3">Product Details</h1>
    <hr />
    <div class="row">
      <div class="col-2">
        <div class="dropdown">
          <button
            class="btn btn-secondary btn-large dropdown-toggle"
            type="button"
            data-bs-toggle="dropdown"
            aria-expanded="false"
          >
            Short by
          </button>
          <ul class="dropdown-menu">
            <li v-for="device in deviceTypes" :key="device.id">
              <a class="dropdown-item" @click="deviceType = device.name">{{
                device.name
              }}</a>
            </li>
          </ul>
        </div>
      </div>

      <div class="col-2">
        <div class="dropdown">
          <button
            class="btn btn-secondary btn-large dropdown-toggle"
            type="button"
            data-bs-toggle="dropdown"
            aria-expanded="false"
          >
            Data length
          </button>
          <ul class="dropdown-menu">
            <li>
              <a class="dropdown-item" @click.prevent="handleSize(2)">2</a>
            </li>
            <li>
              <a class="dropdown-item" @click.prevent="handleSize(5)">5</a>
            </li>
            <li>
              <a class="dropdown-item" @click.prevent="handleSize(10)">10</a>
            </li>
          </ul>
        </div>
      </div>
    </div>

    <table class="table">
      <thead>
        <tr>
          <th>Company</th>
          <th>Device Name</th>
          <th>Description</th>
          <th>Original Price</th>
          <th>Current Price</th>
          <th>Discount</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(product, i) in products" :key="product.id">
          <td>{{ product.companyName }}</td>
          <td
            style="
              max-width: 60px;
              overflow: hidden;
              white-space: nowrap;
              text-overflow: ellipsis;
            "
          >
            {{ product.deviceName || "N/A" }}
          </td>

          <td
            style="
              max-width: 60px;
              overflow: hidden;
              white-space: nowrap;
              text-overflow: ellipsis;
            "
            :title="product.description"
          >
            {{ product.description }}
          </td>

          <td>Rs {{ product.originalPrice }}</td>
          <td>Rs {{ product.currentPrice }}</td>
          <td>{{ product.discount }}% OFF</td>

          <td colspan="2">
            <button
              type="button"
              class="btn btn-info btn-sm"
              @click="navigate(`/admin/update/${product.id}`)"
            >
              UPDATE
            </button>
            <button
              type="button"
              class="btn btn-danger btn-sm"
              @click.prevent="handleDelete(product.id)"
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

    <UpdateItem v-if="handleType === 'update'" :getCategory="getCategory" />

    <template v-if="handleType === 'remove'">
      <div class="input-group mb-3">
        <span class="input-group-text">Select Product</span>
        <select v-model="deviceId" class="form-select">
          <option value="">Please Select Option</option>
          <hr />
          <option v-for="item in productAll" :key="item.id" :value="item.id">
            {{ item.deviceName }}
          </option>
        </select>
      </div>

      <div class="pagination-controls text-center">
        <label class="me-2">Page Size:</label>
        <input
          type="number"
          min="1"
          class="form-control d-inline-block w-auto mb-3"
          v-model="size"
          @change="fetchData"
        />

        <div class="button-group">
          <button
            class="btn btn-primary mx-2"
            @click="page--"
            :disabled="page === 0"
          >
            Previous
          </button>

          <button
            class="btn btn-primary mx-2"
            @click="page++"
            :disabled="page === totalPages - 1"
          >
            Next
          </button>

          <button class="btn btn-secondary w-100 my-5" @click="handleDelete">
            Delete
          </button>
        </div>
      </div>
    </template>
  </div>

  <LoadingSpinner v-if="loading" />
</template>

<style scoped>
/* Keep your styles here if needed */
</style>
