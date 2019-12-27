<template>
	<div class="box">
		<label>用　户　名：<input type="text" name="phone" v-model="phone" placeholder="请输入手机号码" /></label>
		<label>密　　　码：<input type="password" name="password" v-model="password" placeholder="请输入密码" /></label>
		<button @click="login">登录</button>
		<router-link to="register">还未注册？去注册</router-link>
	</div>
</template>

<script>
  import qs from 'qs';
  export default {
		data() {
			return {
				phone: '',
				password: '',
				userToken: '',
        userId: ''
			};
		},
		created(){
      console.log(this)
		},
		methods: {
			login() {
				let obj = {
          phone: this.phone,
          password: this.password,
				};
				this.$store.dispatch('login',qs.stringify(obj)).then((res) => {
          if(res.data.code === 200){
            this.userToken = res.data.data.token;
            this.userId = res.data.data.UserId;
            localStorage.setItem('token',this.userToken);
            localStorage.setItem('userId',this.userId);
            this.$router.go(-1);
          }else{
              this.$router.push({path: '/'});
          }
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	$sc: 25;

	.box {
		width: 80%;
		height: 400/$sc+rem;
		background: #fff;
		position: absolute;
		top: 0;
		left: 0;
		right: 0;
		bottom: 0;
		margin: auto;

		label {
			display: block;
			font-size: 16px;
			margin: 10% 5%;

			input {
				border: 1px solid #f0efed;
				outline: none;
				height: 25/$sc+rem;
			}
		}

		button {
			width: 80%;
			height: 50/$sc+rem;
			background: #06c1ae;
			outline: none;
			text-align: center;
			line-height: 50/$sc+rem;
			margin: 10%;
			color: #fff;
			font-size: 20px;
		}

		a {
			font-size: 12px;
			color: #06c1ae;
			margin-left: 10%;
		}
	}
</style>
