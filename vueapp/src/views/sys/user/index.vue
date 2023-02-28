<template>
  <div class="app-container">
    <el-container>
      <!-- 组织树 -->
      <Tree ref="tree" @treeNodeData="getTreeData"></Tree>
      <el-main>
        <el-form ref="paramsForm" :model="params" :inline="true" class="demo-form-inline" label-suffix="："
                 :size="size">
          <el-form-item label="姓名">
            <el-input v-model="params.name" placeholder="姓名" clearable></el-input>
          </el-form-item>
          <el-form-item>
            <el-button @click="loadData" type="primary" icon="el-icon-search" :size="size">查询
            </el-button>
          </el-form-item>
          <el-form-item style="float: right">
            <el-button :size="size" icon="el-icon-plus" @click="openFormDrawer" type="primary">添加</el-button>
            <el-button :size="size" icon="el-icon-plus" @click="formDrawer.visible=true" type="primary">添加prop
            </el-button>
          </el-form-item>
        </el-form>
        <el-table
          :size="size"
          ref="filterTable"
          :data="pageData.data"
          element-loading-text="Loading"
          border
          fit
          highlight-current-row>
          <el-table-column prop="id" label="ID" v-if="false"></el-table-column>
          <el-table-column prop="userCode" label="工号" sortable></el-table-column>
          <el-table-column prop="name" label="姓名"></el-table-column>
          <el-table-column prop="birthday" label="生日" :formatter="formatDate"></el-table-column>
          <el-table-column prop="tel" label="手机号"></el-table-column>
          <el-table-column prop="email" label="邮箱"></el-table-column>
          <el-table-column prop="address" label="地址"></el-table-column>
          <el-table-column min-width="150px" label="常用操作">
            <template slot-scope="scope">
              <el-button size="mini" icon="el-icon-edit"
                         @click="formDrawer.visible=true;formDrawer.editFormId=scope.row.id">编辑
              </el-button>
              <el-popconfirm style="margin-left: 10px" title="确定删除吗？" @onConfirm="remove(scope.row.id)"
                             :key="scope.row.id">
                <el-button slot="reference" icon="el-icon-delete" size="mini" type="danger">
                  删除
                </el-button>
              </el-popconfirm>

            </template>
          </el-table-column>
        </el-table>
        <div style="padding: 15px;text-align: right">
          <el-pagination @current-change="handleCurrentChange" @size-change="handleSizeChange"
                         :current-page.sync="pageData.current" :page-sizes="[15, 30, 45]" :page-size="pageData.size"
                         layout="total, sizes, prev, pager, next, jumper" :total="pageData.total">
          </el-pagination>
        </div>
      </el-main>
      <!--
      使用prop 实现 父==》子 通信
      使用 emit 实现 子==》父 通信
      -->
      <Form ref="form" :formVisible="formDrawer.visible" :editFormId="formDrawer.editFormId"
            @refreshData="loadData" @closeFormDrawer="closeFormDrawer"></Form>
    </el-container>
  </div>

</template>

<script>
import {getPage, Delete} from "@/api/sys/user";
import Form from "@/views/sys/user/form.vue";
import * as moment from 'moment';
import Tree from "@/views/sys/dept/components/tree.vue";
export default {
  name: "User",
  components: {Form,Tree},
  data() {
    return {
      formDrawer: {//抽屉相关属性
        visible: false,//form组件可见性
        editDrawer: false,
        editFormId: ''
      },
      listLoading: true,
      pageData: {//分页查询
        current: 1,
        size: 10,
        total: 0,
        data: []
      },
      params: {//分页查询参数
        name: '',
        deptId:''
      },
    }
  },
  computed: {
    size() {
      return this.$store.state.settings.defaultSize
    },
  },
  methods: {
    formatDate(row, column, cellValue, index) {
      //格式化日期
      return moment.isDate(new Date(cellValue)) ? moment(cellValue).format('YYYY-MM-DD') : '';
    },
    openFormDrawer() {
      //打开抽屉
      this.$refs.form.visible = true
    },
    closeFormDrawer() {
      //关闭抽屉
      this.formDrawer = {
        visible: false,
        editDrawer: false,
        editFormId: ''
      }
    },
    loadData() {
      this.query(this.params)
    },
    query(data) {
      this.listLoading = true
      data.size = this.pageData.size;
      data.current = this.pageData.current;
      getPage(data).then(response => {
        //console.log(response.data);
        this.pageData.data = response.data.records;
        this.pageData.total = response.data.total;
        this.listLoading = false
      })
    },
    handleSizeChange: function (size) {
      this.$data.pageData.pageSize = size;
      this.loadData();
    },
    handleCurrentChange: function (currentPage) {
      this.$data.pageData.start = currentPage;
      this.loadData();
    },
    remove(id) {
      Delete({id}).then(response => {
        this.$message({
          message: response.msg,
          type: 'success'
        });
        this.loadData();
      })
    },
    getTreeData(data) {//获取树节点信息
      if (data) {
        this.query({
          deptId: data.id
        })
      } else {
        this.loadData();
      }
    }
  }, created() {
    this.loadData()
  }
}
</script>

<style scoped>

.el-main {
  background-color: #FFFFFF;
  margin-left: 10px;
}
.el-table{
  width: 100%;
  min-height:500px;
}
</style>
