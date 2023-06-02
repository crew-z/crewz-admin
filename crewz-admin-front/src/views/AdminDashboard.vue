<template>
  <div class="dashboard p-4">
    <!-- grid wrapper card -->
    <div
      class="wrapper-card grid lg:grid-cols-2 grid-cols-1 md:grid-cols-2 gap-2 mt-0"
    >
      <!-- card  -->
      <div
        class="card bg-white dark:bg-gray-800 w-full rounded-md p-5 border dark:border-gray-700 flex"
      >
        <div class="p-2 max-w-sm">
          <img class="w-16 rounded-md" src="../assets/img/club.svg" alt="" />
        </div>

        <div class="block p-2 w-full">
          <p class="font-semibold text-gray-900 dark:text-gray-200 text-xl">
            {{ totalClubCount }}
          </p>
          <h2 class="font-normal text-gray-600 text-md mt-1">누적 동아리 수</h2>
        </div>
      </div>
      <!-- end card -->
      <div
        class="card bg-white dark:bg-gray-800 w-full rounded-md p-5 border dark:border-gray-700 flex"
      >
        <div class="p-2 max-w-sm">
          <img class="w-16 rounded-md" src="../assets/img/member.svg" alt="" />
        </div>
        <div class="block p-2 w-full">
          <p class="font-semibold text-gray-900 dark:text-gray-200 text-xl">
            {{ totalUserCount }}
          </p>
          <h2 class="font-normal text-gray-600 text-md mt-1">누적 회원수</h2>
        </div>
      </div>
      <!-- end card -->
    </div>
    <!-- end wrapper card -->
    <div class="mt-4 lg:flex block lg:gap-2 relative">
      <div
        class="bg-white dark:bg-gray-800 p-0 w-full rounded-md box-border border dark:border-gray-700"
      >
        <div class="p-5 flex justify-between">
          <div>
            <h1 class="font-bold text-2xl text-gray-800 dark:text-gray-200">
              현재 운영중인 동아리 수
            </h1>
            <p class="text-gray-400 font-lexend font-normal">분기별</p>
          </div>
        </div>

        <div class="pl-4 flex justify-center">
          <apexchart
            class="w-full"
            height="240"
            type="area"
            :options="optionsArea"
            :series="seriesArea"
          ></apexchart>
        </div>

        <br />
      </div>
    </div>
    <div class="mt-2 lg:flex block lg:gap-2">
      <div
        class="mt-2 bg-white dark:bg-gray-800 p-0 w-full rounded-md box-border border dark:border-gray-700"
      >
        <div class="head p-5">
          <h2 class="font-bold text-2xl text-gray-800 dark:text-gray-200">
            지원금 통계
          </h2>
          <p class="text-gray-400 font-lexend font-normal">분기별</p>

          <span class="float-right">
            <h2 class="text-green-500 -mt-12 flex">
              <span class="mr-2"> 10,000원 증가 </span>
            </h2>
          </span>
        </div>
        <div class="wrapper-chart mt-5">
          <apexchart
            width="100%"
            height="280"
            type="area"
            :options="optionsVisitor"
            :series="seriesVisitor"
          ></apexchart>
          <br />
        </div>
      </div>
      <div
        class="mt-2 bg-white dark:bg-gray-800 p-0 w-full rounded-md box-border border dark:border-gray-700"
      >
        <div class="head p-5">
          <h2 class="font-bold text-2xl text-gray-800 dark:text-gray-200">
            카테고리별 동아리
          </h2>
          <!-- <p class="text-gray-400 font-lexend font-normal">부제목</p> -->
        </div>

        <div v-if="loaded" class="wrapper-chart mt-5">
          <apexchart
            width="100%"
            height="280"
            type="pie"
            :options="optionsDonut"
            :series="seriesDonut"
          ></apexchart>
          <div class="p-3"></div>
          <br />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import axios from "axios";
import { ref } from "vue";

