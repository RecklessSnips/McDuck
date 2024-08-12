import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/pages/Home.vue'
import Store from '@/pages/Store.vue'
import Login from '@/components/Login.vue'
import Register from '@/components/Register.vue'
import ProductPage from '@/pages/Product/ProductPage.vue'
import Product from '@/pages/Product/Products.vue'
import OrderHistory from '@/pages/OrderHistory.vue'
import DefaultProduct from '@/pages/Product/DefaultProduct.vue'
import Cart from '@/pages/Cart.vue'

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
          // TODO: 修改回product
          component: DefaultProduct,
          meta: { requiresHeader: true, isDefault: false }
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
      meta: { requiresHeader: false }
    },
    {
      path: '/cart',
      component: Cart,
      meta: { requiresHeader: true }
    },
    {
      path: '/orders',
      component: OrderHistory,
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
