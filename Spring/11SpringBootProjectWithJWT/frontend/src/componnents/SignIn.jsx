import React, { useContext, useState } from "react";
import Style from "../CSS/SignIn.module.css";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import cookies from "js-cookie";
import { toast, ToastContainer } from "react-toastify";
import { toastStyle } from "../Constant/general";
import LoadingSpinner from "./LoadingSpinner";
import { BagContext } from "../Context/ContextProvider";
import { jwtDecode } from "jwt-decode";

function SignIn() {
  const navigate = useNavigate("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);

  const log = async (e) => {
    e.preventDefault();
    if (email && password) {
      try {
        setLoading(true);
        const data = { email:email, password:password };
        const response = await axios.post("http://localhost:8080/user/login", data);
        if (response.status ===200) {
          const tokenDecoded =  jwtDecode(response.data.token);
          if(tokenDecoded.role ==="CUSTOMER"){
            cookies.set(
              "UserCookie",
              JSON.stringify({
                id: response.data.userId,
                gender:response.data.gender,
                token:response.data.token
              }),
              { secure: true, sameSite: "Strict" }
            );
            toast.success(response.data.message, toastStyle);
            setTimeout(() => {
              navigate("/");
            }, 1500);
          }else{
            toast.error("You are not a customer", toastStyle);
          }
        } else {
          toast.error(response.data.message, toastStyle);
        }
      } catch (error) {
        if(error.response?.status){
          toast.error(error.response.data , toastStyle);
        }else{
          toast.error(error.message, toastStyle);
        }
      } finally {
        setLoading(false);
      }
    } else {
      toast.error("Please fill all the fields", toastStyle);
    }
  };

  React.useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  return (
    <>
      <div className={`${Style.hero} p-5`} id="top">
        <div className="container py-5">
          <div className="col-md-6">
            <h2 className="text-center text-white"> Sign In</h2>
            <form className="row g-3 text-start mt-3">
              <div className="col-12">
                <input
                  type="email"
                  value={email}
                  onChange={(e) => {
                    setEmail(e.target.value);
                  }}
                  className="form-control"
                  id="inputEmail4"
                  placeholder="Email"
                />
              </div>
              <div className="col-12">
                <input
                  type="password"
                  value={password}
                  onChange={(e) => {
                    setPassword(e.target.value);
                  }}
                  className="form-control"
                  id="inputPassword4"
                  placeholder="Password"
                />
              </div>
              <div className="col-12">
                <button
                  type="submit"
                  className="btn btn-success col-12"
                  onClick={log}
                  id="btn"
                >
                  Sign In
                </button>
              </div>
              <div className="text-white">
                Don't Have an Account?{" "}
                <span
                  className={Style.signin}
                  onClick={() => navigate("/SignUp#top")}
                >
                  SignUp
                </span>
              </div>
            </form>
          </div>
        </div>
      </div>
      
      {loading && <LoadingSpinner />}
    </>
  );
}

export default SignIn;
