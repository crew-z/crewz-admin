import request from "./core/request";

export const getAdminUserName = () => {
  const uri = `/api/main`; // 해당부분을 adminUserName을 받아오는 경로로 바꾸면 될까 같습니당
  const config = {
    headers: {
      "Content-Type": "application.json",
    },
  };
  return request.get(uri, config);
};
