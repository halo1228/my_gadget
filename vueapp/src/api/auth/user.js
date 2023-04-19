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

export function getAuthMenu(token) {
  return request({
    url: '/router',
    method: 'get',
    params: { token }
  })
}

export function getPubKey() {
  return request({
    url: '/auth/getPubKey',
    method: 'get'
  })
}

