import React, { useEffect } from "react";
import cookies from "js-cookie";
import axios from "axios";
import { useState } from "react";
import { FcDecision } from "react-icons/fc";
import manavtar from "../../public/assets/background/manavtar.png";
import womenavtar from "../../public/assets/background/womenavtar.jpg";
import { toast } from "react-toastify";
import { toastStyle } from "../Constant/general";
import LoadingSpinner from "./LoadingSpinner";

const EditUser = () => {
  const [oldPassword, setOldPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [confirmNewPassword, setConfirmNewPassword] = useState("");
  const [loading, setLoading] = useState(false);
  const userCookie = cookies.get("UserCookie");
  const token = userCookie ? JSON.parse(userCookie).token : null;
  const id = userCookie?JSON.parse(userCookie).id:null;
  const gender = userCookie?JSON.parse(userCookie).gender:null;

  

  const update = async () => {
    if (oldPassword && newPassword && confirmNewPassword) {
      const data = { id, oldPassword, newPassword };
      try {
        setLoading(true);
        const response = await axios.patch(
          "http://localhost:8080/customer/changePassword",
          data,{
            headers: {
              Authorization: `Bearer ${token}`,
            }
          }
        );
        if (response.status === 200) {
          toast.success(response.data, toastStyle);
        } else {
          toast.error(response.data.message, toastStyle);
        }
      } catch (error) {
        if(error.response){
          toast.error(error.response.data||error.message , toastStyle);
        }else{
          toast.error(error.message, toastStyle);
        }
      } finally {
        setLoading(false);
      }
    } else {
      toast.error("Please fill all the Details", toastStyle);
    }
  };

  return (
    <>
      <div class="card text-center container my-5">
        <div class="card-header">Edit User Detail</div>
        <div class="card-body">
          <p class="card-text">Change Account Details</p>
          <p style={{ width: "", fontSize: "3rem" }}>
            {gender === "Male" ? (
              <img
                src={manavtar}
                style={{ width: "10rem", borderRadius: "50%" }}
                class="card-img-top"
                alt="avtar"
              />
            ) : gender === "Female" ? (
              <img
                src={womenavtar}
                style={{ width: "10rem", borderRadius: "50%" }}
                class="card-img-top"
                alt="avtar"
              />
            ) : (
              <FcDecision />
            )}
          </p>
          <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">
              Old Password
            </span>
            <input
              type="password"
              class="form-control"
              placeholder={`Old Password`}
              value={oldPassword}
              onChange={(e) => setOldPassword(e.target.value)}
              aria-label="Username"
              aria-describedby="basic-addon1"
            />
          </div>
          <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">
              New Password
            </span>
            <input
              type="password"
              class="form-control"
              placeholder={`New Password`}
              value={newPassword}
              onChange={(e) => setNewPassword(e.target.value)}
              aria-label="Username"
              aria-describedby="basic-addon1"
            />
          </div>
          <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">
              Confirm Password
            </span>
            <input
              type="password"
              class="form-control"
              placeholder={`Confirm New Password`}
              value={confirmNewPassword}
              onChange={(e) => setConfirmNewPassword(e.target.value)}
              aria-label="Username"
              aria-describedby="basic-addon1"
            />
          </div>
          <button
            className="btn btn-info w-100 text-white"
            type="button"
            onClick={update}
          >
            Update
          </button>
        </div>
      </div>
      {loading && <LoadingSpinner />}
    </>
  );
};

export default EditUser;
