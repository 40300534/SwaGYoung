import axios from './../axios/index.js'

let api = process.env.API_HOST;

const actions = {
	//登录
  login({commit}, obj){
    return new Promise((resolve, reject) => {
      axios.post(api+'user/login', obj,{
        headers: {'content-type' : "application/x-www-form-urlencoded"}
      }).then((response) => {
        resolve(response);
      }).catch((response) => {
        reject(response);
      });
    });
  },

  //注册
  register({commit}, obj){
    return new Promise((resolve, reject) => {
      axios.post(api+'user', obj).then((response) => {
        resolve(response);
      }).catch((response) => {
        reject(response);
      });
    });
  },

	//获取首页最新的产品数据
	findTopNewProducts({commit}){
		return new Promise((resolve, reject) => {
		  axios.get(api+'products/top/new').then((response) => {
		    resolve(response);
		  }).catch((response) => {
		    reject(response);
		  });
		});
	},

  //获取首页最近销售产品数据
  findTopSaleProducts({commit}){
    return new Promise((resolve, reject) => {
      axios.get(api+'products/top/sale').then((response) => {
        resolve(response);
      }).catch((response) => {
        reject(response);
      });
    });
  },

  //获取首页近30天销量最多产品数据
  findTopSaleBestProducts({commit}){
    return new Promise((resolve, reject) => {
      axios.get(api+'products/top/bestSale').then((response) => {
        resolve(response);
      }).catch((response) => {
        reject(response);
      });
    });
  },

  //获取列表页轮播图数据
  findAllSwipeImage({commit}){
    return new Promise((resolve, reject) => {
      axios.get(api+'swipeImages/findAll').then((response) => {
        resolve(response);
      }).catch((response) => {
        reject(response);
      });
    });
  },

  //获取列表页分类数据
  findAllCategory({commit}){
    return new Promise((resolve, reject) => {
      axios.get(api+'category/findAll').then((response) => {
        resolve(response);
      }).catch((response) => {
        reject(response);
      });
    });
  },

  //获取搜索数据
  searchByWordProducts({state, commit}){
    return new Promise((resolve, reject) => {
      axios.get(api+'products/searchByWord',{
        params: {
          keyword: state.value
        }
      }).then((response) => {
        resolve(response);
      }).catch((response) => {
        reject(response);
      });
    });
  },

  //获取详情页数据
  findDetailsByProductId({commit},productId){
    return new Promise((resolve, reject) => {
      axios.get(api+'products/details/' + productId).then((response) => {
        resolve(response);
      }).catch((response) => {
        reject(response);
      });
    });
  },
  //获取产品历史成交价数据
  findHistoryProducts({commit},id){
    return new Promise((resolve, reject) => {
      axios.get(api+'products/history',{
        params: {
          productId: id
        }
      }).then((response) => {
        resolve(response);
      }).catch((response) => {
        reject(response);
      });
    });
  },

  //判断用户是否收藏该产品
  isUserFavorate({commit}, productId){
    return new Promise((resolve, reject) => {
      axios.get(api+'userFavorate/isFavorate',{
        params: {
          userId: localStorage.getItem('userId'),
          productId
        }
      }).then((response) => {
        resolve(response);
      }).catch((response) => {
        reject(response);
      });
    });
  },

  //用户点击收藏or取消
  toggleUserFavorate({commit}, obj){
    return new Promise((resolve, reject) => {
      axios.post(api+'userFavorate/toggle', obj).then((response) => {
        resolve(response);
      }).catch((response) => {
        reject(response);
      });
    });
  },

  //获取分类数据
  findCategoryProducts({commit}, obj){
    return new Promise((resolve, reject) => {
      axios.get(api+'products/category/'+ obj.id,{
        params: {
          pageNum: obj.pageNum,
          pageSize: obj.pageSize
        }
      }).then((response) => {
        resolve(response);
      }).catch((response) => {
        reject(response);
      });
    });
  },

  //获取用户收藏数据
  findUserFavorateProducts({commit}, obj){
    return new Promise((resolve, reject) => {
      axios.get(api+'userFavorate/findAll',{
        params: {
          userId: obj.userId,
          pageNum: obj.pageNum,
          pageSize: obj.pageSize
        }
      }).then((response) => {
        resolve(response);
      }).catch((response) => {
        reject(response);
      });
    });
  },

  //商品购买信息
  findBuyerOrderPreview({commit}, obj){
    return new Promise((resolve, reject) => {
      axios.post(api+ 'order/buyerOrderPreview', obj,{
        headers: {'content-type' : "application/x-www-form-urlencoded"}
      }).then((response) => {
        resolve(response);
      }).catch((response) => {
        reject(response);
      });
    });
  },

  //商品出售信息
  findSellerOrderPreview({commit}, obj){
    return new Promise((resolve, reject) => {
      axios.post(api+ 'order/sellerOrderPreview', obj,{
        headers: {'content-type' : "application/x-www-form-urlencoded"}
      }).then((response) => {
        resolve(response);
      }).catch((response) => {
        reject(response);
      });
    });
  },

  //支付订单
  payProductOrder({commit}, obj){
    return new Promise((resolve, reject) => {
      axios.post(api+ 'pay/alipay?userId='+ obj.userId +'&transcationTypeId=' +obj.transcationTypeId
        + '&biddingPrice=' + obj.biddingPrice,  obj.orderPreviewBO).then((response) => {
        resolve(response);
      }).catch((response) => {
        reject(response);
      });
    });
  },
  //用户购买历史数据
  findUserBuyHistory({commit}, obj){
    return new Promise((resolve, reject) => {
      axios.get(api+'buyer/history/'+ obj.userId,{
        params: {
          pageNum: obj.pageNum,
          pageSize: obj.pageSize
        }
      }).then((response) => {
        resolve(response);
      }).catch((response) => {
        reject(response);
      });
    });
  },

  //用户购买出价数据
  findUserBuyBid({commit}, obj){
    return new Promise((resolve, reject) => {
      axios.get(api+'buyer/bid/'+ obj.userId,{
        params: {
          pageNum: obj.pageNum,
          pageSize: obj.pageSize
        }
      }).then((response) => {
        resolve(response);
      }).catch((response) => {
        reject(response);
      });
    });
  },

  //用户出售出价数据
  findUserSellBid({commit}, obj){
    return new Promise((resolve, reject) => {
      axios.get(api+'seller/sell/'+ obj.userId,{
        params: {
          pageNum: obj.pageNum,
          pageSize: obj.pageSize
        }
      }).then((response) => {
        resolve(response);
      }).catch((response) => {
        reject(response);
      });
    });
  },

  //用户出售历史数据
  findUserSellHistory({commit}, obj){
    return new Promise((resolve, reject) => {
      axios.get(api+'seller/history/'+ obj.userId,{
        params: {
          pageNum: obj.pageNum,
          pageSize: obj.pageSize
        }
      }).then((response) => {
        resolve(response);
      }).catch((response) => {
        reject(response);
      });
    });
  },


  //新建地址
  newAddress({commit}, obj){
    return new Promise((resolve, reject) => {
      axios.post(api+'address', obj).then((response) => {
        resolve(response);
      }).catch((response) => {
        reject(response);
      });
    });
  },


};

export default actions