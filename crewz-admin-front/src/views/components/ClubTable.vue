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
							>동아리 목록</a
						>
					</div>
				</li>
			</ol>
		</nav>
		<!-- end nav -->
		<div class="mt-5 w-full">
			<div>
				<span
					class="text-2xl text-gray-900 font-medium dark:text-gray-200"
				>
					동아리 목록
				</span>
			</div>
			<div
				class="mt-2 bg-white dark:bg-gray-800 p-5 w-full rounded-md box-border border dark:border-gray-700"
			>
				<div class="flex items-center">
					<h2
						class="font-bold text-lg text-gray-800 dark:text-gray-200"
					>
						동아리 리스트
					</h2>

					<div class="absolute" style="right: 10rem; top: 115px">
						<Modal
							title="지원금 등록"
							btnTextClose="취소"
							btnTextSubmit="등록"
							btnColor="bg-[#FBC02D]"
							btnText="지원금 등록"
							@submit="addSubsidy"
						>
							<template v-slot:body>
								<!-- alert -->
								<div
									v-if="successAlert"
									class="bg-orange-100 border-t-4 border-teal-500 rounded-b text-teal-900 px-4 py-3 shadow-md"
									role="alert"
								>
									<div class="flex">
										<div class="py-1">
											<svg
												class="fill-current h-6 w-6 text-teal-500 mr-4"
												xmlns="http://www.w3.org/2000/svg"
												viewBox="0 0 20 20"
											>
												<path
													d="M2.93 17.07A10 10 0 1 1 17.07 2.93 10 10 0 0 1 2.93 17.07zm12.73-1.41A8 8 0 1 0 4.34 4.34a8 8 0 0 0 11.32 11.32zM9 11V9h2v6H9v-4zm0-6h2v2H9V5z"
												/>
											</svg>
										</div>
										<div>
											<p class="font-bold">
												지원금을 성공적으로
												등록하셨습니다.
											</p>
										</div>
									</div>
								</div>
								<form class="w-full max-w-lg">
									<div class="flex flex-wrap -mx-3 mb-6">
										<div class="w-full px-3 mb-6 md:mb-0">
											<div class="relative">
												<label
													class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
													for="grid-zip"
												>
													동아리 이름
												</label>
												<select
													v-model="selectedClub"
													class="block appearance-none w-full bg-gray-200 border border-gray-200 text-gray-700 py-3 px-4 pr-8 rounded leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
													id="grid-state"
												>
													<option :value="null">
														동아리를 선택해주세요
													</option>
													<option
														v-for="item in responseClubList"
														:key="item.clubNo"
														:value="item.clubNo"
													>
														{{ item.clubName }}
													</option>
												</select>
												<div
													class="pointer-events-none absolute inset-y-0 right-0 flex items-center px-2 text-gray-700"
												>
													<svg
														class="fill-current h-4 w-4"
														xmlns="http://www.w3.org/2000/svg"
														viewBox="0 0 20 20"
													>
														<path
															d="M9.293 12.95l.707.707L15.657 8l-1.414-1.414L10 10.828 5.757 6.586 4.343 8z"
														/>
													</svg>
												</div>
											</div>
										</div>

										<div class="w-full px-3 mb-6 md:mb-0">
											<div class="relative">
												<label
													class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
													for="grid-zip"
												>
													담당자
												</label>
												<select
													v-model="selectedAdmin"
													class="block appearance-none w-full bg-gray-200 border border-gray-200 text-gray-700 py-3 px-4 pr-8 rounded leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
													id="grid-state"
												>
													<option :value="null">
														담당자를 선택해주세요
													</option>
													<option
														v-for="item in responseAdminList"
														:key="item.adminNo"
														:value="item.adminNo"
													>
														{{ item.adminName }}
													</option>
												</select>
												<div
													class="pointer-events-none absolute inset-y-0 right-0 flex items-center px-2 text-gray-700"
												>
													<svg
														class="fill-current h-4 w-4"
														xmlns="http://www.w3.org/2000/svg"
														viewBox="0 0 20 20"
													>
														<path
															d="M9.293 12.95l.707.707L15.657 8l-1.414-1.414L10 10.828 5.757 6.586 4.343 8z"
														/>
													</svg>
												</div>
											</div>
										</div>

										<div class="w-full px-3 mb-6 md:mb-0">
											<label
												class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
												for="grid-zip"
											>
												지원금 금액
											</label>
											<input
												v-model="subsidyAmount"
												class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
												id="grid-zip"
												type="text"
												placeholder="지원금을 입력하세요."
											/>
										</div>
									</div>
								</form>
							</template>
						</Modal>
					</div>
				</div>
				<div class="wrapping-table mt-10">
					<table
						class="w-full text-sm text-left text-gray-500 dark:text-gray-400 lg:overflow-auto overflow-x-scroll"
					>
						<thead
							class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400"
						>
							<tr>
								<th scope="col" class="uppercase px-6 py-3">
									동아리이름
								</th>
								<th scope="col" class="uppercase px-6 py-3">
									등록일
								</th>
								<th scope="col" class="uppercase px-6 py-3">
									지원금 내역
								</th>
								<th scope="col" class="uppercase px-6 py-3">
									동아리 상세보기
								</th>
								<th scope="col" class="uppercase px-6 py-3">
									동아리 폐부
								</th>
							</tr>
						</thead>
						<tbody>
							<tr
								class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 odd:bg-white even:bg-gray-50"
								v-for="items in responseList"
								:key="items.clubNo"
							>
								<td class="px-6 py-4">
									{{ items.clubApply.clubName }}
								</td>
								<td class="px-6 py-4">
									{{ items.clubApply.regdate }}
								</td>
								<td>
									<Modal
										title="지원금 내역"
										btnTextClose="나가기"
										btnText="확인"
										width="max-w-full"
										@click="
											getSubsidy(
												items.clubNo,
												subsidyYear
											)
										"
										:showSubmitButton="false"
									>
										<template v-slot:body>
											<div class="flex">
												<div class="w-1/2 p-4">
													<table
														class="w-full max-auto border-collapse border border-gray-300"
													>
														<thead>
															<tr>
																<th
																	class="border border-gray-300 px-4 py-2"
																>
																	지급날짜
																</th>
																<th
																	class="border border-gray-300 px-4 py-2"
																>
																	지급액
																</th>
															</tr>
														</thead>
														<tbody>
															<tr
																v-for="(
																	subsidy,
																	index
																) in responseSubsidyList"
																:key="index"
															>
																<td
																	class="border border-gray-300 px-4 py-2"
																>
																	{{
																		formatDate(
																			subsidy.approveDate
																		)
																	}}
																</td>
																<td
																	class="border border-gray-300 px-4 py-2"
																>
																	{{
																		subsidy.price
																	}}
																</td>
															</tr>
														</tbody>
													</table>
												</div>
												<div
													v-if="loaded"
													class="w-1/2 p-4"
												>
													<select
														v-model="subsidyYear"
														class="dark:bg-gray-800 dark:hover:bg-gray-700 dark:border-gray-700 border max-w-lg px-4 py-3 block rounded-md text-gray-500 dark:text-gray-400 ml-auto"
														@click="
															clickedModalYear(
																subsidyYear
															)
														"
													>
														<option
															v-for="(
																s_year, index
															) in subsidyYearList"
															:value="
																s_year.value
															"
															:key="index"
														>
															{{ s_year.text }}
														</option>
													</select>
													<apexchart
														width="100%"
														height="380"
														type="bar"
														:options="optionsBar"
														:series="seriesBar"
													></apexchart>
												</div>
											</div>
										</template>
									</Modal>
								</td>
								<td>
									<Modal
										title="동아리 상세보기"
										btnTextClose="확인"
										btnText="확인"
										width="max-w-full"
										@click="getClubDetail(items.clubNo)"
										:showSubmitButton="false"
									>
										<template v-slot:body>
											<!-- alert -->
											<div
												v-if="alertVisible"
												class="bg-teal-100 border-t-4 border-teal-500 rounded-b text-teal-900 px-4 py-3 shadow-md"
												role="alert"
											>
												<div class="flex">
													<div class="py-1">
														<svg
															class="fill-current h-6 w-6 text-teal-500 mr-4"
															xmlns="http://www.w3.org/2000/svg"
															viewBox="0 0 20 20"
														>
															<path
																d="M2.93 17.07A10 10 0 1 1 17.07 2.93 10 10 0 0 1 2.93 17.07zm12.73-1.41A8 8 0 1 0 4.34 4.34a8 8 0 0 0 11.32 11.32zM9 11V9h2v6H9v-4zm0-6h2v2H9V5z"
															/>
														</svg>
													</div>
													<div>
														<p class="font-bold">
															동아리장을
															성공적으로
															변경하였습니다.
														</p>
													</div>
												</div>
											</div>

											<div class="flex">
												<div class="w-1/2 p-4">
													<button
														@click="
															updateClubUserGrade
														"
														type="button"
														class="text-white bg-green-700 hover:bg-green-800 focus:outline-none focus:ring-4 focus:ring-green-300 font-medium rounded-full text-sm px-5 py-2.5 text-center mr-2 mb-2 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800"
													>
														동아리장 변경
													</button>

													<table
														class="w-full max-auto border-collapse border border-gray-300"
													>
														<thead>
															<tr>
																<th
																	class="border border-gray-300 px-4 py-2"
																>
																	이름
																</th>
																<th
																	class="border border-gray-300 px-4 py-2"
																>
																	이메일
																</th>
																<th
																	class="border border-gray-300 px-4 py-2"
																>
																	전화번호
																</th>
																<th
																	class="border border-gray-300 px-4 py-2"
																>
																	등급
																</th>
																<th
																	class="border border-gray-300 px-4 py-2"
																>
																	동아리장
																	선택
																</th>
															</tr>
														</thead>
														<tbody>
															<template
																v-for="item in memList"
															>
																<tr
																	class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 odd:bg-white even:bg-gray-50"
																	:key="
																		item.clubNo
																	"
																	v-if="
																		item.userName !==
																		null
																	"
																>
																	<td
																		class="px-6 py-4"
																	>
																		{{
																			item.userName
																		}}
																	</td>
																	<td
																		class="px-6 py-4"
																	>
																		{{
																			item.userEmail
																		}}
																	</td>
																	<td
																		class="px-6 py-4"
																	>
																		{{
																			item.userTel
																		}}
																	</td>
																	<td
																		class="px-6 py-4"
																	>
																		<span
																			v-if="
																				item.clubUserGrade ===
																				2
																			"
																			>동아리장</span
																		>
																		<span
																			v-else
																			>동아리원</span
																		>
																	</td>
																	<td
																		class="..."
																	>
																		<input
																			type="radio"
																			:disabled="
																				isCheckboxDisabled(
																					item
																				)
																			"
																			:checked="
																				selectedUser ===
																				item.userNo
																			"
																			@change="
																				updateSelectedUser(
																					item.userNo
																				)
																			"
																		/>
																		<label
																			:for="
																				'userRadio' +
																				item.userNo
																			"
																			>선택</label
																		>
																	</td>
																</tr>
															</template>
														</tbody>
													</table>
												</div>
												<div class="w-1/2 p-4">
													<apexchart
														v-if="load"
														width="100%"
														height="600"
														type="area"
														:options="optionsArea"
														:series="seriesArea"
														:sparkline="{
															enabled: true,
														}"
													></apexchart>
													<br />

													<div
														class="wrapper-button p-5 flex justify-between mt-3"
													>
														<select
															v-model="
																selectedYear
															"
															class="dark:bg-gray-800 dark:hover:bg-gray-700 dark:border-gray-700 border max-w-lg px-4 py-3 block rounded-md text-gray-500 dark:text-gray-400"
															@change="
																handleYearChange(
																	selectedYear
																)
															"
														>
															<option
																v-for="(
																	year, index
																) in optionsArea.years"
																:value="
																	year.value
																"
																:key="index"
															>
																{{
																	year.name
																}}년
															</option>
														</select>
													</div>
												</div>
											</div>
										</template>
									</Modal>
								</td>
								<td
									v-if="items.clubCloseYn === 'N'"
									class="px-6 py-4"
								>
									<Modal
										title="동아리를 폐부시키겠습니까?"
										btnTextSubmit="확인"
										btnColorSubmit="bg-red-500"
										btnText="폐부"
										@submit="confirmDelete(items.clubNo)"
									>
									</Modal>
								</td>
								<td
									v-if="items.clubCloseYn === 'Y'"
									class="px-6 py-4"
								>
									<a
										class="px-4 py-2 bg-red-500 text-white font-bold rounded disabled opacity-50 cursor-not-allowed"
										>폐부됨</a
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
					v-for="page in pagingUtil.endPage -
					pagingUtil.startPage +
					1"
					:key="page"
				>
					<a
						v-if="
							pagingUtil.pageNumber ===
							page + pagingUtil.startPage - 1
						"
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
	import { ref, watch } from "vue";
	import Modal from "@/components/AdminModal.vue";
	import {
		getList,
		getClubList,
		getAdminList,
		insertSubsidy,
		getClubListByClubNo,
		changeClubLeader,
		getClubMemChartByClubNo,
	} from "@/api/club.js";

	const responseList = ref([]);
	const responseSubsidyList = ref([]);

	let pagingUtil = ref({});

	const init = async () => {
		let initList = await getList();
		responseList.value = initList.data.clubList;
		pagingUtil.value = initList.data.pagingUtil;
		let initClubList = await getClubList();
		responseClubList.value = initClubList.data;
		let initAdminList = await getAdminList();
		responseAdminList.value = initAdminList.data;
	};
	init();

	const loaded = ref(false);
	const subsidyYear = ref("2023");
	// 올해 기준으로 +- 2년 데이터
	const currentModalYear = new Date().getFullYear();
	const subsidyYearList = ref([
		{
			text: (currentModalYear - 2).toString(),
			value: currentModalYear - 2,
		},
		{
			text: (currentModalYear - 1).toString(),
			value: currentModalYear - 1,
		},
		{
			text: currentModalYear.toString(),
			value: currentModalYear,
		},
		{
			text: (currentModalYear + 1).toString(),
			value: currentModalYear + 1,
		},
		{
			text: (currentModalYear + 2).toString(),
			value: currentModalYear + 2,
		},
	]);

	// 동아리 지원금 등록
	const responseClubList = ref([]);
	const responseAdminList = ref([]);
	const selectedClub = ref(null);
	const selectedAdmin = ref(null);
	const subsidyAmount = ref("");
	const successAlert = ref(false);

	const addSubsidy = async () => {
		let requestData = {
			clubNo: { clubNo: selectedClub.value },
			adminNo: { adminNo: selectedAdmin.value },
			price: subsidyAmount.value,
		};
		try {
			await insertSubsidy(requestData);

			successAlert.value = true;
			setTimeout(() => {
				const closeModalEvent = new Event("closeModalEvent");
				window.dispatchEvent(closeModalEvent);
				successAlert.value = false;
			}, 1000);
			selectedAdmin.value = null;
			selectedClub.value = null;
			subsidyAmount.value = null;
		} catch (error) {
			console.log(error);
		}
	};

	let selectedModalClubNo = 0;

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

	const clickPage = async (page) => {
		try {
			axios
				.get("http://localhost:8082/api/club/all?page=" + page)
				.then((res) => {
					responseList.value = res.data.clubList;
					pagingUtil.value = res.data.pagingUtil;
				})
				.catch((Error) => {
					console.log(Error);
				});
		} catch (error) {
			console.log(error);
		}
	};

	const getSubsidy = (clubNo, year) => {
		try {
			axios
				.get(
					"http://localhost:8082/api/clubsubsidy?clubNo=" +
						clubNo +
						"&year=" +
						year
				)
				.then((res) => {
					selectedModalClubNo = clubNo;
					responseSubsidyList.value = res.data;
					seriesBar.value[0].data = processSubsidyData(res.data);
					loaded.value = true;
				})
				.catch((Error) => {
					console.log(Error);
				});
		} catch (error) {
			console.log(error);
		}
	};

	// 차트 데이터
	const seriesBar = ref([
		{
			name: "Product 1",
			data: [],
		},
	]);

	const optionsBar = {
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
			categories: [
				"1월",
				"2월",
				"3월",
				"4월",
				"5월",
				"6월",
				"7월",
				"8월",
				"9월",
				"10월",
				"11월",
				"12월",
			],
		},
		yaxis: {
			show: false,
		},
		colors: ["#4f46e5", "#DC2626"],
		dataLabels: {
			enabled: true,
		},
		stroke: {
			curve: "straight",
		},
	};

	// 리스트 변경
	watch(
		() => subsidyYear.value,
		(newSubSideYear) => {
			try {
				axios
					.get(
						"http://localhost:8082/api/clubsubsidy?clubNo=" +
							selectedModalClubNo +
							"&year=" +
							newSubSideYear
					)
					.then((res) => {
						responseSubsidyList.value = res.data;
						seriesBar.value[0].data = processSubsidyData(res.data);
					})
					.catch((Error) => {
						console.log(Error);
					});
			} catch (Error) {
				console.log(Error);
			}
		}
	);

	const clickedModalYear = (argYear) => {
		subsidyYear.value = argYear;
	};

	const processSubsidyData = (data) => {
		const monthlyPrices = Array(12).fill(0);

		if (data.length > 0) {
			data.forEach((item) => {
				const newDate = new Date(item.approveDate);
				const month = newDate.getMonth();
				monthlyPrices[month] += item.price;
			});
		}

		return monthlyPrices;
	};
	// 동아리별 회원 테이블
	const memList = ref([]);

	const fetchClubMemTable = async (clubNo) => {
		try {
			let requestData = clubNo;
			const response = await getClubListByClubNo(requestData);
			memList.value = response.data;
		} catch (error) {
			console.log(error);
		}
	};

	// 동아리장 변경
	const selectedClubNo = ref(null);
	const selectedUser = ref(null);
	const alertVisible = ref(false);

	const updateSelectedUser = (userNo) => {
		if (selectedUser.value === userNo) {
			selectedUser.value = "";
		} else {
			selectedUser.value = userNo;
		}
	};

	const isCheckboxDisabled = (item) => {
		return item.clubUserGrade === 2;
	};

	const updateClubUserGrade = async () => {
		if (selectedClubNo.value && selectedUser.value) {
			let requestData = {
				clubNo: selectedClubNo.value,
				userNo: selectedUser.value,
			};

			try {
				await changeClubLeader(requestData);

				alertVisible.value = true;
				setTimeout(() => {
					alertVisible.value = false;
				}, 2000);
			} catch (error) {
				console.error(error.response.data);
			}
		} else {
			console.log("동아리장을 선택해주세요.");
		}
	};

	// 동아리 상세보기 차트
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
		colors: ["#1B8C42"],
		dataLabels: {
			enabled: true,
		},
		stroke: {
			curve: "smooth",
		},
		title: {
			text: "년도별 동아리 회원수",
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
				text: "Month(월)",
			},
		},
		years: [], // 선택 가능한 년도 목록
		yaxis: {
			title: {
				text: "동아리 회원수(명)",
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
			fetchChartData(selectedClubNo.value);
		}
	);

	const handleYearChange = (year) => {
		selectedYear.value = year.toString();
		fetchChartData(selectedClubNo.value);
	};

	const getClubDetail = (clubNo) => {
		selectedClubNo.value = clubNo;
		fetchChartData(clubNo);
		fetchClubMemTable(clubNo);
	};

	const fetchChartData = async (clubNo) => {
		try {
			let requestData = clubNo;
			const response = await getClubMemChartByClubNo(requestData);

			optionsArea.value.years = [];
			months.length = 0;
			const data = response.data;

			let yearList = Object.keys(data);
			optionsArea.value.years = [];

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
				name: "회원수",
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
	};

	const confirmDelete = (clubNo) => {
		console.log(clubNo);
		try {
			axios
				.patch("http://localhost:8082/api/club?clubNo=" + clubNo)
				.then((res) => {
					console.log(res);
					window.location.reload();
				})
				.catch((Error) => {
					console.log(Error);
				});
		} catch (Error) {
			console.log(Error);
		}
	};
</script>
