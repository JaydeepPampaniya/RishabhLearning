<script setup>
import { ref, onMounted, watch } from "vue";
import { useRouter } from "vue-router";
import { useToast } from "vue-toastification";
import axios from "axios";
import Cookies from "js-cookie";

const products = ref([]);
const loading = ref(true);
const page = ref(0);
const totalPages = ref(0);
const router = useRouter();
const toast = useToast();

// Fetch user token
const userCookie = Cookies.get("UserCookie");
const token = userCookie ? JSON.parse(userCookie).token : null;

// Fetch Products from API
const fetchProducts = async () => {
  if (!token) {
    toast.error("You are not authorized. Please log in.");
    router.push("/SignIn");
    return;
  }

  try {
    loading.value = true;
    const response = await axios.get(
      `http://localhost:8080/products/getProducts?page=${page.value}&size=6`,
      {
        headers: { Authorization: `Bearer ${token}` },
      }
    );

    if (response.status === 200) {
      products.value = response.data.content || [];
      totalPages.value = response.data.totalPages;
    } else {
      toast.error("Product can't be found");
    }
  } catch (error) {
    if (error.response?.status === 401) {
      toast.error("Session timed out, please log in again.");
      Cookies.remove("UserCookie");
      router.push("/SignIn#top");
    } else if (error.response) {
      toast.error(error.response.data);
    }
  } finally {
    loading.value = false;
  }
};

// Fetch products when page changes
watch(page, fetchProducts);
onMounted(fetchProducts);
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
    <h3 class="popularbottom" id="read">POPULAR PRODUCTS</h3>
  </div>

  <div class="items-container">
    <div v-for="product in products" :key="product.id" class="my-5">
      <div class="item-container">
        <router-link
          :to="`/ProductDetailApi/${product.id}`"
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
            <MdOutlineEventNote /> View Details
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
