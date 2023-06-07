import request from "./core/request.js";

export const getList = (param) => {
  let uri = `/posts`;
  const config = {
    params: param,
  };
  return request.get(uri, config);
};

export const deleteList = (requestData) => {
  const uri = "/posts/resources";
  const config = {};
  return request.post(uri, requestData, config);
};

export const getPopularPostList = () => {
  const uri = `/posts/popular`;
  const config = {
    headers: {
      "Content-Type": "application/json",
    },
  };
  return request.get(uri, config);
};

export const getRecentPostList = () => {
  const uri = `/posts/recent`;
  const config = {
    headers: {
      "Content-Type": "application/json",
    },
  };
  return request.get(uri, config);
};
