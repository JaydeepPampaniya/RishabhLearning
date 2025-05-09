import {
  addDeviceType,
  getDeviceTypes,
  deleteDeviceType,
} from "../../services/deviceTypeApis";
import { toast } from "vue3-toastify";
import store from "../store";

const state = {
  deviceTypes: [],
  loading: false,
  totalPages: 0,
};

const mutations = {
  SET_LOADING(state, status) {
    state.loading = status;
  },
  SET_DEVICE_TYPES(state, { deviceTypes, totalPages }) {
    state.deviceTypes = deviceTypes;
    state.totalPages = totalPages;
  },
};

const actions = {
  async addDeviceType({ dispatch, commit }, credentials) {
    if (store.getters["auth/isAuthenticated"]) {
      try {
        commit("SET_LOADING", true);
        const response = await addDeviceType(credentials);
        if (response?.status === 202) {
          toast.success(response.data);
        }
      } catch (error) {
        console.error("fetchCartProducts error:", error);
        handleApiError(error, dispatch);
      } finally {
        commit("SET_LOADING", false);
      }
    }
  },
  async getDeviceTypes({ dispatch, commit }, page) {
    if (store.getters["auth/isAuthenticated"]) {
      try {
        commit("SET_LOADING", true);
        const response = await getDeviceTypes(page);

        if (response?.status === 200) {
          commit("SET_DEVICE_TYPES", {
            deviceTypes: response.data.content || [],
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
  async deleteDeviceType({ dispatch, commit }, id) {
    if (store.getters["auth/isAuthenticated"]) {
      try {
        commit("SET_LOADING", true);
        const response = await deleteDeviceType(id);
        if (response?.status === 200) {
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
  loading: (state) => state.loading,
  deviceTypes: (state) => state.deviceTypes,
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
