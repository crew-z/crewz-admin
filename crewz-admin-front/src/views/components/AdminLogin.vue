<template>
  <div class="w-full h-screen">
    <div class="flex rounded-md h-screen">
      <div class="bg-white dark:bg-gray-900 w-full">
        <form class="mt-52" @submit.prevent="login()">
          <div
            class="form-body lg:max-w-xl max-w-md mx-auto lg:p-20 p-8 lg:mt-20 mt-20 space-y-8">
            <div
              class="form-head hover:animate-bounce hover:transition hover:text-[color:#ff5a5a]">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 24 24"
                fill="currentColor"
                class="w-10 h-10 mx-auto">
                <path
                  fill-rule="evenodd"
                  d="M12 1.5a5.25 5.25 0 00-5.25 5.25v3a3 3 0 00-3 3v6.75a3 3 0 003 3h10.5a3 3 0 003-3v-6.75a3 3 0 00-3-3v-3c0-2.9-2.35-5.25-5.25-5.25zm3.75 8.25v-3a3.75 3.75 0 10-7.5 0v3h7.5z"
                  clip-rule="evenodd" />
              </svg>
            </div>
            <div class="space-y-3">
              <h2
                class="dark:text-white font-semibold text-gray-800 text-4xl text-center">
                관리자 로그인
              </h2>
              <p class="dark:text-gray-400 text-gray-700 text-center">
                관리자 계정으로 로그인하세요!
              </p>
            </div>
            <div class="space-y-5">
              <div class="relative z-0 w-full mb-6 group">
                <input
                  type="text"
                  v-model="userId"
                  name="floating_Id"
                  id="floating_Id"
                  class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-[#ff5a5a] focus:outline-none focus:ring-0 focus:border-[#ff5a5a] peer"
                  placeholder=" "
                  required />
                <label
                  for="floating_Id"
                  class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-[#ff5a5a] peer-focus:dark:text-[#ff5a5a] peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6"
                  >아이디</label
                >
              </div>
              <div class="relative z-0 w-full mb-6 group">
                <input
                  type="password"
                  v-model="userPassword"
                  name="floating_password"
                  id="floating_password"
                  class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-[#ff5a5a] focus:outline-none focus:ring-0 focus:border-[#ff5a5a] peer"
                  placeholder=" "
                  required />
                <label
                  for="floating_password"
                  class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-[#ff5a5a] peer-focus:dark:text-[#ff5a5a] peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6"
                  >비밀번호</label
                >
              </div>
            </div>
            <div>
              <button
                type="submit"
                class="text-white bg-[#ff5a5a] hover:bg-[#ff5a5a]/80 p-3 w-full rounded-md">
                로그인
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import router from "@/router/index";
import { requestLogin } from "@/api/login";
import { useCookies } from "vue3-cookies";
import store from "@/store/index";

const cookies = useCookies().cookies;

const userId = ref(null);
const userPassword = ref(null);

const login = async () => {
  let requestParam = {
    userId: userId.value,
    userPassword: userPassword.value,
  };
  const response = await requestLogin(requestParam);
  cookies.set("token", response.data.token);
  cookies.set("role", response.data.role);
  cookies.set("name", response.data.name);
  // store에 저장
  store.commit("login", response.data);

  router.push("/");

  console.log("store.state.token ======> " + store.state.token);
};
</script>
