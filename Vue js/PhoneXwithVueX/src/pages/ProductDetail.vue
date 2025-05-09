<script setup>
import { computed, onMounted } from "vue";
import { useStore } from "vuex";
import { useRoute, useRouter } from "vue-router";
import LoadingSpinner from "../components/LoadingSpinner.vue";
import { toast } from "vue3-toastify";

const store = useStore();
const user = computed(() => store.getters["auth/user"]);
const userId = computed(() => user.value?.userId || null);
const route = useRoute();
const router = useRouter();
const productId = route.params.productId;
const product = computed(() => store.getters["products/product"]);
const inBag = computed(() => store.getters["cart/cartStatus"]);
const loading = computed(() => store.getters["cart/loading"]);
const permission = computed(() => store.getters["cart/permission"]);

onMounted(async () => {
  await store.dispatch("auth/checkAuth");
  await store.dispatch("cart/checkPermission");
  if (permission.value) {
    store.dispatch("products/fetchProductById", productId);
    store.dispatch("cart/fetchCartStatus", { userId: userId.value, productId });
  } else {
    setTimeout(() => {
      toast.error("Access denied");
    }, 100);
    router.push("/signIn");
  }
});

const handleCartAction = async () => {
  if (inBag.value) {
    await store.dispatch("cart/removeFromCart", {
      userId: userId.value,
      productId,
    });
  } else {
    await store.dispatch("cart/addToCart", {
      userId: userId.value,
      productId,
    });
  }
};
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
              class="btn btn-remove-bag btn-danger"
              v-if="inBag"
              @click="handleCartAction"
            >
              Remove
            </button>
            <button
              class="btn btn-add-bag btn-success"
              v-else
              @click="handleCartAction"
            >
              Add to Bag
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
  <LoadingSpinner v-if="loading" />
</template>
