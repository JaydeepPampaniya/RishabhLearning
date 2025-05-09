import { createStore } from "vuex";
import auth from "./modules/auth";
import cart from "./modules/cart";
import products from "./modules/products";
import deviceType from "./modules/deviceType";

export default createStore({
  modules: {
    auth,
    cart,
    products,
    deviceType,
  },
});
