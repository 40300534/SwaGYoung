<template>
  <div>
    <div class="top">
      <b @click="toBack"><i class="fa fa-angle-left"></i></b>
      <span>用户出售</span>
    </div>
    <div class="main">
      <ul>
        <li v-for="(item, index) in title" :key="index" :class="{active: index == titleIndex}"
            @click="switchColumns(index)">
          {{item}}
        </li>
      </ul>
    </div>
    <userSellBidComponent v-if="titleIndex ===1"></userSellBidComponent>
    <userSellHistoryComponent v-if="titleIndex ===0"></userSellHistoryComponent>
  </div>
</template>

<script>
  import userSellBidComponent from './userSellBidComponent';
  import userSellHistoryComponent from './userSellHistoryComponent';
  export default {
    components: {userSellBidComponent,userSellHistoryComponent},
    data(){
      return {
        title: ["历史","出价中"],
        titleIndex: 0,
      }
    },
    created(){

    },
    methods: {
      switchColumns(index){
        this.titleIndex = index;
      },
      toBack(){
        this.$router.go(-1);
      },
    }
  }
</script>

<style scoped lang="scss">
  $sc: 25;
  .active{
    color: #f37d0f;
  }
  .top {
    background: #B0BECB;
    width: 100%;
    height: 50/$sc+rem;
    position: fixed;
    top: 0;
    left: 0;
    text-align: center;
    line-height: 50/$sc+rem;
    z-index: 100;
    color: #fff;
    b {
      width: 50/$sc+rem;
      height: 50/$sc+rem;
      display: block;
      float: left;
      text-align: center;
      .fa-angle-left {
        color: #fff;
        font-size: 40px;
        line-height: 50/$sc+rem;
      }
    }
    span{
      margin-left: -50/$sc+rem;
      display: inline-block;
      font-size: 16px;
    }
  }
  .main{
    position: fixed;
    top: 50/$sc+rem;
    width: 100%;
    height: 40/$sc+rem;
    background: #fff;
    z-index: 100;
    ul{
      width: 80%;
      margin: 0 10%;
      li{
        float: left;
        width: 50%;
        list-style: none;
        height: 40/$sc+rem;
        line-height: 40/$sc+rem;
        text-align: center;
      }
    }
  }
</style>