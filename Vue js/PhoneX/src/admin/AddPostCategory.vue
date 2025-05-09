<template>
  <div class="container mt-5">
    <h1 class="text-center p-3">Post Category</h1>
    <hr />
    <div class="my-5">
      <div class="input-group mb-3">
        <span class="input-group-text">Add Category</span>
        <input
          type="text"
          class="form-control"
          placeholder="Enter Category Name"
          v-model="categoryName"
        />
      </div>
      <button class="btn btn-success w-100" @click="submit" :disabled="loading">
        {{ loading ? "Posting..." : "Post" }}
      </button>
    </div>

    <LoadingSpinner v-if="loading" />
  </div>
</template>

<script>
import { ref } from "vue";
import axios from "axios";
import { useToast } from "vue-toastification";
import LoadingSpinner from "../components/LoadingSpinner.vue";
import Cookies from "js-cookie";

export default {
  components: {
    LoadingSpinner,
  },
  setup() {
    const categoryName = ref(""); // Equivalent to useState("")
    const loading = ref(false);
    const toast = useToast(); // Vue Toastification
    const token = JSON.parse(Cookies.get("AdminId"))?.token;

    const submit = async () => {
      if (!categoryName.value.trim()) {
        toast.error("Please enter a category name");
        return;
      }

      loading.value = true;

      try {
        const response = await axios.post(
          "http://localhost:8080/deviceType/addDeviceType",
          { name: categoryName.value },
          {
            headers: { Authorization: `Bearer ${token}` },
          }
        );

        if (response.status === 202) {
          toast.success("Category added successfully!");
          categoryName.value = ""; // Clear input after success
        } else {
          toast.error(response.data.message || "Failed to add category");
        }
      } catch (error) {
        toast.error(error.response.data || "An error occurred");
      } finally {
        loading.value = false;
      }
    };

    return { categoryName, loading, submit };
  },
};
</script>

<style scoped>
.container {
  max-width: 500px;
  margin: auto;
}
</style>
