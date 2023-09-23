import { createRouter, createWebHistory } from 'vue-router'
import ViewAgendamentos from '../views/ViewAgendamentos.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: ViewAgendamentos
    },
    {
      path: '/add',
      name: 'add',
      component: () => import('../views/AddAgendamento.vue')
    },
    {
      path: '/edit/:id',
      name: 'edit',
      component: () => import('../views/UpdateAgendamento.vue')
    },
    {
      path: '/buscar',
      name: 'buscar',
      component: () => import('../views/FindAgendamento.vue')
    }

  ]
})

export default router