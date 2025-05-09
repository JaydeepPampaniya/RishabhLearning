import React, { createContext, useState, useEffect } from "react";
import axios from "axios";
import cookies from "js-cookie"; // Make sure to import cookies if not already
import { useNavigate } from "react-router-dom";
import { toastStyle } from "../Constant/general";
import { toast } from "react-toastify";

export const BagContext = createContext();

const ContextProvider = ({ children }) => {
  const isAuthenticate = () => {
    const { token } = JSON.parse(cookies.get("token"));
    if (!token) {
      toast.error("No token found. Please log in again.", toastStyle);
      return false;
    }

    try {
      const decodedToken = jwtDecode(token);
      const currentTime = Math.round(Date.now() / 1000);
      if (decodedToken.exp > currentTime) {
        return true;
      } else {
        toast.error("Session expired. Please log in again.", toastStyle);
        cookies.remove("UserCookie");
        return false;
      }
    } catch (error) {
      console.error("Error decoding token:", error);
      toast.error("Invalid token. Please log in again.", toastStyle);
      return false;
    }
  };
  return (
    <BagContext.Provider value={{ isAuthenticate }}>
      {children}
    </BagContext.Provider>
  );
};

export default ContextProvider;
