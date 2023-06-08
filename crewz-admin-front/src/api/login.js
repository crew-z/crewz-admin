import request from "./core/request.js";

export const requestLogin = (requestData) => {
  const uri = "/api/login";
  const config = {
    headers: {
      "Content-Type": "application/json",
      //   Authorization: "'Bearer ' + this.$store.state.token",
    },
  };
  return request.post(uri, requestData, config);
};
