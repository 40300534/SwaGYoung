<template>
	<div>
		<!--<div class="share">
      <div class="moreShare">
        <li>微信分享</li>
        <li>微信朋友圈分享</li>
        <li>QQ分享</li>
        <li>QQ空间分享</li>
        <li>新浪微博分享</li>
      </div>
    </div>-->
		<div class="top">
			<b @click="back"><i class="fa fa-angle-left"></i></b>
			<span>商品详情</span>
			<div class="right">
				<em class="em1" @click="toggleUserFavorate"><i v-show="!flagIcon" class="fa fa-heart"></i>
					<i v-show="flagIcon" class="fa fa-heart-o"></i></em>
				<em class="em2"><i class="fa fa-share-square-o"></i></em>
			</div>
		</div>
		<main>
			<div class="swipe">
				<mt-swipe :auto="0">
					<mt-swipe-item class="swipeOne" v-for="(item, index) in productImages" :key="index">
						<img :src="item" />
					</mt-swipe-item>
				</mt-swipe>
			</div>
			<div class="mainDetail">
				<p>{{detailObject.productName}}</p>
				<p class="p1"><b>最近成交价格：</b><span>{{detailObject.lastTransactionPrice}}</span></p>
			</div>
			<div class="mainType">
				<p><span class="span1">产地</span><span class="span2">{{productAttribute.originName}}</span></p>
				<p><span class="span1">品牌</span><span class="span2">{{productAttribute.brandName}}</span></p>
				<p v-for="(item, index) in attributes" :key="index">
					<span class="span1">{{item.displayName}}</span><span class="span2">{{item.value}}</span>
				</p>
			</div>
			<div class="relatedProducts">
				<p>相关商品</p>
				<ul>
					<li v-for="(item, index) in relatedProducts" :key="index">
						<img :src="item.masterPicture">
						<span>{{item.productName}}</span>
					</li>
				</ul>
			</div>
			<div class="mainHtml" v-html="detailObject.description"></div>
			<div style="width: 100%; height: 300px;" id="echart"></div>
		</main>
		<div class="display"></div>
		<div class="bottom">
			<span class="spanLeft" @click="toSellerOrderPreviewPage"><em>
					<span class="span1">￥{{detailObject.maxPrice}}</span>
					<span class="span2"> 最高卖价</span>
				</em><i>&nbsp;出售</i></span>
			<span class="spanRight" @click="toBuyerOrderPreviewPage"><em>
					<span class="span1">￥{{detailObject.price}}</span>
					<span class="span2">最低售价</span>
				</em><i>&nbsp;{{buy}}</i></span>
		</div>
	</div>
</template>

