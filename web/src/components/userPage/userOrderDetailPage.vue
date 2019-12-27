<template>
  <div class="main">
    <div class="top">
      <b><i class="fa fa-angle-left"></i></b>
      <span>订单详情</span>
    </div>
    <div class="address">
      <div class="addressLeft"><img src="../../assets/address.png" alt="" /></div>
      <div class="addressRight">
        <p class="pName">
          <span>轨迹</span>
          <span class="spanTel">86-16776543210</span>
        </p>
        <p>
          <span>北京</span>
          <span>北京市</span>
          <span>海淀区</span>
          <span>清河街道</span>
          <span>安宁庄中街2号阳光一百宾馆257</span>
        </p>
      </div>
    </div>
    <div class="productMessage">
      <div class="productPic">
        <img src="../../assets/goods.jpg" alt="" />
      </div>
      <div class="productName">
        <p>养生堂养生堂养生堂养生堂养生堂养生堂养生堂养生堂养生堂养生堂养生堂养生堂养生堂养生堂养生堂养生堂养生堂养生堂养生堂</p>
        <p>￥108</p>
      </div>
      <div class="orderPrice">
        <li><span>商品总价</span><span>￥108.00</span></li>
        <li><span>运费(快递)</span><span>￥0.00</span></li>
        <li><span>订单总价</span><span>￥108.00</span></li>
      </div>
      <div class="payPrice">
        <li><span>实付款</span><span>￥108.00</span></li>
      </div>
    </div>
    <div class="orderDetails">
      <li><span>订单编号</span><span>1111111111111111111111</span></li>
      <li><span>创建时间</span><span>2019-07-06 13:05:45</span></li>
      <li><span>付款时间</span><span>2019-07-06 13:05:45</span></li>
      <li><span>发货时间</span><span>2019-07-06 13:05:45</span></li>
      <li><span>成交时间</span><span>2019-07-06 13:05:45</span></li>
    </div>
    <div class="cutPlatform">
      <p><span>联系平台</span><span>拨打电话</span></p>
    </div>
  </div>
</template>

<script>
  export default {
    
  }
</script>

<style scoped lang="scss">
  $sc: 25;
  .main{
    background: #eee;
    overflow: hidden;
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
    .address{
      margin-top: 50/$sc+rem;
      background: #fff;
      height: 80/$sc+rem;
      .addressLeft{
        width: 15%;
        float: left;
        height: 100%;
        line-height: 80/$sc+rem;
        text-align: center;
        img{
          width: 60%;
          height: auto;
          display: inline-block;
          margin: auto;
          vertical-align: middle;
        }
      }
      .addressRight{
        width: 85%;
        float: right;
        .pName{
          margin-top: 5/$sc+rem;
          .spanTel{
            font-size: 14px;
            color: rgba(102,102,102,1);
            margin-left: 5/$sc+rem;
            display: inline-block;
          }
        }
      }
    }
    .productMessage{
      width: 100%;
      height: auto;
      background: #fff;
      overflow: hidden;
      margin-top: 5/$sc+rem;
      .productPic{
        display: block;
        width: 100/$sc+rem;
        height: 100/$sc+rem;
        margin: 10/$sc+rem;
        float: left;
        img{
          width: 100%;
          height: 100%;
        }
      }
      .productName{
        float: right;
        width: 67%;
        margin-top: 10/$sc+rem;
        p:first-child{
          width: 100%;
          display: -webkit-box;
          -webkit-box-orient: vertical;
          -webkit-line-clamp: 2;
          overflow: hidden;
        }
        p:last-child{
          margin-top: 15/$sc+rem;
        }
      }
      .orderPrice{
        clear: both;
        width: 100%;
        overflow: hidden;
        padding: 20/$sc+rem 0;
        border-top: 1px solid #eee;
        li{
          list-style: none;
          width: 90%;
          clear: both;
          margin: 0 5%;
          font-size: 14px;
          color: rgba(102,102,102,1);
          padding: 5/$sc+rem 0;
          span:first-child{
            float: left;
          }
          span:last-child{
            float: right;
          }
        }
        li:last-child{
          color: #000;
          font-size: 16px;
        }
      }
      .payPrice{
        width: 100%;
        clear: both;
        border-top: 1px solid #eee;
        background: rgb(250,250,250);
        li{
          list-style: none;
          width: 90%;
          margin: 0 5%;
          padding: 10/$sc+rem 0;
          span:last-child{
            font-size: 18px;
            float: right;
            color: #fa7a7a;
          }
        }
      }
    }
    .orderDetails{
      width: 100%;
      margin-top: 10/$sc+rem;
      background: #fff;
      overflow: hidden;
      li{
        list-style: none;
        color: rgba(102,102,102,1);
        font-size: 12px;
        width: 90%;
        margin: 0 5%;
        overflow: hidden;
        padding: 5/$sc+rem 0;
        span:first-child{
          float: left;
        }
        span:last-child{
          float: right;
        }
      }
    }
    .cutPlatform{
      margin-top: 10/$sc+rem;
      width: 100%;
      background: #fff;
      p{
        width: 90%;
        margin: 0 5%;
        overflow: hidden;
        padding: 10/$sc+rem;
        span{
          display: block;
          width: 49.7%;
          padding: 10/$sc+rem 0;
          text-align: center;
          float: left;
        }
        span:first-child{
          border-right: 1px solid #ccc;
        }
      }
    }
  }

</style>