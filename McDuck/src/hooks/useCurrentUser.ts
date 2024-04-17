import { ref, reactive, onBeforeMount, watch } from 'vue'
const URL = 'http://localhost:8080'

/*
Hook最重要的，是如果我import了这个hook，则将会在所引用的component里执行这个hook
里的逻辑，就相当于把这个hook的代码嵌入了我引入的地方

所以何时执行hook的代码，我可以自行决定，在这里，我让它在 onBeforeMount 的时候执行
*/

export default function () {
  let visible = ref(false)
  let isLogin = ref(false)
  let username = ref(null)
  let currentUser = reactive({
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
    registeredTime: null
  })

  let greeting = ref<string>('Hi User')

  watch(isLogin, (newV: boolean, old: boolean) => {
    console.log('New status: ' + newV)
    console.log('Origional status: ' + old)
  })

  // 这一行最重要，这样我不用返回function，因为这里自动帮我解决了
  onBeforeMount(() => {
    checkLoginStatus()
  })

  const checkLoginStatus = async () => {
    isLogin.value = await checkSession(URL)
    if (!isLogin.value) {
      console.log('Session expired or not found. Please login.')
    }
  }

  async function checkSession(URL: string) {
    try {
      const response = await fetch(`${URL}/api/validate-session`, {
        method: 'GET',
        // Ensures cookies are sent and received with the request
        credentials: 'include'
      })
      if (response.ok) {
        const loggedin = await response.json()
        console.log('User if loggedin: ' + loggedin)
        if (loggedin) {
          const userResponse = await fetch(`${URL}/api/get-user-session`, {
            method: 'GET',
            credentials: 'include'
          })
          const user = await userResponse.json()
          console.log('User: ' + user)
          username.value = user.nickName
          console.log('Username: ' + username)
          Object.assign(currentUser, user)
          console.log('Current User: ' + currentUser)
          greeting.value = 'Hi ' + username.value
          return true
        } else {
          return false
        }
      } else {
        console.log('User not login yet!!!')
        return false
      }
    } catch (error) {
      console.error('Error checking session:', error)
      return false
    }
  }

  return { visible, isLogin, username, currentUser, greeting }
}