const totalClubCount = ref("");
const totalUserCount = ref("");
const clubCountByCategory = ref({});
const totalPriceByQuarter = ref({});
const loaded = ref(false);

//const name = "AdminDashboard";

const optionsArea = {
  xaxis: {
    categories: [
      "22Q1",
      "22Q2",
      "22Q3",
      "22Q4",
      "23Q1",
      "23Q2",
      "23Q3",
      "23Q4",
    ],
    labels: {
      show: true,
      style: {
        colors: "#000000",
        fontSize: "14px",
      },
    },
  },
  yaxis: {
    show: false,
  },
  fontFamily: "Segoe UI, sans-serif",
  stroke: {
    curve: "smooth",
  },
  chart: {
    toolbar: {
      show: false,
    },
    zoom: {
      enabled: false,
    },
    sparkline: {
      enabled: false,
    },
  },
  dataLabels: {
    enabled: true,
  },
  fill: {
    type: "gradient",
    gradient: {
      shadeIntensity: 0,
      opacityFrom: 0.2,
      opacityTo: 0.5,
      stops: [0, 90, 100],
    },
  },
};

const seriesArea = [
  {
    name: "운영중인 동아리",
    data: [20, 30, 45, 60, 50, 60, 70, 81],
  },
];

const optionsVisitor = {
  chart: {
    toolbar: {
      show: false,
    },
    zoom: {
      enabled: false,
    },
    sparkline: {
      enabled: false,
    },
  },
  legend: {
    show: true,
  },
  xaxis: {
    categories: [
      "22Q1",
      "22Q2",
      "22Q3",
      "22Q4",
      "23Q1",
      "23Q2",
      "23Q3",
      "23Q4",
    ],
  },
  // xaxis: {
  //   show: true,
  // },
  yaxis: {
    show: false,
  },
  colors: ["#4f46e5"],
  dataLabels: {
    enabled: true,
  },
  fill: {
    type: "gradient",
    gradient: {
      shadeIntensity: 0,
      opacityFrom: 0,
      opacityTo: 0.3,
      stops: [0, 90, 100],
    },
  },
  stroke: {
    curve: "smooth",
  },
};

const seriesVisitor = ref([
  {
    name: "Visitor",
    data: [10, 20, 30, 40, 40, 30, 20, 10],
  },
]);

const optionsDonut = ref({
  chart: {
    type: "donut",
  },
  legend: {
    show: true,
    fontSize: "16px",
    formatter: function (val, opts) {
      console.log(val);
      console.log(opts);
      return val + ": " + opts.w.globals.series[opts.seriesIndex];
    },
    itemMargin: {
      horizontal: 1,
    },
  },
  labels: [],
});

const seriesDonut = ref([]);

axios
  .get("http://localhost:8082/api/main")
  .then((res) => {
    totalClubCount.value = res.data.totalClubCount;
    totalUserCount.value = res.data.totalUserCount;
    clubCountByCategory.value = res.data.clubCountByCategory;
    totalPriceByQuarter.value = res.data.totalPriceByQuarter;

    for (let i = 0; i < clubCountByCategory.value.length; i++) {
      optionsDonut.value.labels.push(clubCountByCategory.value[i][0]);
      seriesDonut.value.push(clubCountByCategory.value[i][1]);
    }

    // for (let i = 0; i < totalPriceByQuarter.value.length; i++) {
    //   seriesVisitor.value.push(totalPriceByQuarter.value[i][1]);
    // }
    // console.log(clubCountByCategory.value);
    // console.log(clubCountByCategory.value[0][0]);
    // console.log(clubCountByCategory.value[0][1]);
    // console.log(clubCountByCategory.value[1][0]);
    // console.log(clubCountByCategory.value[1][1]);
    // console.log(clubCountByCategory.value[2][0]);
    // console.log(clubCountByCategory.value[2][1]);
    // optionsDonut.value.labels.push(clubCountByCategory.value[0][0]);
    console.log(optionsDonut.value.labels[0]);
    loaded.value = true;
  })
  .catch((err) => console.error(err));
</script>
