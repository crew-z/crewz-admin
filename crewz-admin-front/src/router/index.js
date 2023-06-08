import { createRouter, createWebHistory } from "vue-router";
import { useCookies } from "vue3-cookies";
const cookies = useCookies().cookies;

// import store from "@/store/index";

// Default Pages
import Dashboard from "@/views/AdminDashboard.vue";
// Clubmanagement pages
import ClubInfo from "@/views/components/AdminClubInfo.vue";

// Manage Admin Pages
import AdminManage from "@/views/components/ManageAdmin.vue";
import WithdrawalUser from "@/views/components/WithdrawalUser.vue";

// Component Pages
import Valert from "@/views/components/AdminAlert.vue";
import Vaccrodion from "@/views/components/AdminAccordion.vue";
import Vbadges from "@/views/components/AdminBadges.vue";
import Vbreadcumb from "@/views/components/AdminBreadcumbs.vue";
import Vbutton from "@/views/components/AdminButton.vue";
import Vcard from "@/views/components/AdminCard.vue";
import Vdropdown from "@/views/components/AdminDropdown.vue";
import Vmodal from "@/views/components/AdminModal.vue";
import Login from "@/views/components/AdminLogin.vue";
import Register from "@/views/layouts/auth/AdminRegister.vue";
import ForgotPassword from "@/views/layouts/auth/forgot-password.vue";

// Add Compoenet Page
import Vcategory from "@/views/components/AddCategory.vue";
import VclubTable from "@/views/components/ClubTable.vue";
import VclubApplytable from "@/views/components/ClubApplyTable";

// layouts
import Blank from "@/views/layouts/AdminBlank.vue";

// error page
import Page404 from "@/views/layouts/error/ERROR404.vue";
import Page500 from "@/views/layouts/error/ERROR500.vue";
import PageMaintenance from "@/views/layouts/error/maintenance.vue";
import Tables from "@/views/AdminTables.vue";

var appname = " - Windzo Dashboard Admin Template";

const routes = [
  // Routes
  {
    path: "/",
    name: "Dashboard",
    component: Dashboard,
    meta: { title: "Dashboard " + appname },
    // beforeEnter: (to, from, next) => {
    //   // ... if role.. 어쩌구
    // }
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
    meta: { title: "Login" + appname, hideNav: true },
  },
  // Clubmanagement based Routes
  {
    path: "/clubmenagement/clubdashboard",
    name: "ClubInfo",
    component: ClubInfo,
    meta: { title: "Clubinfo" + appname },
  },

  // Components based Routes
  {
    path: "/category",
    name: "category",
    component: Vcategory,
    meta: { title: "category" + appname },
  },
  {
    path: "/clubtable",
    name: "clubtable",
    component: VclubTable,
    meta: { title: "clubtable" + appname },
  },
  {
    path: "/clubapplytable",
    name: "clubapplytable",
    component: VclubApplytable,
    meta: { title: "clubapplytable" + appname },
  },
  {
    path: "/admin",
    name: "adminManage",
    component: AdminManage,
    meta: { title: "admin" + appname },
  },
  {
    path: "/admin/withdrawal",
    name: "withdrawal",
    component: WithdrawalUser,
    meta: { title: "withdrawal" + appname },
  },
  {
    path: "/component/alert",
    name: "Valert",
    component: Valert,
    meta: { title: "category" + appname },
  },
  {
    path: "/component/accordion",
    name: "Vaccordion",
    component: Vaccrodion,
    meta: { title: "Accordion" + appname },
  },
  {
    path: "/component/badge",
    name: "Vbadge",
    component: Vbadges,
    meta: { title: "Badge" + appname },
  },
  {
    path: "/component/breadcumb",
    name: "Vbreadcumb",
    component: Vbreadcumb,
    meta: { title: "Breadcumb" + appname },
  },
  {
    path: "/component/button",
    name: "Vbutton",
    component: Vbutton,
    meta: { title: "Button" + appname },
  },
  {
    path: "/component/card",
    name: "Vcard",
    component: Vcard,
    meta: { title: "Card" + appname },
  },
  {
    path: "/component/dropdown",
    name: "Vdropdown",
    component: Vdropdown,
    meta: { title: "Dropdown" + appname },
  },
  {
    path: "/component/modal",
    name: "Vmodal",
    component: Vmodal,
    meta: { title: "Modal" + appname },
  },
  // layouts

  {
    path: "/Blank",
    name: "Blank Page",
    component: Blank,
    meta: { title: "Blank Page" + appname },
  },
  {
    path: "/auth/register",
    name: "Register",
    component: Register,
    meta: { title: "Register" + appname, hideNav: true },
  },
  {
    path: "/auth/forgot-password",
    name: "ForgotPassword",
    component: ForgotPassword,
    meta: { title: "i Forgot Password" + appname, hideNav: true },
  },
  // layout/error
  // default page 404
  {
    path: "/:pathMatch(.*)*",
    name: "Page404",
    component: Page404,
    meta: { title: "Upps! 404" + appname, hideNav: true },
  },
  {
    path: "/500",
    name: "Page500",
    component: Page500,
    meta: { title: "Server internal Error" + appname, hideNav: true },
  },
  {
    path: "/maintenance",
    name: "maintenance",
    component: PageMaintenance,
    meta: {
      title: "Sorry the app has been Maintenance" + appname,
      hideNav: true,
    },
  },
  {
    path: "/tables",
    name: "Tables",
    component: Tables,
    meta: {
      title: "Tables" + appname,
    },
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,

  linkExactActiveClass: "exact-active",
});

//네비게이션 가드((뷰 라우터로 URL 접근에 대해서 처리할 수 있음)
router.beforeEach(async (to, from, next) => {
  document.title = to.meta.title; //여기서 모든 라우팅이 대기 상태가 됨
  /**
   * to: 이동할 url 정보가 담긴 라우터 객체
   * from: 현재 url 정보가 담긴 라우터 객체
   * next: to에서 지정한 url로 이동하기 위해 꼭 호출해야 하는 함수
   * next() 가 호출되기 전까지 화면 전환되지 않음
   */
  if (cookies.get("token") === null) {
    if (to.path === "/login") {
      next();
    } else if (to.path === "/") next("/login");
    else {
      // alert?
      next("/login");
    }
    console.log("token null");
  } else {
    if (to.path === "/login") {
      next("/");
    } else {
      next();
    }
  }
});

export default router;
