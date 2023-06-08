import request from "./core/request.js";

export const getList = (requestParam) => {
  let uri = "/api/admin";
  const config = {
    params: requestParam,
  };
  return request.get(uri, config);
};

export const deleteA = (requestData) => {
  let uri = "api/admin";
  const config = {};
  return request.patch(uri, requestData, config);
};

export const addA = (requestData) => {
  const uri = "api/admin";
  const config = {};
  return request.post(uri, requestData, config);
};
