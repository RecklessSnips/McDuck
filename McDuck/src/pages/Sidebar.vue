<!-- 这个侧边栏需要根据当前所选择的种类来判断。
    选择的special deal都是关于这个种类的产品的，
    需要跟 Home Header 所提供的当前所浏览的商品种类来推荐-->
<template>
  <div class="card">
    <Accordion :multiple="true" :activeIndex="[0, 1, 2, 3, 4]">
      <!-- 因为根据不同过滤条件需要展示不同的数据, 可以拿router来展示不同的商品结果 -->
      <AccordionTab
        :pt="{
          headerAction: {
            style: 'text-decoration: none; color: black'
          }
        }"
        v-for="link in links"
        :key="link.title"
        :header="link.title"
      >
        <RouterLink class="accordion" :to="link.path">{{ link.content }}</RouterLink>
      </AccordionTab>
    </Accordion>
  </div>
</template>

<script lang="ts">
export default {
  name: 'Sidebar'
}
</script>

<script lang="ts" setup>
import { RouterLink } from 'vue-router'
import { reactive } from 'vue'

const links = reactive([
  { title: 'Special Deal', content: 'Top deals', path: '/home' },
  { title: 'Top Stores', content: 'Top Store', path: '/home' },
  { title: 'High rated', content: 'High rates', path: '/home' },
  { title: 'Product Condition', content: 'Condition', path: '' },
  { title: 'Product status', content: 'Status', path: '/home' }
])
</script>

<style scoped>
.accordion {
  text-decoration: none;
}
</style>
