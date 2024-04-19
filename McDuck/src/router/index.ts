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
import Default from '@/pages/Product/Default.vue'
import ProductPage from '@/pages/Product/ProductPage.vue'
import Product from '@/pages/Product/Products.vue'

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
          path: 'products',
          component: Product,
          meta: { requiresHeader: true }
        }
        // {
        //   path: 'products/deal',
        //   component: Deal,
        //   meta: { isDefault: false }
        // },
        // {
        //   path: 'products/topstore',
        //   component: TopStore,
        //   meta: { isDefault: false }
        // },
        // {
        //   path: 'products/rate',
        //   component: Rate,
        //   meta: { isDefault: false }
        // },
        // {
        //   path: 'products/condition',
        //   component: Condition,
        //   meta: { isDefault: false }
        // },
        // {
        //   path: 'products/status',
        //   component: Status,
        //   meta: { isDefault: false }
        // },
        // {
        //   // 默认子路由，当父路径被直接访问，会自动加载和显示一个默认视图
        //   path: '',
        //   component: Default,
        //   meta: { isDefault: true }
        // }
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
      path: '/productpage',
      meta: { requiresHeader: true },
      component: ProductPage
    },
    {
      path: '/',
      redirect: '/home'
    }
  ]
})

// 暴露
export default router
