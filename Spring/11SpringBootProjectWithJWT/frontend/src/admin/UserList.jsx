import React, { useState, useEffect } from "react";
import { FcBusinessman, FcBusinesswoman, FcDecision } from "react-icons/fc";
import axios from "axios";
import { toast, ToastContainer } from "react-toastify";
import { toastStyle } from "../Constant/general";
import LoadingSpinner from "../componnent s/LoadingSpinner";
import cookies from "js-cookie";
import { useNavigate } from "react-router-dom";

const UserList = () => {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(false);
  const [page, setPage] = useState(0); // Track the current page
  const [totalPages, setTotalPages] = useState(0); // Track total pages from the backend
  const { token } = JSON.parse(cookies.get("AdminId"));
  const navigate = useNavigate();

  const fetchUsers = async () => {
    try {
      setLoading(true);
      const response = await axios.get(
        `http://localhost:8080/customer/getAllCustomer?page=${page}&size=5`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      setUsers(response.data.content || []); // Get content from Page object
      setTotalPages(response.data.totalPages); // Get total pages from backend
    } catch (error) {
      if (error.response.status === 401) {
        toast.error("Seesion timeout,Log in again", toastStyle);
        navigate("/adminLogin");
        cookies.remove("AdminId");
      } else {
        toast.error(error.message, toastStyle);
      }
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchUsers();
  }, [page]);

  const deleteUser = async (id1) => {
    try {
      setLoading(true);
      const result = await axios.delete(
        `http://localhost:8080/customer/delete/${id1}`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      if (result.status === 200) {
        toast.success(result.data, toastStyle);
        cookies.remove("UserCookie");
        fetchUsers();
      } else {
        toast.error(result.data.message, toastStyle);
      }
    } catch (error) {
      if (error.response.status) {
        toast.error(error.response.data, toastStyle);
      } else {
        toast.error(error.message, toastStyle);
      }
    } finally {
      setLoading(false);
    }
  };

  return (
    <>
      <h1 className="text-center py-1 cl mt-5">UserList</h1>
      <table className="table container text-center">
        <thead>
          <tr>
            <th scope="col">id</th>
            <th scope="col">Profile</th>
            <th scope="col">Name</th>
            <th scope="col">Email</th>
            <th scope="col">Gender</th>
            <th scope="col">Created at</th>
            <th scope="col">Action</th>
          </tr>
        </thead>
        <tbody>
          {users.map((user, i) => (
            <tr key={user.id}>
              <th scope="row">{i + 1 + page * 5}</th>
              <td className="fs-4 text-success">
                {user.gender === "Male" ? (
                  <FcBusinessman />
                ) : user.gender === "Female" ? (
                  <FcBusinesswoman />
                ) : (
                  <FcDecision />
                )}
              </td>
              <td>{user.username}</td>
              <td>{user.user.email}</td>
              <td>{user.gender}</td>
              <td>{user.user.createdTime}</td>
              <td>
                <button
                  type="button"
                  className="btn btn-danger"
                  onClick={() => deleteUser(user.user.id)}
                >
                  DELETE
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {/* Pagination Buttons */}
      <div className="d-flex justify-content-center my-3">
        <button
          className="btn btn-primary mx-2"
          onClick={() => setPage((prev) => Math.max(prev - 1, 0))}
          disabled={page === 0}
        >
          Previous
        </button>
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

export default UserList;
