<script setup>
import { ref, watch, onMounted, computed } from "vue";
import { toast } from "vue3-toastify";
import LoadingSpinner from "../components/LoadingSpinner.vue";
import { useStore } from "vuex";
import { useRoute } from "vue-router";
import Cookies from "js-cookie";

const loading = computed(() => store.getters["products/loading"]);
const store = useStore();
const productStore = computed(() => store.getters["products/product"]);
const deviceTypes = computed(() => store.getters["deviceType/deviceTypes"]);
const route = useRoute();
const productId = route.params.productId;
const totalPages = computed(() => store.getters["deviceType/totalPages"]);
const page = ref(0);
onMounted(async () => {
  await store.dispatch("auth/checkAuth");
  await store.dispatch("products/fetchProductById", productId);
  await store.dispatch("deviceType/getDeviceTypes", page.value);
});
watch(page, async () => {
  await store.dispatch("deviceType/getDeviceTypes", page.value);
});
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
const prefilledProduct = computed(() => ({
  companyName: product.value.companyName || productStore.value?.companyName,
  deviceName: product.value.deviceName || productStore.value?.deviceName,
  originalPrice:
    product.value.originalPrice || productStore.value?.originalPrice,
  currentPrice: product.value.currentPrice || productStore.value?.currentPrice,
  discount: product.value.discount || productStore.value?.discount,
  description: product.value.description || productStore.value?.description,
  deviceTypeId:
    product.value.deviceTypeId || productStore.value?.deviceType?.id,
}));

const handleImageChange = (e) => {
  image.value = e.target.files[0];
};
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
  await store.dispatch("products/updateProduct", {
    formData: formData,
    productId: productId,
  });
};
</script>
<template>
  <div class="container" v-if="productId">
    <h1 class="mt-5 mb-3 text-center">Update {{ productStore?.deviceName }}</h1>
    <div class="input-group mb-3">
      <span class="input-group-text">Image</span>
      <input class="form-control" type="file" @change="handleImageChange" />
    </div>
    <div class="input-group mb-3">
      <span class="input-group-text">Company Name</span>
      <input
        type="text"
        class="form-control"
        v-model="product.companyName"
        :placeholder="productStore?.companyName || 'Company Name'"
      />
    </div>
    <div class="input-group mb-3">
      <span class="input-group-text">Device Name</span>
      <input
        type="text"
        class="form-control"
        v-model="product.deviceName"
        :placeholder="productStore?.deviceName || 'Device Name'"
      />
    </div>
    <div class="input-group mb-3">
      <span class="input-group-text">Original Price</span>
      <input
        type="text"
        class="form-control"
        v-model="product.originalPrice"
        :placeholder="productStore?.originalPrice || 'Original Price'"
      />
    </div>
    <div class="input-group mb-3">
      <span class="input-group-text">Current Price</span>
      <input
        type="text"
        class="form-control"
        v-model="product.currentPrice"
        :placeholder="productStore?.currentPrice || 'Current Price'"
      />
    </div>
    <div class="input-group mb-3">
      <span class="input-group-text">Discount</span>
      <input
        type="text"
        class="form-control"
        v-model="product.discount"
        :placeholder="productStore?.discount || 'Discount %'"
      />
    </div>
    <div class="input-group mb-3">
      <span class="input-group-text">Description</span>
      <textarea
        class="form-control"
        v-model="product.description"
        :placeholder="productStore?.description || 'Product Description'"
      ></textarea>
    </div>

    <div class="input-group mb-3">
      <span class="input-group-text">Device Type</span>
      <select class="form-select" v-model="product.deviceTypeId">
        <option value="">Select Device Type</option>
        <option
          v-for="deviceType in deviceTypes"
          :key="deviceType.id"
          :value="deviceType.id"
        >
          {{ deviceType.name }}
        </option>
      </select>
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
    <button type="button" class="btn btn-secondary w-100 mb-5" @click="submit">
      Submit
    </button>
  </div>

  <LoadingSpinner v-if="loading" />
</template>
