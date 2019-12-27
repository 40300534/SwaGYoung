<template>
	<div>
		<div class="top">
			<b @click="back"><i class="fa fa-angle-left"></i></b>
			<span>商品详情</span>
		</div>
		<main>
			<div class="details">
				<img :src="orderBuyPreview.masterPicture" />
				<p>{{orderBuyPreview.productName}}</p>
				<div class="sell">
					<span>出价</span>
					<span>立即购买</span>
				</div>
				<mt-switch class="slideButton" v-model="isSliderState" @change="switchingSliderState"></mt-switch>
			</div>
			<div class="buy">
				<!--立即购买-->
				<div class="buyBlock" v-show="isSliderState">
					<p><span class="span1">价格</span>
						<span class="span2">{{orderBuyPreview.price}}</span></p>
					<p v-for="(item, index) in feeInfoFixed" :key="index"><span class="span1">{{item.name }}</span>
						<span class="span2">{{item.value}}</span></p>
					<p class="p1"><span>总价</span><span>￥{{buyerOrderPreview.totalAmount}}</span></p>
				</div>
				<!--出价-->
				<div class="buyBlock" v-show="!isSliderState">
					<p><span class="span1">出价</span>￥<input @input="countDepositBidPrice" class="inp1" type="text" v-model="bidPrice"
						 placeholder="输入价格" />
						<span class="span3">(当前最高买价:￥{{buyerOrderPreview.highestBidBuy}})</span>
					</p>
					<p v-for="(item, index) in feeInfoPercent" :key="index"><span class="span1">{{item.name}}</span><span class="span2">{{item.value}}</span></p>
					<!--<p class="p1"><span>总价</span><span>￥2095</span></p>-->
				</div>
				<div class="address">
					<input type="text" @click="toAddressPage" v-model="address" placeholder="输入地址">
				</div>
			</div>
		</main>
		<div class="hide"></div>
		<div class="bottom">
			<i @click="toPaymentPage" v-show="isSliderState">提交订单<span>(去支付￥{{buyerOrderPreview.totalAmount}})</span></i>
			<i @click="toPaymentPage" v-show="!isSliderState">提交订单<span>(去支付保证金:{{depositBidPrice}})</span></i>
		</div>
	</div>
</template>

<script>
	import {
		Toast
	} from 'mint-ui';
	import qs from 'qs';
	import eventVue from '../../utils/eventVue';
	export default {
		props: ['productId'],
		data() {
			return {
				token: localStorage.getItem('token'),
				isSliderState: true,
				buyerOrderPreview: {},
				orderBuyPreview: {},
				feeInfo: [],
				totalAmount: '',
				bidPrice: '',
				depositBidPrice: '',
				htmls: '',
				feeInfoFixed: [],
				feeInfoPercent: [],
				transcationTypeId: 3,
				address: ''
			}
		},
		created() {
			this.address = this.$route.params.data.address;
			this.getData();
		},
		mounted() {
			/*console.log(this.buyerOrderPreview,'op');*/
		},
		methods: {
			toAddressPage() {
				this.$router.push({
					name: 'newAddressPage',
					params: {
						row: 'buyerOrderPreviewPage',
						productId: this.productId
					}
				});
			},
			countDepositBidPrice() {
				this.depositBidPrice = (this.bidPrice * this.buyerOrderPreview.deposit / 100).toFixed(2);
			},
			switchingSliderState() {
				this.isSliderState ? this.transcationTypeId = 3 : this.transcationTypeId = 1;
			},
			toPaymentPage() {
				if (!this.token) {
					this.$router.push('/login');
				} else {
					let obj = {
						userId: localStorage.getItem('userId'),
						transcationTypeId: this.transcationTypeId,
						biddingPrice: this.bidPrice,
						orderPreviewBO: this.buyerOrderPreview
					};
					this.$store.dispatch('payProductOrder', obj).then((res) => {
						console.log(res);
						if (res.data.code === 200) {
							//let routerData = this.$router.push({name:'paymentPage',query:{htmls:res.data}});
							this.htmls = res.data.data;
							const div = document.createElement('div');
							div.innerHTML = this.htmls;
							document.body.appendChild(div);
							document.forms[0].submit();
						} else {
							Toast({
								message: res.data.message,
								position: 'middle',
								duration: 1000
							})
						}
					})
				}
			},
			back() {
				this.$router.go(-1);
			},
			getData() {
				let obj = {
					productId: this.productId,
					userId: localStorage.getItem('userId')
				};
				console.log(obj);
				this.$store.dispatch('findBuyerOrderPreview', qs.stringify(obj)).then((res) => {
					this.buyerOrderPreview = res.data;
					this.orderBuyPreview = res.data.productInfo;

					this.feeInfo = res.data.feeInfo;
					this.feeInfo.forEach((item) => {
						if (item.feeType === 'fixed') {
							item.value = '￥' + item.value;
							this.feeInfoFixed.push(item);
						} else if (item.feeType === 'percent') {
							this.feeInfoPercent.push(item);
							item.value = item.value + '%';
						}
					});

				})
			}
		}
	}
</script>

<style scoped lang="scss">
	$sc: 25;

	.top {
		background: #B0BECB;
		width: 100%;
		height: 50/$sc+rem;
		position: fixed;
		top: 0;
		left: 0;
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

	main {
		margin-top: 50/$sc+rem;
		margin-bottom: 80/$sc+rem;
		width: 100%;
		background: #f2f2f2;

		.details {
			width: 100%;
			background: #fff;
			overflow: hidden;

			img {
				display: block;
				width: 200/$sc+rem;
				height: 200/$sc+rem;
				margin: 20/$sc+rem auto;
			}

			p {
				width: 90%;
				margin: 0 5%;
				text-align: center;
			}

			.sell {
				width: 120/$sc+rem;
				margin: 20/$sc+rem auto;
				background: #f37d0f;
				font-size: 16px;

				span:first-child {
					float: left;
					margin-left: 15/$sc+rem;
				}

				span:last-child {
					float: right;
				}
			}

			.slideButton {
				width: 80/$sc+rem;
				margin: 0 auto;
				display: block;
			}
		}

		.buy {
			width: 100%;
			background: #fff;
			margin-top: 5/$sc+rem;

			.buyBlock {
				width: 90%;
				margin: 0 5%;

				p {
					padding: 10/$sc+rem 0;

					.span1 {
						color: #999;
						display: inline-block;
						width: 30%;
					}

					.inp1 {
						border: none;
						width: 75/$sc+rem;
						border-bottom: 1px solid #ccc;
						outline: none;
						font-size: 18px;
					}

					input::-webkit-input-placeholder {
						color: #ccc;
					}

					.span3 {
						font-size: 12px;
						color: #ccc;
					}
				}

				.p1 {
					float: right;

					span:last-child {
						color: #fa7a7a;
						font-size: 20px;
						display: inline-block;
						margin-left: 20/$sc+rem;
					}
				}
			}

			.address {
				clear: both;
				border: none;

				input {
					outline: none;
					border: none;
					border-bottom: 1px solid #ccc;
					width: 80%;
					margin-left: 10%;
					height: 30/$sc+rem;
					font-size: 18px;
				}
			}
		}
	}

	.bottom {
		width: 100%;
		height: 50/$sc+rem;
		background: #fff;
		position: fixed;
		bottom: 0;
		left: 0;

		i {
			font-style: normal;
			width: 80%;
			margin: 0 auto;
			display: block;
			background: #B0BECB;
			color: #fff;
			text-align: center;
			line-height: 50/$sc+rem;
		}
	}
</style>
