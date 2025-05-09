<script>
import { defineStore } from "pinia";
import { useToast } from "vue-toastification";
import Cookies from "js-cookie";
import jwtDecode from "jwt-decode";

export const useAuthStore = defineStore("auth", {
  state: () => ({
    isAuthenticated: false,
  }),
  actions: {
    checkAuth() {
      const toast = useToast();
      try {
        const token = Cookies.get("token");
        if (!token) {
          toast.error("No token found. Please log in again.");
          this.isAuthenticated = false;
          return;
        }
        const decodedToken = jwtDecode(token);
        const currentTime = Math.floor(Date.now() / 1000);
        if (decodedToken.exp > currentTime) {
          this.isAuthenticated = true;
        } else {
          toast.error("Session expired. Please log in again.");
          Cookies.remove("UserCookie");
          this.isAuthenticated = false;
        }
      } catch (error) {
        console.error("Error decoding token:", error);
        toast.error("Invalid token. Please log in again.");
        this.isAuthenticated = false;
      }
    },
  },
});
</script>
