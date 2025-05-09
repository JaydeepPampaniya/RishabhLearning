<script setup>
import { useStore } from "vuex";
import { computed, defineEmits, ref } from "vue";
import { useRouter } from "vue-router";

const store = useStore();
const searchValue = ref("");
const emit = defineEmits(["closePopup"]);
const router = useRouter();
const products = computed(() => store.getters["products/products"]);

const filteredProducts = computed(() => {
  if (!searchValue.value.trim()) return [];
  return products.value.filter((product) =>
    product.deviceName.toLowerCase().includes(searchValue.value.toLowerCase())
  );
});
const handleNavigate = (id) => {
  navigate(`/productDetailApi/${id}`);
  emit("closePopup");
};
const navigate = (path) => {
  router.replace(path);
};
</script>

<template>
  <div class="search-popup animate__animated animate__fadeIn">
    <div class="search-box">
      <div class="search-header">
        <strong class="title text-light">Search Products</strong>
        <button
          class="btn-close"
          aria-label="Close"
          @click="emit('closePopup')"
        ></button>
      </div>

      <div class="input-group">
        <span class="input-group-text">@</span>
        <input
          type="text"
          class="form-control"
          placeholder="Search products..."
          v-model="searchValue"
        />
      </div>

      <ul v-if="filteredProducts.length" class="search-results">
        <li
          v-for="product in filteredProducts"
          :key="product.id"
          @click="handleNavigate(product.id)"
        >
          <img
            :src="`data:image/jpeg;base64,${product.imageData}`"
            alt="Product Image"
            class="product-img"
          />
          <div class="product-info">
            <strong>{{ product.deviceName }}</strong>
            <p>₹{{ product.currentPrice }}</p>
          </div>
        </li>
      </ul>

      <p v-if="searchValue && !filteredProducts.length" class="no-results">
        No products found.
      </p>
    </div>
  </div>
</template>

<style scoped>
.search-popup {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background: rgba(99, 99, 99, 0.509);
  box-shadow: 0 10px 40px rgba(0, 0, 0, 0.2);
  border-radius: 12px;
  padding: 20px;
  z-index: 1000;
  width: 350px;
  backdrop-filter: blur(3px);
}
.search-box {
  text-align: center;
}

.search-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.title {
  font-size: 18px;
  font-weight: bold;
}

.input-group {
  display: flex;
  border: 1px solid #ddd;
  border-radius: 8px;
  overflow: hidden;
  margin-top: 10px;
}

.input-group-text {
  background: #eee;
  padding: 8px 12px;
}

.form-control {
  flex: 1;
  border: none;
  padding: 8px;
  font-size: 14px;
}

.search-results {
  margin-top: 15px;
  max-height: 250px;
  overflow-y: auto;
  list-style: none;
  padding: 0;
}

.search-results li {
  display: flex;
  align-items: center;
  gap: 12px;
  border-radius: 6px;
  transition: background 0.2s ease-in-out;
  cursor: pointer;
}

.search-results li:hover {
  background: #f1f1f1;
}

.product-img {
  width: 40px;
  height: 60px;
  border-radius: 6px;
  /* object-fit: cover; */
}

.no-results {
  color: #999;
  font-size: 14px;
  margin-top: 15px;
}
</style>
