module.exports = {
  root: true,
  env: {
    node: true,
  },
  extends: ["plugin:vue/recommended"],
  parserOptions: {
    requireConfigFile: false,
    parser: "babel-eslint",
    ecmaVersion: 2020, // ecma 버전 지정
    sourceType: "module", // 스크립트 소스타입 지정
  },
};
