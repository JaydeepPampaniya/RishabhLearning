<script setup>
import { computed, onMounted, watch, ref } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import LoadingSpinner from "../components/LoadingSpinner.vue";

const store = useStore();
const router = useRouter();
const page = ref(0);
const size = ref(6);
const products = computed(() => store.getters["products/products"]);
const totalPages = computed(() => store.getters["products/totalPages"]);
const loading = computed(() => store.getters["products/loading"]);
const deviceTypes = computed(() => store.getters["deviceType/deviceTypes"]);
const deviceType = ref("");
const handleSort = (name) => {
  deviceType.value = name;
  page.value = 0;
};
const fetchProducts = async () => {
  await store.dispatch("products/fetchProducts", {
    page: page.value,
    size: size.value,
    deviceType: deviceType.value,
  });
};

watch([page, deviceType], fetchProducts);
onMounted(async () => {
  await store.dispatch("auth/checkAuth");
  await store.dispatch("deviceType/getDeviceTypes");
  fetchProducts();
});
</script>

<template>
  <div class="background">
    <div class="container text-center">
      <div class="sale row">
        <div class="col-md-6 col-sm-12">
          <h1>
            <span style="color: rgba(110, 57, 57, 0.771)">
              <span style="color: black"> UP TO </span> 40%
            </span>
            OFF
          </h1>
          <p>On Every Latest iPhone available in our mobile store.</p>
          <button class="btn btn-light mx-2" @click="router.push('/Terms#top')">
            READ MORE
          </button>
          <button class="btn btn-light mx-2">
            <a href="#read" style="text-decoration: none; color: black">
              SHOP NOW
            </a>
          </button>
        </div>
        <div class="col-md-6 col-sm-12">
          <div
            id="carouselExampleSlidesOnly"
            class="carousel slide"
            data-bs-ride="carousel"
            data-bs-interval="1800"
          >
            <div class="carousel-inner">
              <div class="carousel-item active">
                <div class="iphone text-center">
                  <img
                    src="../assets/DiscountBanners/iphone.png"
                    class="img-fluid"
                    alt="iPhone"
                  />
                </div>
              </div>
              <div class="carousel-item">
                <div class="iphone text-center">
                  <img
                    src="../assets/DiscountBanners/s24.png"
                    class="img-fluid"
                    alt="Samsung S24"
                  />
                </div>
              </div>
              <div class="carousel-item">
                <div class="iphone text-center">
                  <img
                    src="../assets/DiscountBanners/headphone-prod-1.webp"
                    class="img-fluid"
                    alt="Headphones"
                  />
                </div>
              </div>
              <div class="carousel-item">
                <div class="iphone text-center">
                  <img
                    src="../assets/DiscountBanners/earbuds-prod-5.webp"
                    class="img-fluid"
                    alt="Earbuds"
                  />
                </div>
              </div>
              <div class="carousel-item">
                <div class="iphone text-center">
                  <img
                    src="../assets/DiscountBanners/ps5.png"
                    class="img-fluid"
                    alt="PS5"
                  />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="container mt-5">
    <h3 class="popularbottom">POPULAR PRODUCTS</h3>
  </div>
  <div class="dropdown container">
    <button
      class="btn btn-secondary btn-large dropdown-toggle"
      type="button"
      data-bs-toggle="dropdown"
      aria-expanded="false"
    >
      Short by
    </button>
    <ul class="dropdown-menu">
      <li>
        <a class="dropdown-item" @click="deviceType = ''" value="">All</a>
      </li>
      <li v-for="device in deviceTypes" :key="device.id">
        <a class="dropdown-item" @click="handleSort(device.name)">{{
          device.name
        }}</a>
      </li>
    </ul>
  </div>
  <div class="items-container">
    <div v-for="product in products" :key="product.id" class="my-5">
      <div class="item-container">
        <router-link
          :to="`/productDetailApi/${product.id}`"
          style="text-decoration: none"
        >
          <div class="item-link">
            <img
              class="item-image"
              :src="`data:image/jpeg;base64,${product.imageData}`"
              alt="item image"
            />
            <div class="rating text-dark">4.5 ⭐ | 30250</div>
            <div class="company-name">{{ product.companyName }}</div>
            <div class="item-name">{{ product.deviceName }}</div>
            <div class="price">
              <span class="current-price">Rs {{ product.currentPrice }}</span>
              <span class="original-price">Rs {{ product.originalPrice }}</span>
              <span class="discount">({{ product.discount }}% OFF)</span>
            </div>
          </div>
          <button type="button" class="btn btn-remove-bag btn-info text-white">
            View Details
          </button>
        </router-link>
      </div>
    </div>
  </div>

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
</template>

<style scoped>
@import "../CSS/Home.module.css";
</style>
