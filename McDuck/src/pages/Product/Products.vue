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
    <RouterView></RouterView>
  </div>
</template>

<script lang="ts">
export default {
  name: 'Products'
}
</script>

<script lang="ts" setup>
import type { RouterView } from 'vue-router'
import { ref } from 'vue'

let checked = ref(false)
const sorting = ref()
const sortOption = ref([
  { name: 'Price Low-High', code: 'NY' },
  { name: 'Price High-Low', code: 'RM' },
  { name: 'Rating Low-High', code: 'LDN' },
  { name: 'Rating High-Low', code: 'IST' },
  { name: 'Best Sellers', code: 'PRS' }
])
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
</style>
