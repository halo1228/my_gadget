<template>
    <el-aside width="250px">
        <el-row>
            <el-col :span="18">
                <el-input :size="size"
                          placeholder="输入关键字进行过滤"
                          v-model="filterText">
                </el-input>
            </el-col>
            <el-col :offset="1" :span="3">
                <el-button @click="resetChecked" :size="size"
                           icon="el-icon-refresh-left"></el-button>
            </el-col>
        </el-row>
        <el-tree ref="tree" style="margin-top: 10px"
                 :data="treeData"
                 :props="defaultProps"
                 @node-click="handleNodeClick"
                 :highlight-current="true"
                 :expand-on-click-node="true"
                 node-key="id"
                 :default-expanded-keys="treeExpandData"
                 :filter-node-method="filterNode"
        ></el-tree>
    </el-aside>
</template>

<script>
import {Tree} from "@/api/sys/dept";

export default {
    name: "Tree",
    data() {
        return {
            defaultProps: {//树配置
                children: 'children',
                label: 'name'
            },
            treeData: [],//树数据
            treeExpandData: [],//默认展开树 key
            filterText: '',//搜索关键字
        }
    }
    ,
    computed: {
        size() {
            return this.$store.state.settings.defaultSize
        },
    },

    methods: {
        resetChecked() {
            console.log("4444")
            //取消选择
            this.$refs.tree.setCurrentKey(null);
            this.$emit('treeNodeData', undefined);
        },
        filterNode(value, data) {
            //过滤搜索
            if (!value) {
                return true;
            }
            return data.name.indexOf(value) !== -1;
        }, handleNodeClick(data) {
            //节点点击
            this.$emit('treeNodeData', data);
        }, loadTree() {
            Tree().then(response => {
                this.treeData = response.data;
                //默认展开第一级
                this.treeExpandData.push(response.data[0].id)
            })
        }

    }, created() {
        this.loadTree()
    }, watch: {
        filterText(val) {
            this.$refs.tree.filter(val);
        },

    },
}
</script>

<style scoped>
.el-aside {
    background-color: #FFFFFF;
    padding: 20px;
}

</style>
