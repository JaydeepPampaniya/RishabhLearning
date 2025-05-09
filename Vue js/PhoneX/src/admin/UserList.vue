<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";
import { useToast } from "vue-toastification";
import cookies from "js-cookie";
import LoadingSpinner from "../components/LoadingSpinner.vue";
import { FcBusinessman, FcBusinesswoman, FcDecision } from "vue3-icons/fc";

const toast = useToast();
const users = ref([]);
const loading = ref(false);
const page = ref(0);
const totalPages = ref(0);
const router = useRouter();

// Get Admin Token
const adminCookie = cookies.get("AdminId");
const token = adminCookie ? JSON.parse(adminCookie).token : null;

const fetchUsers = async () => {
  if (!token) {
    router.push("/adminLogin");
    return;
  }

  try {
    loading.value = true;
    const response = await axios.get(
      `http://localhost:8080/customer/getAllCustomer?page=${page.value}&size=5`,
      {
        headers: { Authorization: `Bearer ${token}` },
      }
    );
    users.value = response.data.content || [];
    totalPages.value = response.data.totalPages || 0;
  } catch (error) {
    if (error.response?.status === 401) {
      toast.error("Session timeout, please log in again.");
      cookies.remove("AdminId");
      router.push("/adminLogin");
    } else {
      toast.error(error.response?.data || error.message);
    }
  } finally {
    loading.value = false;
  }
};

// DELETE User API Implementation
const deleteUser = async (id) => {
  try {
    loading.value = true;
    const response = await axios.delete(
      `http://localhost:8080/customer/delete/${id}`,
      {
        headers: { Authorization: `Bearer ${token}` },
      }
    );

    if (response.status === 200) {
      toast.success(response.data);
      fetchUsers(); // Refresh user list
    } else {
      toast.error(response.data.message || "Failed to delete user.");
    }
  } catch (error) {
    if (error.response?.status) {
      toast.error(error.response.data || "Error deleting user.");
    } else {
      toast.error(error.message);
    }
  } finally {
    loading.value = false;
  }
};

// Fetch users when component is mounted
onMounted(fetchUsers);
</script>

<template>
  <div>
    <h1 class="text-center py-1 cl mt-5">User List</h1>
    <table class="table container text-center">
      <thead>
        <tr>
          <th>ID</th>
          <th>Profile</th>
          <th>Name</th>
          <th>Email</th>
          <th>Gender</th>
          <th>Created At</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(user, i) in users" :key="user.id">
          <td>{{ i + 1 + page * 5 }}</td>
          <td class="fs-4 text-success">
            <component
              :is="
                user.gender === 'Male'
                  ? FcBusinessman
                  : user.gender === 'Female'
                  ? FcBusinesswoman
                  : FcDecision
              "
            />
          </td>
          <td>{{ user.username }}</td>
          <td>{{ user.user?.email || "N/A" }}</td>
          <td>{{ user.gender }}</td>
          <td>{{ new Date(user.user?.createdTime).toLocaleString() }}</td>
          <td>
            <button
              type="button"
              class="btn btn-danger"
              @click="deleteUser(user.user?.id)"
            >
              DELETE
            </button>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- Pagination Controls -->
    <div class="d-flex justify-content-center my-3">
      <button
        class="btn btn-primary mx-2"
        @click="
          page = Math.max(page - 1, 0);
          fetchUsers();
        "
        :disabled="page === 0"
      >
        Previous
      </button>
      <span class="mx-2">Page {{ page + 1 }} of {{ totalPages }}</span>
      <button
        class="btn btn-primary mx-2"
        @click="
          page++;
          fetchUsers();
        "
        :disabled="page + 1 >= totalPages"
      >
        Next
      </button>
    </div>

    <LoadingSpinner v-if="loading" />
  </div>
</template>

<style scoped>
/* Add custom styles if needed */
</style>
