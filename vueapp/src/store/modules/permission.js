import {constantRoutes} from '@/router'
import {getRoutes} from '@/api/auth/user' // 获取路由的接口方法
import Layout from '@/layout'
// 映射路由表
/*const componentsMap = {
  '/views/sys/user': () => import('@/views/sys/user'),
  '/sys/user': () => import('@/views/sys/user'),
};*/
// 动态添加路由
const loadView = (view) => {
  return (resolve) => require([`@/views${view}`], resolve)

}

/**
 * 把后台返回菜单组装成routes要求的格式
 * @param {*} routes
 */
export function getAsyncRoutes(routes) {
  const res = []
  const keys = ['path', 'name', 'children', 'redirect', 'alwaysShow', 'meta', 'hidden']
  routes.forEach(item => {
    const newItem = {}
    if (item.component) {
      if (item.component.toLowerCase() === 'layout') {
        newItem.component = Layout
      } else {
        newItem['component'] = loadView(item.component)
      }
    }
    for (const key in item) {
      if (keys.includes(key)) {
        newItem[key] = item[key]
      }
    }

    if (item.children) {
      newItem.children = getAsyncRoutes(item.children)
    }
    res.push(newItem)
  })
  return res
}

const state = {
  routes: [],
  addRoutes: []
}

const mutations = {
  SET_ROUTES: (state, routes) => {
    state.addRoutes = routes // 路由访问
    state.routes = constantRoutes.concat(routes) // 菜单显示
  }
}

const actions = {
  generateRoutes({commit}, roles) {
    return new Promise(async resolve => {
      const routes = await getRoutes() // 获取到后台路由
      routes.data = [
        {
          path: '/user',
          component: "Layout",
          redirect: '/user',
          name: 'Example',
          meta: {title: 'Example', icon: 'el-icon-s-help'},
          children: [{
            path: 'index',
            name: 'User',
            component: '/sys/user',
            meta: {title: 'User', icon: 'el-icon-user', keepAlive: true}
          },
            {
              path: 'index2',
              name: 'User2',
              component: '/sys/user',
              meta: {title: 'User2', icon: 'el-icon-user', keepAlive: true}
            }]
        },
        {
          path: '/dept',
          component: "Layout",
          redirect: '/',
          children: [{
            path: 'index',
            name: 'Dept',
            component: '/sys/dept',
            meta: {title: 'Dept', icon: 'el-icon-office-building', keepAlive: true}
          }]
        },
        {
          path: '/user2',
          component: "Layout",
          redirect: '/user',
          children: [{
            path: 'index',
            name: 'User',
            component: '/sys/user',
            meta: {title: 'User', icon: 'el-icon-user', keepAlive: true}
          }]
        },
        {
          path: '/user22',
          component: "Layout",
          meta: {title: 'User22', icon: 'el-icon-user', keepAlive: true}
        },
        {path: '*', redirect: '/404', hidden: true}
      ]
      const asyncRoutes = getAsyncRoutes(routes.data) // 对路由格式进行处理
      commit('SET_ROUTES', asyncRoutes)
      resolve(asyncRoutes)
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
