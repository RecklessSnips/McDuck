// import './assets/main.css'
import 'primeicons/primeicons.css'
// Fontawesome
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { far } from '@fortawesome/free-regular-svg-icons'
// Add icons to the library
library.add(far)

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import PrimeVue from 'primevue/config'
import Sidebar from 'primevue/sidebar'
import Button from 'primevue/button'
import Image from 'primevue/image'
import FloatLabel from 'primevue/floatlabel'
import IconField from 'primevue/iconfield'
import InputIcon from 'primevue/inputicon'
import InputText from 'primevue/inputtext'
import Badge from 'primevue/badge'
import BadgeDirective from 'primevue/badgedirective'
import SplitButton from 'primevue/splitbutton'
import OverlayPanel from 'primevue/overlaypanel'
import Divider from 'primevue/divider'

import App from './App.vue'

import 'primevue/resources/themes/aura-light-green/theme.css'

const app = createApp(App)

app.use(PrimeVue)
app.use(createPinia())

// Register FontAwesomeIcon component globally
app.component('FontAwesomeIcon', FontAwesomeIcon)

app.component('Sidebar', Sidebar)
app.component('Button', Button)
app.component('Image', Image)
app.component('FloatLabel', FloatLabel)
app.component('IconField', IconField)
app.component('InputIcon', InputIcon)
app.component('InputText', InputText)
app.component('SplitButton', SplitButton)
app.component('OverlayPanel', OverlayPanel)
app.component('Divider', Divider)

app.directive('badge', BadgeDirective)

app.mount('#app')
