<!-- 中间整个大区域，含有商城主页，购物车，店铺页面 -->

<!-- TODO: 用这个Figure：https://getbootstrap.com/docs/5.3/content/typography/ -->
<template>
  <div>
    <RouterView></RouterView>
  </div>
</template>

<script lang="ts">
export default {
  name: 'Main'
}
</script>

<script lang="ts" setup>
import { RouterView } from 'vue-router'
</script>
<style scoped></style>
