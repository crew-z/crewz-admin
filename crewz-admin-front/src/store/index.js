import { createStore } from "vuex";

export default createStore({
  state: {
    role: null,
    token: null,
    name: "관리자",
  },
  getters: {},
  mutations: {
    login(state, resData) {
      state.role = resData.role;
      state.token = resData.token;
      state.name = resData.name;
    },
  },
  actions: {},
  modules: {},
});
