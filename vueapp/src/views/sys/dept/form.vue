<template>
    <div class="app-container">
        <el-form :size="size" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px"
                 class="demo-ruleForm"
                 label-suffix="：">
            <el-form-item label="父组织" prop="parentId">
                <!--引用组织选择器组件-->
                <dept-tree-select ref="deptTreeSelect"   @getValue="getValue($event)"></dept-tree-select>

                <el-tooltip class="item" effect="light" content="不选，默认为根组织" placement="right">
                    <i style="margin-left: 10px" class="el-icon-info"></i>
                </el-tooltip>
            </el-form-item>
            <el-form-item label="组织名" prop="deptName">
                <el-input v-model="ruleForm.deptName"></el-input>
            </el-form-item>
            <el-form-item label="组织编号" prop="deptCode">
                <el-input v-model="ruleForm.deptCode"></el-input>
            </el-form-item>
            <el-form-item label="组织类型" prop="deptType">
                <el-select v-model="ruleForm.deptType" placeholder="请选择组织类型">
                    <el-option label="公司" :value="0"></el-option>
                    <el-option label="部门" :value="1"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="排序" prop="idx">
                <el-slider v-model="ruleForm.idx" show-input></el-slider>
            </el-form-item>
            <el-form-item label="组织简介" prop="description">
                <el-input type="textarea" maxlength="200"
                          show-word-limit :autosize="{ minRows: 3 }" v-model="ruleForm.description"></el-input>
            </el-form-item>
        </el-form>
        <div class="demo-drawer__footer">
            <el-button :size="size" @click="resetForm">重置</el-button>
            <el-button :size="size" type="primary" @click="submitForm" :loading="loading">
                {{ loading ? '提交中 ...' : '确 定' }}
            </el-button>
        </div>
    </div>
</template>

<script>
import {Detail, SubmitForm} from "@/api/sys/dept";
import DeptTreeSelect from "@/views/sys/dept/components/deptTreeSelect.vue";

export default {
    name: "Form",
    components: {DeptTreeSelect},
    data() {
        return {
            loading: false,
            ruleForm: {
                id: '',
                deptName: '',
                deptCode: '',
                deptType: 1,
                parentId: '0',//默认值
                idx: 100,
                description: ''
            },
            editBackData: {},
            rules: {
                deptName: [
                    {required: true, message: '请输入组织名', trigger: 'blur'},
                    {min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur'}
                ],
                deptType: [
                    {required: true, message: '请选择组织类型', trigger: 'blur'},
                ],
            },
        };
    },
    props: {
        editFormId: {
            type: String,
            default: '',
            required: false,
        }
    },
    computed: {
        size() {
            return this.$store.state.settings.defaultSize
        },
    },
    methods: {
        getEditData() {
            if (this.editFormId) {
                //this.loading = true;
                Detail({id: this.editFormId}).then(response => {
                    this.ruleForm = response.data;
                    this.editBackData = response.data;
                    //此为编辑状态 需要将现有父id 传到子组件中
                    this.$refs.deptTreeSelect.deptId = response.data.parentId;
                    //this.loading = false;
                })
            }
        },
        getValue(value) {
            this.ruleForm.parentId = value;
        },
        submitForm() {//添加 编辑公用表单提交
            //编辑保存 需要校验一下选择父ID 是否和本组织ID一样
            if (this.ruleForm.parentId === this.ruleForm.id) {
                this.$message({
                    message: '上级组织不能选择当前组织！！',
                    type: 'error'
                });
                return;
            }
            this.$refs.ruleForm.validate((valid) => {
                if (valid) {
                    this.loading = true;
                    SubmitForm(this.ruleForm, !this.editFormId).then(
                        response => {
                            this.$message({
                                message: response.msg,
                                type: 'success'
                            });
                            //刷新父页面数据
                            this.$emit('refreshData');
                            //关闭本抽屉
                            this.$refs.drawer.closeDrawer()
                        }
                    ).finally(() => {
                        this.loading = false;
                        this.clearForm();
                    })
                } else {
                    this.$message({
                        message: '表单校验失败，请按照要求正确填写！！',
                        type: 'warning'
                    });
                    return false;
                }
            });
        },
        resetForm() {//重置表单数据
            if (this.editFormId) {
                //编辑
                this.ruleForm = this.editBackData;
                this.$refs.deptTreeSelect.deptId = this.editBackData.parentId;
            } else {
                //添加
                this.$refs.ruleForm.resetFields();
                this.$refs.deptTreeSelect.clearValue()
            }
        },
        clearForm() {
            if (!this.editFormId) {
                this.$refs.ruleForm.resetFields();
                this.$refs.deptTreeSelect.clearValue()
            }
        },
        getSelectValue(value) {
            this.ruleForm.parentId = value;
        }
    }, created() {
        this.getEditData()
    }
}
</script>

<style>

</style>
