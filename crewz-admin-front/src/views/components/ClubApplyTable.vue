<template>
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
              >동아리 신청 목록</a
            >
          </div>
        </li>
      </ol>
    </nav>
    <!-- end nav -->
    <div class="mt-5 w-full">
      <div>
        <span class="text-2xl text-gray-900 font-medium dark:text-gray-200">
          동아리 신청 목록
        </span>
      </div>
      <div
        class="mt-2 bg-white dark:bg-gray-800 p-5 w-full rounded-md box-border border dark:border-gray-700"
      >
        <h2 class="font-bold text-lg text-gray-800 dark:text-gray-200">
          동아리 신청 리스트
        </h2>
        <div class="wrapper-button flex items-end mt-3">
          <select
            v-model="selectedKeyword"
            class="dark:bg-gray-800 dark:hover:bg-gray-700 dark:border-gray-700 border max-w-lg px-4 py-3 block rounded-md text-gray-500 dark:text-gray-400 ml-auto"
            @click="clickedKeyword(selectedKeyword)"
          >
            <option
              v-for="(keyword, index) in keywordList"
              :value="keyword.value"
              :key="index"
            >
              {{ keyword.text }}
            </option>
          </select>
        </div>
        <div class="wrapping-table mt-10">
          <table
            class="w-full text-sm text-left text-gray-500 dark:text-gray-400 lg:overflow-auto overflow-x-scroll"
          >
            <thead
              class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400"
            >
              <tr>
                <th
                  scope="col"
                  class="uppercase px-6 py-3"
                  style="width: 120px"
                >
                  신청자
                </th>
                <th scope="col" class="uppercase px-6 py-3">이메일</th>
                <th scope="col" class="uppercase px-6 py-3">동아리이름</th>
                <th scope="col" class="uppercase px-6 py-3">개설 목적</th>
                <th scope="col" class="uppercase px-6 py-3">활동 내용</th>
                <th
                  scope="col"
                  class="uppercase px-6 py-3"
                  style="width: 130px"
                >
                  신청 일자
                </th>
              </tr>
            </thead>
            <tbody>
              <tr
                class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 odd:bg-white even:bg-gray-50"
                v-for="items in responseList"
                :key="items.clubNo"
              >
                <td class="px-6 py-4" style="width: 120px">
                  {{ items.user.userName }}
                </td>
                <td class="px-6 py-4">
                  {{ items.user.userEmail }}
                </td>
                <td class="px-6 py-4">
                  {{ items.clubName }}
                </td>
                <td class="px-6 py-4">
                  {{ items.clubPurpose }}
                </td>
                <td class="px-6 py-4">
                  {{ items.clubActivities }}
                </td>
                <td class="px-6 py-4" style="width: 130px">
                  {{ formatDate(items.regdate) }}
                </td>
                <td
                  v-if="items.clubApproveYn === null"
                  class="px-6 py-4 flex space-x-2"
                  style="width: 200px"
                >
                  <div>
                    <button
                      class="bg-green-500 hover:bg-green-600 text-white font-bold rounded border flex text-sm gap-2 text-white py-3 px-5"
                      @click="clubApprove(items.clubApplyNo, items.user.userNo)"
                    >
                      승인
                    </button>
                  </div>
                  <Modal
                    title="반려 사유 등록"
                    btnTextSubmit="등록"
                    btnColor="bg-red-500 hover:bg-red-600 text-white font-bold rounded"
                    btnText="반려"
                    @submit="
                      submitRefuseReason(items.clubApplyNo, items.user.userNo)
                    "
                    :showCancelButton="false"
                  >
                    <template v-slot:body>
                      <div class="space-y-5 pb-5">
                        <div class="space-y-3">
                          <p>반려 사유를 등록해주세요</p>
                          <input
                            type="textarea"
                            name="refuseReason"
                            class="p-2 border dark:border-gray-600 dark:bg-gray-700 w-full rounded outline-none"
                            style="height: 200px"
                          />
                        </div>
                      </div>
                    </template>
                  </Modal>
                </td>
                <td
                  v-if="items.clubApproveYn === 'Y'"
                  colspan="2"
                  class="text-center"
                  style="width: 200px"
                >
                  <a
                    class="px-4 py-2 bg-green-500 text-white font-bold rounded disabled opacity-50 cursor-not-allowed"
                    >승인됨</a
                  >
                </td>
                <td
                  v-if="items.clubApproveYn === 'N'"
                  colspan="2"
                  class="text-center"
                  style="width: 100px"
                >
                  <a
                    class="px-4 py-2 bg-red-500 text-white font-bold rounded disabled opacity-50 cursor-not-allowed"
                    >반려됨</a
                  >
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
import { ref, watch } from "vue";
import Modal from "@/components/AdminModal.vue";
import { getList, updateApprove } from "@/api/clubapply.js";
const responseList = ref([]);
const selectedKeyword = ref("all");
const keywordList = ref([
  {
    text: "전체 리스트",
    value: "all",
  },
  {
    text: "대기 리스트",
    value: "wait",
  },
  {
    text: "승인 리스트",
    value: "approve",
  },
  {
    text: "반려 리스트",
    value: "refuse",
  },
]);

let pagingUtil = ref({});
let pageNum = 1;

const init = async () => {
  let requestParam = {
    page: pageNum,
  };
  let response = await getList(requestParam);
  console.log(response.data);
  responseList.value = response.data.clubApplyList;
  pagingUtil.value = response.data.pagingUtil;
};
init();

const clickPage = async (page) => {
  pageNum = page;
  init();
};

const formatDate = (dateStr) => {
  // Date 객체 생성
  var date = new Date(dateStr);

  // 연도, 월, 일 정보 추출
  var year = date.getFullYear();
  var month = String(date.getMonth() + 1).padStart(2, "0");
  var day = String(date.getDate()).padStart(2, "0");

  // 변환된 날짜
  var transformedDate = year + "-" + month + "-" + day;

  return transformedDate;
};
const clubApprove = async (clubApplyNo, userNo) => {
  let requestData = {
    clubApplyNo: clubApplyNo,
    userNo: userNo,
    clubRefuseReason: "",
    clubApproveYn: "Y",
  };
  await updateApprove(requestData);
  init();
};
const submitRefuseReason = async (clubApplyNo, userNo) => {
  const refuseReason = document.getElementsByName("refuseReason")[0].value;
  if (refuseReason == "") {
    alert("반려 사유를 입력해주세요.");
    return false;
  } else {
    let requestData = {
      clubApplyNo: clubApplyNo,
      userNo: userNo,
      clubRefuseReason: refuseReason,
      clubApproveYn: "N",
    };
    await updateApprove(requestData);
    init();
  }
};

// 리스트 변경
watch(
  () => selectedKeyword.value,
  async (newKeyword) => {
    let requestParam = {
      keyword: newKeyword,
    };
    let response = await getList(requestParam);
    responseList.value = response.data.clubApplyList;
    pagingUtil.value = response.data.pagingUtil;
  }
);

const clickedKeyword = (keyword) => {
  selectedKeyword.value = keyword;
};
</script>
