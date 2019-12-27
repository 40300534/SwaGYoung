<template>
  <div class="main">
    <div @click="cancelUpload" v-show="isUploadMessage" class="uploadUserMessages">
      <div @click.stop class="userMessage">
        <li><input type="file" /></li>
        <li>修改昵称</li>
      </div>
    </div>
    <div class="top">
      <div class="right">
        <span><i class="fa fa-file-text-o"></i></span>
        <span><i class="fa fa-cog" @click="toUserSettingPage"></i></span>
      </div>
    </div>
    <div class="personal">
      <img @click="uploadHead" src="@/assets/header.png"/>
      <a @click="toLogoPage">立即登录</a>
      <div class="clickRight" @click="toUserHomePage">个人主页<i class="fa fa-angle-right"></i></div>
    </div>

    <div class="buySell">
      <div class="buy" @click="toUserBuyPage">
        <i class="fa fa-shopping-cart"></i>
        购买
        <span class="spanBuySell">
          100
          <i class="fa fa-angle-right"></i>
        </span>

      </div>
      <div class="sell" @click="toUserSellPage">
        <i class="fa fa-shopping-cart"></i>
        出售
        <span class="spanBuySell">
          99
          <i class="fa fa-angle-right"></i>
        </span>
      </div>
    </div>

    <div class="collection" @click="toUserFavoratePage">
      <i class="fa fa-heart"></i>
      收藏
      <span class="spanColloction">
          <i class="fa fa-angle-right"></i>
        </span>
    </div>

    <div class="assets">
      <i class="fa fa-line-chart"></i>
      资产
      <span class="spanAssets">
          <i class="fa fa-angle-right"></i>
        </span>
    </div>


    <button @click="shareHref()">分享</button>
    <Footer></Footer>
  </div>
</template>

<script>
  var Intent = null,
    File = null,
    Uri = null,
    main = null;
  var shares = null;
  var shareImageUrl = '';

  import wx from 'weixin-js-sdk'
  import Footer from '@/components/common/Footer'

  export default {
    components: {Footer},
    data(){
      return {
        isUploadMessage: false
      }
    },
    created(){
      let _this = this;
      mui.plusReady(function() {
        _this.updateSerivces();
        if (window.plus) {
          document.addEventListener('plusready', _this.updateSerivces())
        } else {
          alert('PC端')
        }
      })
    },
    mounted() {
      //this.wxRegister()
    },
    methods: {
      shareHref() {
        let _this = this;
        _this.shareAction("weixin", "WXSceneSession");
        /*var ids = [{
            id: "weixin",
            ex: "WXSceneSession" /!*微信好友*!/
          }, {
            id: "weixin",
            ex: "WXSceneTimeline" /!*微信朋友圈*!/
          }],
          bts = [{
            title: "发送给微信好友"
          }, {
            title: "分享到微信朋友圈"
          }];
        plus.nativeUI.actionSheet({
            cancel: "取消",
            buttons: bts
          },
          function(e) {
            var i = e.index;
            if(i > 0) {
              _this.shareAction(ids[i - 1].id, ids[i - 1].ex);
            }
          }
        );*/
      },
      shareAction(id, ex) {
        let _this = this;
        var s = null;

        if(!id || !(s = shares[id])) {
          _this.outLine("无效的分享服务！");
          return;
        }
        if(s.authenticated) {
          _this.outSet("---已授权---");
          _this.shareMessage(s, ex);
        } else {
          _this.outSet("---未授权---");
          s.authorize(function() {
            _this.shareMessage(s, ex);
          }, function(e) {
            _this.outLine("认证授权失败");
          });
        }
      },
      outLine(msg) {
        //console.log(msg);
        mui.toast(msg);
      },
      outSet(msg) {
        //console.log(msg);
      },
      updateSerivces() {
        let _this = this
        plus.share.getServices(function(s) {

          shares = {};
          for(var i in s) {
            var t = s[i];
            shares[t.id] = t;
          }
          _this.outSet("获取分享服务列表成功");
        }, function(e) {
          _this.outSet("获取分享服务列表失败：" + e.message);
        });
      },
      shareMessage(s, ex) {
        let AppID = '789';
        var msg = {
          content: 'ceshi',
          href: 'https://www.taobao.com?id='+ AppID, //换成你自己的
          title: '限时团购周 挑战最低价',
          content: '限时团购周 挑战最低价',
          thumbs: ["http://img.alicdn.com/tps/i4/TB1.ttgeEuF3KVjSZK9SuvVtXXa.jpg_240x240q90.jpg"],
          pictures: ["http://img.alicdn.com/tps/i4/TB1.ttgeEuF3KVjSZK9SuvVtXXa.jpg_240x240q90.jpg"],
          extra: {
            scene: ex
          }
        };
        s.send(msg, function() {
          _this.outLine("分享成功!");
        }, function(e) {
          _this.outLine("分享失败!");
        });
      },
      /*search(){
        //this.wxRegister();
      },*/
      uploadHead(){
        this.isUploadMessage = true;
      },
      cancelUpload(){
        this.isUploadMessage = false;
      },
      toUserBuyPage(){
        this.$router.push({path: '/userBuy'});
      },
      toUserSellPage(){
        this.$router.push({path:'/userSell'});
      },
      toUserSettingPage(){
        this.$router.push({path: '/userSetting'});
      },
      toUserFavoratePage(){
        this.$router.push({path: '/userFavorate'});
      },
      //微信分享
      /*wxRegister () {
        // 这边的接口请换成你们自的
        this.$axios.post(process.env.API_HOST + 'weChat/share', {reqUrl: window.location.href.split('#')[0]}, {
          timeout: 5000,
          withCredentials: true
        }).then((res) => {
          console.log(res);
          console.log(res.data, '22');
          wx.config({
            debug: false, // 开启调试模式
            appId: 'wx12e89e49926d870f', // 必填，公众号的唯一标识
            timestamp: res.data.timestamp, // 必填，生成签名的时间戳
            nonceStr: res.data.nonceStr, // 必填，生成签名的随机串
            signature: res.data.signature, // 必填，签名，见附录1
            jsApiList: ['updateAppMessageShareData','updateTimelineShareData'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
          })
        }).catch((error) => {
          console.log(error)
        });
        wx.ready(() => {
          //分享微信朋友圈
          wx.updateAppMessageShareData({
            title: '限时团购周 挑战最低价', // 分享标题
            link: window.location.href.split('#')[0], // 分享链接
            imgUrl: 'http://img.alicdn.com/tps/i4/TB1.ttgeEuF3KVjSZK9SuvVtXXa.jpg_240x240q90.jpg',// 分享图标
            success () {
              // 用户成功分享后执行的回调函数
              alert('分享成功')
            },
            cancel () {
              // 用户取消分享后执行的回调函数
              alert('已取消分享')
            }
          });
          wx.updateTimelineShareData({
            title: '限时团购周 挑战最低价', // 分享标题
            link: window.location.href.split('#')[0], // 分享链接
            imgUrl: 'http://img.alicdn.com/tps/i4/TB1.ttgeEuF3KVjSZK9SuvVtXXa.jpg_240x240q90.jpg',// 分享图标
            success () {
              // 用户成功分享后执行的回调函数
              alert('分享成功')
            },
            cancel () {
              // 用户取消分享后执行的回调函数
              alert('已取消分享')
            }
          })
        })
      },*/

      toLogoPage() {
        this.$router.push({path: '/login'});
      },
      toUserHomePage() {
        this.$router.push({path: '/userHome'})
      },
      go() {
        alert(location.href.split('#')[0], '111')
      },
      dd() {
        console.log(location.href, '111')
      }
    }
  }
