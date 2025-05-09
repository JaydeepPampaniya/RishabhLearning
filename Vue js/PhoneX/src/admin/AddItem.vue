<script setup>
import { ref, onMounted, watch } from "vue";
import axios from "axios";
import { useToast } from "vue-toastification";
import LoadingSpinner from "../components/LoadingSpinner.vue";
import UpdateItem from "./UpdateItem.vue";
import cookies from "js-cookie";
import { MdWatch } from "vue3-icons/md";

const toast = useToast();
const getCategory = ref([]);
const handleType = ref(null);
const loading = ref(false);
const deviceId = ref(null);
const { token } = JSON.parse(cookies.get("AdminId"));
const page = ref(0);
const size = ref(6);
const totalPages = ref(0);
const productAll = ref([]);
const product = ref({
  companyName: "",
  deviceName: "",
  originalPrice: "",
  currentPrice: "",
  discount: "",
  description: "",
  deviceTypeId: "",
});
const image = ref(null);

const handleInputChange = (e) => {
  product.value[e.target.name] = e.target.value;
};

const handleImageChange = (e) => {
  image.value = e.target.files[0];
};

const submit = async () => {
  if (
    !product.value.companyName ||
    !product.value.deviceName ||
    !product.value.originalPrice ||
    !product.value.currentPrice ||
    !product.value.discount ||
    !product.value.description ||
    !product.value.deviceTypeId
  ) {
    toast.error("Please fill all the required fields");
    return;
  }

  const formData = new FormData();
  formData.append("imageFile", image.value);
  formData.append(
    "addProductDTO",
    new Blob([JSON.stringify(product.value)], { type: "application/json" })
  );

  try {
    loading.value = true;
    const result = await axios.post(
      "http://localhost:8080/products/addProduct",
      formData,
      {
        headers: { Authorization: `Bearer ${token}` },
      }
    );
    if (result.status === 201) {
      toast.success(result.data);
      fetchData();
    } else {
      toast.error("Something went wrong");
    }
  } catch (error) {
    toast.error(error.response?.data || error.message);
  } finally {
    loading.value = false;
  }
};

const fetchCategories = async () => {
  try {
    loading.value = true;
    const response = await axios.get(
      "http://localhost:8080/deviceType/getDeviceType",
      { headers: { Authorization: `Bearer ${token}` } }
    );
    if (response.status === 200) {
      getCategory.value = response.data || [];
    }
  } catch (error) {
    toast.error(error.response?.data || error.message);
  } finally {
    loading.value = false;
  }
};

const fetchData = async () => {
  try {
    loading.value = true;
    const response = await axios.get(
      `http://localhost:8080/products/getProducts?page=${page.value}&size=${size.value}`,
      { headers: { Authorization: `Bearer ${token}` } }
    );
    if (response.status === 200) {
      productAll.value = response.data.content;
      totalPages.value = response.data.totalPages;
    }
  } catch (error) {
    toast.error(error.response?.data || error.message);
  } finally {
    loading.value = false;
  }
};

const handleDelete = async () => {
  try {
    loading.value = true;
    const response = await axios.delete(
      `http://localhost:8080/products/${deviceId.value}/delete`,
      { headers: { Authorization: `Bearer ${token}` } }
    );
    if (response.status === 202) {
      toast.success(response.data);
      deviceId.value = "";
      fetchData();
    }
  } catch (error) {
    toast.error(error.response?.data || error.message);
  } finally {
    loading.value = false;
  }
};
watch([page, size], () => {
  fetchData();
});
onMounted(() => {
  fetchCategories();
  fetchData();
});
</script>

<template>
  <div class="container mt-5">
    <h1 class="text-center py-3">Add/Remove/Update Product</h1>
    <hr />

    <div class="input-group mb-3">
      <span class="input-group-text">What you want?</span>
      <select v-model="handleType" class="form-select">
        <option value="select">Please Select Option</option>
        <hr />
        <option value="add">Add</option>
        <option value="update">Update</option>
        <option value="remove">Remove</option>
      </select>
    </div>

    <template v-if="handleType === 'add'">
      <div class="input-group mb-3">
        <span class="input-group-text">Image</span>
        <input type="file" class="form-control" @change="handleImageChange" />
      </div>

      <div v-for="(value, key) in product" :key="key" class="input-group mb-3">
        <span class="input-group-text">{{
          key.replace(/([A-Z])/g, " $1")
        }}</span>
        <input type="text" class="form-control" v-model="product[key]" />
      </div>

      <div class="input-group mb-3">
        <span class="input-group-text">Category</span>
        <select v-model="product.deviceTypeId" class="form-select">
          <option value="">Select Category</option>
          <hr />
          <option v-for="item in getCategory" :key="item.id" :value="item.id">
            {{ item.name }}
          </option>
        </select>
      </div>

      <button class="btn btn-secondary w-100 mb-5" @click="submit">
        Submit
      </button>
    </template>

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
