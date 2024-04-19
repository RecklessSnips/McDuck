<!-- 这个作为默认的展示商品区 -->
<template>
  <div>
    <!-- 用toolbar来装这些小功能，可以选择
    Dropdown，splitbutton -->
    <Toolbar>
      <template #start>
        <p style="margin: 0">999 Results</p>
      </template>

      <template #end>
        <InputSwitch
          v-model="checked"
          :pt="{
            root: {
              style: 'width: 40px; height:20px'
            }
          }"
        />
        <span class="switchtext">In Stock</span>
        <Dropdown
          v-model="sorting"
          :options="sortOption"
          optionLabel="name"
          placeholder="Sort by"
          :pt="{
            list: {
              style: 'padding-left: 1rem'
            }
          }"
          class="dropdown w-full md:w-14rem"
        />
      </template>
    </Toolbar>
    <div v-if="isReady" class="card">
      <DataView :value="productList" :layout="layout">
        <template #header>
          <div class="d-flex justify-content-end">
            <DataViewLayoutOptions v-model="layout" />
          </div>
        </template>

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
                    <Tag
                      :value="product.stock_quantity"
                      :severity="getSeverity(product)"
                      class="position-absolute"
                      style="left: 4px; top: 4px"
                    ></Tag>
                  </div>
                  <div
                    class="d-flex flex-column flex-md-row justify-content-between align-items-center flex-grow-1 gap-4"
                  >
                    <div
                      class="d-flex flex-row flex-md-column justify-content-between align-items-start gap-2"
                    >
                      <div class="product-list" @click="checkProduct(product)">
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
                        <Button icon="pi pi-heart" outlined class="flex-grow-1"></Button>
                        <Button
                          icon="pi pi-shopping-cart"
                          label="Buy Now"
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
                    <div class="product-actions d-flex gap-2">
                      <Button
                        icon="pi pi-shopping-cart"
                        label="Buy Now"
                        :disabled="product.stock_quantity < 0"
                        class="flex-grow-1 white-space-nowrap"
                      ></Button>
                      <Button icon="pi pi-heart" outlined class="flex-grow-1"></Button>
                    </div>
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
  name: 'Products'
}
</script>

<script lang="ts" setup>
import { ref, reactive, onBeforeMount, onUnmounted } from 'vue'
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

let checked = ref(false)
const sorting = ref()
const sortOption = ref([
  { name: 'Price Low-High', code: 'NY' },
  { name: 'Price High-Low', code: 'RM' },
  { name: 'Rating Low-High', code: 'LDN' },
  { name: 'Rating High-Low', code: 'IST' },
  { name: 'Best Sellers', code: 'PRS' }
])

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

emitter.on('getProductsByCategory', (data: any) => {
  console.log(data)
  console.log('data', data)
  console.log('Products recieved!!!', data.data)
  console.log('Keywords: ', data.keywords)
  keyword.value = data.keywords
  console.log('keyword:', keyword.value)
  // 清空当前搜索的结果，
  productList.splice(0, productList.length)
  Object.assign(productList, data.data)
  isReady.value = true
  stopGetRandomProducts.value = true
  console.log(isReady.value)
})

// 在组件卸载时解绑事件
onUnmounted(() => {
  emitter.off('getProductsByCategory')
})

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
