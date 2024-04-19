// 利用 mitt 实现任意组件通信
import mitt from 'mitt'

// 调用 mitt， emitter可以绑定和触发事件
const emitter = mitt()

// 暴露 emitter
export default emitter
