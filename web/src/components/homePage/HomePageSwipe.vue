<template>
  <div>
    <div class="box">
      <div class="main"><i></i>最新的产品 <em>more</em></div>
      <ul>
        <li v-for="(item, index) in topNewProducts" :key="item.id">
          <img :src="item.masterPicture" />
          <span>{{item.productName}}</span>
        </li>
      </ul>
    </div>

    <div class="banner">
      <img src="@/assets/banner.jpg" />
    </div>

    <div class="box">
      <div class="main"><i></i>最近销售的产品 <em>more</em></div>
      <ul>
        <li v-for="(item, index) in topSaleProducts" :key="item.id">
          <img :src="item.masterPicture" />
          <span>{{item.productName}}</span>
        </li>
      </ul>
    </div>

    <div class="banner">
      <img src="@/assets/banner.jpg" />
    </div>

    <div class="box">
      <div class="main"><i></i>近30天销量最多 <em>more</em></div>
      <ul>
        <li v-for="(item, index) in topSaleBestProducts" :key="item.id">
          <img :src="item.masterPicture" />
          <span>{{item.productName}}</span>
        </li>
      </ul>
    </div>

    <div class="banner">
      <img src="@/assets/banner.jpg" />
    </div>

  </div>
</template>

<script>
  export default {
    name: "HomePageSwipe",
    data(){
      return {
        topNewProducts: [],
        topSaleProducts: [],
        topSaleBestProducts: []
      }
    },
    created(){
      this.getData()
    },
    methods: {
      getData() {
        this.$store.dispatch('findTopNewProducts').then((res) => {
          this.topNewProducts = res.data.list;
        });

        this.$store.dispatch('findTopSaleProducts').then((res) => {
          this.topSaleProducts = res.data.list;
        });

        this.$store.dispatch('findTopSaleBestProducts').then((res) => {
          this.topSaleBestProducts = res.data.list;
        });

      }
    }
  }
</script>

<style scoped lang="scss">
  $sc: 25;
  .box ::-webkit-scrollbar {
    display: none;
  }

  .box{
    width: 90%;
    height: 200/$sc+rem;
    margin: 10/$sc+rem 5%;
    overflow: hidden;

    .main{
      width: 100%;
      height: 30/$sc+rem;
      line-height: 30/$sc+rem;

      i{
        display: inline-block;
        height: 25/$sc+rem;
        width: 5/$sc+rem;
        background: yellow;
        vertical-align: middle;
        margin: -5/$sc+rem 10/$sc+rem 0 0;
      }

      em{
        display: inline-block;
        font-style: normal;
        float: right;
        color: #999;
        font-size: 20px;
        text-decoration: underline;
      }
    }

    ul{
      display: -webkit-box;
      overflow-x: scroll;
      -webkit-overflow-scrolling:touch;
      padding-bottom: 20/$sc+rem;

      li{
        width: 200/$sc+rem;
        height: 200/$sc+rem;
        list-style: none;
        display: -webkit-flex;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;

        img{
          width: 80/$sc+rem;
          display: block;
        }

        span{
          margin-top: 30/$sc+rem;
          vertical-align: middle;
          text-align: center;
          overflow: hidden;
          text-overflow:ellipsis;
          white-space: nowrap;
          width: 80%;
        }
      }
    }
  }

  .banner{
    width: 100%;
    height: 200/$sc+rem;

    img{
      display: block;
      width: 100%;
      height: 100%;
    }
  }

</style>