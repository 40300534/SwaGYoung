// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import Mint from "mint-ui"
Vue.use(Mint);
import router from './router'
import store from './store'
import Axios from 'axios'
import VDistpicker from 'v-distpicker';

/*import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);*/

let Authorization = localStorage.getItem('token');
Axios.defaults.headers.common['Authorization'] = Authorization;
Vue.prototype.$axios = Axios;

Vue.component('v-distpicker', VDistpicker);

Vue.config.productionTip = false;
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
	store,
  components: { App },
  template: '<App/>'
});
