<script setup>
import { ref, onMounted, watch } from "vue";
import { useRoute, useRouter } from "vue-router";
import axios from "axios";
import { useToast } from "vue-toastification";
import cookies from "js-cookie";
import LoadingSpinner from "./LoadingSpinner.vue";

const route = useRoute();
const router = useRouter();
const productId = route.params.productId;
const toast = useToast();

const product = ref({});
const loading = ref(false);
const inBag = ref(false);

const userCookie = cookies.get("UserCookie");
const token = userCookie ? JSON.parse(userCookie).token : null;
const userId = userCookie ? JSON.parse(userCookie).id : null;

// Redirect if not logged in
onMounted(() => {
  if (!userId || !token) {
    router.push("/SignIn");
  }
});

// Fetch Product Details
const fetchProduct = async () => {
  loading.value = true;
  try {
    const response = await axios.get(
      `http://localhost:8080/products/${productId}/getProduct`,
      {
        headers: { Authorization: `Bearer ${token}` },
      }
    );

    if (response.status === 200) {
      product.value = response.data;
    } else {
      toast.error(response.data.message);
    }
  } catch (error) {
    handleSessionError(error);
  } finally {
    loading.value = false;
  }
};

// Fetch Cart Status
const fetchCartStatus = async () => {
  loading.value = true;
  try {
    const response = await axios.get(
      `http://localhost:8080/cart/getCartProduct/${userId}/${productId}`,
      {
        headers: { Authorization: `Bearer ${token}` },
      }
    );

    if (response.status === 200) {
      inBag.value = response.data;
    } else {
      toast.error(response.data.message);
    }
  } catch (error) {
    handleSessionError(error);
  } finally {
    loading.value = false;
  }
};

// Add to Cart
const addToBag = async () => {
  if (!userId || !token) return router.push("/SignIn");

  loading.value = true;
  const data = { productId: product.value.id, userId, quantity: 1 };

  try {
    const response = await axios.post(
      "http://localhost:8080/cart/addToCart",
      data,
      {
        headers: { Authorization: `Bearer ${token}` },
      }
    );

    if (response.status === 202) {
      fetchCartStatus();
      toast.success(response.data);
    }
  } catch (error) {
    toast.error(error.response?.data || error.message);
  } finally {
    loading.value = false;
  }
};

// Remove from Cart
const handleRemoveItem = async () => {
  loading.value = true;
  try {
    const response = await axios.delete(
      `http://localhost:8080/cart/delete/${productId}/${userId}`,
      {
        headers: { Authorization: `Bearer ${token}` },
      }
    );

    if (response.status === 200) {
      fetchCartStatus();
      toast.success(response.data);
    }
  } catch (error) {
    toast.error(error.response?.data || error.message);
  } finally {
    loading.value = false;
  }
};

// Handle Session Expiry
const handleSessionError = (error) => {
  if (error.response?.status === 401) {
    cookies.remove("UserCookie");
    toast.error("Please login again, session timeout.");
    router.push("/SignIn");
  } else {
    toast.error(error.response?.data || error.message);
  }
};

// Fetch product & cart status when productId changes
watch(() => productId, fetchProduct, { immediate: true });
watch(() => productId, fetchCartStatus, { immediate: true });
</script>

<template>
  <div class="container my-5" id="top">
    <div class="row">
      <div class="card mb-3">
        <div v-if="product" class="row g-0">
          <div class="col-md-4">
            <img
              :src="'data:image/jpeg;base64,' + product.imageData"
              class="img-fluid rounded-start"
              alt="Product"
            />
          </div>
          <div class="col-md-8 my-5">
            <div class="card-body">
              <h5 class="card-title">{{ product.deviceName }}</h5>
              <div class="company-name">{{ product.companyName }}</div>
              <p class="card-text">{{ product.description }}</p>
            </div>
            <div class="price text-center">
              <span class="current-price">Rs {{ product.currentPrice }}</span>
              <span class="original-price">Rs {{ product.originalPrice }}</span>
              <span class="discount">({{ product.discount }}% OFF)</span>
            </div>
            <div class="rating text-center">4.5 ⭐ | 30250</div>
            <button
              v-if="inBag"
              class="btn btn-remove-bag btn-danger"
              @click="handleRemoveItem"
            >
              Remove
            </button>
            <button
              v-else
              class="btn btn-add-bag btn-success"
              @click="addToBag"
            >
              Add to Bag
            </button>
          </div>
        </div>
      </div>
    </div>
    <LoadingSpinner v-if="loading" />
  </div>
</template>

<style scoped>
/* Add styles if needed */
</style>
