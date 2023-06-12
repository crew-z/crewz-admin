<template>
  <div class="alert h-auto p-3">
    <nav class="flex" aria-label="Breadcrumb">
      <ol class="inline-flex items-center space-x-1 md:space-x-3">
        <li class="inline-flex items-center">
          <a
            href="/admin"
            class="inline-flex items-center text-sm font-medium text-gray-700 hover:text-gray-900 dark:text-gray-400 dark:hover:text-white"
          >
            <svg
              class="mr-2 w-4 h-4"
              fill="currentColor"
              viewBox="0 0 20 20"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                d="M10.707 2.293a1 1 0 00-1.414 0l-7 7a1 1 0 001.414 1.414L4 10.414V17a1 1 0 001 1h2a1 1 0 001-1v-2a1 1 0 011-1h2a1 1 0 011 1v2a1 1 0 001 1h2a1 1 0 001-1v-6.586l.293.293a1 1 0 001.414-1.414l-7-7z"
              ></path>
            </svg>
            Admin 관리
          </a>
        </li>
        <li>
          <div class="flex items-center">
            <svg
              class="w-6 h-6 text-gray-400"
              fill="currentColor"
              viewBox="0 0 20 20"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                fill-rule="evenodd"
                d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z"
                clip-rule="evenodd"
              ></path>
            </svg>
            <a
              href="/admin"
              class="ml-1 text-sm font-medium text-gray-700 hover:text-gray-900 md:ml-2 dark:text-gray-400 dark:hover:text-white"
              >관리자 등록</a
            >
          </div>
        </li>
      </ol>
    </nav>
    <!-- end nav -->
    <div class="mt-5 w-full">
      <div>
        <span class="text-2xl text-gray-900 font-medium dark:text-gray-200">
          관리자 리스트
        </span>
        <div class="absolute" style="right: 10rem; top: 115px">
          <Modal
            title="관리자 등록"
            btnTextClose="취소"
            btnTextSubmit="등록"
            btnColor="bg-[#00a5a5]"
            btnText="관리자 등록"
            @submit="addAdmin"
          >
            <template v-slot:body>
              <form>
                <div class="space-y-5 pb-5">
                  <div class="space-y-3">
                    <label for="nAdminName"
                      >이름<span class="text-rose-500"> *</span></label
                    >
                    <input
                      type="text"
                      placeholder=""
                      id="nAdminName"
                      v-model="nAdminName"
                      class="p-2 border dark:border-gray-600 dark:bg-gray-700 w-full rounded outline-none"
                    />
                  </div>
                  <div class="space-y-3">
                    <label for="nAdminId"
                      >아이디<span class="text-rose-500"> *</span></label
                    >
                    <input
                      type="text"
                      placeholder=""
                      id="nAdminId"
                      v-model="nAdminId"
                      class="p-2 border dark:border-gray-600 dark:bg-gray-700 w-full rounded outline-none"
                    />
                  </div>
                  <div class="space-y-3">
                    <label for="nAdminPassword"
                      >비밀번호<span class="text-rose-500"> *</span></label
                    >
                    <input
                      type="password"
                      placeholder=""
                      id="nAdminPassword"
                      v-model="nAdminPassword"
                      class="p-2 border dark:border-gray-600 dark:bg-gray-700 w-full rounded outline-none"
                    />
                  </div>
                  <div class="space-y-3">
                    <label for="nAdminPassword2">비밀번호 확인</label>
                    <input
                      type="password"
                      placeholder=""
                      id="nAdminPassword2"
                      v-model="nAdminPassword2"
                      class="p-2 border dark:border-gray-600 dark:bg-gray-700 w-full rounded outline-none"
                    />
                  </div>
                  <div class="space-y-3">
                    <label for="nAdminTel"
                      >전화번호<span class="text-rose-500"> *</span></label
                    >
                    <input
                      type="text"
                      placeholder=""
                      id="nAdminTel"
                      v-model="nAdminTel"
                      class="p-2 border dark:border-gray-600 dark:bg-gray-700 w-full rounded outline-none"
                    />
                  </div>
                  <div class="space-y-3">
                    <label for="nAdminEmail"
                      >이메일<span class="text-rose-500"> *</span></label
                    >
                    <input
                      type="email"
                      placeholder=""
                      id="nAdminEmail"
                      v-model="nAdminEmail"
                      class="p-2 border dark:border-gray-600 dark:bg-gray-700 w-full rounded outline-none"
                    />
                  </div>
                </div>
              </form>
            </template>
          </Modal>
        </div>
        <div class="absolute" style="right: 3rem; top: 115px">
          <Modal
            title="❗️"
            btnTextSubmit="삭제"
            btnColorSubmit="bg-[#ff5a5a]"
            btnText="관리자 삭제"
            @submit="deleteAdmin"
          >
            <template v-slot:body>
              <div>
                <p>관리자를 삭제하시겠습니까?</p>
              </div>
            </template>
          </Modal>
        </div>
      </div>
      <div
        class="mt-2 bg-white dark:bg-gray-800 p-5 w-full rounded-md box-border border dark:border-gray-700"
        style="height: 700px"
      >
        <h2 class="font-bold text-lg text-gray-800 dark:text-gray-200">
          관리자 리스트
        </h2>
        <div class="wrapping-table mt-10">
          <table
            class="w-full text-sm text-left text-gray-500 dark:text-gray-400 lg:overflow-auto overflow-x-scroll"
          >
            <thead
              class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400"
            >
              <tr>
                <th scope="col" class="uppercase px-2 py-3"></th>
                <th scope="col" class="uppercase px-2 py-3">#</th>
                <th scope="col" class="uppercase px-7 py-3">이름</th>
                <th scope="col" class="uppercase px-7 py-3">아이디</th>
                <th scope="col" class="uppercase px-7 py-3">전화번호</th>
                <th scope="col" class="uppercase px-7 py-3">이메일</th>
              </tr>
            </thead>
            <tbody>
              <template v-for="items in responseList" :key="items.adminNo">
                <tr
                  class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 odd:bg-white even:bg-gray-50 hover:bg-[#ffeaea]"
                  v-if="items.adminDeleteYn === 'N'"
                >
                  <td class="px-6 py-4">
                    <input
                      type="radio"
                      :id="items.adminNo"
                      v-model="checkedId"
                      :value="items.adminNo"
                    />
                  </td>
                  <td class="px-6 py-4">
                    {{ items.adminNo }}
                  </td>
                  <td class="px-6 py-4">
                    {{ items.adminName }}
                  </td>
                  <td class="px-6 py-4">
                    {{ items.adminId }}
                  </td>
                  <td class="px-6 py-4">
                    {{ items.adminTel }}
                  </td>
                  <td class="px-6 py-4">
                    {{ items.adminEmail }}
                  </td>
                </tr>
              </template>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
  <!-- 페이징 -->
  <div
    v-if="responseList.length > 0"
    class="flex justify-center mt-10"
    style="height: 280px"
  >
    <div>
      <nav
        class="isolate inline-flex -space-x-px rounded-md shadow-sm"
        aria-label="Pagination"
      >
        <!-- 이전 페이지 존재 -->
        <a
          v-if="
            pagingUtil.startPage === pagingUtil.pageNumber &&
            !pagingUtil.existPrePageGroup
          "
          class="relative inline-flex items-center rounded-l-md px-2 py-2 text-gray-400 ring-1 ring-inset ring-gray-300 focus:z-20 focus:outline-offset-0"
        >
          <span class="sr-only">Previous</span>
          <svg
            class="h-5 w-5"
            viewBox="0 0 20 20"
            fill="currentColor"
            aria-hidden="true"
          >
            <path
              fill-rule="evenodd"
              d="M12.79 5.23a.75.75 0 01-.02 1.06L8.832 10l3.938 3.71a.75.75 0 11-1.04 1.08l-4.5-4.25a.75.75 0 010-1.08l4.5-4.25a.75.75 0 011.06.02z"
              clip-rule="evenodd"
            />
          </svg>
        </a>
        <a
          v-else
          @click="clickPage(pagingUtil.pageNumber - 1)"
          class="relative inline-flex items-center rounded-l-md px-2 py-2 text-gray-700 ring-1 ring-inset ring-gray-300 hover:bg-gray-50 focus:z-20 focus:outline-offset-0"
        >
          <span class="sr-only">Previous</span>
          <svg
            class="h-5 w-5"
            viewBox="0 0 20 20"
            fill="currentColor"
            aria-hidden="true"
          >
            <path
              fill-rule="evenodd"
              d="M12.79 5.23a.75.75 0 01-.02 1.06L8.832 10l3.938 3.71a.75.75 0 11-1.04 1.08l-4.5-4.25a.75.75 0 010-1.08l4.5-4.25a.75.75 0 011.06.02z"
              clip-rule="evenodd"
            />
          </svg>
        </a>

        <!-- 페이지 번호 -->
        <span
          v-for="page in pagingUtil.endPage - pagingUtil.startPage + 1"
          :key="page"
        >
          <a
            v-if="pagingUtil.pageNumber === page + pagingUtil.startPage - 1"
            @click="clickPage(page + pagingUtil.startPage - 1)"
            class="relative z-10 inline-flex items-center bg-primary text-white px-4 py-2 text-sm font-semibold text-black focus:z-20 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-gray-700"
          >
            {{ page + pagingUtil.startPage - 1 }}
          </a>
          <a
            v-else
            @click="clickPage(page + pagingUtil.startPage - 1)"
            aria-current="page"
            class="relative inline-flex items-center px-4 py-2 text-sm font-semibold text-gray-900 ring-1 ring-inset ring-gray-300 hover:bg-gray-50 focus:z-20 focus:outline-offset-0"
          >
            {{ page + pagingUtil.startPage - 1 }}
          </a>
        </span>

        <!-- 다음 페이지 존재 -->
        <a
          v-if="
            pagingUtil.endPage === pagingUtil.pageNumber &&
            !pagingUtil.existNextPageGroup
          "
          class="relative inline-flex items-center rounded-r-md px-2 py-2 text-gray-400 ring-1 ring-inset ring-gray-300 focus:z-20 focus:outline-offset-0"
        >
          <span class="sr-only">Next</span>
          <svg
            class="h-5 w-5"
            viewBox="0 0 20 20"
            fill="currentColor"
            aria-hidden="true"
          >
            <path
              fill-rule="evenodd"
              d="M7.21 14.77a.75.75 0 01.02-1.06L11.168 10 7.23 6.29a.75.75 0 111.04-1.08l4.5 4.25a.75.75 0 010 1.08l-4.5 4.25a.75.75 0 01-1.06-.02z"
              clip-rule="evenodd"
            />
          </svg>
        </a>
        <a
          v-else
          @click="clickPage(pagingUtil.pageNumber + 1)"
          class="relative inline-flex items-center rounded-r-md px-2 py-2 text-gray-700 ring-1 ring-inset ring-gray-300 hover:bg-gray-50 focus:z-20 focus:outline-offset-0"
        >
          <span class="sr-only">Next</span>
          <svg
            class="h-5 w-5"
            viewBox="0 0 20 20"
            fill="currentColor"
            aria-hidden="true"
          >
            <path
              fill-rule="evenodd"
              d="M7.21 14.77a.75.75 0 01.02-1.06L11.168 10 7.23 6.29a.75.75 0 111.04-1.08l4.5 4.25a.75.75 0 010 1.08l-4.5 4.25a.75.75 0 01-1.06-.02z"
              clip-rule="evenodd"
            />
          </svg>
        </a>
      </nav>
    </div>
  </div>
</template>
<script setup>
import { ref } from "vue";
import Modal from "@/components/AdminModal.vue";
// import store from "@/store/index";
import { getList, deleteA, addA } from "@/api/manageadmin";

const responseList = ref([]);
let pagingUtil = ref({});

let pageNum = 1;

const init = async () => {
  let requestParam = {
    page: pageNum,
  };
  let response = await getList(requestParam);
  console.log(response.data);
  responseList.value = response.data.adminUserList;
  pagingUtil.value = response.data.pagingUtil;
};
init();

const clickPage = async (page) => {
  pageNum = page;
  init();
};

const checkedId = ref(null);
const deleteAdmin = async () => {
  let requestData = {
    adminNo: checkedId.value,
    adminDeleteYn: "Y",
  };
  let responseDel = await deleteA(requestData);
  if (responseDel.status == 200) {
    window.location.reload();
  }
  init();
};

const addAdmin = async () => {
  const adminName = document.getElementById("nAdminName").value;
  const adminId = document.getElementById("nAdminId").value;
  const adminPassword = document.getElementById("nAdminPassword").value;
  const adminEmail = document.getElementById("nAdminEmail").value;
  const adminTel = document.getElementById("nAdminTel").value;
  let requestData = {
    adminName: adminName,
    adminId: adminId,
    adminPassword: adminPassword,
    adminEmail: adminEmail,
    adminTel: adminTel,
  };
  await addA(requestData);
  init();
};
</script>
