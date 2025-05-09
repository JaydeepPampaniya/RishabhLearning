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

export const fetchCartStatus = async (userId, productId) => {
  const token = Cookies.get("token");
  try {
    const response = await apiClient.get(
      `/cart/getCartProduct/${userId}/${productId}`,
      {
        headers: { Authorization: `Bearer ${token}` },
      }
    );
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const addToCart = async (userId, productId) => {
  const token = Cookies.get("token");
  const data = { productId, userId, quantity: 1 };
  try {
    const response = await apiClient.post(`/cart/addToCart`, data, {
      headers: { Authorization: `Bearer ${token}` },
    });
    return response;
  } catch (error) {
    throw error;
  }
};

export const removeFromCart = async (userId, productId) => {
  const token = Cookies.get("token");
  try {
    const response = await apiClient.delete(
      `/cart/delete/${productId}/${userId}`,
      {
        headers: { Authorization: `Bearer ${token}` },
      }
    );
    return response;
  } catch (error) {
    throw error;
  }
};

export const fetchCartProducts = async (userId) => {
  const token = Cookies.get("token");
  try {
    const response = await apiClient.get(`/cart/getCartProducts/${userId}`, {
      headers: { Authorization: `Bearer ${token}` },
    });
    return response;
  } catch (error) {
    throw error;
  }
};
