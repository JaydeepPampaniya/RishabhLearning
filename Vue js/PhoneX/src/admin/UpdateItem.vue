<script setup>
import { ref, watch, onMounted, computed } from "vue";
import axios from "axios";
import { toast } from "vue3-toastify";
import "vue3-toastify/dist/index.css";
import cookies from "js-cookie";
import LoadingSpinner from "../components/LoadingSpinner.vue"; // Adjust path if needed

// State Variables
const productAll = ref([]);
const loading = ref(false);
const productId = ref(null);
const oneProduct = ref(null);
const { token } = JSON.parse(cookies.get("AdminId"));

const props = defineProps(["getCategory"]);

// Pagination
const page = ref(0);
const size = ref(6);

// Product State
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

// Fetch all products with pagination
const fetchProducts = async () => {
  try {
    loading.value = true;
    const { data } = await axios.get(
      `http://localhost:8080/products/getProducts?page=${page.value}&size=${size.value}`,
      { headers: { Authorization: `Bearer ${token}` } }
    );
    productAll.value = data.content || [];
  } catch (error) {
    handleApiError(error);
  } finally {
    loading.value = false;
  }
};

// Fetch single product
const fetchOneProduct = async () => {
  if (!productId.value) return;
  try {
    loading.value = true;
    const { data } = await axios.get(
      `http://localhost:8080/products/${productId.value}/getProduct`,
      { headers: { Authorization: `Bearer ${token}` } }
    );
    oneProduct.value = data;
  } catch (error) {
    handleApiError(error);
  } finally {
    loading.value = false;
  }
};

// Handle API Errors
const handleApiError = (error) => {
  toast.error(error.response?.data || error.message);
};

// Computed: Pre-filling existing product data when updating
const prefilledProduct = computed(() => ({
  companyName: product.value.companyName || oneProduct.value?.companyName,
  deviceName: product.value.deviceName || oneProduct.value?.deviceName,
  originalPrice: product.value.originalPrice || oneProduct.value?.originalPrice,
  currentPrice: product.value.currentPrice || oneProduct.value?.currentPrice,
  discount: product.value.discount || oneProduct.value?.discount,
  description: product.value.description || oneProduct.value?.description,
  deviceTypeId: product.value.deviceTypeId || oneProduct.value?.deviceType?.id,
}));

// Handle Image Upload
const handleImageChange = (e) => {
  image.value = e.target.files[0];
};

// Submit Form
const submit = async () => {
  if (!image.value) {
    toast.error("Please upload an image");
    return;
  }

  const formData = new FormData();
  formData.append("imageFile", image.value);
  formData.append(
    "updateProductDto",
    new Blob([JSON.stringify(prefilledProduct.value)], {
      type: "application/json",
    })
  );

  try {
    loading.value = true;
    const result = await axios.patch(
      `http://localhost:8080/products/${productId.value}/updateProduct`,
      formData,
      { headers: { Authorization: `Bearer ${token}` } }
    );

    if (result.status === 202) {
      toast.success(result.data);
      fetchOneProduct();
    } else {
      toast.error("Something went wrong");
    }
  } catch (error) {
    handleApiError(error);
  } finally {
    loading.value = false;
  }
};

// Pagination Handlers
const handleNextPage = () => page.value++;
const handlePreviousPage = () => {
  if (page.value > 0) page.value--;
};

// Watchers
watch(productId, fetchOneProduct);
watch(page, fetchProducts);
watch(size, fetchProducts);

// Fetch products on mount
onMounted(fetchProducts);
</script>
<template>
  <div>
    <!-- Product Selection -->
    <div class="input-group mb-3">
      <span class="input-group-text">Select Product</span>
      <select class="form-select" v-model="productId">
        <option value="">Please Select Option</option>
        <option v-for="item in productAll" :key="item.id" :value="item.id">
          {{ item.deviceName }}
        </option>
      </select>
    </div>

    <!-- Pagination Controls -->
    <div class="d-flex justify-content-between mb-3">
      <button
        class="btn btn-secondary"
        @click="handlePreviousPage"
        :disabled="page === 0"
      >
        Previous
      </button>
      <input type="number" min="1" class="form-control w-25" v-model="size" />
      <button class="btn btn-secondary" @click="handleNextPage">Next</button>
    </div>

    <!-- Show inputs only when a product is selected -->
    <div v-if="productId">
      <!-- Image Upload -->
      <div class="input-group mb-3">
        <span class="input-group-text">Image</span>
        <input class="form-control" type="file" @change="handleImageChange" />
      </div>

      <!-- Product Fields -->
      <div class="input-group mb-3">
        <span class="input-group-text">Company Name</span>
        <input
          type="text"
          class="form-control"
          v-model="product.companyName"
          :placeholder="oneProduct.companyName || 'Company Name'"
        />
      </div>

      <div class="input-group mb-3">
        <span class="input-group-text">Device Name</span>
        <input
          type="text"
          class="form-control"
          v-model="product.deviceName"
          :placeholder="oneProduct.deviceName || 'Device Name'"
        />
      </div>

      <div class="input-group mb-3">
        <span class="input-group-text">Original Price</span>
        <input
          type="text"
          class="form-control"
          v-model="product.originalPrice"
          :placeholder="oneProduct.originalPrice || 'Original Price'"
        />
      </div>

      <div class="input-group mb-3">
        <span class="input-group-text">Current Price</span>
        <input
          type="text"
          class="form-control"
          v-model="product.currentPrice"
          :placeholder="oneProduct.currentPrice || 'Current Price'"
        />
      </div>

      <div class="input-group mb-3">
        <span class="input-group-text">Discount</span>
        <input
          type="text"
          class="form-control"
          v-model="product.discount"
          :placeholder="oneProduct.discount || 'Discount %'"
        />
      </div>

      <div class="input-group mb-3">
        <span class="input-group-text">Description</span>
        <textarea
          class="form-control"
          v-model="product.description"
          :placeholder="oneProduct.description || 'Product Description'"
        ></textarea>
      </div>

      <!-- Category Selection -->
      <div class="input-group mb-3">
        <span class="input-group-text">Category</span>
        <select class="form-select" v-model="product.deviceTypeId">
          <option value="">Select Category</option>
          <option v-for="item in getCategory" :key="item.id" :value="item.id">
            {{ item.name }}
          </option>
        </select>
      </div>

      <!-- Submit Button -->
      <button
        type="button"
        class="btn btn-secondary w-100 mb-5"
        @click="submit"
      >
        Submit
      </button>
    </div>

    <!-- Loading Spinner -->
    <LoadingSpinner v-if="loading" />
  </div>
</template>

<style scoped>
/* Add custom styles if needed */
</style>
