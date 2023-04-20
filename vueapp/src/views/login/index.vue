<template>
    <div class="login-container">
        <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" auto-complete="on"
                 label-position="left">

            <div class="title-container">
                <h3 class="title">Welcome Login</h3>
            </div>

            <el-form-item prop="username">
        <span class="svg-container">
          <svg-icon icon-class="user"/>
        </span>
                <el-input
                        ref="username"
                        v-model="loginForm.username"
                        placeholder="Username"
                        name="username"
                        type="text"
                        tabindex="1"
                        auto-complete="on"
                />
            </el-form-item>

            <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password"/>
        </span>
                <el-input
                        :key="passwordType"
                        ref="password"
                        v-model="loginForm.password"
                        :type="passwordType"
                        placeholder="Password"
                        name="password"
                        tabindex="2"
                        auto-complete="on"
                        @keyup.enter.native="handleLogin"
                />
                <span class="show-pwd" @click="showPwd">
          <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'"/>
        </span>
            </el-form-item>

            <el-form-item prop="validCode">
                  <span class="svg-container">
                    <svg-icon icon-class="captcha"/>
                  </span>
                <el-input style="width: 50%"
                          ref="validCode"
                          v-model="loginForm.validCode"
                          name="validCode">

                </el-input>
                <img :src="validCodeBase64" class="validCode-img" @click="refreshCode"/>
            </el-form-item>

            <el-button :loading="loading" type="primary" style="width:100%;margin-bottom:30px;"
                       @click.native.prevent="handleLogin">Login
            </el-button>

            <!--      <div class="tips">
                    <span style="margin-right:20px;">welcome to system.</span>
                  </div>-->

        </el-form>
    </div>
</template>

<script>
import {validUsername} from '@/utils/validate'
import smCrypto from '@/utils/smCrypto'
import {getValidCode} from "@/api/auth/user";
import Lodash from "@/utils/lodash";

export default {
    name: 'Login',
    data() {
        const validateUsername = (rule, value, callback) => {
            if (!validUsername(value)) {
                callback(new Error('Please enter the correct user name'))
            } else {
                callback()
            }
        }
        const validatePassword = (rule, value, callback) => {
            if (value.length < 6) {
                callback(new Error('The password can not be less than 6 digits'))
            } else {
                callback()
            }
        }
        return {
            validCodeBase64: '',
            loginForm: {
                username: 'admin',
                password: '1234567',
                validCode: '',
                validCodeReqNo: ''
            },
            loginRules: {
                username: [{required: true, trigger: 'blur', validator: validateUsername}],
                password: [{required: true, trigger: 'blur', validator: validatePassword}],
                validCode: [{required: true, message: '请输入验证码', trigger: 'blur'}],
            },
            loading: false,
            passwordType: 'password',
            redirect: undefined
        }
    },
    watch: {
        $route: {
            handler: function (route) {
                this.redirect = route.query && route.query.redirect
            },
            immediate: true
        }
    },
    methods: {
        refreshCode() {
            getValidCode().then((response) => {
                this.validCodeBase64 = response.data.validCodeBase64;
                this.loginForm.validCodeReqNo = response.data.validCodeReqNo
            })
        },
        showPwd() {
            if (this.passwordType === 'password') {
                this.passwordType = ''
            } else {
                this.passwordType = 'password'
            }
            this.$nextTick(() => {
                this.$refs.password.focus()
            })
        },
        handleLogin() {
            this.$refs.loginForm.validate(valid => {
                if (valid) {
                    this.loading = true
                    //密码加密
                    let doEncrypt = smCrypto.doSm2Encrypt(this.loginForm.password);
                    //深拷贝对象 避免影响到现有form
                    let params = Lodash.copyObj(this.loginForm)
                    //设置加密后的密码
                    params.password = doEncrypt;
                    this.$store.dispatch('user/login', params).then(() => {
                        this.$router.push({path: this.redirect || '/'})
                    }).finally(() => {
                        this.loading = false
                    })
                } else {
                    return false
                }
            })
        }
    }, created() {
        this.refreshCode()
    }
}
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg: #283443;
$light_gray: #fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
$bg: #2d3a4b;
$dark_gray: #889aa4;
$light_gray: #eee;

.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  /* background-image: url("~@/assets/img/login-backgroud.png");
   opacity:0.4;*/
  overflow: hidden;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0 auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }

  .validCode-img {
    border: 1px solid var(--border-color-split);
    cursor: pointer;
    width: 30%;
    height: 40px;
    margin: 6px 6px;
    float: right;
  }
}
</style>
