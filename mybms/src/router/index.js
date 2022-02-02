import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login'
import request from "../utils/request";
const routes = [
  {
    path: '/',
    meta:{},
    name: 'Home',
    component: Home
  },
  {
    path: '/Login',
    name: 'Login',
    component: Login
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})
//导航守卫 无token或token不合法跳转回登录页
router.beforeEach((to,from,next)=>{
  //拿到token
  const token = localStorage.getItem('token');
  request.get("/mybms/user/",{
    headers:{
      token: token
    }
  }).then(res=>{
    console.log(res.code)
    if(to.name!='Login' && res.code!==100 ){
      next({name:"Login"});
    }
    else{
      next();
    }
  })
  //if(to.name!='Login' && !isTokenEffective ) next({name: 'Login'});
  //else next()

})
export default router
