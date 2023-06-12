import request from "./core/request.js";

export const getList = (requestParam) => {
  let uri = "/api/user";
  const config = {
    params: requestParam,
  };
  return request.get(uri, config);
};

export const deleteU = (requestData) => {
  let uri = "/api/user";
  const config = {};
  return request.patch(uri, requestData, config);
};
