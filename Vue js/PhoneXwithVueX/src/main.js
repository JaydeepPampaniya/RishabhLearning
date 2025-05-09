import { createApp, defineAsyncComponent } from "vue";
import { createRouter, createWebHistory } from "vue-router";
import Toast, { toast } from "vue3-toastify";
import store from "./store/store";
import "vue3-toastify/dist/index.css";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import "animate.css";
import "./CSS/App.css";

const App = defineAsyncComponent(() => import("./App.vue"));
const Layout = defineAsyncComponent(() => import("./components/Layout.vue"));
const AdminLayout = defineAsyncComponent(() =>
  import("./admin/AdminLayout.vue")
);
const Home = defineAsyncComponent(() => import("./pages/Home.vue"));
const SignUp = defineAsyncComponent(() => import("./pages/SignUp.vue"));
const SignIn = defineAsyncComponent(() => import("./pages/SignIn.vue"));
const Bag = defineAsyncComponent(() => import("./pages/Bag.vue"));
const About = defineAsyncComponent(() => import("./pages/About.vue"));
const PrivacyPolicy = defineAsyncComponent(() =>
  import("./pages/PrivacyPolicy.vue")
);
const ReturnPolicy = defineAsyncComponent(() => import("./pages/Return.vue"));
const Term = defineAsyncComponent(() => import("./components/Term.vue"));
const EditUser = defineAsyncComponent(() =>
  import("./components/EditUser.vue")
);
const Captcha = defineAsyncComponent(() => import("./pages/Captcha.vue"));
const ProductDetailApi = defineAsyncComponent(() =>
  import("./pages/ProductDetail.vue")
);
const UserList = defineAsyncComponent(() => import("./admin/CustomerList.vue"));
const AddItem = defineAsyncComponent(() => import("./admin/ProductsList.vue"));
const AddDeviceType = defineAsyncComponent(() =>
  import("./admin/DeviceType.vue")
);
const NotFound = defineAsyncComponent(() =>
  import("./components/NotFound.vue")
);
const AddProducts = defineAsyncComponent(() =>
  import("./admin/AddProducts.vue")
);
const UpdateItem = defineAsyncComponent(() =>
  import("./admin/UpdateProduct.vue")
);

const routes = [
  {
    path: "/",
    component: Layout,
    children: [
      { path: "", component: Home },
      { path: "editUser", component: EditUser },
      { path: "signUp", component: SignUp, meta: { requireUnauth: true } },
      { path: "signIn", component: SignIn, meta: { requireUnauth: true } },
      { path: "bag", component: Bag },
      { path: "about", component: About },
      { path: "privacyPolicy", component: PrivacyPolicy },
      { path: "return", component: ReturnPolicy },
      { path: "term", component: Term },
      { path: "captcha", component: Captcha },
      {
        path: "productDetailApi/:productId",
        component: ProductDetailApi,
      },
    ],
  },
  {
    path: "/admin",
    component: AdminLayout,
    children: [
      { path: "", component: UserList },
      { path: "productsList", component: AddItem },
      { path: "deviceType", component: AddDeviceType },
      { path: "addProducts", component: AddProducts },
      { path: "update/:productId", component: UpdateItem },
    ],
    beforeEnter: (to, from, next) => {
      const user = store.getters["auth/user"];
      if (!user || !["ADMIN", "OWNER"].includes(user.role)) {
        setTimeout(() => {
          toast.error("Access denied");
        }, 100);
        store.dispatch("auth/logout");
      } else {
        next();
      }
    },
  },
  {
    path: "/:notFound(.*)",
    component: NotFound,
  },
];

export const router = createRouter({
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

// router.beforeEach(async (to, from, next) => {
//   try {
//     await store.dispatch("auth/checkAuth");
//     const isAuthenticated = store.getters["auth/isAuthenticated"];
//     if (to.meta.requireUnauth && isAuthenticated) {
//       next("/");
//     } else if (!isAuthenticated && !to.meta.requireUnauth) {
//       toast.error("Please login first");
//       next("/signIn");
//     } else {
//       next();
//     }
//   } catch (error) {
//     console.error("Auth check failed:", error);
//     next("/signIn");
//   }
// });

const app = createApp(App);

app.use(store);
app.use(router);
app.use(Toast, {
  position: "top-right",
  autoClose: 1800,
  closeOnClick: true,
  pauseOnHover: true,
  newestOnTop: true,
  transition: "Vue-Toastification__bounce",
});

app.mount("#app");
