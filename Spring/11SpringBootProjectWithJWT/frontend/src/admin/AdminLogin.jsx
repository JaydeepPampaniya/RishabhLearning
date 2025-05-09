import React, { useEffect } from "react";
import "../CSS/Admin.css";
import { useState } from "react";
import axios from "axios";
import { useLocation, useNavigate } from "react-router-dom";
import cookies from "js-cookie";
import { toast, ToastContainer } from "react-toastify";
import { toastStyle } from "../Constant/general";
import LoadingSpinner from "../componnents/LoadingSpinner";
import { jwtDecode } from "jwt-decode";

const AdminLogin = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);

  const log = async (e) => {
    e.preventDefault();
    if (!email || !password) {
      toast.error("Please fill all the fields", toastStyle);
      return;
    }
    const data = { email, password };
    setLoading(true);
    try {
      const response = await axios.post("http://localhost:8080/user/login", data);
      if (response.status === 200) {
        const tokenDecoded = jwtDecode(response.data.token);
        if (tokenDecoded.role === "ADMIN" || tokenDecoded.role === "OWNER") {
          cookies.set(
            "AdminId",
            JSON.stringify({
              id: response.data.userId,
              token: response.data.token,
            }),
            { secure: true, sameSite: "Strict" }
          );
          toast.success(response.data.message, toastStyle);
          setTimeout(() => navigate("/Admin/AdminMiddle"), 1500);
        } else {
          toast.error("You are not admin or owner", toastStyle);
        }
      } else {
        toast.error(response.data.message, toastStyle);
      }
    } catch (error) {
      if (error.response) {
        toast.error(error.response.data || error.message, toastStyle);
      } else {
        toast.error(error.message, toastStyle);
      }
    } finally {
      setLoading(false);
    }
    
  };

  const location = useLocation();

  useEffect(() => {
    if (location.pathname === "/adminLogin") {
      cookies.remove("AdminId");
    }
  }, [location]);

  return (
    <>
      <div className="background" id="top">
        <div className="container" style={{ padding: "4.8rem 0rem" }}>
          <div className="admin">
            <div className="login">
              <h2 className="text-center text-white"> Sign In</h2>
              <form className="row g-3 text-start mt-3">
                <div>
                  <input
                    type="email"
                    value={email}
                    className="form-control"
                    id="inputEmail4"
                    onChange={(e) => setEmail(e.target.value)}
                    placeholder="Email"
                  />
                </div>
                <div>
                  <input
                    type="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    className="form-control"
                    id="inputPassword4"
                    placeholder="Password"
                  />
                </div>
                <div>
                  <button
                    type="submit"
                    className="btn btn-secondary col-12"
                    id="btn"
                    onClick={log}
                  >
                    Sign In
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
      {loading && <LoadingSpinner />}
    </>
  );
};

export default AdminLogin;
