<template>
	<div class="dashboard p-4">
		<div class="mt-2 w-full">
			<div
				class="lg:flex grid-cols-1 lg:space-y-0 space-y-3 gap-5 justify-between"
			>
				<div>
					<p class="uppercase text-xs text-gray-700 font-semibold">
						overview
					</p>
					<h1
						class="text-2xl text-gray-900 dark:text-gray-200 font-medium"
					>
						Dashboard
					</h1>
				</div>
				<div class="flex gap-2">
					<button
						class="bg-white dark:bg-gray-800 hover:border-gray-200 dark:hover:bg-gray-700 dark:text-white dark:border-gray-700 border rounded py-2 px-5"
					>
						New view
					</button>
					<button
						class="bg-primary border flex gap-2 text-white hover:bg-primary/80 dark:border-gray-700 rounded py-3 px-5"
					>
						<span class="icon text-2xl"
							><Icon icon="ic:twotone-plus"
						/></span>
						<span class="text"> Create New Report</span>
					</button>
				</div>
			</div>
		</div>

		<!-- end wrapper card -->

		<!-- 동아리 회원수 차트 -->
		<div class="mt-2 lg:flex block lg:gap-2 relative">
			<div
				class="mt-2 bg-white dark:bg-gray-800 p-0 w-full rounded-md box-border border dark:border-gray-700"
			>
				<div class="p-5 flex justify-between">
					<div>
						<h2
							class="font-medium text-sm text-gray-800 dark:text-gray-200"
						>
							활동중인 회원수
						</h2>
						<h1
							class="font-semibold text-4xl text-gray-800 dark:text-gray-200"
						>
							20명
						</h1>

						<p class="text-gray-400 font-lexend font-normal">
							your sales chart per-years
						</p>
					</div>
				</div>

				<apexchart
					width="100%"
					height="260"
					type="area"
					:options="optionsArea"
					:series="seriesArea"
					:sparkline="{
						enabled: true,
					}"
				></apexchart>
				<br />
				<hr />
				<div class="wrapper-button p-5 flex justify-between mt-3">
					<select
						v-model="selectedYear"
						class="dark:bg-gray-800 dark:hover:bg-gray-700 dark:border-gray-700 border max-w-lg px-4 py-3 block rounded-md text-gray-500 dark:text-gray-400"
					>
						<option
							v-for="(year, index) in optionsArea.years"
							:value="year.value"
							:key="index"
						>
							{{ year.name }}년
						</option>
					</select>
				</div>
			</div>
		</div>
		<!-- end of 동아리 회원수 차트 -->

		<!-- 지원금 내역 차트 -->
		<div class="mt-2 lg:flex block lg:gap-2">
			<div
				class="mt-2 bg-white dark:bg-gray-800 p-0 w-full rounded-md box-border border dark:border-gray-700"
			>
				<div class="head p-5">
					<h2
						class="font-bold text-lg text-gray-800 dark:text-gray-200"
					>
						5,355
					</h2>
					<p class="text-gray-400 font-lexend font-normal">
						Visitor this week
					</p>

					<span class="float-right">
						<h2 class="text-green-500 -mt-12 flex">
							<span class="mr-2"> 47.9% </span
							><span>
								<Icon icon="akar-icons:arrow-up" />
							</span>
						</h2>
					</span>
				</div>
				<div class="wrapper-chart mt-5">
					<apexchart
						width="100%"
						height="380"
						type="area"
						:options="optionsVisitor"
						:series="seriesVisitor"
					></apexchart>
					<br />
					<hr />
					<div class="footer p-5">
						<div class="wrapper-button flex justify-between mt-3">
							<select
								name=""
								id=""
								class="dark:bg-gray-800 dark:hover:bg-gray-700 dark:border-gray-700 border max-w-lg px-4 py-3 block rounded-md text-gray-500 dark:text-gray-400"
							>
								<option value="">0000년</option>
							</select>
							<button
								class="uppercase border-b border-red-600 text-red-600"
							>
								Vistor Report
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- end of 지원금 내역 차트 -->
	</div>
</template>

<script setup>
	import { Icon } from "@iconify/vue";
	import axios from "axios";
	import { ref } from "vue";

	// chart data area
	const seleted = "";
	const selectedYear = ref([]); // 현재 년도로 초기화
	selectedYear.value = new Date().getFullYear().toString();
	const optionsArea = ref({
		xaxis: {
			categories: [],
		},
		fontFamily: "Segoe UI, sans-serif",
		stroke: {
			curve: "straight",
		},
		chart: {
			toolbar: {
				show: true,
			},
			zoom: {
				enabled: false,
			},
			sparkline: {
				enabled: true,
			},
		},
		markers: {
			size: 0,
		},
		years: [], // 선택 가능한 년도 목록
		yaxis: {
			show: false,
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
		chartData: [],
	});

	const chart = {
		fontFamily: "lexend, sans-serif",
	};

	const seriesArea = ref([
		{
			name: "",
			data: [],
		},
	]);

	const optionsVisitor = {
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
	};

	const seriesVisitor = [
		{
			name: "Visitor ",
			data: [30, 40, 45, 50, 49, 60, 70, 91],
		},
	];

	// end chart data line

	const components = {
		Icon,
	};

	async function fetchChartData() {
		const clubNo = 1;
		try {
			const response = await axios.get(
				`http://localhost:8082/clubmenagement/clubdashboard/${clubNo}`
			);
			const data = response.data; // 응답 데이터에서 필요한 데이터
			let yearList = Object.keys(data);
			// console.log(yearList);
			// console.log(Object.values(data));

			const detailData = Object.values(data);
			// console.log(detailData[0][0]);

			for (let [key, value] of Object.entries(data)) {
				console.log(key);
				console.log(value);
			}

			for (let i = 0; i < yearList.length; i++) {
				let obj = {};
				obj["name"] = yearList[i];
				obj["value"] = yearList[i];
				optionsArea.value.years.push(obj);
			}

			const chartData = []; // 차트 배열

			// 년도별로 데이터
			for (let i = 0; i < yearList.length; i++) {
				const yearData = data[yearList[i]];
				const monthCounts = [];

				// 해당 년도의 월별 데이터
				for (let j = 0; j < yearData.length; j++) {
					const { month, count } = yearData[j];
					monthCounts.push(count); // 월별 데이터의 count 값 배열에 추가
				}

				// 차트
				const chartEntry = {
					name: "활동중인 회원 수",
					data: monthCounts, // 추출된 월별 count 값
				};

				chartData.push(chartEntry); // 변환된 데이터를 차트 데이터 배열에 추가
			}

			// 차트 설정 업데이트
			optionsArea.value.xaxis.categories = Object.keys(data["2023"]).map(
				(month) => `${month}월`
			);
			seriesArea.value = chartData;
		} catch (error) {
			console.error(
				"차트 데이터를 가져오는 중 오류가 발생했습니다:",
				error.response
			);
		}
	}
	fetchChartData();
</script>
