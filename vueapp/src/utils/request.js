import axios from 'axios'
import {MessageBox, Message} from 'element-ui'
import store from '@/store'
import {getToken} from '@/utils/auth'

// 创建axios实例
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  // withCredentials: true, // 跨域请求时发送cookie
  timeout: 5000 // request timeout
})

// request interceptor
service.interceptors.request.use(
  config => {
    // 在发送请求之前做一些事情

    if (store.getters.token) {
      // 让每个请求携带令牌
      // ['X-Token']是自定义标题键
      // 请根据实际情况修改
      config.headers['X-Token'] = getToken()
    }
    return config
  },
  error => {
    // do something with request error
    //console.log(error) // for debug
    return Promise.reject(error)
  }
)

//响应拦截器
service.interceptors.response.use(
  /**
   * 如果要获取http信息，例如标头或状态
   *  return  response => response
   */

  /**通过自定义代码确定请求状态这里只是一个示例，
   * 您也可以通过HTTP状态代码来判断状态
   */
  response => {
    // 配置了blob，不处理直接返回文件流
    if (response.config.responseType === 'blob') {
      if (response.status === 200) {
        return response
      } else {
        Message({
          message: '文件下载失败或此文件不存在',
          type: 'error',
          duration: 5 * 1000
        })
        return
      }
    }

    const res = response.data;
    // 如果未200自定义代码，则判断为错误。
    if (res.code !== 200) {


      //50008: 非法令牌; 50012: 其他客户端登录; 50014: 令牌过期；
      if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
        // to re-login
        MessageBox.confirm('You have been logged out, you can cancel to stay on this page, or log in again', 'Confirm logout', {
          confirmButtonText: 'Re-Login',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          store.dispatch('user/resetToken').then(() => {
            location.reload()
          })
        })
      }else{
        Message({
          message: res.msg || ' Error',
          type: 'error',
          duration: 5 * 1000
        })
      }
      return Promise.reject(new Error(res.msg || 'Error'))
    } else {
      return res
    }
  },
  error => {
    //console.log('err' + error) // for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
