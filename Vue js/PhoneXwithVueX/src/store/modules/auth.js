import Cookies from "js-cookie";
import { jwtDecode } from "jwt-decode";
import { toast } from "vue3-toastify";
import { router } from "../../main";
import { customerList, deleteCustomer } from "../../services/apis";
import store from "../store";

const state = {
  isAuthenticated: false,
  user: Cookies.get("token") ? jwtDecode(Cookies.get("token")) : null,
  loading: false,
  totalPages: 0,
  customers: [],
};

const mutations = {
  SET_AUTH(state, user) {
    state.isAuthenticated = true;
    state.user = user;
  },
  SET_LOADING(state, status) {
    state.loading = status;
  },
  LOGOUT(state) {
    state.isAuthenticated = false;
    state.user = null;
  },
  SET_CUSTOMERS(state, { customers, totalPages }) {
    state.customers = customers;
    state.totalPages = totalPages;
  },
};

const actions = {
  async checkAuth({ commit, dispatch }) {
    const token = Cookies.get("token");
    if (token) {
      try {
        const user = jwtDecode(token);
        if (user.exp * 1000 < Date.now()) {
          setTimeout(() => {
            toast.error("User session expired, please login again");
          }, 100);
          await dispatch("logout");
        } else {
          commit("SET_AUTH", user);
        }
      } catch (error) {
        await dispatch("logout");
      }
    } else {
      setTimeout(() => {
        toast.error("User unauthenticated, please login again");
      }, 100);
      await dispatch("logout");
    }
  },
  async login({ commit }, token) {
    try {
      commit("SET_LOADING", true);
      const user = jwtDecode(token);
      Cookies.set("token", token, { secure: true, sameSite: "Strict" });
      commit("SET_AUTH", user);
    } catch (error) {
      console.error("Error decoding token:", error);
    } finally {
      commit("SET_LOADING", false);
    }
  },

  async customerList({ commit, dispatch }, page) {
    if (store.getters["auth/isAuthenticated"]) {
      try {
        commit("SET_LOADING", true);
        const response = await customerList(page);
        if (response.status === 200) {
          commit("SET_CUSTOMERS", {
            customers: response.data.content || [],
            totalPages: response.data.totalPages,
          });
        }
      } catch (error) {
        handleApiError(error, dispatch);
      } finally {
        commit("SET_LOADING", false);
      }
    }
  },
  async deleteCustomer({ commit, dispatch }, id) {
    if (store.getters["auth/isAuthenticated"]) {
      try {
        commit("SET_LOADING", true);
        const response = await deleteCustomer(id);
        if (response.status === 200) {
          toast.success(response.data);
          await dispatch("customerList", 1);
        }
      } catch (error) {
        handleApiError(error, dispatch);
      } finally {
        commit("SET_LOADING", false);
      }
    }
  },
  logout({ commit }) {
    Cookies.remove("token");
    commit("LOGOUT");
    router.push("/signIn");
  },
};

const getters = {
  isAuthenticated: (state) => state.isAuthenticated,
  user: (state) => state.user,
  loading: (state) => state.loading,
  customers: (state) => state.customers,
  totalPages: (state) => state.totalPages,
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
