import cookies from "js-cookie";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import React, { useState, useEffect } from "react";
import { RiDeleteBin5Fill } from "react-icons/ri";
import LoadingSpinner from "./LoadingSpinner";
import { toast } from "react-toastify";
import { toastStyle } from "../Constant/general";
import EmptyBagItem from "./EmptyBagItem";

const Bag = () => {
  const [bagItems, setBagItems] = useState([]);
  const navigate = useNavigate();

  const [loading, setLoading] = useState(true);
  const [totalMRP, setTotalMRP] = useState(0);
  const [discount, setDiscount] = useState(0);
  const [time, setTime] = useState(new Date());
  const userCookie = cookies.get("UserCookie");
  const token = userCookie ? JSON.parse(userCookie).token : null;
  const id = userCookie ? JSON.parse(userCookie).id : null;

  useEffect(() => {
    const interval = setInterval(() => {
      const newTime = new Date();
      newTime.setDate(newTime.getDate() + 7);
      setTime(newTime);
    }, 10000);

    return () => {
      clearInterval(interval);
    };
  }, []);

  // Fetch cart products
  const fetchProducts = async () => {
    if (!id) {
      navigate("/SignIn");
      return;
    }

    try {
      setLoading(true);
      const response = await axios.get(
        `http://localhost:8080/cart/getCartProducts/${id}`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      if (response.status === 200) {
        if (response.data === "Cart is empty.") {
          setBagItems([]);
        } else {
          setBagItems(response.data);
          console.log(response.data);

          setDiscount(
            response.data.reduce(
              (acc, item) =>
                acc +
                (item.products.currentPrice - item.products.originalPrice),
              0
            )
          );
          setTotalMRP(
            response.data.reduce(
              (acc, item) => acc + parseInt(item.products.currentPrice),
              0
            )
          );
        }
      } else {
        toast.error(response.data.message, toastStyle);
      }
    } catch (error) {
      if (error.response.status === 401) {
        cookies.remove("UserCookie");
        toast.error("Please login again,Session timeout", toastStyle);
        navigate("/SignIn");
      } else {
        toast.error(error.response?.data || error.message, toastStyle);
      }
    } finally {
      setLoading(false);
    }
  };

  const handleRemoveItem = async (productId) => {
    try {
      setLoading(true);
      const response = await axios.delete(
        `http://localhost:8080/cart/delete/${productId}/${id}`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      if (response.status === 200) {
        fetchProducts();
        toast.success(response.data, toastStyle);
      } else {
        toast.error("Something went wrong", toastStyle);
      }
    } catch (error) {
      if (error.response.status === 401) {
        cookies.remove("UserCookie");
        toast.error("Please login again,Session timeout", toastStyle);
        navigate("/SignIn");
      } else {
        toast.error(error.response?.data || error.message, toastStyle);
      }
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchProducts();
  }, []);

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  return (
    <>
      <div className="container mb-5" id="top">
        <div className="container mb-5">
          {bagItems.length === 0 ? (
            <EmptyBagItem />
          ) : (
            <div className="row">
              <div className="bag-items-container col-md-8 col-sm-12">
                {bagItems.map((item) => (
                  <div className="bag-item-container" key={item.id}>
                    <div className="item-left-part">
                      <img
                        className="bag-item-img"
                        src={
                          `data:image/jpeg;base64,${item.products.imageData}` ||
                          "placeholder-image-url"
                        }
                        alt={item.products.deviceName}
                      />
                    </div>
                    <div className="item-right-part">
                      <div className="company">{item.products.companyName}</div>
                      <div className="item-name">
                        {item.products.deviceName}
                      </div>
                      <div className="price-container">
                        <span className="current-price">
                          Rs {item.products.currentPrice}
                        </span>
                        <span className="original-price">
                          Rs {item.products.originalPrice}
                        </span>
                        <span className="discount-percentage">
                          ({item.products.discount}% OFF)
                        </span>
                      </div>
                      <div className="return-period">
                        <span className="return-period-days">7 days</span>{" "}
                        return available
                      </div>
                      <div className="delivery-details">
                        Delivery by {time.toLocaleDateString()}
                      </div>
                    </div>
                    <div
                      className="remove-from-cart"
                      onClick={() => handleRemoveItem(item.products.id)}
                    >
                      <RiDeleteBin5Fill />
                    </div>
                  </div>
                ))}
              </div>
              <div className="col-md-4 col-sm-12 pt-5">
                <div className="bag-summary">
                  <div className="bag-details-container">
                    <div className="price-header">
                      PRICE DETAILS ({bagItems.length} Items)
                    </div>
                    <div className="price-item">
                      <span className="price-item-tag">Total MRP</span>
                      <span className="price-item-value">
                        ₹{Math.round(totalMRP)}
                      </span>
                    </div>
                    <div className="price-item">
                      <span className="price-item-tag">Discount on MRP</span>
                      <span className="price-item-value priceDetail-base-discount">
                        ₹{discount}
                      </span>
                    </div>
                    <div className="price-item">
                      <span className="price-item-tag">Convenience Fee</span>
                      <span className="price-item-value">₹99</span>
                    </div>
                    <hr />
                    <div className="price-footer">
                      <span className="price-item-tag">Total Amount</span>
                      <span className="price-item-value">
                        ₹{Math.round(totalMRP) + 99}
                      </span>
                    </div>
                  </div>
                  <button
                    className="btn-place-order"
                    onClick={() => navigate("/Delivery")}
                  >
                    PLACE ORDER
                  </button>
                </div>
              </div>
            </div>
          )}
        </div>
      </div>
      {loading && <LoadingSpinner />}
    </>
  );
};

export default Bag;
