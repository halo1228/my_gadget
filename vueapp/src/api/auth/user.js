import request from '@/utils/request'
/* 此为 用户*/
export function login(data) {
  return request({
    url: '/login/doLogin',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/auth/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/login/logout',
    method: 'post'
  })
}

export function getRoutes(token) {
  return request({
    url: '/auth/router',
    method: 'get',
    params: { token }
  })
}

export  function  getValidCode() {
  return request({
    url: '/code/validCode',
    method: 'get',
  })
}


