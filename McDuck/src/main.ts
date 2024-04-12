// import './assets/main.css'
import 'primevue/resources/themes/saga-blue/theme.css' // theme
import 'primevue/resources/primevue.min.css' // core css
import 'primeicons/primeicons.css' // icons

// Fontawesome
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { far } from '@fortawesome/free-regular-svg-icons'
// Add icons to the library
library.add(far)

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import router from './router'

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
import Accordion from 'primevue/accordion'
import AccordionTab from 'primevue/accordiontab'
import Toolbar from 'primevue/toolbar'
import CascadeSelect from 'primevue/cascadeselect'
import Dropdown from 'primevue/dropdown'
import InputSwitch from 'primevue/inputswitch'
import Slider from 'primevue/slider'
import Password from 'primevue/password'
import InputGroup from 'primevue/inputgroup'
import InputGroupAddon from 'primevue/inputgroupaddon'
import FileUpload from 'primevue/fileupload'
import Toast from 'primevue/toast'
import ToastService from 'primevue/toastservice'
import DataView from 'primevue/dataview'
import DataViewLayoutOptions from 'primevue/dataviewlayoutoptions'
import Column from 'primevue/column'
import ColumnGroup from 'primevue/columngroup'
import Row from 'primevue/row'
import Tag from 'primevue/tag'

import App from './App.vue'

import 'primevue/resources/themes/aura-light-green/theme.css'

const app = createApp(App)

app.use(PrimeVue)
app.use(router)
app.use(ToastService)
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
app.component('Accordion', Accordion)
app.component('AccordionTab', AccordionTab)
app.component('Toolbar', Toolbar)
app.component('CascadeSelect', CascadeSelect)
app.component('Dropdown', Dropdown)
app.component('InputSwitch', InputSwitch)
app.component('Slider', Slider)
app.component('Password', Password)
app.component('InputGroup', InputGroup)
app.component('InputGroupAddon', InputGroupAddon)
app.component('FileUpload', FileUpload)
app.component('Toast', Toast)
app.component('DataView', DataView)
app.component('DataViewLayoutOptions', DataViewLayoutOptions)
app.component('Column', Column)
app.component('ColumnGroup', ColumnGroup)
app.component('Row', Row)
app.component('Tag', Tag)

app.directive('badge', BadgeDirective)

app.mount('#app')
