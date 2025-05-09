import axios from "axios";
import cookies from "js-cookie";
import store from "../store/store";
const API_BASE_URL = "http://localhost:8080";

const apiClient = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

export const registerCustomer = async (userData) => {
  try {
    const response = await apiClient.post("/customer/register", userData);
    return response;
  } catch (error) {
    throw error.response ? error.response.data : error;
  }
};

export const signInUser = async (credentials) => {
  try {
    const response = await apiClient.post("/user/login", credentials);
    return response;
  } catch (error) {
    throw error.response ? error.response.data : error;
  }
};

export const customerList = async (page = 0) => {
  const token = cookies.get("token");
  try {
    const response = await apiClient.get(
      `/customer/getAllCustomer?page=${page}&size=5`,

      {
        headers: { Authorization: `Bearer ${token}` },
      }
    );
    return response;
  } catch (error) {
    throw error;
  }
};
export const deleteCustomer = async (id) => {
  const token = cookies.get("token");
  try {
    const response = await apiClient.delete(
      `/customer/delete/${id}`,

      {
        headers: { Authorization: `Bearer ${token}` },
      }
    );
    return response;
  } catch (error) {
    throw error;
  }
};
