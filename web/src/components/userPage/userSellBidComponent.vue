<template>
  <div class="nav">
    <p class="pSort">
      <span v-for="(item, index) in sortList" :key="index" @click="changeSort(index)"
      :class="{vclass: index === sortIndex}">{{item}}</span>
    </p>
    <ul v-infinite-scroll="loadMore"
        infinite-scroll-disabled="loading"
        infinite-scroll-distance="10">
      <li v-for="(item, index) in userSellBidProducts" :key="index">
        <img :src="item.image" alt="" />
        <div class="right">
          <p class="p1">{{item.productName}}</p>
          <p class="p2">{{item.createTime}}</p>
          <p class="p3">￥{{item.price}}</p>
        </div>
      </li>
    </ul>
  </div>
</template>
<script>
  import toast from '../../assets/js/toast.js';
  import { InfiniteScroll, Toast  } from 'mint-ui';
  import Vue from 'vue'
  Vue.use(InfiniteScroll);
  export default {
    data(){
      return {
        sortList: ['时间降序', '时间升序', '价格降序', '价格升序'],
        sortIndex: 0,
        pageNum: 1,
        userSellBidProducts:[],
        loading:false,
        hasMoreData: true,
      }
    },
    methods: {
      changeSort(index){
        this.sortIndex = index;
        if(index === 0){
          this.changeTimeDesc();
        } else if(index === 1){
          this.changeTimeAsc();
        } else if(index === 2){
          this.changePriceDesc();
        }else if(index === 3){
          this.changePriceAsc();
        }
      },
      changeTimeAsc(){
        this.userSellBidProducts.sort((a, b) => {
          let aTimeString = a.createTime;
          let bTimeString = b.createTime;
          aTimeString = aTimeString.replace(/-/g, '/');
          bTimeString = bTimeString.replace(/-/g, '/');
          let aTime = new Date(aTimeString).getTime();
          let bTime = new Date(bTimeString).getTime();
          return aTime - bTime;
        })
      },
      changePriceAsc(){
        this.userSellBidProducts.sort((newPrice, oldPrice) => {
          let newPriceString = newPrice.price;
          let oldPriceString = oldPrice.price;
          return newPriceString - oldPriceString;
        })
      },
      changePriceDesc(){
        this.userSellBidProducts.sort((newPrice, oldPrice) => {
          let newPriceString = newPrice.price;
          let oldPriceString = oldPrice.price;
          return oldPriceString - newPriceString;
        })
      },
      changeTimeDesc(){
        this.userSellBidProducts.sort((a, b) => {
          let aTimeString = a.createTime;
          let bTimeString = b.createTime;
          aTimeString = aTimeString.replace(/-/g, '/');
          bTimeString = bTimeString.replace(/-/g, '/');
          let aTime = new Date(aTimeString).getTime();
          let bTime = new Date(bTimeString).getTime();
          return bTime - aTime;
        })
      },
      getData(){
        if(!this.hasMoreData){
          return;
        }
        this.loading = true;
        let close = Toast({
          message:'加载中。。。',
          position: 'middle',
          duration: -1
        });
        let obj ={
          userId: localStorage.getItem('userId'),
          pageNum: this.pageNum,
          pageSize: 5
        };
        this.$store.dispatch('findUserSellBid', obj).then((res) => {
          if(res.data.list == ''){
            close.close();
            Toast({
              message: toast.userSellBidProduct,
              position: 'middle',
              duration: 1000
            });
          }else {
            this.userSellBidProducts = this.userSellBidProducts.concat(res.data.list);
            let totalPage = res.data.pages;
            let currentPage = res.data.pageNum;
            this.loading = false;
            this.hasMoreData = totalPage !== currentPage;
            this.pageNum += this.hasMoreData ?1:0;
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
  .vclass{
    color: yellow;
  }
  .nav {
    margin-top: 120/$sc+rem;
    .pSort{
      color: #ccc;
      height: 30/$sc+rem;
      line-height: 30/$sc+rem;
      position: fixed;
      top: 80/$sc+rem;
      background: #fff;
      z-index: 100;
      text-align: center;
      span{
        display: inline-block;
        margin-right: 5/$sc+rem;
      }
    }
    ul {
      width: 96%;
      margin: 0 2%;
      overflow: hidden;

      li {
        list-style: none;
        margin-bottom: 10/$sc+rem;
        width: 100%;
        overflow: hidden;

        img {
          display: block;
          float: left;
          width: 134/$sc+rem;
          height: 134/$sc+rem;
        }

        .right {
          width: 58%;
          float: right;
          margin-left: 15/$sc+rem;
          position: relative;
          height: 134/$sc+rem;

          .p1 {
            width: 100%;
            display: -webkit-box;
            -webkit-box-orient: vertical;
            -webkit-line-clamp: 2;
            overflow: hidden;
          }

          .p2 {
            color: #d6d6d6;
          }

          .p3 {
            color: #D66228;
            position: absolute;
            left: 0;
            bottom: 0;
            font-size: 25px;
          }

          .p4 {
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
</style>