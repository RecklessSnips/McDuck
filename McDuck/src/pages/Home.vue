<template>
  <!-- 作为商城的主页，包括左边侧边栏: Sidebar.vue
    中间商品展示区：Products.vue
    以及右边的内容: 暂未写component -->
  <div class="mx-5 mt-3">
    <!-- TODO: 加一个coursal让它变得更好看 -->
    <div class="row mx-1 mt-3 d-flex justify-content-evenly">
      <!-- <div class="col-sm-2 bg-primary"><Sidebar></Sidebar></div> -->
      <!-- <div class="col-sm-10 bg-warning"><Products></Products></div> -->
      <!-- <div v-if="showDeafult" class="col-sm-10 bg-warning"><DefaultProduct></DefaultProduct></div> -->
      <!-- <div v-if="!showDeafult" class="col-sm-10 bg-warning"><Product></Product></div> -->
      <div class="col-sm-10 bg-warning"><DefaultProduct></DefaultProduct></div>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  name: 'Home'
}
</script>

<script lang="ts" setup>
import Sidebar from '@/pages/Sidebar.vue'
import Products from '@/pages/Product/Products.vue'
import Product from '@/pages/Product/Products.vue'
import DefaultProduct from '@/pages/Product/DefaultProduct.vue'
import { computed } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const showDeafult = computed(() => route.meta.isDefault)
</script>
<style scoped></style>
