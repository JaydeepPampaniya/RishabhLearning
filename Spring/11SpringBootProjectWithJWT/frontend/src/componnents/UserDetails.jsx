import React, { useContext } from "react";
import manavtar from "../../public/assets/background/manavtar.png";
import womenavtar from "../../public/assets/background/womenavtar.jpg";
import cookies from "js-cookie";
import Styles from "../CSS/UserDetails.module.css";
import { useNavigate } from "react-router-dom";
import { jwtDecode } from "jwt-decode";

const UserDetails = ({ closePopup }) => {
  const navigate = useNavigate();
  const { gender, token } = JSON.parse(cookies.get("UserCookie"));

  const decodedToken = jwtDecode(token);
  const userName = decodedToken.sub;
  const email = decodedToken.email;
  const handleLogout = () => {
    cookies.remove("UserCookie");
    navigate("/");
    closePopup();
  };

  return (
    <div className={`${Styles.userMain} animate__animated animate__fadeInUp`}>
      <div className="d-flex justify-content-between align-items-center mb-3">
        <strong className="fs-4">User Profile</strong>
        <button
          type="button"
          className="btn-close"
          aria-label="Close"
          onClick={closePopup}
        ></button>
      </div>
      <div className="card mb-3 p-3">
        <div className="text-center">
          <p>
            {gender === "Male" ? (
              <img
                src={manavtar}
                style={{ width: "6rem", borderRadius: "50px" }}
                alt="avatar"
              />
            ) : user.gender === "Female" ? (
              <img
                src={womenavtar}
                style={{ width: "6rem", borderRadius: "50px" }}
                alt="avatar"
              />
            ) : (
              <FcDecision style={{ fontSize: "6rem" }} />
            )}
          </p>
          <p className="fs-5 mb-1">{userName}</p>
          <p className="text-secondary">{email}</p>
          <button
            type="button"
            className="btn btn-danger mt-4 me-2"
            onClick={handleLogout}
          >
            Log out
          </button>
          <button
            type="button"
            className="btn btn-success mt-4 me-2"
            onClick={() => {
              navigate("/Captcha#top");
            }}
          >
            Edit User Detail
          </button>
        </div>
      </div>
    </div>
  );
};

export default UserDetails;