</script>

<style lang="scss" scoped>
  $sc: 25;
  .clickRight {
    float: right;
    height: 100%;
    text-align: center;
    font-size: 12px;
    line-height: 70/$sc+rem;
    margin-right: 10/$sc+rem;

    .fa-angle-right {
      vertical-align: middle;
      font-size: 25px;
      margin-top: -4/$sc+rem;
      margin-left: 5/$sc+rem;
      color: #ccc;
    }
  }

  .main {
    background: #f2f2f2;
    .uploadUserMessages{
      position: fixed;
      width: 100%;
      height: 100%;
      background: rgba(0,0,0,0.5);
      z-index: 100;
      .userMessage{
        position: absolute;
        bottom: 0;
        height: auto;
        background: #fff;
        width: 100%;
        li{
          height: 40/$sc+rem;
          width: 90%;
          margin: 0 auto;
          line-height: 40/$sc+rem;
          list-style: none;
          border-bottom: 1px solid rgba(0,0,0,0.5);
          text-align: center;
        }
        li:last-child{
          border: none;
        }
      }
    }
    .top {
      width: 100%;
      height: 50/$sc+rem;
      background: #B0BECB;

      .right {
        width: 100/$sc+rem;
        float: right;
        height: 100%;

        span {
          display: block;
          float: left;
          color: #fff;
          width: 50%;
          height: 100%;
          text-align: center;
          line-height: 50/$sc+rem;
          font-size: 25px;
        }
      }
    }

    .personal {
      width: 100%;
      margin: 5/$sc+rem 0;
      height: 70/$sc+rem;
      background: #fff;
      overflow: hidden;

      img {
        width: 50/$sc+rem;
        height: 50/$sc+rem;
        border-radius: 50%;
        display: inline-block;
        vertical-align: middle;
        margin: 10/$sc+rem;
      }

      a {
        text-decoration: underline;
      }
    }

    .buySell {
      width: 100%;
      margin: 5/$sc+rem 0;
      height: 50/$sc+rem;
      background: #fff;
      overflow: hidden;
      line-height: 50/$sc+rem;

      .fa-shopping-cart {
        font-size: 25px;
        color: #ccc;
        margin-top: -4/$sc+rem;
        margin-left: 10/$sc+rem;
        margin-right: 10/$sc+rem;
        vertical-align: middle;
      }

      .spanBuySell {
        height: 100%;
        display: block;
        float: right;

        .fa-angle-right {
          font-size: 25px;
          color: #ccc;
          vertical-align: middle;
          margin-top: -4/$sc+rem;
          margin-right: 10/$sc+rem;
        }
      }

      .buy {
        width: 49%;
        float: left;
        height: 100%;
        line-height: 50/$sc+rem;
        border-right: 1px solid #f2f2f2;
      }

      .sell {
        width: 50%;
        float: right;
        height: 100%;
        line-height: 50/$sc+rem;
      }
    }

    .collection, .assets {
      width: 100%;
      margin: 5/$sc+rem 0;
      height: 50/$sc+rem;
      background: #fff;
      overflow: hidden;
      line-height: 50/$sc+rem;

      .fa-heart {
        color: red;
        margin: -4/$sc+rem 10/$sc+rem 0 10/$sc+rem;
        font-size: 20px;
        vertical-align: middle;
      }

      .fa-line-chart {
        margin: -4/$sc+rem 10/$sc+rem 0 10/$sc+rem;
        font-size: 20px;
        vertical-align: middle;
        color: #ccc;
      }

      .spanColloction, .spanAssets {
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
</style>
