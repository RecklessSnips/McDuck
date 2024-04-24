<template>
  <!-- 作为商城的主页，包括左边侧边栏: Sidebar.vue
    中间商品展示区：Products.vue
    以及右边的内容: 暂未写component -->
  <div class="mx-5 mt-3">
    <!-- TODO: 加一个coursal让它变得更好看 -->
    <div class="row mx-1 mt-3 d-flex justify-content-evenly">
      <!-- TODO: Header 来显示当前商品的种类等等信息 -->
      <h1 class="ms-3">Alienware</h1>
      <Divider />
      <!-- <div class="col-sm-2 bg-primary"><Sidebar></Sidebar></div> -->
      <!-- <div class="col-sm-10 bg-warning"><Products></Products></div> -->
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
import DefaultProduct from '@/pages/Product/DefaultProduct.vue'
</script>
<style scoped></style>
