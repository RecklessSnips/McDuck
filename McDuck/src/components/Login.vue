<template>
  <!-- 不知道如何布局了，
        首先调宽高是否占满父容器，其次
        加个背景色调试
        style="background-color: red" -->
  <Button class="btn btn-outline-success" label="Back" icon="pi pi-arrow-left" @click="back" />
  <div class="d-flex flex-column justify-content-center" style="background-color:; height: 70vh">
    <div>
      <h1 class="text-center">A penny saved is a penny earned</h1>
    </div>
    <div class="d-flex justify-content-center align-items-center" style="height: 50vh; width: 90vw">
      <!-- 这个div是为了保持d-flex的形状 -->
      <div>
        <div class="mt-5">
          <InputGroup>
            <InputGroupAddon>
              <i class="pi pi-user"></i>
            </InputGroupAddon>
            <InputText inputId="username" placeholder="Username" />
          </InputGroup>
        </div>

        <div class="mt-4">
          <InputGroup>
            <InputGroupAddon>
              <i class="pi pi-lock"></i>
            </InputGroupAddon>
            <Password v-model="value" :feedback="false" placeholder="Password" toggleMask />
          </InputGroup>
        </div>

        <div class="d-flex justify-content-center mt-4">
          <Button
            label="Login"
            icon="pi pi-user"
            severity="success"
            class="btn btn-outline-success"
          ></Button>
        </div>
      </div>

      <div class="w-full md:w-2">
        <Divider layout="vertical" class="divider hidden md:flex"><span>OR</span></Divider>
      </div>

      <div class="w-full md:w-5 flex align-items-center justify-content-center py-5">
        <Button
          label="Sign Up"
          icon="pi pi-user-plus"
          severity="success"
          class="btn btn-outline-primary"
        ></Button>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  name: 'Login'
}
</script>

<script lang="ts" setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const back = () => {
  router.replace('/home')
}
const value = ref(null)
</script>

<style scoped>
.divider {
  height: 200px;
  margin: 0 30px;
}
</style>
