import axios from 'axios'

const http = axios.create({
  // baseURL: '/api',
 //timeout: 6000,
 headers: {
    'Content-Type':' application/json',
    'X-Requested-With': 'XMLHttpRequest'
 }
});
http.interceptors.request.use((config) => {
  // 在发送请求之前做些什么
  var token=localStorage.getItem('token');
  if(token===undefined||token===null){
    config.headers.Authorization='';
  }else{
    config.headers.Authorization=token;
  }
  return config
}, function (error) {
  // 对请求错误做些什么
  return Promise.reject(error)
});
export default http
