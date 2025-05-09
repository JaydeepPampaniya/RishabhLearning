import axios from "axios";
import Cookies from "js-cookie";
import store from "../store/store";
const API_BASE_URL = "http://localhost:8080";

const apiClient = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

export const fetchProducts = async (page, size, deviceType) => {
  const token = Cookies.get("token");
  try {
    const response = await apiClient.get(
      `/products/getProducts?deviceType=${deviceType}&page=${page}&size=${size}`,
      {
        headers: { Authorization: `Bearer ${token}` },
      }
    );
    return response;
  } catch (error) {
    throw error;
  }
};

export const fetchProductById = async (productId) => {
  const token = Cookies.get("token");
  try {
    const response = await apiClient.get(`/products/${productId}/getProduct`, {
      headers: { Authorization: `Bearer ${token}` },
    });
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const addProduct = async (formData) => {
  const token = Cookies.get("token");
  if (store.getters["auth/isAuthenticated"]) {
    try {
      const response = await axios.post(
        "http://localhost:8080/products/addProduct",
        formData,
        {
          headers: { Authorization: `Bearer ${token}` },
        }
      );
      return response;
    } catch (error) {
      throw error;
    }
  }
};
export const updateProduct = async ({ productId, formData }) => {
  const token = Cookies.get("token");
  try {
    const response = await axios.patch(
      `http://localhost:8080/products/${productId}/updateProduct`,
      formData,
      {
        headers: { Authorization: `Bearer ${token}` },
      }
    );
    return response;
  } catch (error) {
    throw error;
  }
};

export const deleteProduct = async (productId) => {
  const token = Cookies.get("token");
  try {
    const response = await apiClient.delete(`/products/${productId}/delete`, {
      headers: { Authorization: `Bearer ${token}` },
    });
    return response;
  } catch (error) {
    throw error;
  }
};
