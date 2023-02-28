<template>
  <el-drawer
    :title="editFormId?'编辑':'添加'"
    ref="drawer"
    :destroy-on-close="true"
    :visible.sync="visible"
    direction="rtl"
    :before-close="handleClose"
    size="50%">
    <div class="app-container">
      <el-form :size="size" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm"
               label-suffix="：">
        <el-form-item label="组织" prop="deptId">
          <!--引用组织选择器组件-->
          <dept-tree-select ref="deptTreeSelect" @getValue="getValue($event)"></dept-tree-select>

        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>
        <el-form-item label="工号" prop="userCode">
          <el-input v-model="ruleForm.userCode"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="ruleForm.sex">
            <el-radio :label="0">男</el-radio>
            <el-radio :label="1">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="手机号" prop="tel">
          <el-input v-model="ruleForm.tel"></el-input>
        </el-form-item>
        <el-form-item label="email" prop="email">
          <el-input v-model="ruleForm.email"></el-input>
        </el-form-item>
        <el-form-item label="生日">
          <el-form-item prop="birthday">
            <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.birthday"
                            style="width: 100%;"></el-date-picker>
          </el-form-item>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="ruleForm.address"></el-input>
        </el-form-item>
        <el-form-item label="个人简介" prop="description">
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
  </el-drawer>

</template>

<script>
import {Detail, SubmitForm} from "@/api/sys/user";
import SelectTree from "@/views/sys/dept/components/selectTree.vue";
import DeptTreeSelect from "@/views/sys/dept/components/deptTreeSelect.vue";
export default {
  name: "Form",
  components: {DeptTreeSelect, SelectTree},
  data() {
    return {
      loading: false,
      visible: false,
      ruleForm: {
        deptId:'',
        id: '',
        name: '',
        userCode: '',
        sex: 0,
        tel: '',
        birthday: '',
        address: '',
        description: ''
      },
      editBackData: {},
      rules: {
        name: [
          {required: true, message: '请输入姓名', trigger: 'blur'},
          {min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur'}
        ],
        deptId:[
          {required: true, message: '必须选择组织', trigger: 'blur'}
        ]
      }
    };
  },
  watch: {
    editFormId(newVal, oldVal) {
      if (newVal) {
        //this.loading = true;
        Detail({id: newVal}).then(response => {
          this.ruleForm = response.data;
          this.editBackData = response.data;
          //向组织选择树组件传递默认值
          this.$refs.deptTreeSelect.deptId = response.data.deptId;
          //this.loading = false;
        })
      }
    },
    formVisible(newVal, oldVal) {
      if (newVal) {
        this.visible = newVal;
      }
    }
  },
  props: {
    formVisible: {
      type: Boolean,
      default: false,
      required: true,
    },
    editFormId: {
      type: String,
      //default: '',
      required: false,
    }
  },
  computed: {
    size() {
      return this.$store.state.settings.defaultSize
    },
  },
  methods: {
    getValue(value) {
      console.log(value, 12)
      this.ruleForm.deptId = value;
    },
    handleClose(done) {//关闭抽屉
      //因为存在组件缓存 需要刷新当前表单
      this.$refs.ruleForm.resetFields();
      //重置显示属性为false
      this.visible = false;
      //调用父组件抽屉关闭方法 重置抽屉属性
      this.$emit('closeFormDrawer');
    },
    submitForm() {//添加 编辑公用表单提交
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
          })
        } else {
          return false;
        }
      });
    },
    resetForm() {//重置表单数据
      if (this.editFormId) {
        //编辑
        this.ruleForm = this.editBackData;
        this.$refs.deptTreeSelect.deptId = this.editBackData.deptId;
      } else {
        //添加
        this.$refs.ruleForm.resetFields();
      }

    },getSelectValue(value) {
      this.ruleForm.deptId = value;
    }
  }
}
</script>

<style scoped>

</style>
