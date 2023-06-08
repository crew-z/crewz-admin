import axios, { HttpStatusCode } from "axios";
// import { useCookies } from "vue3-cookies";
// import { authStore } from "../../store/auth.js";

const request = axios.create({
	baseURL: "http://localhost:8082",
	timeout: 30000,
});
/*
request.interceptors.request.use(
  (config) => {
    const cookies = useCookies().cookies;
    if (cookies.get("token")) {
      config.headers["Authorization"] = cookies.get("token");
      config.headers["adminId"] = cookies.get("adminId");
    }
    return config;
  },
  (error) => {
    console.log(error); // for debug
    Promise.reject(error);
  }
);
*/
request.interceptors.response.use(
	(response) => {
		const statuses = [
			HttpStatusCode.Ok,
			HttpStatusCode.Created,
			HttpStatusCode.NoContent,
		];
		if (statuses.includes(response.status)) {
			console.log("Request Success");
		}
		return response;
	},
	async (error) => {
		alert(error.response.data.message);
		// if (error.response.data.message.indexOf("인증") !== -1) {
		//   authStore().logout();
		// }
		return Promise.reject(error);
	}
);

export default request;
