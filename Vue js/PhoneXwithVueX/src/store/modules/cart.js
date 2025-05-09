import {
  fetchCartStatus,
  addToCart,
  removeFromCart,
  fetchCartProducts,
} from "../../services/cartApis";
import { toast } from "vue3-toastify";
import store from "../store";

const state = {
  cartStatus: false,
  loading: false,
  cartProducts: [],
  permission: false,
};

const mutations = {
  SET_CART_STATUS(state, status) {
    state.cartStatus = status;
  },
  SET_LOADING(state, status) {
    state.loading = status;
  },
  SET_PRODUCTS(state, products) {
    state.cartProducts = products;
  },
  SET_PERMISSION(state, permissions) {
    state.permission = permissions.includes("WRITE_CART");
  },
};

const actions = {
  async fetchCartStatus({ commit, dispatch }, { userId, productId }) {
    if (store.getters["auth/isAuthenticated"]) {
      try {
        commit("SET_LOADING", true);
        const status = await fetchCartStatus(userId, productId);
        commit("SET_CART_STATUS", status);
      } catch (error) {
        console.error("fetchCartStatus error:", error);
        handleApiError(error, dispatch);
      } finally {
        commit("SET_LOADING", false);
      }
    }
  },

  async fetchCartProducts({ commit, dispatch }, userId) {
    if (store.getters["auth/isAuthenticated"]) {
      try {
        commit("SET_LOADING", true);
        const response = await fetchCartProducts(userId);
        if (response?.status === 200) {
          commit("SET_PRODUCTS", response.data);
        }
      } catch (error) {
        console.error("fetchCartProducts error:", error);
        handleApiError(error, dispatch);
      } finally {
        commit("SET_LOADING", false);
      }
    }
  },

  async addToCart({ commit, dispatch }, { userId, productId }) {
    if (store.getters["auth/isAuthenticated"]) {
      try {
        commit("SET_LOADING", true);
        const response = await addToCart(userId, productId);
        if (response?.status === 202) {
          toast.success(response.data);
          commit("SET_CART_STATUS", true);
        }
      } catch (error) {
        console.error("addToCart error:", error);
        handleApiError(error, dispatch);
      } finally {
        commit("SET_LOADING", false);
      }
    }
  },

  async removeFromCart({ commit, dispatch }, { userId, productId }) {
    if (store.getters["auth/isAuthenticated"]) {
      try {
        commit("SET_LOADING", true);
        const response = await removeFromCart(userId, productId);
        if (response?.status === 200) {
          toast.success(response.data);
          commit("SET_CART_STATUS", false);
        }
      } catch (error) {
        console.error("removeFromCart error:", error);
        handleApiError(error, dispatch);
      } finally {
        commit("SET_LOADING", false);
      }
    }
  },

  async checkPermission({ commit, rootGetters }) {
    const user = rootGetters["auth/user"];
    commit("SET_PERMISSION", [...user.permissions]);
  },
};

const getters = {
  cartStatus: (state) => state.cartStatus,
  loading: (state) => state.loading,
  cartProducts: (state) => state.cartProducts,
  permission: (state) => state.permission,
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
