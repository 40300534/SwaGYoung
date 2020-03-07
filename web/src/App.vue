<template>
  <div id="app">
		<!-- <router-view></router-view> -->
    <!-- <img src="./assets/logo.png"> -->
		<keep-alive>
				<router-view v-if="$route.meta.keepAlive">
						<!-- 这里是会被缓存的视图组件 -->
				</router-view>
		</keep-alive>
		 
		<router-view v-if="!$route.meta.keepAlive">
				<!-- 这里是不被缓存的视图组件 -->
		</router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
   /*mounted() {
      if(this._isMobile()) {
        this.$router.replace('/');
      } else {
        this.$router.replace('/pchome');
      }
    },
    methods: {
      _isMobile() {
        let flag = navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i)
        return flag;
      }
    }*/
}
</script>

<style lang="scss" scoped>
  $sc: 25;
	@import './assets/css/style.css';
	$sc: 25;
html, body{
	width: 100%;
	height: 100%;
	background: #f0efed;
	-webkit-tap-highlight-color: rgba(0,0,0,0);
}
  .wh_content_all{
    background: red;
  }
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
</style>
