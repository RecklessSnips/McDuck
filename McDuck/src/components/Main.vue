<!-- 商城整个页面布局，上面是header，下面商城主页，购物车，店铺页面 -->

<!-- TODO: 用这个Figure：https://getbootstrap.com/docs/5.3/content/typography/ -->
<template>
  <div>
    <!-- 这个div会被渲染出来，所以要把if加到div上 -->
    <div v-if="showHeader" class="mx-5 mt-5">
      <Header></Header>
    </div>
    <!-- 这是同级的view，任何的统计view被routerlink导航之后都会传到这里 -->
    <router-view></router-view>
  </div>
</template>

<script lang="ts">
export default {
  name: 'Main'
}
</script>

<script lang="ts" setup>
import { computed } from 'vue'
import { RouterView } from 'vue-router'
import Header from '@/components/Header.vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const showHeader = computed(() => route.meta.requiresHeader)
</script>
<style scoped></style>
