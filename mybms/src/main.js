import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './assets/global.css'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import Vue from 'vue'
import VueParticles from 'vue-particles'
import Vuex from 'vuex'
createApp(App).use(store).use(router).use(ElementPlus,{
    locale: zhCn,
}).use(VueParticles).use(Vuex).mount('#app')
