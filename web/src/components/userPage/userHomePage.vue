<template>
  <div class="main">
    <div class="top">
      <b @click="back"><i class="fa fa-angle-left"></i></b>
      <span>个人主页</span>
    </div>
    <div class="personal">
      <div class="left">
        <img src="../../assets/header.png" alt=""/>
      </div>
      <div class="right">
        <span>Andy</span>
        <span>13901403456</span>
        <span>用户等级：王者</span>
      </div>
    </div>
    <div class="pic">
      <li>
        <el-upload
          class="avatar-uploader"
          action="https://jsonplaceholder.typicode.com/posts/"
          :show-file-list="false"
          :on-success="handleAvatarSuccess">
          <img v-if="imageUrl" :src="imageUrl" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </li>
      <li><img src="../../assets/goods.jpg"  alt=""/></li>
      <li><img src="../../assets/goods.jpg" alt=""/></li>
      <li><img src="../../assets/goods.jpg" alt=""/></li>
      <li><img src="../../assets/goods.jpg" alt=""/></li>
      <li><img src="../../assets/goods.jpg" alt=""/></li>
      <li><img src="../../assets/goods.jpg" alt=""/></li>
      <li><img src="../../assets/goods.jpg" alt=""/></li>
      <li><img src="../../assets/goods.jpg" alt=""/></li>
    </div>
  </div>
</template>

<script>
  export default {
    data(){
      return {
        imageUrl: ''
      }
    },
    methods: {
      back(){
        this.$router.go(-1);
      },
      handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
      },
    }
  }
</script>

<style scoped lang="scss">
  $sc: 25;
  .main{
    .top{
      background: #B0BECB;
      height: 50/$sc+rem;
      width: 100%;
      text-align: center;
      line-height: 50/$sc+rem;
      span{
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
    .personal {
      width: 100%;
      margin: 5/$sc+rem 0 0;
      background: #fff;
      overflow: hidden;
      .left{
        width: 50/$sc+rem;
        float: left;
        margin-top: 10/$sc+rem;
        img {
          width: 50/$sc+rem;
          height: 50/$sc+rem;
          border-radius: 50%;
          display: inline-block;
          vertical-align: middle;
          margin: 10/$sc+rem;
          margin-top: -1/$sc+rem;
        }
      }
      .right{
        margin-top: 20/$sc+rem;
        span{
          display: block;
          margin-left: 70/$sc+rem;
          font-size: 16px;
        }
      }
    }
    .pic{
      clear: both;
      display: -webkit-flex;
      display: flex;
      flex-wrap: wrap;
      margin-top: 20/$sc+rem;
      li{
        list-style: none;
        width: 120/$sc+rem;
        height: 120/$sc+rem;
        display: -webkit-flex;
        display: flex;
        align-items: center;
        justify-content: center;
        border: 2px solid #fff;
        img{
          width: 100%;
          height: 100%;
          display: block;
        }
        .avatar-uploader .el-upload {
          border: 1px dashed #d9d9d9;
          border-radius: 6px;
          cursor: pointer;
          position: relative;
          overflow: hidden;
        }
        .avatar-uploader .el-upload:hover {
          border-color: #409EFF;
        }
        .avatar-uploader-icon {
          font-size: 28px;
          color: #8c939d;
          width: 114px;
          height: 114px;
          line-height: 114px;
          text-align: center;
          border: 1px dotted #8c939d;
        }
        .avatar {
          width: 178px;
          height: 178px;
          display: block;
        }
      }
    }
  }
</style>