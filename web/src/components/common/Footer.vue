<template>
  <div>
    <div class="hide"></div>
    <div class="footer">
      <router-link
        @click="change(index)"
        v-for="(item, index) in bottomSwitching"
        :key="item.id" :to="item.path"
        tag="li">
        <span v-html="item.text"></span>
        <i>{{item.name}}</i>
      </router-link>
    </div>
  </div>

</template>

<script>
	export default {
		data() {
			return {
				bottomSwitching: [{
						id: 1,
            text: '<i class="fa fa-home"></i>',
						name: "首页",
						path: '/'
					},
					{
						id: 2,
            text: '<i class="fa fa-list-alt"></i>',
						name: '商品',
						path: '/product'
					},
					{
						id: 3,
            text: '<i class="fa fa fa-list"></i>',
						name: '卖家',
						path: '/sellRequest'
					},
					{
						id: 4,
            text: '<i class="fa fa fa-user-o"></i>',
						name: '我的',
						path: '/user'
					}
				]
			};
		},
    methods: {
		  change(index){
		    console.log(index)
      }
    }
	}
</script>

<style lang="scss" scoped>
	$sc: 25;
	.router-link-exact-active {
		color: #ff6300;
	}
  .hide{
    width: 100%;
    height: 50/$sc+rem;
    background: #fff;
  }
	.footer {
		display: inline-block;
    width: 100%;
    height: 50/$sc+rem;
		position: fixed;
		bottom: 0;
		left: 0;
		background: #eee;
		color: #ccc;
		li{
			width: 25%;
      font-size: 12px;
			display: block;
			float: left;
      height: 50/$sc+rem;
      background: #fff;
			text-decoration: none;
			text-align: center;
      span{
        display: block;
        font-size: 25px;
        margin: 0;
        padding: 0;
      }
      i{
        display: block;
        margin-top: -0.2rem;
        font-style: normal;
      }
		}
	}
</style>
