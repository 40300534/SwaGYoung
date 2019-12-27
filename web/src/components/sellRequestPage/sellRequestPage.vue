<template>
  <div class="main">
    <div class="top">
      <div class="top">
        <b @click="back"><i class="fa fa-angle-left"></i></b>
        <span>添加商品</span>
      </div>
    </div>
    <div class="nav">
      <li>
        <span>品　　牌</span>
        <b><i class="fa fa-angle-right"></i></b>
      </li>
      <li>
        <label><span>名　　称</span><input type="text" /></label>
      </li>
      <li>
        <label><span>货品产地</span><input type="text" /></label>
      </li>
      <div class="pic">
        <p>添加图片</p>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
  import Footer from '@/components/common/Footer'
  export default {
    components: {Footer}

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
  .nav{
    margin-top: 50/$sc+rem;
    li{
      list-style: none;
      width: 90%;
      margin: 0 5%;
      border-bottom: 1px solid #B0BECB;
      height: 50/$sc+rem;
      line-height: 50/$sc+rem;
      b{
        float: right;
        .fa-angle-right{
          font-size: 20px;
          color: #ccc;
        }
      }
      input{
        border: none;
        outline: none;
        width: 75%;
        margin-left: 5%;
        height: 90%;
      }
    }
    .pic{
      width: 90%;
      margin:3% 5%;
    }
  }
</style>