<template>
  <!-- sidebar -->
  <nav class="sidebar bg-white dark:bg-gray-800">
    <!-- sidebar head -->
    <div class="sidebar-head pt-4 px-4 pb-2">
      <router-link to="/" exact class="flex">
        <img
          class="w-8 mt-2"
          src="@/assets/logo/logo-admin.svg"
          alt="crewz logo"
          style="width: 160px; height: 40px" />
      </router-link>
    </div>
    <div class="sidebar-head pt-4 px-4 pb-2">
      <p>
        <span class="mr-4 text-xl font-semibold">{{ adminUserName }}</span>
        <button
          type="button"
          class="text-white bg-[#ff5a5a] hover:bg-[#ff1a1a] focus:ring-4 focus:ring-red-300 font-medium rounded-lg text-sm px-3 py-2 mr-2 mb-2 dark:bg-red-600 dark:hover:bg-red-700 focus:outline-none dark:focus:ring-red-800"
          @click="logout">
          로그아웃
        </button>
      </p>
    </div>
    <!-- sidebar list -->
    <div class="sidebar-list p-4 mt-2 divide-y dark:divide-gray-700">
      <div class="pb-5">
        <p class="font-medium text-gray-400 dark:text-gray-400">Menu</p>
        <div class="wrap-item mt-4 dark:text-gray-500">
          <div class="item">
            <router-link
              to="/"
              exact
              class="w-full flex text-left rounded-md box-border p-3 hover:bg-gray-200 dark:hover:bg-gray-700">
              <span class="mr-3 text-xl"><Icon icon="bxs:dashboard" /></span>
              <span class="w-full"> 대시보드 </span>
            </router-link>
          </div>
          <div class="item mt-3">
            <menu-accordion>
              <template v-slot:icon>
                <Icon icon="gg:components" />
              </template>
              <template v-slot:title> 동아리 관리 </template>
              <template v-slot:content>
                <router-link
                  to="/category"
                  class="w-full text-left block rounded-md p-3 hover:bg-gray-200 dark:hover:bg-gray-700">
                  카테고리 관리
                </router-link>
                <router-link
                  to="/clubtable"
                  class="w-full text-left block rounded-md p-3 hover:bg-gray-200 dark:hover:bg-gray-700">
                  동아리 목록
                </router-link>
                <router-link
                  to="/clubapplytable"
                  class="w-full text-left block rounded-md p-3 hover:bg-gray-200 dark:hover:bg-gray-700">
                  동아리 신청 목록
                </router-link>
              </template>
            </menu-accordion>
          </div>
          <div class="item mt-3" v-if="adminRole === 'ROLE_CAPTAIN'">
            <menu-accordion>
              <template v-slot:icon>
                <Icon icon="ri:pages-fill" />
              </template>
              <template v-slot:title> Admin 관리 </template>
              <template v-slot:content>
                <router-link
                  to="/admin"
                  class="w-full text-left block rounded-md p-3 hover:bg-gray-200 dark:hover:bg-gray-700">
                  관리자 목록
                </router-link>
                <router-link
                  to="/admin/withdrawal"
                  class="w-full text-left block rounded-md p-3 hover:bg-gray-200 dark:hover:bg-gray-700">
                  회원 목록
                </router-link>
              </template>
            </menu-accordion>
          </div>
        </div>
      </div>
      <!-- <div class="pb-5">
        <div class="item mt-3">
          <menu-accordion>
            <template v-slot:icon>
              <Icon icon="gg:components" />
            </template>
            <template v-slot:title> 참조 컨포넌트 </template>
            <template v-slot:content>
              <div class="">
                <router-link
                  to="/blank"
                  class="w-full text-left block rounded-md p-3 hover:bg-gray-200 dark:hover:bg-gray-700">
                  Blank Page
                </router-link>
                <menu-accordion>
                  <template v-slot:title> Error Page </template>
                  <template v-slot:content>
                    <router-link
                      to="/404page"
                      class="w-full text-left block rounded-md p-3 hover:bg-gray-200 dark:hover:bg-gray-700">
                      404 page
                    </router-link>
                    <router-link
                      to="/500"
                      class="w-full text-left block rounded-md p-3 hover:bg-gray-200 dark:hover:bg-gray-700">
                      500 page
                    </router-link>
                    <router-link
                      to="/maintenance"
                      class="w-full text-left block rounded-md p-3 hover:bg-gray-200 dark:hover:bg-gray-700">
                      Maintenance page
                    </router-link>
                  </template>
                </menu-accordion>
                <menu-accordion>
                  <template v-slot:title> Authentication </template>
                  <template v-slot:content>
                    <router-link
                      to="/auth/login"
                      class="w-full text-left block rounded-md p-3 hover:bg-gray-200 dark:hover:bg-gray-700">
                      Login
                    </router-link>
                    <router-link
                      to="/auth/register"
                      class="w-full text-left block rounded-md p-3 hover:bg-gray-200 dark:hover:bg-gray-700">
                      Register
                    </router-link>
                    <router-link
                      to="/auth/forgot-password"
                      class="w-full text-left block rounded-md p-3 hover:bg-gray-200 dark:hover:bg-gray-700">
                      Forgot Password
                    </router-link>
                  </template>
                </menu-accordion>
              </div>
              <router-link
                to="/component/alert"
                class="w-full text-left block rounded-md p-3 hover:bg-gray-200 dark:hover:bg-gray-700">
                Alert
              </router-link>
              <router-link
                to="/component/accordion"
                class="w-full text-left block rounded-md p-3 hover:bg-gray-200 dark:hover:bg-gray-700">
                Accordion
              </router-link>
              <router-link
                to="/component/badge"
                class="w-full text-left block rounded-md p-3 hover:bg-gray-200 dark:hover:bg-gray-700">
                Badge
              </router-link>
              <router-link
                to="/component/breadcumb"
                class="w-full text-left block rounded-md p-3 hover:bg-gray-200 dark:hover:bg-gray-700">
                Breadcumb
              </router-link>
              <router-link
                to="/component/button"
                class="w-full text-left block rounded-md p-3 hover:bg-gray-200 dark:hover:bg-gray-700">
                Button
              </router-link>
              <router-link
                to="/component/card"
                class="w-full text-left block rounded-md p-3 hover:bg-gray-200 dark:hover:bg-gray-700">
                Card
              </router-link>
              <router-link
                to="/component/dropdown"
                class="w-full text-left block rounded-md p-3 hover:bg-gray-200 dark:hover:bg-gray-700">
                Dropdown
              </router-link>
              <router-link
                to="/component/modal"
                class="w-full text-left block rounded-md p-3 hover:bg-gray-200 dark:hover:bg-gray-700">
                Modal
              </router-link>
            </template>
          </menu-accordion>
        </div>
      </div> -->
    </div>
  </nav>
</template>

<script setup>
import { Icon } from "@iconify/vue";
import MenuAccordion from "./MenuAccordion.vue";
import { useCookies } from "vue3-cookies";
import store from "@/store/index";

const cookies = useCookies().cookies;
const adminRole = store.state.role;
const adminUserName = store.state.name;

const logout = () => {
  let logout = confirm("로그아웃 하시겠습니까?");
  if (logout) {
    cookies.remove("token");
    cookies.remove("role");
    window.location.reload();
  }
};
</script>
