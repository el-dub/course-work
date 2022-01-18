import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from "axios"
import VueAxios from "vue-axios";
import BootstrapVue from "bootstrap-vue";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import "@/assets/css/main.css"

Vue.config.productionTip = false
if (window.location.href.indexOf('localhost:80') !== 1) {
  axios.defaults.baseURL = 'http://localhost:8081';
} else {
  axios.defaults.baseURL = 'https://localhost:8081';
}
Vue.use(VueAxios, axios)
Vue.use(BootstrapVue)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
