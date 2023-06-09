import axios, { HttpStatusCode } from "axios";
import { useCookies } from "vue3-cookies";

const host = location.hostname;

const request = axios.create({
  baseURL: "http://" + host + ":8082",
  // baseURL: "http://localhost:8082",
  timeout: 30000,
});

request.interceptors.request.use(
  (config) => {
    const cookies = useCookies().cookies;
    if (cookies.get("token")) {
      config.headers["Authorization"] = cookies.get("token");
    }
    return config;
  },
  (error) => {
    console.log(error);
    Promise.reject(error);
  }
);

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
    return Promise.reject(error);
  }
);

export default request;
