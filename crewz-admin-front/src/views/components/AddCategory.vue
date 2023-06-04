<template>
  <!-- Search component Alert with name of type,  press Ctrl + F -->

  <div class="alert h-auto p-3">
    <nav class="flex" aria-label="Breadcrumb">
      <ol class="inline-flex items-center space-x-1 md:space-x-3">
        <li class="inline-flex items-center">
          <a
            href="#"
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
            동아리 관리
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
              href="#"
              class="ml-1 text-sm font-medium text-gray-700 hover:text-gray-900 md:ml-2 dark:text-gray-400 dark:hover:text-white"
              >카테고리 관리</a
            >
          </div>
        </li>
      </ol>
    </nav>
    <!-- end nav -->
    <div class="mt-5 w-full">
      <div>
        <span class="text-2xl text-gray-900 font-medium dark:text-gray-200">
          카테고리 관리
        </span>
        <div class="absolute" style="left: 185px; top: 115px">
          <Modal
            title="카테고리 등록"
            btnTextClose="취소"
            btnTextSubmit="등록"
            btnText="카테고리 등록"
            @submit="addCategory"
          >
            <template v-slot:body>
              <div class="space-y-5 pb-5">
                <div class="space-y-3">
                  <p>카테고리 명</p>
                  <input
                    type="text"
                    placeholder=""
                    name="categoryName"
                    class="p-2 border dark:border-gray-600 dark:bg-gray-700 w-full rounded outline-none"
                  />
                </div>
              </div>
            </template>
          </Modal>
        </div>
      </div>
      <div
        class="mt-2 bg-white dark:bg-gray-800 p-5 w-full rounded-md box-border border dark:border-gray-700"
      >
        <h2 class="font-bold text-lg text-gray-800 dark:text-gray-200">
          카테고리 리스트
        </h2>
        <div class="wrapping-table mt-10">
          <table
            class="w-full text-sm text-left text-gray-500 dark:text-gray-400 lg:overflow-auto overflow-x-scroll"
          >
            <thead
              class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400"
            >
              <tr>
                <th scope="col" class="uppercase px-6 py-3">카테고리 번호</th>
                <th scope="col" class="uppercase px-6 py-3">카테고리 이름</th>
                <th scope="col" class="uppercase px-6 py-3">
                  카테고리 삭제여부
                </th>
                <th scope="col" class="uppercase px-6 py-3">
                  카테고리 생성일자
                </th>
                <th scope="col" class="uppercase px-6 py-3">카테고리 정보</th>
              </tr>
            </thead>
            <tbody>
              <tr
                class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 odd:bg-white even:bg-gray-50"
                v-for="items in responseList"
                :key="items.categoryNo"
              >
                <td class="px-6 py-4">
                  {{ items.categoryNo }}
                </td>
                <td class="px-6 py-4">
                  {{ items.categoryName }}
                </td>
                <td class="px-6 py-4">
                  {{ items.categoryDeleteFlag }}
                </td>
                <td class="px-6 py-4">
                  {{ items.categoryCreateDate }}
                </td>
                <td>
                  <Modal
                    title="사용중인 동아리"
                    btnText="확인"
                    btnTextSubmit="카테고리 삭제"
                    @click="getBoardList(items.categoryNo)"
                    @submit="deleteCategory(items.categoryNo)"
                    :showCancelButton="false"
                  >
                    <template v-slot:body>
                      <div class="flex">
                        <div class="w-full p-4">
                          <table
                            class="w-full text-sm text-left text-gray-500 dark:text-gray-400 lg:overflow-auto overflow-x-scroll"
                          >
                            <thead
                              class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400"
                            >
                              <tr>
                                <th scope="col" class="uppercase px-6 py-3">
                                  동아리명
                                </th>
                              </tr>
                            </thead>
                            <tbody>
                              <tr
                                class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 odd:bg-white even:bg-gray-50"
                                :key="board.boardNo"
                                v-for="board in boardList"
                              >
                                <td class="px-6 py-4">
                                  {{ board.boardTitle }}
                                </td>
                              </tr>
                            </tbody>
                          </table>
                        </div>
                      </div>
                    </template>
                  </Modal>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
  <!-- 페이징 -->
  <div v-if="responseList.length > 0" class="flex justify-center mt-10">
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
import axios from "axios";
import { ref } from "vue";
import Modal from "@/components/AdminModal.vue";
const responseList = ref([]);
const boardList = ref([]);
let pagingUtil = ref({});
try {
  axios
    .get("http://localhost:8082/api/category/all")
    .then((res) => {
      console.log(res);
      responseList.value = res.data.categoryInfoList;
      pagingUtil.value = res.data.pagingUtil;
    })
    .catch((Error) => {
      console.log(Error);
    });
} catch (error) {
  console.log(error);
}

const clickPage = async (page) => {
  try {
    axios
      .get("http://localhost:8082/api/category/all?page=" + page)
      .then((res) => {
        responseList.value = res.data.categoryInfoList;
        pagingUtil.value = res.data.pagingUtil;
      })
      .catch((Error) => {
        console.log(Error);
      });
  } catch (error) {
    console.log(error);
  }
};

const addCategory = () => {
  const categoryName = document.getElementsByName("categoryName")[0].value;
  try {
    axios
      .post("http://localhost:8082/api/category/", {
        categoryName: categoryName,
      })
      .then(function (res) {
        if (res.status === 200) {
          window.location.reload();
        }
      })
      .catch(function (Error) {
        console.log(Error);
      });
  } catch (error) {
    console.log(error);
  }
};
const getBoardList = async (categoryNo) => {
  try {
    axios
      .get("http://localhost:8082/api/category/" + categoryNo)
      .then((res) => {
        console.log(res);
        boardList.value = res.data;
      })
      .catch((Error) => {
        console.log(Error);
      });
  } catch (error) {
    console.log(error);
  }
};
const deleteCategory = (categoryNo) => {
  try {
    axios
      .delete("http://localhost:8082/api/category/" + categoryNo)
      .then(function (res) {
        if (res.status === 200) {
          window.location.reload();
        }
      })
      .catch(function (Error) {
        console.log(Error);
      });
  } catch (error) {
    console.log(error);
  }
};
</script>
