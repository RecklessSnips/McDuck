<!-- 这个作为默认的展示商品区 -->
<template>
  <div>
    <div v-if="isReady" class="card">
      <DataView :value="productList" :layout="layout">
        <template #grid="slotProps">
          <div class="row g-0">
            <div
              v-for="product in productList"
              :key="product.product_id"
              class="col-12 col-sm-6 col-md-4 col-xl-3 p-2"
            >
              <div class="product card border-1 border border-secondary p-2 d-flex flex-column">
                <!-- 商品图片和 Tag -->
                <div class="product-content d-flex justify-content-center align-items-center p-2">
                  <div class="img-container">
                    <img
                      class="rounded-circle"
                      :src="product.image_path"
                      :alt="product.product_name"
                      style="height: 100%; width: 100%"
                    />
                    <Tag
                      :value="product.stock_quantity"
                      :severity="getSeverity(product)"
                      class="position-absolute"
                      style="left: 4px; top: 4px"
                    ></Tag>
                  </div>
                </div>
                <!-- 商品描述 -->
                <div class="pt-2 pb-1">
                  <!-- 商品类型，名称，评价 -->
                  <div class="d-flex justify-content-between align-items-start gap-2">
                    <div class="product-list" @click="checkProduct(product)">
                      <span class="font-weight-medium text-secondary text-sm">{{
                        product.author
                      }}</span>
                      <div class="h5 mt-1">{{ product.product_name }}</div>
                    </div>
                    <div class="bg-light p-1 rounded-pill">
                      <div
                        class="bg-white d-flex align-items-center gap-2 justify-content-center py-1 px-2 rounded-pill"
                        style="
                          box-shadow:
                            0px 1px 2px 0px rgba(0, 0, 0, 0.04),
                            0px 1px 2px 0px rgba(0, 0, 0, 0.06);
                        "
                      >
                        <span class="text-dark font-weight-medium text-sm">{{
                          product.review_star
                        }}</span>
                        <i class="pi pi-star-fill text-warning"></i>
                      </div>
                    </div>
                  </div>
                  <!-- 商品价格，购买按钮，收藏按钮 -->
                  <div class="d-flex flex-column gap-4 mt-4">
                    <span class="h4 font-weight-bold text-dark">${{ product.price }}</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </template>
      </DataView>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  name: 'DefaultProduct'
}
</script>

<script lang="ts" setup>
import { ref, reactive, watch, onBeforeMount, onUnmounted } from 'vue'
import emitter from '@/util/emitter'
import router from '@/router'

// Store (Pinia)
import { useCurrentUserStore } from '@/stores/currentUser'
import { storeToRefs } from 'pinia'

const currentUserStore = useCurrentUserStore()
let { skipRandomProducts } = storeToRefs(currentUserStore)

const URL = 'http://localhost:8080'
let productList = reactive<Product[]>([])
let keyword = ref('')
const layout = ref('grid')
let isReady = ref(false)
let stopGetRandomProducts = ref(false)

onBeforeMount(() => {
  if (!skipRandomProducts.value) {
    isReady.value = true
    console.log(isReady.value)
    getRandomProducts()
  }
})

const getSeverity = (product: Product) => {
  const quantity = product.stock_quantity
  if (quantity > 500) {
    return 'primary'
  } else if (quantity > 200) {
    return 'success'
  } else if (quantity > 90) {
    return 'info'
  } else if (quantity > 50) {
    return 'warning'
  } else if (quantity > 10) {
    return 'danger'
  }
}

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

// 点击后，跳到当前商品的组件
const checkProduct = (product: Product) => {
  router.push({
    path: '/productpage',
    query: {
      currentProduct: JSON.stringify(product),
      searchKeywords: keyword.value
    }
  })
}

const getRandomProducts = () => {
  fetch(`${URL}/api/getRandomProduct`, {
    method: 'GET',
    credentials: 'include',
    headers: {
      'Content-Type': 'application/json'
    }
  })
    .then((response) => {
      return response.json()
    })
    .then((products) => {
      Object.assign(productList, products)
      console.log(productList)
    })
}

/*
 每次返回主页，会加载这个component，所以这个emitter会被调用
 所以这里需要判断：
 1.是否是从 Header 进行的搜索，从而传来的
 2.还是从 productPage 点击了（返回搜索记录）按钮，返回的
 两者返回的数据不一样
 */
emitter.on('getProductsByCategory', (data: any) => {
  console.log(data)
  console.log('data', data.data)
  console.log(typeof data.data === 'undefined')
  // 判断是否是从某个产品返回来的，为了保证当前搜索记录
  if (typeof data.data === 'undefined') {
    if (typeof data.keywords === 'undefined') {
      console.log('No keywords! Perform randomSearch()')
      isReady.value = true
      productList.splice(0, productList.length)
      getRandomProducts()
    } else if (data.keywords != '') {
      console.log('kkkkkk: ', data.keywords)
      setCategory(data.keywords)
      data.keywords = ''
    }
  } else {
    console.log('Products recieved!!!', data.data)
    console.log('Keywords: ', data.keywords)
    keyword.value = data.keywords
    console.log('keyword:', keyword.value)
    // 清空当前搜索的结果，
    productList.splice(0, productList.length)
    // 赋值给productList，更新列表
    Object.assign(productList, data.data)
    // 调成准备好展示
    isReady.value = true
    // 不要调用random方法
    stopGetRandomProducts.value = true
    data.keywords = ''
    console.log(isReady.value)
  }
})

const setCategory = (result: string) => {
  console.log('result is: ', result)
  fetch(`${URL}/api/searchProductByCategory`, {
    method: 'POST',
    credentials: 'include',
    headers: {
      'Content-Type': 'text/plain'
    },
    body: result
  })
    .then((response) => {
      return response.json()
    })
    .then((data) => {
      console.log('Searched catagory: ', data)
      // 阻止再次调用getRandomProduct函数
      skipRandomProducts.value = true
      productList.splice(0, productList.length)
      Object.assign(productList, data)
      isReady.value = true
      stopGetRandomProducts.value = true
      console.log(productList)
    })
}

// 在组件卸载时解绑事件
onUnmounted(() => {
  emitter.off('getProductsByCategory')
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
