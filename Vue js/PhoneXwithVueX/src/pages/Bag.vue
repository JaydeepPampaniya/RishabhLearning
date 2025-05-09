<script setup>
import { ref, onMounted, computed, watch } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import LoadingSpinner from "../components/LoadingSpinner.vue";
import EmptyBagItem from "../components/EmptyBagItem.vue";
import { toast } from "vue3-toastify";
const store = useStore();
const router = useRouter();

const user = computed(() => store.getters["auth/user"]);
const userId = computed(() => user.value?.userId || null);
const loading = computed(() => store.getters["cart/loading"]);
const cartProducts = computed(() => store.getters["cart/cartProducts"]);
const permission = computed(() => store.getters["cart/permission"]);
onMounted(async () => {
  await store.dispatch("auth/checkAuth");
  await store.dispatch("cart/checkPermission");
  if (permission.value) {
    store.dispatch("cart/fetchCartProducts", userId.value);
  } else {
    setTimeout(() => {
      toast.error("Access denied");
    }, 100);
    router.push("/signIn");
  }
});

const handleRemoveItem = async (productId) => {
  await store.dispatch("cart/removeFromCart", {
    userId: userId.value,
    productId,
  });
  store.dispatch("cart/fetchCartProducts", userId.value);
};

const totalMRP = computed(() =>
  cartProducts.value.reduce(
    (acc, item) => acc + (item.products?.originalPrice || 0),
    0
  )
);
const discount = computed(() =>
  cartProducts.value.reduce(
    (acc, item) =>
      acc +
      ((item.products?.originalPrice || 0) -
        (item.products?.currentPrice || 0)),
    0
  )
);
const totalAmount = computed(() => totalMRP.value - discount.value + 99);
</script>

<template>
  <div class="container mb-5" id="top">
    <div class="container mb-5">
      <div v-if="cartProducts?.length === 0">
        <EmptyBagItem />
      </div>
      <div v-else class="row">
        <div class="bag-items-container col-md-8 col-sm-12">
          <div
            v-for="item in cartProducts"
            :key="item.id"
            class="bag-item-container"
          >
            <div class="item-left-part">
              <img
                class="bag-item-img"
                :src="`data:image/jpeg;base64,${
                  item.products?.imageData || ''
                }`"
                :alt="item.products?.deviceName || 'Product Image'"
              />
            </div>
            <div class="item-right-part">
              <div class="company">
                {{ item.products?.companyName || "Unknown" }}
              </div>
              <div class="item-name">
                {{ item.products?.deviceName || "Product" }}
              </div>
              <div class="price-container">
                <span class="current-price"
                  >Rs {{ item.products?.currentPrice || 0 }}</span
                >
                <span class="original-price"
                  >Rs {{ item.products?.originalPrice || 0 }}</span
                >
                <span class="discount-percentage"
                  >({{ item.products?.discount || 0 }}% OFF)</span
                >
              </div>
            </div>
            <div
              class="remove-from-cart"
              @click="handleRemoveItem(item.products?.id)"
            >
              <span>🗑</span>
            </div>
          </div>
        </div>

        <div class="col-md-4 col-sm-12 pt-5">
          <div class="bag-summary">
            <div class="bag-details-container">
              <div class="price-header">
                PRICE DETAILS ({{ cartProducts?.length ?? 0 }} Items)
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
                  >₹{{ Math.round(discount) }}</span
                >
              </div>
              <div class="price-item">
                <span class="price-item-tag">Convenience Fee</span>
                <span class="price-item-value">₹99</span>
              </div>
              <hr />
              <div class="price-footer">
                <span class="price-item-tag">Total Amount</span>
                <span class="price-item-value"
                  >₹{{ Math.round(totalAmount) }}</span
                >
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
