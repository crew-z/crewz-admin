import request from "./core/request.js";

export const getList = () => {
	let uri = `/api/club/all`;
	const config = {};
	return request.get(uri, config);
};

// 동아리 지원금 동아리목록 불러오기
export const getClubList = () => {
	let uri = `/api/clubsubsidy/clublist`;
	const config = {
		headers: {
			"Content-Type": "application/json",
		},
	};
	return request.get(uri, config);
};

// 동아리 지원금 admin 목록 불러오기
export const getAdminList = () => {
	let uri = `/api/clubsubsidy/adminlist`;
	const config = {
		headers: {
			"Content-Type": "application/json",
		},
	};
	return request.get(uri, config);
};

// 동아리 지원금 등록하기
export const insertSubsidy = (requestData) => {
	const uri = `/api/clubsubsidy`;
	const config = {};
	return request.post(uri, requestData, config);
};

// 동아리별 회원목록 불러오기
export const getClubListByClubNo = (requestData) => {
	const uri = `/api/clubdashboard/${requestData}`;
	const config = {};
	return request.post(uri, requestData, config);
};

// 동아리장 변경
export const changeClubLeader = (requestData) => {
	const uri = `/api/clubdashboard/${requestData.clubNo}?userNo=${requestData.userNo}`;
	const config = {};
	return request.patch(uri, requestData, config);
};

// 동아리별 회원수 차트 불러오기
export const getClubMemChartByClubNo = (requestData) => {
	const uri = `/api/clubdashboard/${requestData}`;
	const config = {};
	return request.get(uri, config);
};
