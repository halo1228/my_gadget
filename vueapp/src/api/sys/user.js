import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/sys/user/list',
    method: 'get',
    params
  })
}

export function getPage(data) {
  return request({
    url: '/sys/user/page',
    method: 'post',
    data
  })
}


export function SubmitForm(data, isAdd) {
  return request({
    url: isAdd ?'/sys/user/add':'/sys/user/update',
    method: 'post',
    data
  })
 /* if(isAdd){
    return request({
      url: isAdd ?'/sys/user/add':'/sys/user/update',
      method: 'post',
      data
    })
  }else{
    return request({
      url: '/sys/user/update',
      method: 'post',
      data
    })
  }*/
  /*return request({
    url: '/sys/user/' + isAdd ? 'add' : 'update',
    method: 'post',
    data
  })*/

}

export function Add(data) {
  return request({
    url: '/sys/user/add',
    method: 'post',
    data
  })
}


export function Update(data) {
  return request({
    url: '/sys/user/update',
    method: 'post',
    data
  })
}

export function Detail(params) {
  return request({
    url: '/sys/user/detail',
    method: 'get',
    params
  })
}


export function Delete(params){
  return request({
    url: '/sys/user/delete',
    method: 'get',
    params
  })
}
