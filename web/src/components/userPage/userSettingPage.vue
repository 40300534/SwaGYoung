<template>
  <div>
    <div class="top">
      <b @click="back"><i class="fa fa-angle-left"></i></b>
      <span>个人设置</span>
    </div>
    <div class="main">
      <!--地址-->
      <div class="collection">
        <span class="span1">
          <i class="fa fa-map-marker"></i>
        </span>
        地址
        <span class="spanColloction">
          <i class="fa fa-angle-right"></i>
        </span>
      </div>
      <!--收款账户-->
      <div class="collection">
        <span class="span1">
          <i class="fa fa-user-circle"></i>
        </span>
        收款账户
        <span class="spanColloction">
          <i class="fa fa-angle-right"></i>
        </span>
      </div>
      <!--通知设置-->
      <div class="collection">
        <span class="span1">
          <i class="fa fa-newspaper-o"></i>
        </span>
        通知设置
        <span class="spanColloction">
          <i class="fa fa-angle-right"></i>
        </span>
      </div>
      <!--清除缓冲-->
      <div class="collection">
        <span class="span1">
          <i class="fa fa-wrench"></i>
        </span>
        清除缓冲
        <span class="spanColloction">
          <i class="fa fa-angle-right"></i>
        </span>
      </div>

    </div>
    <div class="bottom">退出登录</div>
  </div>
</template>

<script>
  export default {
    data(){
      return {

      }
    },
    methods: {
      back(){
        this.$router.go(-1)
      }
    }
  }
</script>

<style scoped lang="scss">
  $sc: 25;
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
    span{
      display: inline-block;
      margin-left: -50/$sc+rem;
    }
    b {
      width: 50/$sc+rem;
      height: 50/$sc+rem;
      display: block;
      float: left;
      text-align: center;

      .fa-angle-left {
        color: #fff;
        font-size: 30px;
        line-height: 50/$sc+rem;
      }
    }
  }
  .main{
    background: #f2f2f2;
    overflow: hidden;
    margin-top: 50/$sc+rem;
    .collection{
      width: 100%;
      margin: 5/$sc+rem 0;
      height: 50/$sc+rem;
      background: #fff;
      overflow: hidden;
      line-height: 50/$sc+rem;
      .span1{
        display: inline-block;
        width: 10%;
      }
      .fa-map-marker, .fa-user-circle, .fa-newspaper-o, .fa-wrench {
        color: #ccc;
        margin: -4/$sc+rem 0 0 10/$sc+rem;
        font-size: 20px;
        vertical-align: middle;
      }
      .spanColloction{
        height: 100%;
        display: block;
        float: right;
        .fa-angle-right {
          font-size: 25px;
          color: #ccc;
          margin-right: 10/$sc+rem;
          margin-top: 12/$sc+rem;
        }
      }
    }
  }
  .bottom{
    width: 80%;
    height: 50/$sc+rem;
    position: fixed;
    bottom: 30/$sc+rem;
    left: 10%;
    background: #B0BECB;
    color: #fff;
    text-align: center;
    line-height: 50/$sc+rem;
  }
</style>