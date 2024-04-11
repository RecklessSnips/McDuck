import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/pages/Home.vue'
import Store from '@/pages/Store.vue'
import Deal from '@/pages/Product/Deal.vue'
import TopStore from '@/pages/Product/TopStore.vue'
import Rate from '@/pages/Product/Rate.vue'
import Condition from '@/pages/Product/Condition.vue'
import Status from '@/pages/Product/Status.vue'
import Login from '@/components/Login.vue'
import Register from '@/components/Register.vue'

const router = createRouter({
  // 路由工作模式
  history: createWebHistory(),
  // 路由规则
  routes: [
    {
      path: '/home',
      component: Home,
      meta: { requiresHeader: true },
      children: [
        {
          path: 'products/deal',
          component: Deal
        },
        {
          path: 'products/topstore',
          component: TopStore
        },
        {
          path: 'products/rate',
          component: Rate
        },
        {
          path: 'products/condition',
          component: Condition
        },
        {
          path: 'products/status',
          component: Status
        }
      ]
    },
    {
      name: 'login',
      path: '/login',
      component: Login,
      meta: { requiresHeader: false }
    },
    {
      name: 'register',
      path: '/register',
      component: Register,
      meta: { requiresHeader: false }
    },
    {
      path: '/store',
      component: Store,
      meta: { requiresHeader: true }
    },
    {
      path: '/',
      redirect: '/home'
    }
  ]
})

// 暴露
export default router
