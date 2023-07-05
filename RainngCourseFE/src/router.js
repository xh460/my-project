import Vue from "vue";
import VueRouter from "vue-router";
import Container from "./views/Container.vue";
import Login from "./views/Login";

import Home from "./views/Home";
import buildTeam from "@/views/core/team/BuildTeam";
import TeamBI from "@/views/core/team/TeamBI";
import BI from "@/views/core/team/BI";
import register from "@/views/register";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "container",
    component: Container,
    children: [
      {
        path: "/student",
        name: "student-home",
        component: Home
      },
      {
        path: "/core/teamBuild",
        name: "core-teamBuild",
        component: buildTeam
      },
      {
        path: "/core/teamBI",
        name: "core-teamBI",
        component: TeamBI
      },

      {
        path: "/core/BI",
        name: "core-BI",
        component: BI
      },


    ]
  },
  {
    path: "/login",
    name: "login",
    component: Login
  } ,
  {
    path: "/register",
    name: "register",
    component: register
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
