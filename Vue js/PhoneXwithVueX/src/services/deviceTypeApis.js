import axios from "axios";
import Cookies from "js-cookie";
const API_BASE_URL = "http://localhost:8080";

const apiClient = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

export const addDeviceType = async (credentials) => {
  const token = Cookies.get("token");
  try {
    const response = await apiClient.post(
      "/deviceType/addDeviceType",
      credentials,
      {
        headers: { Authorization: `Bearer ${token}` },
      }
    );
    return response;
  } catch (error) {
    throw error;
  }
};
export const getDeviceTypes = async (page) => {
  const token = Cookies.get("token");
  try {
    const response = await apiClient.get(
      `/deviceType/getDeviceTypes?page=${page}&size=5`,
      {
        headers: { Authorization: `Bearer ${token}` },
      }
    );
    return response;
  } catch (error) {
    throw error;
  }
};
export const deleteDeviceType = async (id) => {
  const token = Cookies.get("token");
  try {
    const response = await apiClient.delete(
      `/deviceType/${id}/delete`,

      {
        headers: { Authorization: `Bearer ${token}` },
      }
    );
    return response;
  } catch (error) {
    throw error;
  }
};
