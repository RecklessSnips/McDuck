<!-- 购物车页面 -->
<!-- 这个作为默认的展示商品区 -->
<template>
  <div class="d-flex row mx-5 mt-5 mb-5">
    <div v-if="!isReady" class="d-flex justify-content-center"><h1>Your cart is empty!</h1></div>
    <div v-if="isReady" class="card">
      <h1>Your Shopping Cart</h1>
      <DataView :value="productList" :layout="layout">
        <template #list="slotProps">
          <div class="container-fluid px-0">
            <div v-for="product in productList" :key="product.product_id" class="row no-gutters">
              <div class="col-12">
                <div class="d-flex flex-column flex-sm-row align-items-center p-4 gap-3">
                  <div class="img-container position-relative" style="width: 7.5rem; height: 8rem">
                    <img
                      class="d-block rounded-circle"
                      :src="product.image_path"
                      :alt="product.product_name"
                      style="height: 100%; width: 100%"
                    />
                  </div>
                  <div
                    class="d-flex flex-column flex-md-row justify-content-between align-items-center flex-grow-1 gap-4"
                  >
                    <div
                      class="d-flex flex-row flex-md-column justify-content-between align-items-start gap-2"
                    >
                      <div class="product-list">
                        <span class="text-muted text-sm">{{ product.author }}</span>
                        <div class="h5 mt-2">{{ product.product_name }}</div>
                      </div>
                      <div class="bg-light p-1 rounded-pill">
                        <div
                          class="bg-white d-flex align-items-center gap-2 justify-content-center py-1 px-2 rounded-pill"
                          style="
                            box-shadow:
                              0px 1px 2px rgba(0, 0, 0, 0.04),
                              0px 1px 2px rgba(0, 0, 0, 0.06);
                          "
                        >
                          <span class="text-dark font-weight-medium text-sm">{{
                            product.review_star
                          }}</span>
                          <i class="pi pi-star-fill text-warning"></i>
                        </div>
                      </div>
                    </div>
                    <div class="d-flex flex-column align-items-end gap-5">
                      <span class="h4 font-weight-bold text-dark">${{ product.price }}</span>
                      <div class="d-flex flex-row-reverse flex-md-row gap-2">
                        <Toast />
                        <Button icon="pi pi-heart" outlined class="flex-grow-1"></Button>
                        <Button
                          icon="pi pi-shopping-cart"
                          label="Remove"
                          @click="removeCart(currentUser, product)"
                          :disabled="product.stock_quantity < 0"
                          class="flex-grow-1 white-space-nowrap"
                        ></Button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </template>
      </DataView>
      <Divider></Divider>
      <div class="d-flex justify-content-end">
        <h2>Total price: {{ totalPrice }}</h2>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  name: 'Cart'
}
</script>

<script lang="ts" setup>
import { ref, reactive, watch, computed, onMounted, onUnmounted } from 'vue'
import emitter from '@/util/emitter'
import { useToast } from 'primevue/usetoast'
import router from '@/router'

// Hook
import useCurrentUser from '@/hooks/useCurrentUser'

const { visible, isLogin, username, currentUser, greeting } = useCurrentUser()

const URL = 'http://localhost:8080'
let productList = reactive<Product[]>([])
const layout = ref('list')
let isReady = ref(false)
const toast = useToast()
let stopGetRandomProducts = ref(false)

// 计算总价格
const totalPrice = computed(() => {
  return parseFloat(productList.reduce((sum, product) => sum + product.price, 0).toFixed(2))
})

onMounted(async () => {
  watch(
    [currentUser, isLogin],
    ([newUser, newLogin], [oldUser, oldLogin]) => {
      if (newLogin && newUser && Object.keys(newUser).length > 0) {
        fetchProducts()
      }
    },
    {
      immediate: true
    }
  )
})

// Product
export interface Product {
  product_id: string
  category: string
  product_name: string
  author: string
  description: string
  price: number
  stock_quantity: number
  review_star: number
  review_message: string
  image_path: string
  listing_date: string
}

// 因为这个hook是async的，直接在onMounted里调用会导致数据丢失，需要写成async形式
const fetchProducts = async () => {
  if (!currentUser || Object.keys(currentUser).length === 0) {
    console.error('User data is not available or not loaded.')
    return
  }
  console.log('Fetching products for:', JSON.stringify(currentUser))
  try {
    const response = await fetch(`${URL}/api/getCart`, {
      method: 'POST',
      credentials: 'include',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(currentUser)
    })

    if (!response.ok) {
      isReady.value = false
      return
    }

    const products = await response.json()
    console.log('Shopping Cart:', products)
    Object.assign(productList, products)
    isReady.value = true
  } catch (error) {
    console.error('Error fetching products:', error)
  }
}

const removeCart = (user: any, producte: Product) => {
  const cart = {
    user: user,
    product: producte
  }
  fetch(`${URL}/api/deleteCart`, {
    method: 'POST',
    credentials: 'include',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(cart)
  })
    .then((response) => {
      return response.json()
    })
    .then((ifRemoved) => {
      console.log('If removed: ', ifRemoved)
      toast.add({
        severity: 'success',
        summary: 'Item removed',
        detail: '1 item removed from the cart',
        life: 1000
      })
      setTimeout(() => {
        window.location.reload()
      }, 1000)
    })
}

/*
 每次返回主页，会加载这个component，所以这个emitter会被调用
 所以这里需要判断：
 1.是否是从 Header 进行的搜索，从而传来的
 2.还是从 productPage 点击了（返回搜索记录）按钮，返回的
 两者返回的数据不一样
 */
// emitter.on('getProductsByCategory', (data: any) => {})

// 在组件卸载时解绑事件
onUnmounted(() => {
  emitter.off('currentUser')
})
</script>

<style scoped>
.dropdown {
  width: 200px;
  padding: 0;
}
.switchtext {
  margin-left: 8px;
  margin-right: 20px;
}

.product.card {
  /* 高度充满，让内部元素不会溢出 */
  height: 100%;
  widows: 150%;
  display: flex;
  flex-direction: column;
  justify-content: space-between; /* Distributes space evenly */
}
.product-content {
  flex-grow: 1; /* Allows this div to expand to fill available space */
  display: flex;
  align-items: center; /* Center the content vertically */
  justify-content: center; /* Center the content horizontally */
}
.img-container {
  width: 150%; /* Full width of the parent */
  height: 260px; /* Fixed height for images */
  overflow: hidden; /* Hides parts of the image that exceed the container size */
  display: flex;
  align-items: center; /* Helps center the image vertically */
}
.img-container img {
  width: 100%; /* Make the image full width of the container */
  height: auto; /* Maintain aspect ratio */
  min-height: 100%; /* Ensures the container height is filled */
}
.product-actions {
  padding: 0.5rem; /* Padding around buttons */
}

.product-list {
  cursor: pointer;
}
</style>
