import { createApp } from 'vue';
import { createRouter, createWebHistory } from 'vue-router';

import App from './App.vue';
import TeamsList from './components/teams/TeamsList.vue';
import UsersList from './components/users/UsersList.vue';
import TeamMembers from './components/teams/TeamMembers.vue';
import NotFound from './components/NotFound.vue';

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {path:'/',redirect:'/teams'},
    { path: '/teams', component: TeamsList ,alias:'/'}, // our-domain.com/teams => TeamsList
    { path: '/users', component: UsersList },
    { path: '/teams/:teamId', component: TeamMembers, props: true },
    {path:'/:notFound(.*)',component:NotFound}
  ],
  linkActiveClass: 'active'
});

const app = createApp(App);

app.use(router);

app.mount('#app');