<script>
	import echarts from 'echarts';
	import qs from 'qs';
	export default {
		props: ['productId'],
		data() {
			return {
				buy: "",
				flagIcon: 1,
				productImages: [],
				detailObject: {},
				productAttribute: {},
				attributes: [],
				relatedProducts: [],
				createTime: [],
				price: [],
				token: localStorage.getItem('token')
			}
		},
		created() {
			this.isUserFavorate();
			this.getData();
		},
		mounted() {

		},
		methods: {
			toggleUserFavorate() {
				if (this.token) {
					let obj = {
						productId: this.productId,
						userId: localStorage.getItem('userId')
					};
					this.$store.dispatch('toggleUserFavorate', obj).then((res) => {
						console.log(res);
						res.data.code === 200 ? this.flagIcon = false : this.flagIcon = true;
					})
				} else {
					this.$router.push({
						path: '/login'
					});
				}
			},
			back() {
				this.$router.go(-1)
			},
			isUserFavorate() {
				this.$store.dispatch('isUserFavorate', this.productId).then((res) => {
					this.flagIcon = !res.data;
				});
			},
			getData() {
				this.$store.dispatch('findDetailsByProductId', this.productId).then((res) => {
					console.log(res);
					res.data.inventory === 0 ? this.buy = '售空' : this.buy = '购买';
					this.detailObject = res.data;
					!this.detailObject.lastTransactionPrice ? this.detailObject.lastTransactionPrice = '暂无价格' :
						this.detailObject.lastTransactionPrice = '￥' + this.detailObject.lastTransactionPrice;
					this.productImages = this.detailObject.productImages;
					this.productAttribute = this.detailObject.productAttributes;
					this.attributes = this.productAttribute.attributes;
					this.relatedProducts = this.detailObject.relatedProducts;
				});
				this.$store.dispatch('findHistoryProducts', this.productId).then((res) => {
					res.data.forEach((item) => {
						this.createTime.push(item.createTime);
						this.price.push(item.price);
						let myChart = echarts.init(document.getElementById('echart'));
						let option = {
							tooltip: {
								trigger: 'axis'
							},
							xAxis: [{
								data: this.createTime,
								gridIndex: 1,
								axisLabel: {
									textStyle: {
										color: '#999', //坐标值得具体的颜色
									}
								}
							}],
							yAxis: [{
								splitLine: {
									show: false
								},
								gridIndex: 1,
								axisLabel: {
									margin: 2,
									formatter: function(value, index) {
										if (value >= 1000 && value < 10000) {
											value = value / 1000 + "千";
										} else if (value >= 10000 && value < 100000) {
											value = value / 10000 + "万"
										}
										return value;
									}
								},
							}],
							grid: [{
								bottom: '10%'
							}, {
								top: '10%'
							}],
							series: [{
								type: 'line',
								showSymbol: false,
								data: this.price,
								color: 'skyBlue' //折线的颜色
							}]
						};
						myChart.setOption(option);
					})
				})
			},
			toBuyerOrderPreviewPage() {
				this.$router.push({
					name: 'buyerOrderPreviewPage',
					params: {
						id: this.id
					}
				})
			},
			toSellerOrderPreviewPage() {
				this.$router.push({
					name: 'sellerOrderPreviewPage',
					params: {
						id: this.id
					}
				})
			}
		}
	}
</script>

