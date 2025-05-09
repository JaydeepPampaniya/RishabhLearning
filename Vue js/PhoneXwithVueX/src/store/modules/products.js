import {
  fetchProducts,
  fetchProductById,
  addProduct,
  updateProduct,
  deleteProduct,
} from "../../services/productApis";
import { toast } from "vue3-toastify";
import store from "../store";

const state = {
  products: [],
  totalPages: 0,
  product: null,
  loading: false,
};

const mutations = {
  SET_PRODUCTS(state, { products, totalPages }) {
    state.products = products;
    state.totalPages = totalPages;
  },
  SET_PRODUCT(state, product) {
    state.product = product;
  },
  SET_LOADING(state, status) {
    state.loading = status;
  },
};

const actions = {
  async fetchProducts({ commit, dispatch }, { page, size, deviceType }) {
    if (store.getters["auth/isAuthenticated"])
      try {
        commit("SET_LOADING", true);
        const response = await fetchProducts(page, size, deviceType);
        commit("SET_PRODUCTS", {
          products: response.data.content || [],
          totalPages: response.data.totalPages,
        });
      } catch (error) {
        console.error("fetchProducts error:", error);
        handleApiError(error, dispatch);
      } finally {
        commit("SET_LOADING", false);
      }
  },

  async fetchProductById({ commit, dispatch }, productId) {
    if (store.getters["auth/isAuthenticated"]) {
      try {
        commit("SET_LOADING", true);
        const product = await fetchProductById(productId);
        commit("SET_PRODUCT", product);
      } catch (error) {
        console.error("fetchProductById error:", error);
        handleApiError(error, dispatch);
      } finally {
        commit("SET_LOADING", false);
      }
    }
  },

  async addProduct({ commit, dispatch }, formData) {
    if (store.getters["auth/isAuthenticated"]) {
      try {
        commit("SET_LOADING", true);
        const response = await addProduct(formData);
        if (response.status === 201) {
          toast.success(response.data);
        }
      } catch (error) {
        console.error("fetchProductById error:", error);
        handleApiError(error, dispatch);
      } finally {
        commit("SET_LOADING", false);
      }
    }
  },
  async updateProduct({ commit, dispatch }, { productId, formData }) {
    if (store.getters["auth/isAuthenticated"]) {
      try {
        commit("SET_LOADING", true);
        const response = await updateProduct({ productId, formData });
        if (response.status === 202) {
          toast.success(response.data);
        }
      } catch (error) {
        console.error("fetchProductById error:", error);
        handleApiError(error, dispatch);
      } finally {
        commit("SET_LOADING", false);
      }
    }
  },

  async deleteProduct({ commit, dispatch }, productId) {
    if (store.getters["auth/isAuthenticated"]) {
      try {
        commit("SET_LOADING", true);
        const response = await deleteProduct(productId);
        if (response.status === 202) {
          toast.success(response.data);
        }
      } catch (error) {
        handleApiError(error, dispatch);
      } finally {
        commit("SET_LOADING", false);
      }
    }
  },
};

const getters = {
  products: (state) => state.products,
  totalPages: (state) => state.totalPages,
  product: (state) => state.product,
  loading: (state) => state.loading,
};

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters,
};

const handleApiError = (error, dispatch) => {
  if (error.response) {
    const status = error.response.status;
    const message = error.response.data || "An error occurred";

    switch (status) {
      case 401:
        setTimeout(() => {
          toast.error("Session expired, please log in again.");
        }, 100);
        dispatch("auth/logout", null, { root: true });
        break;
      case 403:
        toast.error("You do not have permission to perform this action.");
        break;
      case 404:
        toast.error(message);
        break;
      case 500:
        toast.error("Internal server error. Please try again later.");
        break;
      default:
        toast.error(message);
    }
  } else if (error.request) {
    toast.error("No response from server. Check your network connection.");
  } else {
    toast.error("Something went wrong. Please try again.");
  }
};
