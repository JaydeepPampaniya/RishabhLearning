import { FaSearch } from "react-icons/fa";
import { FaShoppingCart } from "react-icons/fa";
import React, { useRef, useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { Toast } from "bootstrap";
import { FaListUl } from "react-icons/fa";
import { useNavigate } from "react-router-dom";
import Style from "../CSS/Navbar.module.css";
import { MdOutlineLogin } from "react-icons/md";
import Dropdown from "react-bootstrap/Dropdown";
import { BurgerArrow } from "react-burger-icons";
import cookies from "js-cookie";
import { FiUserCheck } from "react-icons/fi";
import { toast } from "react-toastify";
import { toastStyle } from "../Constant/general";
import UserDetails from "./UserDetails";
import LoadingSpinner from "./LoadingSpinner";

const Navbar = () => {
  const [loading, setLoading] = useState(false);
  const [seeUserDetails, setSeeUserDetails] = useState(false);
  const navigate = useNavigate();
  const [isClosed, setIsClosed] = useState(false);
  const userCookie = cookies.get("UserCookie");

  var id = null;

  if (userCookie) {
    try {
      var { id } = JSON.parse(userCookie);
    } catch (error) {
      toast.error("Error parsing cookie:", toastStyle);
    }
  }

  const handleOnClick = () => {
    if (id) {
      navigate("/Bag#top");
    } else {
      navigate("/SignIn#top");
    }
  };

  return (
    <>
      <nav className={`${Style.header} navbar navbar-expand-lg sticky-top`}>
        <div className="container">
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="offcanvas"
            data-bs-target="#offcanvasNavbar"
            aria-controls="offcanvasNavbar"
            aria-label="Toggle navigation"
          >
            <FaListUl
              className="navbar-toggler-icon my-toggler text-white"
              style={{ fontSize: "20px" }}
            />
          </button>
          <div
            className="offcanvas offcanvas-start text-center"
            style={{ width: "40%", backgroundColor: "rgba(4, 0, 10, 0.819)" }}
            tabindex="-1"
            id="offcanvasNavbar"
            aria-labelledby="offcanvasNavbarLabel"
          >
            <div className="offcanvas-header">
              <h5
                className="offcanvas-title text-white"
                id="offcanvasNavbarLabel"
              >
                Phone-<span className={Style.x}>X</span>
              </h5>
              <a
                type="button"
                className={`${Style.iconclose} btn-close`}
                data-bs-dismiss="offcanvas"
                aria-label="Close"
                onClick={() => setIsClosed(!isClosed)}
              >
                <BurgerArrow className={Style.close} isClosed={isClosed} />
              </a>
            </div>
            <div className={`offcanvas-body`}>
              <ul
                className={`${Style.navbarlist} navbar-nav me-auto mb-2 mb-lg-0`}
              >
                <li className="nav-item">
                  <a
                    className={`${Style.home} nav-link text-light`}
                    aria-current="page"
                    href="#"
                    onClick={() => {
                      navigate("/");
                    }}
                  >
                    HOME
                  </a>
                </li>
                <li className="nav-item">
                  <a
                    className={`${Style.about} nav-link text-light `}
                    href="#"
                    onClick={() => navigate("/About")}
                  >
                    {" "}
                    ABOUT{" "}
                  </a>
                </li>
              </ul>
            </div>
          </div>
          <a
            className={`${Style.logo} navbar-brand`}
            href="#"
            style={{ color: "white", fontWeight: "700" }}
          >
            Phone
            <span style={{ color: "red" }}>X</span>
          </a>
          <div
            className="btn-group"
            role="group"
            aria-label="Basic outlined example"
          >
            <form role="search">
              <button
                type="button"
                className={`${Style.search} btn px-1 position-relative`}
                id="toastButton"
                data-bs-dismiss="toast"
                aria-label="Close"
              >
                <FaSearch className={`text-white`} />
              </button>
              {id ? (
                <button
                  type="button"
                  className={`${Style.search} btn`}
                  onClick={() => setSeeUserDetails(true)}
                >
                  <FiUserCheck className={`text-white`} />
                </button>
              ) : (
                <button type="button" className="btn mx-0 px-0">
                  <Dropdown>
                    <Dropdown.Toggle
                      variant=""
                      id="dropdown-basic"
                      className={`${Style.signUp} text-white`}
                    >
                      <MdOutlineLogin />
                    </Dropdown.Toggle>
                    <Dropdown.Menu className="bg-secondary">
                      <Dropdown.Item
                        className="text-info fw-bolder"
                        onClick={() => navigate("/SignIn#top")}
                      >
                        Sign In
                      </Dropdown.Item>
                      <Dropdown.Item
                        className="text-denger fw-bolder"
                        onClick={() => navigate("/SignUp#top")}
                      >
                        Sign Up
                      </Dropdown.Item>
                    </Dropdown.Menu>
                  </Dropdown>
                </button>
              )}
              <button
                type="button"
                className={`${Style.cart} btn px-1`}
                onClick={handleOnClick}
              >
                <FaShoppingCart className={`text-white`} />
              </button>
            </form>
          </div>
        </div>
      </nav>
      {loading && <LoadingSpinner />}

      {seeUserDetails && (
        <UserDetails closePopup={() => setSeeUserDetails(false)} />
      )}
    </>
  );
};

export default Navbar;
