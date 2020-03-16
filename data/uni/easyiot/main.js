import Vue from 'vue'
import App from './App'

import basics from 'pages/index/basics.vue'
Vue.component('basics',basics)

import personal from 'pages/index/personal.vue'
Vue.component('personal',personal)

// import plugin from './pages/plugin/home.vue'
// Vue.component('plugin',plugin)

import cuCustom from './colorui/components/cu-custom.vue'
Vue.component('cu-custom',cuCustom)


Vue.config.productionTip = false

App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()

 



