import React, { useRef, useState, useEffect } from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
import LoadingSpinner from "./LoadingSpinner";
import { MdOutlineEventNote } from "react-icons/md";
import { toast } from "react-toastify";
import { toastStyle } from "../Constant/general";
import Style from "../CSS/Home.module.css";
import cookies from "js-cookie";

const Home = () => {
  const [loading, setLoading] = useState(true);
  const [products, setProducts] = useState([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const navigate = useNavigate();

  const userCookie = cookies.get("UserCookie");
  const token = userCookie ? JSON.parse(userCookie).token : null;

  const fetchProducts = async () => {
    if (!token) {
      toast.error("You are not authorized. Please log in.", toastStyle);
      navigate("/SignIn");
      return;
    }

    try {
      setLoading(true);
      const response = await axios.get(
        `http://localhost:8080/products/getProducts?page=${page}&size=6`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      if (response.status === 200) {
        setProducts(response.data.content || []);
        setTotalPages(response.data.totalPages);
      } else {
        toast.error("Product can't be found", toastStyle);
      }
    } catch (error) {
      if (error.response.status === 401) {
        toast.error("session time out please login again", toastStyle);
        cookies.remove("UserCookie");
        navigate("/SignIn#top");
      } else if (error.response) {
        toast.error(error.response.data, toastStyle);
      }
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchProducts();
  }, [page]);

  React.useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  return (
    <>
      <div className={`${Style.background}`} id="top">
        <div className="container">
          <div className="text-center">
            <div className={`${Style.sale} row`}>
              <div className="col-md-6 col-sm-12">
                <h1>
                  <span style={{ color: "rgba(110, 57, 57, 0.771)" }}>
                    {" "}
                    <span style={{ color: "black" }}> UP TO </span> 40%
                  </span>{" "}
                  OFF
                </h1>
                <p>On Every Latest Iphones available in our mobile store.</p>
                <button
                  type="button"
                  className="btn btn-light mx-2"
                  onClick={() => navigate("/Term#top")}
                >
                  READ MORE
                </button>
                <button type="button" className="btn btn-light mx-2">
                  <a
                    href="#read"
                    style={{ textDecoration: "none", color: "black" }}
                  >
                    SHOP NOW
                  </a>
                </button>
              </div>
              <div className="col-md-6 col-sm-12">
                <div
                  id="carouselExampleSlidesOnly"
                  className="carousel slide"
                  data-bs-ride="carousel"
                  data-bs-interval="1800"
                >
                  <div className="carousel-inner">
                    <div className="carousel-item active">
                      <div className={`${Style.iphone} text-center`}>
                        <img
                          src="../DiscountBanners/iphone.png"
                          className="img-fluid"
                          alt="iPhone"
                        />
                      </div>
                    </div>
                    <div className="carousel-item">
                      <div className={`${Style.iphone} text-center`}>
                        <img
                          src="../DiscountBanners/s24.png"
                          className="img-fluid"
                          alt="Samsung S24"
                        />
                      </div>
                    </div>
                    <div className="carousel-item">
                      <div className={`${Style.iphone} text-center`}>
                        <img
                          src="../DiscountBanners/headphone-prod-1.webp"
                          className="img-fluid"
                          alt="Headphones"
                        />
                      </div>
                    </div>
                    <div className="carousel-item">
                      <div className={`${Style.iphone} text-center`}>
                        <img
                          src="../DiscountBanners/earbuds-prod-5.webp"
                          className="img-fluid"
                          alt="Earbuds"
                        />
                      </div>
                    </div>
                    <div className="carousel-item">
                      <div className={`${Style.iphone} text-center`}>
                        <img
                          src="../DiscountBanners/ps5.png"
                          className="img-fluid"
                          alt="PS5"
                        />
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className="container mt-5">
        <h3 className="popularbottom" id="read">
          POPULAR PRODUCTS
        </h3>
      </div>
      <div className="items-container">
        {products?.map((product) => (
          <div className="my-5" key={product.id}>
            <div className="item-container" key={product.id}>
              <Link
                to={`/ProductDetailApi/${product.id}`}
                style={{ textDecoration: "none" }}
              >
                <div className="item-link">
                  <img
                    className="item-image"
                    src={`data:image/jpeg;base64,${product.imageData}`}
                    alt="item image"
                  />
                  <div className="rating text-dark">4.5 ⭐ | 30250</div>
                  <div className="company-name">{product.companyName}</div>
                  <div className="item-name">{product.deviceName}</div>
                  <div className="price">
                    <span className="current-price">
                      Rs {product.currentPrice}
                    </span>
                    <span className="original-price">
                      Rs {product.originalPrice}
                    </span>
                    <span className="discount">({product.discount}% OFF)</span>
                  </div>
                </div>
                <button
                  type="button"
                  className="btn btn-remove-bag btn-info text-white"
                >
                  <MdOutlineEventNote /> View Details
                </button>
              </Link>
            </div>
          </div>
        ))}
      </div>

      <div className="d-flex justify-content-center my-3">
        <button
          className="btn btn-primary mx-2"
          onClick={() => setPage((prev) => Math.max(prev - 1, 0))}
          disabled={page === 0}
        >
          Previous
        </button>
        {[...Array(totalPages)].map((_, index) => (
          <button
            key={index}
            className="btn btn-primary mx-1"
            onClick={() => setPage(index)}
            disabled={page === index}
          >
            {index + 1}
          </button>
        ))}
        <button
          className="btn btn-primary mx-2"
          onClick={() => setPage((prev) => prev + 1)}
          disabled={page + 1 >= totalPages}
        >
          Next
        </button>
      </div>

      {loading && <LoadingSpinner />}
    </>
  );
};

export default Home;
