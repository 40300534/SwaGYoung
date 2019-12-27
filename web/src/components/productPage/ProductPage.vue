<template>
  <div>
    <div class="box">
      <div class="search">
        <label>
          <img src="@/assets/search.png" />
          <input type="text" v-model.trim="$store.state.value" @input="searchByWordProducts" />
        </label>
      </div>
      <ul class="ulType">
        <li v-for="(item, index) in allCategorys"
            :key="item.id"
            @click="toListPage(index)"
            :class="{active:index===currentIndex}">
          {{item.name}}
        </li>
      </ul>
    </div>
    <div class="hide"></div>
    <div class='pox'>
      <li v-for="(item, index) in Search"  :key="index">{{item.name}}</li>
    </div>
    <Classification></Classification>
    <ul class="main"
        v-infinite-scroll="loadMore"
        infinite-scroll-disabled="loading"
        infinite-scroll-distance="10">
      <li v-for="(item, index) in productsList" :key="index" @click="toDetailsPage(item.productId)">
        <img :src="item.masterPicture" />
        <span>{{item.productName}}</span>
        <i>{{item.price}}</i>
      </li>
      <div class="bottom"></div>
    </ul>
    <Footer></Footer>

  </div>
</template>

<script>
  import Classification from "./Classification";
  import Footer from './../common/Footer';
  import { InfiniteScroll } from 'mint-ui';
  import Vue from 'vue'
  Vue.use(InfiniteScroll);
  import { Toast } from 'mint-ui';
  export default {
    components: {Classification, Footer},
    data(){
      return {
        categoryId: 1,
        currentIndex: 0,
        value: "",
        allCategorys: [],
        productSearchList: [],
        loading:false,
        hasMoreData: true,
        pageNum: 1,
        productsList: []
      }
    },

    created(){
      this.findAllCategory();
    },
    methods: {
      init(){
        this.productsList = [];
        this.loading = false;
        this.hasMoreData = true;
        this.pageNum = 1;
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
          id: this.categoryId,
          pageNum: this.pageNum,
          pageSize: 5
        };
        this.$store.dispatch('findCategoryProducts', obj).then((res) => {
          res.data.list.forEach((item) => {
            item.inventory === 0? item.price = '售空': item.price = '￥' + item.price;
          });
          this.productsList = this.productsList.concat(res.data.list);
          let totalPage = res.data.pages;
          let currentPage = res.data.pageNum;
          this.loading = false;
          this.hasMoreData = totalPage !== currentPage;
          this.pageNum += this.hasMoreData ?1:0;
          close.close();
        })
      },
      loadMore(){
        this.getData();
      },

      findAllCategory() {
        this.$store.dispatch('findAllCategory').then((res) => {
          this.allCategorys = res.data
        })
      },

      searchByWordProducts(){
        this.$store.dispatch('searchByWordProducts').then((res) => {
          this.productSearchList = res.data;
        })
      },

      toListPage(index) {
        this.currentIndex = index;
        this.categoryId = ++index;
        this.init();
        this.getData();
      },
      toDetailsPage(id) {
        this.$router.push({'path': 'productsDetails/' + id})
      }
    },
    computed: {
      Search(){
        if(this.$store.state.value){
          return this.productSearchList.filter(()=>{  //过滤数组元素
            return this.$store.state.value.includes(this.$store.state.value); //如果包含字符返回true
          });
        }
      }
    }
  }
</script>

<style scoped lang="scss">
  $sc: 25;
  .active{
    color: #000;
  }
  .hide{
    height: 80/$sc+rem;
  }

  .box {
    width: 100%;
    height: 80/$sc+rem;
    background: #fff;
    overflow: hidden;
    position: fixed;
    top:0;
    left:0;
    z-index: 99;

    .ulType{
      margin-top: 10/$sc+rem;
      display: flex;
      display: -webkit-flex;
      justify-content: center;
      align-items: center;
      height: 80/$sc+rem;
      width: 100%;
      color: #999;
      li{
        list-style: none;
        width: 20%;
        height: 100%;
        text-align: center;
        font-size: 14px;
      }
    }

    .search {
      width: 80%;
      height: 40/$sc+rem;
      margin-left: 10%;
      margin-top: 5/$sc+rem;
      border-radius: 25/$sc+rem;
      background: #eee;

      img{
        width: 30/$sc+rem;
        height: auto;
        display: block;
        float: left;
        margin-left: 10/$sc+rem;
        margin-top: 4/$sc+rem;
      }

      input{
        -webkit-appearance: none;
        border: none;
        outline: none;
        width: 85%;
        height: 100%;
        float: right;
        background: #eee;
        border-radius:0 25/$sc+ rem 25/$sc+rem 0 ;
      }
    }
  }

  .pox{
    position: fixed;
    top: 80/$sc+rem;
    width: 100%;
    z-index: 99;
    max-height: 400/$sc+rem;
    overflow: auto;
    background: rgba(255,255,255,0.9);

    li{
      list-style: none;
      width: 90%;
      margin-left: 5%;
      border-bottom: 1px solid #999;
      padding: 10/$sc+rem 0;

    }
  }


  .bottom{
    height: 50/$sc+rem;
    width: 100%;
  }
  .main{
    position: absolute;
    top: 280/$sc+rem;
    display: flex;
    display: -webkit-flex;
    width: 100%;
    flex-wrap: wrap;
    margin-top: 10/$sc+rem;

    li:nth-child(2n){
      border-right: none;
    }

    li{
      width: 49.8%;
      height: 300/$sc+rem;
      list-style: none;
      border-bottom: 1px solid #ccc;
      border-right: 1px solid #ccc;
      display: flex;
      display: -webkit-flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;

      img{
        display: block;
        width: 80%;
        height: 150/$sc+rem;
      }

      span{
        display: block;
        margin: 10/$sc+rem 0;
        overflow: hidden;
        text-align: center;
        text-overflow: ellipsis;
        width: 100%;
        white-space: nowrap;
      }

      i{
        display: block;
        font-style: normal;
      }
    }
  }
</style>