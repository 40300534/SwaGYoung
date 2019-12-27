<template>
  <div>
    <div class="swipe">
      <mt-swipe :auto="4000">
        <mt-swipe-item class="swipeone" v-for="(item, index) in allSwipeImage" :key="item.id">
          <img :src="item.imageFolder" />
        </mt-swipe-item>
      </mt-swipe>
    </div>
  </div>
</template>

<script>
  export default {
    name: "Classification",
    data(){
      return {
        allSwipeImage:[]
      }
    },
    created() {
      this.getData()
    },
    methods: {
      getData(){
        this.$store.dispatch('findAllSwipeImage').then((res) => {
          this.allSwipeImage = res.data
        })
      }
    }
  }
</script>

<style scoped lang="scss">
  $sc: 25;
  .swipe{
    width: 100%;
    height: 200/$sc+rem;
    margin-top: 5/$sc+rem;

    .swipeone{
      width: 100%;
      height: 100%;

      img{
        width: 100%;
        height: 100%;
        display: block;
      }
    }
  }
</style>