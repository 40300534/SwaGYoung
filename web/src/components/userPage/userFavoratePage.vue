<template>
  <div class="main">
    <div class="top">
      <b @click="back"><i class="fa fa-angle-left"></i></b>
      <span>我的收藏</span>
    </div>
    <div class="nav">
      <ul v-infinite-scroll="loadMore"
      infinite-scroll-disabled="loading"
      infinite-scroll-distance="10">
        <li v-for="(item, index) in productsList" :key="index">
          <img :src="item.masterPicture" alt="" />
          <div class="right">
            <p class="p1">{{item.productName}}</p>
            <!--<p class="p2">276098人收藏</p>-->
            <p class="p3">￥{{item.price}}</p>
            <p class="p4">找相似</p>
          </div>
        </li>
        <!--<li>
          <img src="../../assets/goods.jpg" alt="" />
          <div class="right">
            <p class="p1">这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊</p>
            <p class="p2">276098人收藏</p>
            <p class="p3">￥324</p>
            <p class="p4">找相似</p>
          </div>
        </li>
        <li>
          <img src="../../assets/goods.jpg" alt="" />
          <div class="right">
            <p class="p1">这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊</p>
            <p class="p2">276098人收藏</p>
            <p class="p3">￥324</p>
            <p class="p4">找相似</p>
          </div>
        </li>
        <li>
          <img src="../../assets/goods.jpg" alt="" />
          <div class="right">
            <p class="p1">这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊这可真是一件好裙子啊</p>
            <p class="p2">276098人收藏</p>
            <p class="p3">￥324</p>
            <p class="p4">找相似</p>
          </div>
        </li>-->
      </ul>
    </div>
  </div>
</template>

<script>
  import { InfiniteScroll, Toast  } from 'mint-ui';
  import Vue from 'vue'
  Vue.use(InfiniteScroll);
  export default {
    data(){
      return {
        loading:false,
        has: true,
        num: 1,
        productsList: []
      }
    },
    created(){

    },
    methods: {
      back(){
        this.$router.go(-1);
      },
      getData(){
        if(!this.has){
          return;
        }
        this.loading = true;
        let close = Toast({
          message:'加载中。。。',
          position: 'middle',
          duration: -1
        });
        let obj = {
          userId: localStorage.getItem('userId'),
          pageNum: this.num,
          pageSize: 5
        };
        this.$store.dispatch('findUserFavorateProducts', obj).then((res) => {
          if(res.data.list == '') {
            close.close();
          } else {
            this.productsList = this.productsList.concat(res.data.list);
            let totalPage = res.data.pages;
            let currentPage = res.data.pageNum;
            this.loading = false;
            this.has = totalPage !== currentPage;
            this.num += this.has ? 1 : 0;
            close.close();
          }
        })
      },
      loadMore(){
        this.getData();
      }
    }
  }
</script>

<style scoped lang="scss">
  $sc: 25;
  .main {
    .top {
      background: #B0BECB;
      height: 50/$sc+rem;
      width: 100%;
      text-align: center;
      line-height: 50/$sc+rem;
      position: fixed;
      top: 0;
      left: 0;
      z-index: 1;
      span {
        display: inline-block;
        margin-left: -50/$sc+rem;
        color: #fff;
      }

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
    }
    .nav{
      margin-top: 50/$sc+rem;
      ul{
        width: 96%;
        margin: 5/$sc+rem 2%;
        overflow: hidden;
        li{
          list-style: none;
          margin-top: 10/$sc+rem;
          width: 100%;
          overflow: hidden;
          img{
            display: block;
            float: left;
            width: 134/$sc+rem;
            height: 134/$sc+rem;
          }
          .right{
            width: 58%;
            float: right;
            margin-left: 15/$sc+rem;
            position: relative;
            height: 134/$sc+rem;
            .p1{
              width: 100%;
              display: -webkit-box;
              -webkit-box-orient: vertical;
              -webkit-line-clamp: 2;
              overflow: hidden;
            }
            .p2{
              color: #d6d6d6;
            }
            .p3{
              color: #D66228;
              position: absolute;
              left: 0;
              bottom: 0;
              font-size: 25px;
            }
            .p4{
              font-size: 12px;
              position: absolute;
              width: 55/$sc+rem;
              height: 24/$sc+rem;
              line-height: 24/$sc+rem;
              text-align: center;
              border: 1px solid #cacaca;
              color: #cacaca;
              border-radius: 15/$sc+rem;
              bottom: 0;
              right: 0
            }
          }
        }
      }
    }
  }
</style>