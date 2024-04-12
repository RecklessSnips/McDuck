<!-- 个人或者别的商家的店铺页面 -->
<template>
  <div class="mx-5 mt-3">
    <div class="bg-warning">"You made it!"</div>

    <!-- 中间商品内容 -->
    <div class="card">
      <Toast />
      <FileUpload
        name="demo[]"
        :url="uploadtURL"
        @upload="onAdvancedUpload($event)"
        :multiple="true"
        accept="image/*"
        :maxFileSize="10000000"
      >
        <template #empty>
          <p>Drag and drop files to here to upload.</p>
        </template>
      </FileUpload>
      <p v-if="uploadStatus">{{ uploadStatus }}</p>
    </div>
    <Button label="Show" class="btn btn-outline-primary" @click="show()" />
  </div>
</template>

<script lang="ts">
export default {
  name: 'Store'
}
</script>

<script lang="ts" setup>
import { ref } from 'vue'
import { useToast } from 'primevue/usetoast'
import FileUpload from 'primevue/fileupload'

const toast = useToast()
const uploadStatus = ref('')

const URL = 'http://localhost:8080'
const uploadtURL = 'http://localhost:8080/api/upload'

const onAdvancedUpload = (event: any) => {
  if (event.files && event.files.length > 0) {
    const formData = new FormData()
    // 如果支持多文件上传，可以遍历event.files数组
    for (let file of event.files) {
      formData.append('demo[]', file) // 确保这里与后端期望的字段名匹配
    }

    fetch(`${URL}/api/upload`, {
      method: 'POST',
      body: formData
      // 注意：不设置 'Content-Type'，fetch会自动设置正确的类型，包括multipart边界
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error('Network response was not ok.')
        }
        return response.text() // 解析后端返回的文本响应
      })
      .then((data) => {
        toast.add({ severity: 'info', summary: 'Success', detail: data, life: 3000 }) // 显示成功消息
      })
      .catch((error) => {
        if (error.response.status === 413) {
          // 如果错误是因为文件过大
          toast.add({
            severity: 'error',
            summary: 'Upload Failed',
            detail: 'File too large!', // 自定义错误信息
            life: 3000
          })
        } else {
          // 对于其他类型的HTTP错误，可以显示服务器返回的错误信息或自定义错误信息
          toast.add({
            severity: 'error',
            summary: 'Upload Failed',
            detail: error.response.statusText || 'An error occurred during file upload.',
            life: 3000
          })
        }
      })
  }
}

const show = () => {
  toast.add({ severity: 'info', summary: 'Info', detail: 'Testing', life: 3000 })
}
</script>
<style scoped></style>
