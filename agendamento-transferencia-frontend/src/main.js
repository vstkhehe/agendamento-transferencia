import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'
import Toaster from "@meforma/vue-toaster";
import VueTheMask from 'vue-the-mask'

const app = createApp(App)

app.use(VueTheMask)

app.use(router)

app.use(Toaster)

app.mount('#app')
