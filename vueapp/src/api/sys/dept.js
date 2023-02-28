import request from '@/utils/request'


export function getPage(data) {
  return request({
    url: '/sys/dept/page',
    method: 'post',
    data
  })
}


export function SubmitForm(data, isAdd) {
  return request({
    url: isAdd ?'/sys/dept/add':'/sys/dept/update',
    method: 'post',
    data
  })
}

export function Detail(params) {
  return request({
    url: '/sys/dept/detail',
    method: 'get',
    params
  })
}
export function LazyTree(params) {
  return request({
    url: '/sys/dept/lazyTree',
    method: 'get',
    params
  })
}

export function Tree() {
  return request({
    url: '/sys/dept/tree',
    method: 'get',
  })
}

export function Delete(params){
  return request({
    url: '/sys/dept/delete',
    method: 'get',
    params
  })
}
