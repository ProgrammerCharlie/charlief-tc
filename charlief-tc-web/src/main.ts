import './assets/main.css'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import Mitt from 'mitt'

const app = createApp(App)
const mitt = Mitt()
declare module 'vue' {
  export interface ComponentCustomProperties {
    $mitt: typeof mitt
  }
}
app.config.globalProperties.$mitt = mitt
app.use(createPinia())
app.use(ElementPlus)

app.mount('#app')
