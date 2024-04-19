import { ref, reactive } from 'vue'
import { defineStore } from 'pinia'

//                             这块跟文件名保持一致:
export const useCurrentUserStore = defineStore('currentUser', () => {
  const ifLogin = ref(false)
  let skipRandomProducts = ref(false)
  const user = reactive({
    nickName: null,
    password: null,
    firstName: null,
    lastName: null,
    fullName: null,
    address: null,
    phoneNumber: null,
    email: null,
    creditCard: null,
    balance: null,
    registeredTime: new Date().toISOString()
  })

  return { ifLogin, user, skipRandomProducts }
})
