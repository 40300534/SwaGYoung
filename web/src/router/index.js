import Vue from 'vue';
import Router from 'vue-router';
import ProductPage from '../components/productPage/ProductPage';
import HomePage from '../components/homePage/HomePage';
import Login from '../components/Login';
import Register from '../components/Register';
import productsDetailsPage from "../components/productPage/productDetailPage/productsDetailsPage";
import buyerOrderPreviewPage from "../components/productPAge/productDetailPage/buyerOrderPreviewPage";
import userPage from "../components/userPage/userPage"
import sellerOrderPreviewPage from "../components/productPage/productDetailPage/sellerOrderPreviewPage";
import newAddressPage from "../components/userPage/newAddressPage";
import userSettingPage from "../components/userPage/userSettingPage";
import userFavoratePage from "../components/userPage/userFavoratePage";
import sellRequestPage from  '../components/sellRequestPage/sellRequestPage';
import userBuyPage from '../components/userPage/userBuyPage';
import paymentPage from '../components/productPage/productDetailPage/paymentPage';
import userHomePage from "../components/userPage/userHomePage";
import userSellPage from "../components/userPage/userSellPage";
import userOrderDetailPage from "../components/userPage/userOrderDetailPage";
Vue.use(Router);

export default new Router({
	//mode: 'history',
  routes: [
    {
      path: '/',
      name: 'HomePage',
      component: HomePage
    },
    {
      path: '/product',
      name: 'ProductPage',
      component: ProductPage,
    },
		{
		  path: '/login',
			name: 'Login',
			component: Login
		},
		{
			path: '/register',
			name: 'register',
			component: Register
		},
    {
      path: '/productsDetails/:productId',
      name: 'productsDetailsPage',
      component: productsDetailsPage,
      props: true
    },
    {
	    path:'/user',
      name: 'user',
      component: userPage
    },
    {
      path: '/userHome',
      name: 'userHomePage',
      component: userHomePage
    },
    {
      path: '/buyerOrderPreview/:productId',
      name: 'buyerOrderPreviewPage',
      component: buyerOrderPreviewPage,
      props: true
    },
    {
      path: '/sellerOrderPreview/:productId',
      name: 'sellerOrderPreviewPage',
      component: sellerOrderPreviewPage,
      props: true
    },
    {
      path: '/newaddress',
      name: 'newAddressPage',
      component: newAddressPage
    },
    {
      path: '/userSetting',
      name: 'userSettingPage',
      component: userSettingPage
    },
    {
      path: '/userOrderDetail',
      name: 'userOrderDetailPage',
      component: userOrderDetailPage
    },
    {
      path: '/userFavorate',
      name: 'userFavoratePage',
      component: userFavoratePage
    },
    {
      path: '/sellRequest',
      name: 'sellRequestPage',
      component: sellRequestPage
    },
    {
      path: '/userBuy',
      name: 'userBuyPage',
      component: userBuyPage
    },
    {
      path: '/userSell',
      name: 'userSellPage',
      component: userSellPage
    },
    {
      path: '/payment',
      name: 'paymentPage',
      component: paymentPage
    }
  ]
})
