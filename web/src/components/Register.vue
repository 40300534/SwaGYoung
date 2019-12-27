<template>
		<div class="box">
			<label>用户姓名：<input type="text" name="name" v-model="name" placeholder="输入真实姓名" /></label>
			<label>用户昵称：<input type="text" name="displayName" v-model="displayName" placeholder="输入昵称" /></label>
      <label>用户性别：男<input class="sex" type="radio" name="sex" value="1" v-model="sex"/>女<input class="sex" type="radio" name="sex" v-model="sex" value="2"/></label>
      <label>联系方式：<input type="text" name="phone" v-model="phone" placeholder="输入联系方式" /></label>
      <label>用户生日：<input type="date" name="dob" v-model="dob" placeholder="输入出生年月日"/></label>
			<label>密　　码：<input type="password" name="password" v-model="password" placeholder="6-12位数字字母组成" /></label>
			<label>确认密码：<input type="password" v-model="passwordOne" placeholder="6-12位数字字母组成" /></label>
			<button @click="register">注册</button>
		</div>
</template>

<script>
	export default {
		data() {
			return {
				name: '',
        displayName: '',
        sex:'',
				phone: '',
				password: '',
				passwordOne: '',
        dob: ''
			};
		},
		methods: {
			register(){
				//验证手机号
				const regPhone=/^1[34578]\d{9}$/;
				if(regPhone.test(this.phone) == false){
					alert('手机号不符合规范');
					this.phone = '';
				}
				//验证密码
				const regPwd = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$/
				if(regPwd.test(this.password) == false){
					alert('密码不规范');
					this.password = '';
				}
				//二次验证密码
				if(this.password !== this.passwordOne){
					alert('您输入的密码不一致')
					this.passwordOne = ''
				}
				let obj = {
				  name: this.name,
          displayName: this.displayName,
					sex: this.sex,
          phone: this.phone,
					password: this.password,
          dob: this.dob
				};
				console.log(obj);
				this.$store.dispatch('register', obj).then((res) => {
				  console.log(res);
					if(res.data.code === 20000){
					  this.$router.push({path: '/login'})
          }
				})
			} 
		}
	}
</script>

<style lang="scss" scoped>
	$sc: 25;
	.box{
		width: 80%;
		height: 450/$sc+rem;
		position: absolute;
		top: 0;
		left: 0;
		right: 0;
		bottom: 0;
		margin: 20/$sc+rem auto;
		label{
			display: block;
			font-size: 16px;
			margin: 10% 5%;
      .sex{
        width: 20/$sc+rem;
        margin-left: 5/$sc+rem;
        margin-right: 25/$sc+rem;
        margin-top: -4/$sc+rem;
        vertical-align: middle;
      }
			input{
				border: 1px solid #f0efed;
				outline: none;
				height: 25/$sc+rem;
        width: 68%;
			}
		}
		button{
			width: 80%;
			height: 50/$sc+rem;
			background: #06c1ae;
			outline: none;
			text-align: center;
			line-height: 50/$sc+rem;
			margin: 5% 10%;
			color: #fff;
			font-size: 20px;
		}
	}
</style>
