<!-- 中间整个大区域，含有商城主页，购物车，店铺页面 -->

<!-- TODO: 用这个Figure：https://getbootstrap.com/docs/5.3/content/typography/ -->
<template>
  <div class="mx-5 mt-5">
    <Header v-if="showHeader"></Header>
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
