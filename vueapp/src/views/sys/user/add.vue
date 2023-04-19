<template>
    <el-container class="app-container">
        <el-main>
            <el-form
                    ref="ruleForm"
                    :size="size"
                    :model="ruleForm"
                    :rules="rules"
                    label-width="100px"
                    class="demo-ruleForm"
                    label-suffix="："
            >
                <el-col :span="11">
                    <el-form-item label="姓名" prop="name">
                        <el-input v-model="ruleForm.name"/>
                    </el-form-item>
                    <el-form-item label="工号" prop="userCode">
                        <el-input v-model="ruleForm.userCode"/>
                    </el-form-item>
                    <el-form-item label="性别" prop="sex">
                        <el-radio-group v-model="ruleForm.sex">
                            <el-radio :label="0">男</el-radio>
                            <el-radio :label="1">女</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item label="手机号" prop="tel">
                        <el-input v-model="ruleForm.tel"/>
                    </el-form-item>
                    <el-form-item label="email" prop="email">
                        <el-input v-model="ruleForm.email"/>
                    </el-form-item>
                    <el-form-item label="生日">
                        <el-form-item prop="birthday">
                            <el-date-picker
                                    v-model="ruleForm.birthday"
                                    type="date"
                                    placeholder="选择日期"
                                    style="width: 100%;"
                            />
                        </el-form-item>
                    </el-form-item>
                    <el-form-item label="地址" prop="address">
                        <el-input v-model="ruleForm.address"/>
                    </el-form-item>
                    <el-form-item label="个人简介" prop="description">
                        <el-input
                                v-model="ruleForm.description"
                                type="textarea"
                                maxlength="200"
                                show-word-limit
                                :autosize="{ minRows: 3 }"
                        />
                    </el-form-item>
                </el-col>
                <el-col :span="11" :offset="1">
                    <el-form-item label="组织" prop="deptId">
                        <!--引用组织选择器组件-->
                        <dept-tree-select ref="deptTreeSelect" @getValue="getValue($event)"/>
                    </el-form-item>
                    <el-form-item label="账号" prop="access">
                        <el-input v-model="ruleForm.access"/>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input v-model="ruleForm.password"/>
                    </el-form-item>
                    <el-form-item label="确认密码" prop="checkPass">
                        <el-input v-model="ruleForm.checkPass"/>
                    </el-form-item>
                </el-col>
            </el-form>
        </el-main>
        <el-footer>
            <el-row type="flex" justify="center">
                <el-col :span="5">
                    <el-button :size="size" @click="resetForm">重置</el-button>
                    <el-button :size="size" type="primary" :loading="loading" @click="submitForm">
                        {{ loading ? '提交中 ...' : '确 定' }}
                    </el-button>
                </el-col>
            </el-row>
        </el-footer>
    </el-container>


</template>

<script>
import {Detail, SubmitForm} from '@/api/sys/user'
import {getPubKey} from "@/api/auth/user"
import {validPassWord} from "@/utils/validate"
import {sm2} from "sm-crypto"
import DeptTreeSelect from '@/views/sys/dept/components/deptTreeSelect.vue'
import Bus from '@/bus'

export default {
    name: 'Add',
    components: {DeptTreeSelect},
    data() {
        var validatePass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入密码'));
            } else {
                if (validPassWord(value)) {
                    if (this.ruleForm.checkPass !== '') {
                        this.$refs.ruleForm.validateField('checkPass');
                    }
                    callback();
                } else {
                    callback(new Error("密码应为字母，数字，特殊符号(~!@#$%^&*()_.)，两种及以上组合，8-12位字符串，如：xyl37@baa"))
                }
            }
        };
        var validatePass2 = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入密码'));
            } else if (value !== this.ruleForm.password) {
                callback(new Error('两次输入密码不一致!'));
            } else {
                callback();
            }
        };
        return {
            loading: false,
            visible: false,
            ruleForm: {
                deptId: '',
                deptUserId: '',
                id: '',
                name: '',
                userCode: '',
                sex: 0,
                tel: '',
                birthday: '',
                address: '',
                description: '',
                access: '',
                password: '',
                checkPass: ''
            },
            rules: {
                name: [
                    {required: true, message: '请输入姓名', trigger: 'blur'},
                    {min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur'}
                ],
                deptId: [
                    {required: true, message: '必须选择组织', trigger: 'blur'}
                ],
                access: [
                    {required: true, message: '账号不能为空', trigger: 'blur'}
                ],
                password: [
                    {required: true, message: '密码不能为空', trigger: 'blur'},
                    {validator: validatePass, trigger: 'blur'},
                ],
                checkPass: [
                    {required: true, message: '密码不能为空', trigger: 'blur'},
                    {validator: validatePass2, trigger: 'blur'},
                ],
            }
        }
    },
    computed: {
        size() {
            return this.$store.state.settings.defaultSize
        }
    },
    methods: {
        getValue(value) {
            this.ruleForm.deptId = value
        },
        submitForm() { // 添加 编辑公用表单提交
            this.$refs.ruleForm.validate((valid) => {
                if (valid) {
                    this.loading = true;
                    getPubKey().then(
                        response => {
                            console.log(response.data)
                            let doEncrypt = sm2.doEncrypt(this.ruleForm.password, response.data);
                            let params = {};
                            params = this.ruleForm;
                            params.password = doEncrypt;
                            params.checkPass = doEncrypt;
                            SubmitForm(params, true).then(
                                response => {
                                    this.$message({
                                        message: response.msg,
                                        type: 'success'
                                    })
                                    // 刷新父页面数据
                                    this.$emit('refreshData')
                                    // 关闭抽屉
                                    Bus.$emit('closeDefaultDrawer')
                                }
                            ).finally(() => {
                                this.loading = false
                            })

                        }
                    ).catch(() => {
                        this.loading = false;
                        this.$message({
                            message: '网络异常，稍后重试',
                            type: 'success'
                        })
                    })
                } else {
                    return false
                }
            })
        },
        resetForm() { // 重置表单数据
            // 添加
            this.$refs.ruleForm.resetFields()
            this.$refs.deptTreeSelect.clearValue()
        },/* getSelectValue(value) { // 获取树选择的值
            this.ruleForm.deptId = value
        }*/
    },
}
</script>

<style scoped>

</style>
