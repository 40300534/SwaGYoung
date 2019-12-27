<template>
	<div class="box">
		<div class='top'><b><i class="fa fa-angle-left"></i></b><span>新增收貨地址</span></div>
		<mt-field @click.native="showAddress" readonly v-model="address" label="收货地址" placeholder="选择您所在的地区" type="text"></mt-field>
		<mt-field label="街道/门牌号" v-model="detailedAddress" placeholder="详细地址,例:16号楼55层501室" type="text"></mt-field>
		<mt-field label="联系人" v-model="username" placeholder="填写收货人姓名" type="text"></mt-field>
		<mt-field label="手机号" v-model="phone" placeholder="填写收货人手机号" type="tel"></mt-field>
		<input type="checkbox" :true-value="1" :false-value="0" @click="checkAddress" />是否为默认地址
		<div class="bottom">
			<span @click="save">保存地址</span>
		</div>
		<!--三级联动-->
		<div class="address" v-show="addressFlag">
			<v-distpicker class="picker" type="mobile" @selected='selected'>
			</v-distpicker>
		</div>
	</div>
</template>

<script>
	import eventVue from '../utils/eventVue';
	export default {
		data() {
			return {
				value: '',
				addressFlag: false,
				address: '',
				detailedAddress: '',
				province: '',
				city: '',
				area: '',
				username: '',
				phone: ''
			};
		},
		created() {

		},
		methods: {
			checkAddress() {
				console.log(this.value);
			},
			showAddress() {
				this.addressFlag = true;
			},
			selected(data) {
				this.province = data.province.value;
				this.city = data.city.value;
				this.area = data.area.value;
				this.address = this.province + this.city + this.area;
				this.addressFlag = false;
			},
			save() {
				let address = {
					address: this.address + this.detailedAddress,
					province: this.province,
					city: this.city,
					phone: this.phone,
					userName: this.username,
					isDefault: 0,
					userId: localStorage.getItem('userId')
				};
				this.$store.dispatch('newAddress', address).then((res) => {

					address.id = res.data;
					this.$router.push({
						name: 'buyerOrderPreviewPage',
						params: {
							productId: this.$route.params.productId,
							data: address
						}
					});
				});
			}
		}
	}
</script>

<style scoped lang="scss">
	$sc: 25;

	.default {
		input {
			margin-left: 20/$sc+rem;
		}
	}

	.top {
		background: #B0BECB;
		width: 100%;
		height: 50/$sc+rem;
		text-align: center;
		line-height: 50/$sc+rem;
		z-index: 100;
		color: #fff;

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

		span {
			margin-left: -50/$sc+rem;
			display: inline-block;
			font-size: 16px;
		}
	}

	.mint-cell {
		min-height: 60/$sc+rem;
	}

	.bottom {
		background: #fff;

		span {
			display: inline-block;
			width: 90%;
			height: 50/$sc+rem;
			font-size: 16px;
			color: #fff;
			margin: 20% 5%;
			background: #B0BECB;
			text-align: center;
			line-height: 50/$sc+rem;
		}
	}

	.address {
		position: fixed;
		bottom: 0;
		left: 0;
		height: 300/$sc+rem;
		width: 100%;
		overflow: auto;

	}
</style>
