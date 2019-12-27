<template>
  <div class="nav">
    <ul v-infinite-scroll="loadMore"
        infinite-scroll-disabled="loading"
        infinite-scroll-distance="10">
      <li v-for="(item, index) in userBuyHistoryPrducts" :key="index">
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
  import toast from '@/assets/js/toast.js';
  import { InfiniteScroll, Toast  } from 'mint-ui';
  import Vue from 'vue'
  Vue.use(InfiniteScroll);
  export default {
    data(){
      return {
        pageNum: 1,
        userBuyHistoryPrducts:[],
        loading:false,
        hasMoreData: true,
      }
    },
    methods: {
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
        let obj = {
          userId: localStorage.getItem('userId'),
          pageNum: this.Pagenum,
          pageSize: 5,
        };
        this.$store.dispatch('findUserBuyHistory', obj).then((res) => {
          if(res.data.list == ''){
            close.close();
            Toast({
              message:toast.userBuyHistoryProduct,
              position: 'middle',
              duration: 1000
            })
          }else{
            this.userBuyHistoryPrducts = this.userBuyHistoryPrducts.concat(res.data.list);
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
  .nav {
    ul {
      width: 96%;
      margin: 90/$sc+rem 2% 0;
      overflow: hidden;

      li {
        list-style: none;
        margin-top: 10/$sc+rem;
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