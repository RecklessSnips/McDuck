<!-- 这个是从 products.vue 页面点进来，单独展示一个商品的组件 -->
<template>
  <div class="d-flex row mx-5 mt-5">
    <!-- 图片 -->
    <Breadcrumb :model="items"></Breadcrumb>
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
  </div>
</template>

<script lang="ts">
export default {
  name: 'ProductPage'
}
</script>

<script lang="ts" setup>
import Breadcrumb from 'primevue/breadcrumb'
import { ref, reactive, watchEffect } from 'vue'
import { useRoute, useRouter } from 'vue-router'

const route = useRoute()
const router = useRouter()

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
  command?: () => void // Optional, include only if some items will not have commands
}

const items = ref<BreadcrumbItem[]>([])

watchEffect(() => {
  const searchParam = route.query.searchKeywords

  items.value = [
    {
      label: 'Home',
      command: () => {
        router.push('/')
      }
    },
    {
      label: 'Search',
      command: () => {
        router.push({ path: '/home', query: { search: searchParam } })
      }
    }
  ]

  // if (route.path.includes('productpage')) {
  //   items.value.push({
  //     label: 'Product Details',
  //     command: () => {
  //       router.push({
  //         path: '/productpage',
  //         query: { currentProduct: route.query.currentProduct, search: searchParam }
  //       })
  //     }
  //   })
  // }
})
</script>

<style scoped>
.product-image {
  display: block;
  margin: auto auto;
}
</style>
