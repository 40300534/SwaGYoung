<template>
  <div id="app">
    <router-view />
		<!--<Footer></Footer>-->
  </div>
</template>

<script>
import Footer from '@/components/common/Footer'
export default {
  name: 'App',
	components: {
		Footer
	}
}
</script>

<style lang="scss">
  @import './assets/css/style.css';
  $sc:25;
    *{
        padding: 0;
        margin: 0;
    }
    #app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
	font-size: 16px;
}
  .mint-switch-input:checked + .mint-switch-core{
    background-color: #fa7a7a;
    border:1px solid #fa7a7a;
    width: 80/$sc+rem;
  }
  .mint-switch-input:checked + .mint-switch-core::after{
    webkit-transform: translateX(38/$sc+rem);
    transform: translateX(38/$sc+rem);
  }
  .mint-switch-core {
    width: 80/$sc+rem;
    background: #fa7a7a;
    border:1px solid #fa7a7a;
  }
  .mint-switch-core::before{
    background-color: #fa7a7a;
    width: 40/$sc+rem;
  }
  .mint-switch-core::after{
    width: 40/$sc+rem;
    border: none;
  }
  </style>
