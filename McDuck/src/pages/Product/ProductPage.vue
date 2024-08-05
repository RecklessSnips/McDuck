<!-- 这个是从 products.vue 页面点进来，单独展示一个商品的组件 -->
<template>
  <div class="d-flex row mx-5 mt-5">
    <!-- 图片 -->
    <Breadcrumb :model="items"> </Breadcrumb>
    <div class="col-sm-6">
      <img
        class="product-image"
        :src="product.image_path"
        :alt="product.product_name"
        style="height: 24rem; width: 20rem"
      />
    </div>
    <!-- 商品信息 -->
    <div class="col-sm-6">
      <Tag :value="getTagInfo(product)" :severity="getSeverity(product)"></Tag>
      <h1>{{ product.product_name }}</h1>
      <p>{{ product.author }}</p>
      <Rating v-model="rating" readonly :cancel="false" />
      <Divider></Divider>
      <p>{{ product.description }}</p>
    </div>

    <div class="d-flex justify-content-center">
      <Toast />
      <button v-if="isLogin" @click="addCart(currentUser, product)" class="btn btn-outline-success">
        Add to Cart
      </button>
      <h2 v-if="!isLogin">Please login to buy this product!</h2>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  name: 'ProductPage'
}
</script>

<script lang="ts" setup>
import Breadcrumb from 'primevue/breadcrumb'
import api from '@/api/test.js'
import { ref, reactive, watchEffect, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import emitter from '@/util/emitter'
import { useToast } from 'primevue/usetoast'
// Store (Pinia)
import { useCurrentUserStore } from '@/stores/currentUser'
import { storeToRefs } from 'pinia'

// Hook
import useCurrentUser from '@/hooks/useCurrentUser'

const route = useRoute()
const router = useRouter()

const currentUserStore = useCurrentUserStore()
const { visible, isLogin, username, currentUser, greeting } = useCurrentUser()

const toast = useToast()

let { skipRandomProducts } = storeToRefs(currentUserStore)
let stopGetRandomProducts = ref(false)

const URL = 'http://localhost:8080'

onMounted(() => {
  getBreadCrumb(product)
})

// 接受传过来的商品
interface Product {
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
}

const product = reactive<Product>({
  product_id: '',
  category: '',
  product_name: '',
  author: '',
  description: '',
  price: 0,
  stock_quantity: 0,
  review_star: 0,
  review_message: '',
  image_path: ''
})

if (typeof route.query.currentProduct === 'string') {
  Object.assign(product, JSON.parse(route.query.currentProduct))
} else {
  console.error('currentProduct is not available or not a valid string')
}

// 接受商品信息
const tagInfo = ref('')
const getSeverity = (product: Product) => {
  if (product.review_star >= 4) {
    tagInfo.value = 'Highly Rated'
    return 'primary'
  } else if (product.review_star === 3) {
    tagInfo.value = 'Good condition'
    return 'info'
  } else if (product.review_star === 2) {
    tagInfo.value = 'Fair'
    return 'warning'
  } else if (product.review_star === 1) {
    tagInfo.value = 'Mostly bad'
    return 'danger'
  } else if (product.review_star === 0) {
    tagInfo.value = 'No info'
    return 'secondary'
  }
}

const getTagInfo = (product: Product) => {
  const severity = getSeverity(product)
  // Optionally do something with severity if needed
  return tagInfo.value
}

const rating = ref(0)
rating.value = product.review_star

// 返回上一层搜索记录
interface BreadcrumbItem {
  label: string
  command?: () => void
}

const items = ref<BreadcrumbItem[]>([])
// let items = ref<string[]>([])

const getBreadCrumb = (product: Product) => {
  const category_string = product.category.toLowerCase()
  let category = ''

  const addItem = (label: string) => {
    items.value.push({
      label,
      command: async () => {
        const products = await api.getCategoryProducts(label)
        console.log(products)
        router.push('/home/products').then(() => {
          stopGetRandomProducts.value = true
          emitter.emit('getProductsByCategory', { data: products, keyword: '' })
        })
      }
    })
  }

  if (category_string.includes('romance')) {
    category = 'romance'
    addItem('Books')
    addItem(category)
    addItem(product.product_name)
  } else if (category_string.includes('science')) {
    category = 'science'
    addItem('Books')
    addItem(category)
    addItem(product.product_name)
  } else if (category_string.includes('starwars')) {
    category = 'starwars'
    addItem('Books')
    addItem(category)
    addItem(product.product_name)
  } else if (category_string.includes('thriller')) {
    category = 'thriller'
    addItem('Books')
    addItem(category)
    addItem(product.product_name)
  } else if (category_string.includes('computer')) {
    category = 'computer'
    addItem('Electronic')
    addItem(category)
    addItem(product.product_name)
  } else if (category_string.includes('headset')) {
    category = 'headset'
    addItem('Electronic')
    addItem(category)
    addItem(product.product_name)
  } else if (category_string.includes('speaker')) {
    category = 'speaker'
    addItem('Electronic')
    addItem(category)
    addItem(product.product_name)
  } else if (category_string.includes('tv')) {
    category = 'tv'
    addItem('Electronic')
    addItem(category)
    addItem(product.product_name)
  } else if (category_string.includes('menclothes')) {
    category = 'menclothes'
    addItem('Clothes')
    addItem(category)
    addItem(product.product_name)
  } else if (category_string.includes('womenclothes')) {
    category = 'womenclothes'
    addItem('Clothes')
    addItem(category)
    addItem(product.product_name)
  } else if (category_string.includes('refrigerator')) {
    category = 'refrigerator'
    addItem('Kitchen')
    addItem(category)
    addItem(product.product_name)
  } else if (category_string.includes('coffeemaker')) {
    category = 'coffeemaker'
    addItem('Kitchen')
    addItem(category)
    addItem(product.product_name)
  } else if (category_string.includes('airfryer')) {
    category = 'airfryer'
    addItem('Kitchen')
    addItem(category)
    addItem(product.product_name)
  } else if (category_string.includes('outdoor')) {
    category = 'outdoor'
    addItem('Sports')
    addItem(category)
    addItem(product.product_name)
  }
}

watchEffect(() => {
  const searchParam = ref(route.query.searchKeywords)

  items.value = [
    {
      label: 'Home',
      command: () => {
        router.push('/')
      }
    }
  ]
})

const addCart = (currentUser: any, product: Product) => {
  let cart = {
    user: currentUser,
    product: product
  }
  console.log(currentUser)
  console.log(product)
  console.log(cart)
  fetch(`${URL}/api/addCart`, {
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
    .then((data) => {
      console.log('If insereted into the Cart:', data)
      toast.add({
        severity: 'success',
        summary: 'Product Added!',
        detail: '1 Product add into cart ',
        life: 2000
      })
    })
}
</script>

<style scoped>
.product-image {
  display: block;
  margin: auto auto;
}
.tabView {
  text-decoration: none;
}
</style>
