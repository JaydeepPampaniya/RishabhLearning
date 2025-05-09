<script setup>
import { ref, onMounted, computed } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";
import cookies from "js-cookie";
import { useToast } from "vue-toastification";
import EmptyBagItem from "./EmptyBagItem.vue";
import LoadingSpinner from "./LoadingSpinner.vue";

const router = useRouter();
const bagItems = ref([]);
const loading = ref(true);
const totalMRP = ref(0);
const discount = ref(0);
const time = ref(new Date());
const toast = useToast();
// Fetch user info from cookies
const userCookie = cookies.get("UserCookie");
const token = userCookie ? JSON.parse(userCookie).token : null;
const id = userCookie ? JSON.parse(userCookie).id : null;

// Redirect to sign-in if user is not logged in
if (!id) {
  router.push("/SignIn");
}

// Auto-update estimated delivery date every 10 seconds
onMounted(() => {
  const interval = setInterval(() => {
    const newTime = new Date();
    newTime.setDate(newTime.getDate() + 7);
    time.value = newTime;
  }, 10000);

  return () => clearInterval(interval);
});

// Fetch cart products
const fetchProducts = async () => {
  try {
    loading.value = true;
    const response = await axios.get(
      `http://localhost:8080/cart/getCartProducts/${id}`,
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );

    if (response.status === 200) {
      if (response.data === "Cart is empty.") {
        bagItems.value = [];
      } else {
        bagItems.value = response.data;

        // Calculate total MRP and discount
        discount.value = response.data.reduce(
          (acc, item) =>
            acc + (item.products.originalPrice - item.products.currentPrice),
          0
        );
        totalMRP.value = response.data.reduce(
          (acc, item) => acc + parseInt(item.products.currentPrice),
          0
        );
      }
    } else {
      toast.error(response.data.message);
    }
  } catch (error) {
    if (error.response.status === 401) {
      cookies.remove("UserCookie");
      toast.error("Please login again, session timeout");
      router.push("/SignIn");
    } else {
      toast.error(error.response?.data || error.message);
    }
  } finally {
    loading.value = false;
  }
};

// Remove item from cart
const handleRemoveItem = async (productId) => {
  try {
    loading.value = true;
    const response = await axios.delete(
      `http://localhost:8080/cart/delete/${productId}/${id}`,
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );

    if (response.status === 200) {
      fetchProducts();
      toast.success(response.data);
    } else {
      toast.error("Something went wrong");
    }
  } catch (error) {
    if (error.response.status === 401) {
      cookies.remove("UserCookie");
      toast.error("Please login again, session timeout");
      router.push("/SignIn");
    } else {
      toast.error(error.response?.data || error.message);
    }
  } finally {
    loading.value = false;
  }
};

// Computed property for total amount
const totalAmount = computed(() => Math.round(totalMRP.value) + 99);

// Fetch products when component mounts
onMounted(() => {
  fetchProducts();
});
</script>

<template>
  <div class="container mb-5" id="top">
    <div class="container mb-5">
      <EmptyBagItem v-if="bagItems.length === 0" />
      <div v-else class="row">
        <!-- Cart Items -->
        <div class="bag-items-container col-md-8 col-sm-12">
          <div
            v-for="item in bagItems"
            :key="item.id"
            class="bag-item-container"
          >
            <div class="item-left-part">
              <img
                class="bag-item-img"
                :src="
                  `data:image/jpeg;base64,${item.products.imageData}` ||
                  'placeholder-image-url'
                "
                :alt="item.products.deviceName"
              />
            </div>
            <div class="item-right-part">
              <div class="company">{{ item.products.companyName }}</div>
              <div class="item-name">{{ item.products.deviceName }}</div>
              <div class="price-container">
                <span class="current-price"
                  >Rs {{ item.products.currentPrice }}</span
                >
                <span class="original-price"
                  >Rs {{ item.products.originalPrice }}</span
                >
                <span class="discount-percentage"
                  >({{ item.products.discount }}% OFF)</span
                >
              </div>
              <div class="return-period">
                <span class="return-period-days">7 days</span> return available
              </div>
              <div class="delivery-details">
                Delivery by {{ time.toLocaleDateString() }}
              </div>
            </div>
            <div
              class="remove-from-cart"
              @click="handleRemoveItem(item.products.id)"
            >
              <span>🗑</span>
            </div>
          </div>
        </div>

        <!-- Cart Summary -->
        <div class="col-md-4 col-sm-12 pt-5">
          <div class="bag-summary">
            <div class="bag-details-container">
              <div class="price-header">
                PRICE DETAILS ({{ bagItems.length }} Items)
              </div>
              <div class="price-item">
                <span class="price-item-tag">Total MRP</span>
                <span class="price-item-value"
                  >₹{{ Math.round(totalMRP) }}</span
                >
              </div>
              <div class="price-item">
                <span class="price-item-tag">Discount on MRP</span>
                <span class="price-item-value priceDetail-base-discount"
                  >₹{{ discount }}</span
                >
              </div>
              <div class="price-item">
                <span class="price-item-tag">Convenience Fee</span>
                <span class="price-item-value">₹99</span>
              </div>
              <hr />
              <div class="price-footer">
                <span class="price-item-tag">Total Amount</span>
                <span class="price-item-value">₹{{ totalAmount }}</span>
              </div>
            </div>
            <button class="btn-place-order" @click="router.push('/')">
              PLACE ORDER
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
  <LoadingSpinner v-if="loading" />
</template>

<style scoped>
/* Add necessary styles */
</style>
