import request from "./core/request.js";

export const getList = (requestParam) => {
  let uri = `/api/category/all`;
  const config = {
    params: requestParam,
  };
  return request.get(uri, config);
};

export const categoryAdd = (requestData) => {
  const uri = "/api/category";
  const config = {};
  return request.post(uri, requestData, config);
};

export const boardCategoryList = (requestData) => {
  const uri = `/api/category/${requestData.categoryNo}`;
  const config = {};
  return request.get(uri, config);
};

export const categoryRemove = (requestData) => {
  const uri = `/api/category/${requestData.categoryNo}`;
  const config = {};
  return request.delete(uri, config);
};
