<template>
  <!-- App -->
  <div class="flex bg-gray-50 font-lexend dark:bg-gray-900">
    <div
      v-if="!$route.meta.hideNav"
      class="lg:block"
      :class="{ 'lg:block hidden': !sidebar, block: sidebar }">
      <div
        class="lg:flex-auto w-sidebar bg-white dark:bg-gray-800 border-r-2 dark:border-gray-700 lg:z-0 z-20 overflow-auto lg:relative fixed">
        <perfect-scrollbar class="h-screen">
          <Sidebar v-if="!$route.meta.hideNav" @sidebarToggle="close" />
        </perfect-scrollbar>
      </div>
    </div>
    <div
      class="flex-auto w-full overflow-auto h-screen transition-colors"
      id="body-scroll">
      <!-- <Header v-if="!$route.meta.hideNav" @sidebarToggle="open" /> -->

      <transition name="slide-up" mode="out-in">
        <router-view />
      </transition>
      <Footer v-if="!$route.meta.hideNav" />
    </div>
  </div>
  <!-- End app -->
</template>

<script>
// Vue components
import Sidebar from "@/components/AdminSidebar";
import Footer from "@/components/AdminFooter";
// npm-js
import Scrollbar from "smooth-scrollbar";
// cookie
import { useCookies } from "vue3-cookies";

const cookies = useCookies().cookies;

export default {
  name: "App",

  data() {
    return {
      sidebarDark: false,
      sidebar: false,
      adminName: cookies.get("name"),
      adminRole: cookies.get("role"),
    };
  },
  created() {
    // //메인 컴포넌트를 렌더링하면서 토큰체크
    // let token = this.$store.getters.getToken;
    // if (token.access == null && token.refresh == null) {
    //   //다 없으면 로그인 페이지로
    //   //이미 로그인 페이지가 떠있는 상태에서 새로 고침하면 중복 에러 떠서 이렇게 처리함
    //   this.$router.push({ name: "Login" }).catch(() => {});
    // }
  },

  components: {
    Footer,
    Sidebar,
  },
  methods: {
    open() {
      this.sidebar = true;
    },
    close() {
      this.sidebar = false;
    },
  },
  watch: {
    $route() {
      this.sidebar = false;
    },
  },
  mounted() {
    Scrollbar.init(document.querySelector("#body-scroll"));
  },
};
</script>

<style>
/*
  Enter and leave animations can use different
  durations and timing functions.
*/
.slide-up-enter-active {
  transition: all 0.3s ease-out;
}

.slide-up-leave-active {
  transition: all 0.8s cubic-bezier(1, 0.5, 0.8, 1);
}

.slide-up-enter-from,
.slide-up-leave-to {
  transform: translateY(20px);
  opacity: 0;
}
</style>
