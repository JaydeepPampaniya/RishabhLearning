import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import { GrAddCircle } from "react-icons/gr";
import LoadingSpinner from "./LoadingSpinner";
import cookies from "js-cookie";
import { AiFillDelete } from "react-icons/ai";
import { toast } from "react-toastify";
import { toastStyle } from "../Constant/general";

function ProductDetailApi() {
  const { productId } = useParams();
  const navigate = useNavigate();

  const [product, setProduct] = useState({});
  const [loading, setLoading] = useState(false);
  const [inBag, setInBag] = useState(false);
  const userCookie = cookies.get("UserCookie");
  const token = userCookie ? JSON.parse(userCookie).token : null;
  const id = userCookie?JSON.parse(userCookie).id:null;
  
  useEffect(() => {
    if (!id || !token) {
      navigate("/SignIn");
    }
  }, [id, token, navigate]);

 
  useEffect(() => {
    if (!token || !id) {
      return navigate("/SignIn"); 
    }

    const fetchProduct = async () => {
      setLoading(true);
      try {
        const productResponse = await axios.get(
          `http://localhost:8080/products/${productId}/getProduct`,
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );

        if (productResponse.status === 200) {
          setProduct(productResponse.data);
        } else {
          toast.error(productResponse.data.message, toastStyle);
        }
      } catch (error) {
        if(error.response.status===401){
          cookies.remove("UserCookie");
          toast.error("Please login again,Session timeout",toastStyle)
          navigate("/SignIn");
        }else{
          toast.error(error.response?.data || error.message, toastStyle);
        }
      } finally {
        setLoading(false);
      }
    };

    fetchProduct();
  }, [productId, id, token]);

  const fetchCartStatus = async () => {
    setLoading(true);

    try {
      const response = await axios.get(
        `http://localhost:8080/cart/getCartProduct/${id}/${productId}`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      if (response.status === 200) {
        setInBag(response.data);
      } else {
        toast.error(response.data.message, toastStyle);
      }
    } catch (error) {
      if(error.response.status===401){
        cookies.remove("UserCookie");
        toast.error("Please login again,Session timeout",toastStyle)
        navigate("/SignIn");
      }else{
        toast.error(error.response?.data || error.message, toastStyle);
      }
    
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchCartStatus();
  }, [id, productId, token]);


  const addToBag = async () => {
    if (!id || !token) {
      return navigate("/SignIn");
    }

    setLoading(true);

    const data = { productId: product.id, userId: id, quantity: 1 };

    try {
      const response = await axios.post(
        "http://localhost:8080/cart/addToCart",
        data,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      if (response.status === 202) {
        fetchCartStatus();
        toast.success(response.data, toastStyle);
      }
    } catch (error) {
      toast.error(error.response?.data || error.message, toastStyle);
    } finally {
      setLoading(false);
    }
  };

  // Remove item from the cart
  const handleRemoveItem = async () => {
  
    setLoading(true);

    try {
      const response = await axios.delete(
        `http://localhost:8080/cart/delete/${productId}/${id}`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      if (response.status === 200) {
        fetchCartStatus();
        toast.success(response.data, toastStyle);
      }
    } catch (error) {
      toast.error(error.response?.data || error.message, toastStyle);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  return (
    <>
      <div className="container my-5" id="top">
        <div className="row">
          <div className="card mb-3">
            {product && (
              <div className="row g-0">
                <div className="col-md-4">
                  <img
                    src={`data:image/jpeg;base64,${product.imageData}`}
                    className="img-fluid rounded-start"
                    alt="Product"
                  />
                </div>
                <div className="col-md-8 my-5">
                  <div className="card-body">
                    <h5 className="card-title">{product.deviceName}</h5>
                    <div className="company-name">{product.companyName}</div>
                    <p className="card-text">{product.description}</p>
                  </div>
                  <div className="price text-center">
                    <span className="current-price">
                      Rs {product.currentPrice}
                    </span>
                    <span className="original-price">
                      Rs {product.originalPrice}
                    </span>
                    <span className="discount">({product.discount}% OFF)</span>
                  </div>
                  <div className="rating text-center">4.5 ⭐ | 30250</div>
                  {inBag ? (
                    <button
                      type="button"
                      className="btn btn-remove-bag btn-danger"
                      onClick={() => handleRemoveItem(product.id)}
                    >
                      <AiFillDelete /> Remove
                    </button>
                  ) : (
                    <button
                      type="button"
                      className="btn btn-add-bag btn-success"
                      onClick={addToBag}
                    >
                      <GrAddCircle /> Add to Bag
                    </button>
                  )}
                </div>
              </div>
            )}
          </div>
        </div>
      </div>
      {loading && <LoadingSpinner />}
    </>
  );
}

export default ProductDetailApi;
