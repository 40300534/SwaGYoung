<template>
  <div class="main">
    <HomePagePic></HomePagePic>
    <HomePageSwipe></HomePageSwipe>
    <Footer></Footer>
  </div>
</template>

<script>
  import HomePagePic from "./HomePagePic";
  import HomePageSwipe from "./HomePageSwipe";
  import Footer from '@/components/common/Footer'
  export default {
    name: "HomePage",
    components: {HomePageSwipe, HomePagePic,Footer},
    data(){
      return {
        list: []
      }
    },
    created(){

    },
    methods: {

    }
  }
</script>

<style scoped>

</style>