<script setup>
import { onMounted, ref, computed, watch } from "vue";
import { useStore } from "vuex";

const store = useStore();
const deviceTypes = computed(() => store.getters["deviceType/deviceTypes"]);
const totalPages = computed(() => store.getters["deviceType/totalPages"]);
const page = ref(0);
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
  await store.dispatch("products/addProduct", formData);
};
watch(page, async () => {
  await store.dispatch("deviceType/getDeviceTypes", page.value);
});
onMounted(async () => {
  await store.dispatch("auth/checkAuth");
  await store.dispatch("deviceType/getDeviceTypes", page.value);
});
</script>
<template>
  <div class="container">
    <h1 class="mt-5 mb-3 text-center">Add Product</h1>
    <div class="input-group mb-3">
      <span class="input-group-text">Image</span>
      <input type="file" class="form-control" @change="handleImageChange" />
    </div>
    <div v-for="(value, key) in product" :key="key" class="input-group mb-3">
      <span class="input-group-text">{{ key.replace(/([A-Z])/g, " $1") }}</span>
      <input type="text" class="form-control" v-model="product[key]" />
    </div>
    <div class="input-group mb-3">
      <span class="input-group-text">Device Type</span>
      <select v-model="product.deviceTypeId" class="form-select">
        <option value="">Select Device Type</option>
        <hr />
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
    <button class="btn btn-secondary w-100 mb-5" @click="submit">Submit</button>
  </div>
</template>
