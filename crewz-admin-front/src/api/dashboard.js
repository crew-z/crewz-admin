import request from "./core/request";

export const getDashboard = () => {
  const uri = `/api/main`;
  const config = {
    headers: {
      "Content-Type": "application.json",
    },
  };
  return request.get(uri, config);
};
