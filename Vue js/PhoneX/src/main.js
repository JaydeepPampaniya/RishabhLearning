import { createApp } from "vue";
import { createPinia } from "pinia";
import Toast from "vue-toastification";
import "vue-toastification/dist/index.css";
import { createRouter, createWebHistory } from "vue-router";
import App from "./App.vue";
import Home from "./components/Home.vue";
import SignUp from "./components/SignUp.vue";
import SignIn from "./components/SignIn.vue";
import Bag from "./components/Bag.vue";
import About from "./components/About.vue";
import Privacypolicy from "./components/PrivacyPolicy.vue";
import ReturnPolicy from "./components/Return.vue";
import Term from "./components/Term.vue";
import AdminLogin from "./admin/AdminLogin.vue";
import EditUser from "./components/EditUser.vue";
import AdminMiddle from "./admin/AdminMiddle.vue";
import Captcha from "./components/Captcha.vue";
import ProductDetailApi from "./components/ProductDetail.vue";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import "animate.css";
import "./CSS/App.css";
import "primeicons/primeicons.css";
import Layout from "./components/Layout.vue";

const routes = [
  {
    path: "/",
    component: Layout,
    children: [
      { path: "", component: Home },
      { path: "EditUser", component: EditUser },
      { path: "SignUp", component: SignUp },
      { path: "SignIn", component: SignIn },
      { path: "Bag", component: Bag },
      { path: "About", component: About },
      { path: "Privacypolicy", component: Privacypolicy },
      { path: "Return", component: ReturnPolicy },
      { path: "Terms", component: Term },
      { path: "Captcha", component: Captcha },
      { path: "ProductDetailApi/:productId", component: ProductDetailApi },
    ],
  },
  { path: "/AdminLogin", component: AdminLogin },
  { path: "/Admin/AdminMiddle", component: AdminMiddle },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition;
    } else {
      return { top: 0 };
    }
  },
});

const app = createApp(App);
app.use(router);
const pinia = createPinia();
app.use(pinia);
app.use(Toast, {
  position: "top-right",
  timeout: 1800,
  closeOnClick: true,
  pauseOnHover: true,
  draggable: true,
  transition: "Vue-Toastification__bounce",
});

app.mount("#app");
