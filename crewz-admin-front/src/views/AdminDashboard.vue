<template>
  <div class="dashboard p-4">
    <!-- end nav -->
    <!-- <div class="mt-2 w-full">
      <div
        class="lg:flex grid-cols-1 lg:space-y-0 space-y-3 gap-5 justify-between"
      >
        <div class="flex gap-2"></div>
      </div>
    </div> -->

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
            100
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
            256
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
              현재 운영중인 동아리
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

        <div class="wrapper-chart mt-5">
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

<script>
// @ is an alias to /src
import { Icon } from "@iconify/vue";

export default {
  name: "AdminDashboard",
  data() {
    return {
      // 현재 운영중인 동아리
      optionsArea: {
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
      },

      chart: {
        fontFamily: "lexend, sans-serif",
      },

      seriesArea: [
        {
          name: "운영중인 동아리",
          data: [20, 30, 45, 60, 50, 60, 70, 81],
        },
      ],
      optionsBar: {
        chart: {
          toolbar: {
            show: false,
          },
          zoom: {
            enabled: false,
          },
          sparkline: {
            enabled: true,
          },
        },
        legend: {
          show: false,
        },
        xaxis: {
          show: false,
        },
        yaxis: {
          show: false,
        },
        colors: ["#4f46e5", "#DC2626"],
        dataLabels: {
          enabled: false,
        },
        stroke: {
          curve: "straight",
        },
      },

      // 지원금 통계
      optionsVisitor: {
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
          show: false,
        },
        xaxis: {
          show: false,
        },
        yaxis: {
          show: false,
        },
        colors: ["#4f46e5"],
        dataLabels: {
          enabled: false,
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
      },

      seriesVisitor: [
        {
          name: "Visitor ",
          data: [80, 40, 45, 50, 20, 60, 70, 91],
        },
      ],

      // 카테고리별 동아리
      optionsDonut: {
        chart: {
          type: "donut",
        },
        legend: {
          show: true,
          fontSize: "16px",
          formatter: function (val, opts) {
            return val + ": " + opts.w.globals.series[opts.seriesIndex];
          },
          itemMargin: {
            horizontal: 1,
          },
        },
        labels: ["운동", "게임", "스터디", "카테고리4", "기타등등"],
      },

      seriesDonut: [30, 20, 10, 5, 20],
    };
    // end chart data line
  },
  components: {
    Icon,
  },
  mounted() {},
};
</script>
