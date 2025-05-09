import axios from "axios";
import React, { useEffect, useState } from "react";
import { toastStyle } from "../Constant/general";
import LoadingSpinner from "../componnents/LoadingSpinner";
import { toast } from "react-toastify";
import cookies from "js-cookie";

const UpdateItem = ({ getCategory }) => {
  const [productAll, setProductAll] = useState([]);
  const [loading, setLoading] = useState(false);
  const [productId, setProductId] = useState(null);
  const [oneProduct, setOneProduct] = useState(null);
  const { token } = JSON.parse(cookies.get("AdminId"));
  
  // State for Pagination
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(6); // Default size 6
  
  // Fetch All Products with Pagination
  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true);
        const response = await axios.get(
          `http://localhost:8080/products/getProducts?page=${page}&size=${size}`,
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );
        if (response.status === 200) {
          setProductAll(response.data.content); // Adjusted for Spring Pageable response
        }
      } catch (error) {
        toast.error(error.response?.data || error.message, toastStyle);
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, [page, size]);
  
  // Fetch Single Product
  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true);
        if (productId != null) {
          const response = await axios.get(
            `http://localhost:8080/products/${productId}/getProduct`,
            {
              headers: {
                Authorization: `Bearer ${token}`,
              },
            }
          );
          if (response.status === 200) {
            setOneProduct(response.data);
          }
        }
      } catch (error) {
        toast.error(error.response?.data || error.message, toastStyle);
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, [productId]);
  
  const [product, setProduct] = useState({
    companyName: "",
    deviceName: "",
    originalPrice: "",
    currentPrice: "",
    discount: "",
    description: "",
    deviceTypeId: "",
  });
  
  const [image, setImage] = useState(null);
  
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setProduct({ ...product, [name]: value });
  };

  const handleImageChange = (e) => {
    setImage(e.target.files[0]);
  };
  console.log(oneProduct);
  const submit = async (e) => {
    e.preventDefault();
    if (!image) {
      toast.error("Please upload an image", toastStyle);
      return;
    }

    const formData = new FormData();
    formData.append("imageFile", image);
   
      
    const updatedProduct = {
      companyName: product.companyName || oneProduct?.companyName,
      deviceName: product.deviceName || oneProduct?.deviceName,
      originalPrice: product.originalPrice || oneProduct?.originalPrice,
      currentPrice: product.currentPrice || oneProduct?.currentPrice,
      discount: product.discount || oneProduct?.discount,
      description: product.description || oneProduct?.description,
      deviceTypeId: product.deviceTypeId || oneProduct?.deviceType?.id,
    };

    formData.append(
      "updateProductDto",
      new Blob([JSON.stringify(updatedProduct)], { type: "application/json" })
    );

    try {
      setLoading(true);
      const result = await axios.patch(
        `http://localhost:8080/products/${productId}/updateProduct`,
        formData,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      if (result.status === 202) {
        toast.success(result.data, toastStyle);
      } else {
        toast.error("Something went wrong", toastStyle);
      }
    } catch (error) {
      toast.error(error.response?.data || error.message, toastStyle);
    } finally {
      setLoading(false);
    }
  };

  // Pagination Handlers
  const handleNextPage = () => setPage((prev) => prev + 1);
  const handlePreviousPage = () => setPage((prev) => (prev > 0 ? prev - 1 : 0));
  const handlePageSizeChange = (e) => setSize(e.target.value);

  return (
    <>
      <div className="input-group mb-3">
        <span className="input-group-text" id="basic-addon1">
          Select Product
        </span>
        <select
          className="form-select"
          aria-label="Default select example"
          onChange={(e) => setProductId(e.target.value)}
          name="productId"
        >
          <option value="select">Please Select Option</option>
          <hr />
          {productAll.map((item) => (
            <option key={item.id} value={item.id}>
              {item.deviceName}
            </option>
          ))}
        </select>
      </div>

      {/* Pagination Controls */}
      <div className="d-flex justify-content-between mb-3">
        <button
          className="btn btn-secondary"
          onClick={handlePreviousPage}
          disabled={page === 0}
        >
          Previous
        </button>
        <input
          type="number"
          min="1"
          className="form-control w-25"
          value={size}
          onChange={handlePageSizeChange}
          placeholder="Items per page"
        />
        <button className="btn btn-secondary" onClick={handleNextPage}>
          Next
        </button>
      </div>

      <div className="input-group mb-3">
        
        {/* Image upload input */}
        <span className="input-group-text" id="basic-addon1">
          Image
        </span>
        <input
          className="form-control"
          type="file"
          onChange={handleImageChange}
        />
      </div>

      {/* Product input fields */}
      <div className="input-group mb-3">
        <span className="input-group-text" id="basic-addon1">
          Company Name
        </span>
        <input
          type="text"
          className="form-control"
          placeholder={oneProduct?.companyName || "Company Name"}
          name="companyName"
          value={product.companyName}
          onChange={handleInputChange}
        />
      </div>

      <div className="input-group mb-3">
        <span className="input-group-text" id="basic-addon1">
          Device Name
        </span>
        <input
          type="text"
          className="form-control"
          placeholder={oneProduct?.deviceName || "Device Name"}
          name="deviceName"
          aria-describedby="basic-addon1"
          value={product.deviceName}
          onChange={handleInputChange}
        />
      </div>
      <div className="input-group mb-3">
        <span className="input-group-text" id="basic-addon1">
          Original Price
        </span>
        <input
          type="text"
          className="form-control"
          placeholder={`₹ ${oneProduct?.originalPrice || "Original Price"}`}
          name="originalPrice"
          aria-describedby="basic-addon1"
          value={product.originalPrice}
          onChange={handleInputChange}
        />
      </div>
      <div className="input-group mb-3">
        <span className="input-group-text" id="basic-addon1">
          Current Price
        </span>
        <input
          type="text"
          className="form-control"
          placeholder={`₹${oneProduct?.currentPrice || "Current Price"}`}
          name="currentPrice"
          aria-describedby="basic-addon1"
          value={product.currentPrice}
          onChange={handleInputChange}
        />
      </div>
      <div className="input-group mb-3">
        <span className="input-group-text" id="basic-addon1">
          Discount
        </span>
        <input
          type="text"
          className="form-control"
          placeholder={`%${oneProduct?.discount || "Discount"}`}
          name="discount"
          aria-describedby="basic-addon1"
          value={product.discount}
          onChange={handleInputChange}
        />
      </div>
      <div className="input-group  mb-3">
        <span className="input-group-text">Description</span>
        <textarea
          className="form-control"
          aria-label="With textarea"
          placeholder={oneProduct?.description}
          value={product.description}
          name="description"
          onChange={handleInputChange}
        ></textarea>
      </div>
      <div className="input-group mb-3">
        <span className="input-group-text" id="basic-addon1">
          Category
        </span>
        <select
          className="form-select"
          aria-label="Default select example"
          onChange={handleInputChange}
          name="deviceTypeId"
          value={product.deviceTypeId}
        >
          <option value="select">Select Category</option>
          <hr />
          {getCategory
            ? getCategory.map((item) => (
                <option key={item.id} value={item.id}>
                  {item.name}
                </option>
              ))
            : null}
        </select>
      </div>
      <button
        type="button"
        className="btn btn-secondary w-100 mb-5"
        onClick={submit}
      >
        Submit
      </button>
      {loading && <LoadingSpinner />}
    </>
  );
};

export default UpdateItem;
