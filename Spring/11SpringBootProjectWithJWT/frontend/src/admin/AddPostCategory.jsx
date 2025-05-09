import React from "react";
import axios from "axios";
import { useState } from "react";
import { toast, ToastContainer } from "react-toastify";
import { toastStyle } from "../Constant/general";
import LoadingSpinner from "../componnents/LoadingSpinner";
import cookies from 'js-cookie';

const AddPostCategory = () => {
  const [name, setCategoryName] = useState();
  const [loading, setLoading] = useState(false);
  const {token} = JSON.parse(cookies.get("AdminId"))

  const submit = async () => {
    if (!name) {
      toast.error("Please enter category name", toastStyle);
      return;
    }
    setLoading(true);
    const data = {name}
    try {
      const result = await axios.post("http://localhost:8080/deviceType/addDeviceType", data,{
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      if(result.status ===202){
        toast.success(result.data, toastStyle);
      }else{
        toast.error(result.data.message, toastStyle);
      }
    } catch (error) {
      if(error.response.status){
        toast.error(error.response.data);
      }else{
        toast.error(error.response?.data?.message || error.message, toastStyle);
      }
      
    } finally{
      setLoading(false);
    }
  };
  
  return (
    <>
      <div className="container mt-5">
        <h1 className="text-center p-3">Post Category</h1>
        <hr />
        <div className="my-5">
          <div className="input-group mb-3">
            <span className="input-group-text" id="basic-addon1">
              Add Category
            </span>
            <input
              type="text"
              className="form-control"
              placeholder="Add Category"
              aria-label="Username"
              aria-describedby="basic-addon1"
              value={name}
              onChange={(e) => setCategoryName(e.target.value)}
            />
          </div>
          <button
            type="button"
            className="btn btn-success w-100"
            onClick={submit}
          >
            Post
          </button>
        </div>
      </div>
    
      {loading && <LoadingSpinner />}
    </>
  );
}

export default AddPostCategory;
