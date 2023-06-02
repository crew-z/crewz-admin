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
					v-if="load"
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
						@click="clickedYear(selectedYear)"
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
		<!-- end of 지원금 내역 차트 -->
	</div>
</template>

<script setup>
	import { Icon } from "@iconify/vue";
	import axios from "axios";
	import { ref, watch } from "vue";

	// chart data area
	const seleted = "";
	const selectedYear = ref([]); // 현재 년도로 초기화
	let currentYear = new Date().getFullYear().toString();
	selectedYear.value = currentYear;
	const months = [];

	const load = ref(false);

	const optionsArea = ref({
		chart: {
			height: 350,
			type: "line",
			dropShadow: {
				enabled: true,
				color: "#000",
				top: 18,
				left: 7,
				blur: 10,
				opacity: 0.2,
			},
			toolbar: {
				show: false,
			},
			sparkline: false,
		},
		colors: ["#77B6EA"],
		dataLabels: {
			enabled: true,
		},
		stroke: {
			curve: "smooth",
		},
		title: {
			text: "Average High & Low Temperature",
			align: "left",
		},
		grid: {
			borderColor: "#e7e7e7",
			row: {
				colors: ["#f3f3f3"], // takes an array which will be repeated on columns
				opacity: 0.5,
			},
		},
		markers: {
			size: 1,
		},
		xaxis: {
			categories: [],
			title: {
				text: "Month",
			},
		},
		years: [], // 선택 가능한 년도 목록
		yaxis: {
			title: {
				text: "동아리 회원수",
			},
			min: 0,
			max: 15,
		},
		legend: {
			position: "top",
			horizontalAlign: "right",
			floating: true,
			offsetY: -25,
			offsetX: -5,
		},

		chartData: [],
	});

	const chart = {
		fontFamily: "lexend, sans-serif",
	};

	const seriesArea = ref([
		{
			name: "회원수",
			data: [],
		},
	]);

	// end chart data line

	// 년도 변경
	watch(
		() => selectedYear.value,
		(newValue) => {
			currentYear = newValue;
			fetchChartData();
		}
	);

	const clickedYear = (year) => {
		selectedYear.value = year.toString();
	};

	async function fetchChartData() {
		const clubNo = 1;
		try {
			optionsArea.value.years = [];
			months.length = 0;

			const response = await axios.get(
				`http://localhost:8082/clubmenagement/clubdashboard/${clubNo}`
			);
			const data = response.data; // 응답 데이터에서 필요한 데이터

			let yearList = Object.keys(data);

			for (let i = 0; i < yearList.length; i++) {
				let obj = {};
				obj["name"] = yearList[i];
				obj["value"] = yearList[i];
				optionsArea.value.years.push(obj);
			}

			const chartData = []; // 차트 배열

			// 년도별 데이터

			const yearData = data[currentYear];
			const monthCounts = [];

			// 해당 년도의 월별 데이터
			for (let j = 0; j < yearData.length; j++) {
				const { month, count } = yearData[j];
				months.push(month);
				monthCounts.push(count); // 월별 데이터의 count 값 배열에 추가
			}

			optionsArea.value.xaxis.categories = months;

			// 차트
			const chartEntry = {
				name: "회원수 : ",
				data: monthCounts, // 추출된 월별 count 값
			};

			chartData.push(chartEntry); // 변환된 데이터를 차트 데이터 배열에 추가

			seriesArea.value = chartData;
			load.value = true;
		} catch (error) {
			console.error(
				"차트 데이터를 가져오는 중 오류가 발생했습니다:",
				error.response
			);
		}
	}
	fetchChartData();
</script>
