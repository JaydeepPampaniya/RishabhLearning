import React from "react";
import { useState } from "react";
import axios from "axios";
import { useEffect } from "react";
import { toastStyle } from "../Constant/general";
import { toast, ToastContainer } from "react-toastify";
import LoadingSpinner from "../componnents/LoadingSpinner";
import UpdateItem from "./UpdateItem";
import cookies from "js-cookie";
const AddItem = () => {
  const [getCategory, setGetCategory] = useState([]);
  const [handleType, setHandleType] = useState(null);
  const [loading, setLoading] = useState(false);
  const [deviceId, setDeletedId] = useState(null);
  const { token } = JSON.parse(cookies.get("AdminId"));
  const [page, setPage] = useState(0); // Current page
  const [size, setSize] = useState(6); // Default page size
  const [totalPages, setTotalPages] = useState(0);
  const [productAll, setProductAll] = useState([]);
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
    // setProduct({...product, image: e.target.files[0]})
  };

  const submit = async (e) => {
    e.preventDefault();
    if (
      !product.companyName ||
      !product.deviceName ||
      !product.originalPrice ||
      !product.currentPrice ||
      !product.discount ||
      !product.description ||
      !product.deviceTypeId
    ) {
      toast.error("Please fill all the required fields", toastStyle);
      return;
    }

    const formData = new FormData();
    // Append the image file
    formData.append("imageFile", image);
    // Append the product data with the correct name ('addProductDTO')
    formData.append(
      "addProductDTO", // Change 'product' to 'addProductDTO' to match backend
      new Blob([JSON.stringify(product)], { type: "application/json" })
    );

    try {
      setLoading(true);
      const result = await axios.post(
        "http://localhost:8080/products/addProduct",
        formData,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      if (result.status === 201) {
        console.log(result.data);

        toast.success(result.data, toastStyle);
        fetchData();
      } else {
        toast.error("Something went wrong", toastStyle);
      }
    } catch (error) {
      if (error.response.status) {
        toast.error(error.response.data || error.message, toastStyle);
      } else {
        toast.error(error.message, toastStyle);
      }
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true);
        const response = await axios.get(
          "http://localhost:8080/deviceType/getDeviceType",
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );
        if (response.status === 200) {
          setGetCategory(response.data || []);
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

    fetchData();
  }, []);
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
        setProductAll(response.data.content); // Get paginated content
        setTotalPages(response.data.totalPages); // Get total pages
      }
    } catch (error) {
      toast.error(error.response?.data || error.message, toastStyle);
    } finally {
      setLoading(false);
    }
  };

  // Fetch data when page or size changes
  useEffect(() => {
    fetchData();
  }, [page, size]);

  const handleSizeChange = (e) => {
    setSize(e.target.value);
    setPage(0); // Reset to first page on size change
  };

  const handleNextPage = () => {
    if (page < totalPages - 1) setPage(page + 1);
  };

  const handlePreviousPage = () => {
    if (page > 0) setPage(page - 1);
  };

  const handleDelete = async (e) => {
    e.preventDefault();
    try {
      setLoading(true);
      const response = await axios.delete(
        `http://localhost:8080/products/${deviceId}/delete`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      if (response.status === 202) {
        toast.success(response.data, toastStyle);
        setDeletedId(" ");
        fetchData();
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

  return (
    <>
      <div className="container mt-5">
        <h1 className="text-center py-3">Add/Remove/Update Product</h1>
        <hr />
        <div className="input-group mb-3">
          <span className="input-group-text" id="basic-addon1">
            What you want?
          </span>
          <select
            className="form-select"
            aria-label="Default select example"
            onChange={(e) => setHandleType(e.target.value)}
            name="categories"
            value={handleType}
          >
            <option value="select">Please Select Option</option>
            <hr />
            <option value="add">Add</option>
            <option value="update">Update</option>
            <option value="remove">Remove</option>
          </select>
        </div>
        {handleType === "add" ? (
          <>
            <div className="input-group mb-3">
              <span className="input-group-text" id="basic-addon1">
                Image
              </span>
              <input
                className="form-control"
                aria-label="Username"
                aria-describedby="basic-addon1"
                type="file"
                action="/upload"
                id="selectFiles"
                name="myFile"
                enctype="multipart/form-data"
                onChange={handleImageChange}
              />
            </div>
            <div className="input-group mb-3">
              <span className="input-group-text" id="basic-addon1">
                Company Name
              </span>
              <input
                type="text"
                className="form-control"
                placeholder="-"
                aria-label=""
                aria-describedby="basic-addon1"
                onChange={handleInputChange}
                name="companyName"
                value={product.companyName}
              />
            </div>
            <div className="input-group mb-3">
              <span className="input-group-text" id="basic-addon1">
                Device Name
              </span>
              <input
                type="text"
                className="form-control"
                placeholder="-"
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
                placeholder="₹"
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
                placeholder="₹"
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
                placeholder="%"
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
          </>
        ) : null}
        {handleType === "update" ? (
          <UpdateItem getCategory={getCategory} />
        ) : null}

        {handleType === "remove" ? (
          <>
            <div className="input-group mb-3">
              <span className="input-group-text" id="basic-addon1">
                Select Product
              </span>
              <select
                className="form-select"
                aria-label="Default select example"
                onChange={(e) => setDeletedId(e.target.value)}
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

            <div className="pagination-controls text-center">
              <label htmlFor="pageSize" className="me-2">
                Page Size:
              </label>
              <input
                id="pageSize"
                type="number"
                min="1"
                className="form-control d-inline-block w-auto mb-3"
                value={size}
                onChange={handleSizeChange}
              />

              <div className="button-group">
                <button
                  className="btn btn-primary mx-2"
                  onClick={handlePreviousPage}
                  disabled={page === 0}
                >
                  Previous
                </button>

                <button
                  className="btn btn-primary mx-2"
                  onClick={handleNextPage}
                  disabled={page === totalPages - 1}
                >
                  Next
                </button>

                <button
                  type="button"
                  className="btn btn-secondary w-100 my-5"
                  onClick={handleDelete}
                >
                  Delete
                </button>
              </div>
            </div>
          </>
        ) : null}
      </div>

      {loading && <LoadingSpinner />}
    </>
  );
};

export default AddItem;