<style scoped lang="scss">
	@import '../../../assets/css/style.css';
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

		span {
			display: inline-block;
			margin-left: 50/$sc+rem;
		}

		b {
			width: 50/$sc+rem;
			height: 50/$sc+rem;
			display: block;
			float: left;
			text-align: center;

			.fa-angle-left {
				color: #fff;
				font-size: 30px;
				line-height: 50/$sc+rem;
			}
		}

		.right {
			width: 100/$sc+rem;
			height: 100%;
			float: right;

			.em1 {
				float: left;
			}

			.em2 {
				float: right;
			}

			em {
				width: 50/$sc+rem;
				display: inline-block;
				text-align: center;

				.fa-share-square-o,
				.fa-heart-o {
					color: #fff;
					font-size: 20px;
					vertical-align: middle;
				}

				.fa-heart {
					color: red;
					font-size: 20px;
					vertical-align: middle;
				}
			}
		}
	}

	main {
		margin-top: 50/$sc+rem;
		height: auto;
		background: #f2f2f2;

		.swipe {
			width: 100%;
			background: #fff;
			height: 200/$sc+rem;

			.swipeOne {
				width: 100%;
				height: 100%;

				img {
					width: 200/$sc+rem;
					height: 200/$sc+rem;
					display: block;
					margin: 0 auto;
				}
			}
		}

		.mainDetail {
			width: 100%;
			background: #fff;
			margin: 0 auto;
			padding: 10/$sc+rem 0;

			.p1 {
				text-align: left;

				b {
					font-size: 14px;
					font-weight: normal;
					text-align: left;
					color: #ccc;
					margin-left: 35/$sc+rem;
				}

				span {
					color: #000;
				}
			}

			p {
				width: 90%;
				margin: 0 5%;
				text-align: center;
				font-size: 18px;
				font-weight: 500;
			}

			.relatedProducts {
				width: 100%;
				height: 200/$sc+rem;
				//margin: 10/$sc+rem 5%;
				overflow: hidden;

				ul {
					display: -webkit-box;
					overflow-x: scroll;
					-webkit-overflow-scrolling: touch;
					padding-bottom: 20/$sc+rem;

					li {
						width: 200/$sc+rem;
						height: 200/$sc+rem;
						list-style: none;
						display: -webkit-flex;
						display: flex;
						flex-direction: column;
						justify-content: center;
						align-items: center;

						img {
							width: 80/$sc+rem;
							display: block;
						}

						span {
							margin-top: 30/$sc+rem;
							vertical-align: middle;
							text-align: center;
							overflow: hidden;
							text-overflow: ellipsis;
							white-space: nowrap;
							width: 80%;
						}
					}
				}
			}
		}

		.mainType {
			width: 100%;
			background: #fff;
			margin-top: 5/$sc+rem;

			p {
				width: 90%;
				margin: 0 5%;
				padding: 5/$sc+rem 0;
				color: #999;

				.span1 {
					display: inline-block;
					width: 20%;
					font-size: 12px;
				}

				.span2 {
					display: inline-block;
					margin-left: 80/$sc+rem;
					color: #000000;
					font-size: 14px;
				}
			}
		}

		.relatedProducts {
			width: 100%;
			background: #fff;
			margin-top: 5/$sc+rem;

			p {
				width: 90%;
				margin: 0 5%;
				padding: 10/$sc+rem 0;
				font-size: 18px;
			}

			ul {
				display: -webkit-box;
				overflow-x: scroll;
				-webkit-overflow-scrolling: touch;
				padding-bottom: 20/$sc+rem;

				li {
					width: 200/$sc+rem;
					height: 200/$sc+rem;
					list-style: none;
					display: -webkit-flex;
					display: flex;
					flex-direction: column;
					justify-content: center;
					align-items: center;

					img {
						width: 80/$sc+rem;
						display: block;
					}

					span {
						margin-top: 30/$sc+rem;
						vertical-align: middle;
						text-align: center;
						overflow: hidden;
						text-overflow: ellipsis;
						white-space: nowrap;
						width: 80%;
					}
				}
			}
		}

		/*.mainHtml{
      margin-top: 5/$sc+rem;
      margin-left: 7/$sc+rem;
    }*/
		#echart {
			background: #fff;
			margin-top: 5/$sc+rem;
		}

	}

	.display {
		height: 60/$sc+rem;
		width: 100%;
	}

	.bottom {
		border-top: 1px solid #e6e6e6;
		background: #fff;
		width: 100%;
		height: 60/$sc+rem;
		position: fixed;
		bottom: 0;
		left: 0; //89bceb

		.spanLeft {
			float: left;
			color: #89bceb;
			border-right: 1px solid #e6e6e6;
		}

		.spanRight {
			float: right;
			color: #fa7a7a;
			//border-left: 1px solid #e6e6e6;
		}

		span {
			display: block;
			height: 100%;
			width: 49.8%;

			i {
				font-style: normal;
				display: block;
				float: right;
				width: 45%;
				text-align: left;
				line-height: 60/$sc+rem;
				font-size: 20px;
			}

			em {
				font-style: normal;
				display: inline-block;
				width: 55%;
				height: 100%;

				.span1 {
					height: 45%;
					display: inline-block;
					width: 100%;
					text-align: right;
					line-height: 30/$sc+rem;
					font-size: 20px;
					margin-top: 4%;
				}

				.span2 {
					height: 50%;
					font-size: 12px;
					display: inline-block;
					width: 100%;
					text-align: right;
				}
			}
		}
	}

	.share {
		position: fixed;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		background: rgba(0, 0, 0, 0.5);
		z-index: 100;

		.moreShare {
			position: absolute;
			max-height: 300/$sc+rem;
			width: 100%;
			bottom: 0;
			left: 0;
			background: #fff;
			display: flex;

			li {
				list-style: none;
				width: 25%;
			}
		}
	}
</style>
