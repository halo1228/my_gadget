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
    name: 'Edit',
    components: {DeptTreeSelect},
    props: {
        editFormId: {
            type: String,
            // default: '',
            required: true
        }
    },
    data() {
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
            },
            editBackData: {},
            rules: {
                name: [
                    {required: true, message: '请输入姓名', trigger: 'blur'},
                    {min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur'}
                ],
                deptId: [
                    {required: true, message: '必须选择组织', trigger: 'blur'}
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
        getEditData() { // 获取编辑数据
            if (this.editFormId) {
                // this.loading = true;
                Detail({id: this.editFormId}).then(response => {
                    this.ruleForm = response.data
                    this.editBackData = response.data
                    // 向组织选择树组件传递默认值
                    this.$refs.deptTreeSelect.deptId = response.data.deptId
                    // this.loading = false;
                })
            }
        },
        getValue(value) {
            this.ruleForm.deptId = value
        },
        submitForm() { // 添加 编辑公用表单提交
            this.$refs.ruleForm.validate((valid) => {
                if (valid) {
                    this.loading = true;
                    SubmitForm(this.ruleForm, !this.editFormId).then(
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
                } else {
                    return false
                }
            })
        },
        resetForm() { // 重置表单数据
            this.ruleForm = this.editBackData
            this.$refs.deptTreeSelect.deptId = this.editBackData.deptId
        },/* getSelectValue(value) { // 获取树选择的值
            this.ruleForm.deptId = value
        }*/
    }, created() {
        this.getEditData()
    },
}
</script>

<style scoped>

</style>
