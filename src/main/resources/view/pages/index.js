import Vue from 'vue'
import Router from 'vue-router'
import pageApp from '@/components/homepage'

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'index',
            component: index
        }
    ]
})
//Vue Router测试