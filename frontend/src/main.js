import { createApp } from 'vue'
import App from './App.vue'
import './style.css'
import router from './router/router.js'

import PrimeVue from 'primevue/config'
import Aura from '@primeuix/themes/aura'
import ToastService from 'primevue/toastservice';

const app = createApp(App)
app.use(PrimeVue, {
    theme: {
        preset: Aura,
        options: {
            darkModeSelector: false
        }
    }
});
app.use(router);
app.use(ToastService);
app.mount('#app')