const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: true,

  outputDir: "../backend/crewz-admin/src/main/resources/static",

  devServer: {
    proxy: {
      '/': {
        target: "http://localhost:8082",
        changeOrigin: true,
      }
    }
  }
});
