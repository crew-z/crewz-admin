<template>
  <div class="dashboard p-4">
    <!-- grid wrapper card -->
    <div
      class="wrapper-card grid lg:grid-cols-3 grid-cols-1 md:grid-cols-3 gap-4 mt-0"
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
            {{ Math.round(animatedTotalClubCount) }}개
          </p>
          <h2 class="font-normal text-gray-600 text-md mt-1">누적 동아리</h2>
        </div>
      </div>
      <!-- end card -->
      <div
        class="card bg-white dark:bg-gray-800 w-full rounded-md p-5 border dark:border-gray-700 flex"
      >
        <div class="p-2 max-w-sm">
          <img
            class="w-16 rounded-md"
            src="../assets/img/operating.svg"
            alt=""
          />
        </div>

        <div class="block p-2 w-full">
          <p class="font-semibold text-gray-900 dark:text-gray-200 text-xl">
            {{ Math.round(animatedTotalOperatingClubs) }}개
          </p>
          <h2 class="font-normal text-gray-600 text-md mt-1">
            운영중인 동아리
          </h2>
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
            {{ Math.round(animatedTotalUserCount) }}명
          </p>
          <h2 class="font-normal text-gray-600 text-md mt-1">전체 회원</h2>
        </div>
      </div>
      <!-- end card -->
    </div>

    <div class="mt-4 lg:flex block lg:gap-2 relative">
      <div
        class="bg-white dark:bg-gray-800 p-0 w-full rounded-md box-border border dark:border-gray-700"
      >
        <div class="head p-5">
          <h1 class="font-bold text-2xl text-gray-800 dark:text-gray-200">
            분기별 동아리 추이
          </h1>
        </div>
        <div v-if="loaded" class="wrapper-chart p-4">
          <apexchart
            width="100%"
            height="380"
            type="bar"
            :options="optionsClubCountBar"
            :series="seriesClubCountBar"
          ></apexchart>
        </div>
      </div>
    </div>

    <div class="mt-2 lg:flex block lg:gap-4">
      <div
        class="mt-2 bg-white dark:bg-gray-800 p-0 w-full rounded-md box-border border dark:border-gray-700"
      >
        <div class="head p-5">
          <h2 class="font-bold text-2xl text-gray-800 dark:text-gray-200">
            지원금 통계
          </h2>
          <p class="text-gray-400 font-lexend font-normal">분기별</p>
        </div>
        <div v-if="loaded" class="wrapper-chart mt-5">
          <apexchart
            width="100%"
            height="280"
            type="area"
            :options="optionsSubsidyLine"
            :series="seriesSubsidyLine"
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
        </div>

        <div v-if="loaded" class="wrapper-chart mt-5">
          <apexchart
            width="100%"
            height="280"
            type="pie"
            :options="optionsCategoryDonut"
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
import { ref } from "vue";
import { getDashboard } from "@/api/dashboard.js";

const totalClubCount = ref("");
const totalUserCount = ref("");
const totalOperatingClubs = ref("");
const countByQuarterOfClubCreateDate = ref("");
const countByQuarterOfClubCloseDate = ref("");
const clubCountByCategory = ref({});
const totalPriceByQuarter = ref({});

const animatedTotalClubCount = ref(0);
const animatedTotalOperatingClubs = ref(0);
const animatedTotalUserCount = ref(0);

const loaded = ref(false);

const optionsArea = ref({
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
  xaxis: {
    categories: [],
    labels: {
      show: true,
    },
  },
  yaxis: {
    show: false,
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
  stroke: {
    curve: "smooth",
  },
});

const seriesArea = ref([
  {
    name: "운영중인 동아리",
    data: [],
  },
]);

const optionsSubsidyLine = ref({
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
  xaxis: {
    categories: [],
  },
  yaxis: {
    show: false,
  },
  dataLabels: {
    enabled: false,
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
  stroke: {
    curve: "smooth",
  },
});

const seriesSubsidyLine = ref([
  {
    name: "지원금",
    data: [],
  },
]);

const optionsClubCountBar = ref({
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
  xaxis: {
    categories: [],
  },
  yaxis: {
    show: false,
  },
  colors: ["#1C5BFF", "#FF3D3D"],
  dataLabels: {
    enabled: true,
  },
  stroke: {
    curve: "straight",
  },
});

const seriesClubCountBar = ref([
  {
    name: "생성된 동아리",
    data: [],
  },
  {
    name: "삭제된 동아리",
    data: [],
  },
]);

const optionsCategoryDonut = ref({
  chart: {
    type: "donut",
  },
  legend: {
    show: true,
    formatter: function (val, opts) {
      return val + ": " + opts.w.globals.series[opts.seriesIndex];
    },
    itemMargin: {
      horizontal: 1,
    },
  },
  labels: [],
});

const seriesDonut = ref([]);

function animateCount(target, animatedRef) {
  const stepTime = 10; // 20 milliseconds between each step
  const totalSteps = 100; // total number of animation steps
  const increment = target / totalSteps; // amount to increment at each step

  const intervalId = setInterval(() => {
    if (animatedRef.value < target) {
      animatedRef.value += increment;
    } else {
      animatedRef.value = target; // ensure it doesn't go over target
      clearInterval(intervalId); // stop the interval
    }
  }, stepTime);
}

const init = async () => {
  const res = await getDashboard();
  totalClubCount.value = res.data.totalClubCount;
  totalUserCount.value = res.data.totalUserCount;
  totalOperatingClubs.value = res.data.totalOperatingClubs;
  animateCount(totalClubCount.value, animatedTotalClubCount); // start the animation for totalClubCount
  animateCount(totalOperatingClubs.value, animatedTotalOperatingClubs); // start the animation for totalOperatingClubs
  animateCount(totalUserCount.value, animatedTotalUserCount);
  clubCountByCategory.value = res.data.clubCountByCategory;
  totalPriceByQuarter.value = res.data.totalPriceByQuarter;
  countByQuarterOfClubCreateDate.value =
    res.data.countByQuarterOfClubCreateDate;
  countByQuarterOfClubCloseDate.value = res.data.countByQuarterOfClubCloseDate;

  for (let i = 0; i < clubCountByCategory.value.length; i++) {
    optionsCategoryDonut.value.labels.push(clubCountByCategory.value[i][0]);
    seriesDonut.value.push(clubCountByCategory.value[i][1]);
  }

  for (let i = 0; i < totalPriceByQuarter.value.length; i++) {
    optionsArea.value.xaxis.categories.push(totalPriceByQuarter.value[i][0]);
    seriesArea.value[0].data.push(totalPriceByQuarter.value[i][1]);
    optionsSubsidyLine.value.xaxis.categories.push(
      totalPriceByQuarter.value[i][0]
    );
    seriesSubsidyLine.value[0].data.push(totalPriceByQuarter.value[i][1]);
  }

  for (let i = 0; i < countByQuarterOfClubCreateDate.value.length; i++) {
    optionsClubCountBar.value.xaxis.categories.push(
      countByQuarterOfClubCreateDate.value[i][0]
    );
    seriesClubCountBar.value[0].data.push(
      countByQuarterOfClubCreateDate.value[i][1]
    );
    seriesClubCountBar.value[1].data.push(
      countByQuarterOfClubCloseDate.value[i][1]
    );
  }

  loaded.value = true;
};

init();
</script>
