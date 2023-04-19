<!--树选择器公用组件
https://blog.csdn.net/Mr_JavaScript/article/details/88604270/
https://github.com/Mr-Wiill/el-tree-select/blob/master/src/components/treeSelect.vue
-->
<!-- ps:貌似有更好替代品 后续再研究， https://github.com/wchbrad/vue-easy-tree/blob/main/element-ui-tree.zh-CN.md-->
<template>
    <el-select :value="valueTitle" :clearable="clearable" @clear="clearHandle">
        <el-input
                class="selectInput"
                :placeholder="placeholder"
                v-model="filterText">
        </el-input>

        <el-option :value="valueTitle" :label="valueTitle" class="options">
            <el-tree id="tree-option"
                     ref="selectTree"
                     :accordion="accordion"
                     :data="options"
                     :props="props"
                     :node-key="props.value"
                     :default-expanded-keys="defaultExpandedKey"
                     :filter-node-method="filterNode"
                     @node-click="handleNodeClick">
            </el-tree>
        </el-option>
    </el-select>
</template>

<script>
export default {
    name: "el-tree-select",
    data() {
        return {
            filterText: '',
            valueId: '',    // 初始值
            valueTitle: '',
            defaultExpandedKey: []
        }
    },
    props: {
        /* 配置项 */
        props: {
            type: Object,
            default: () => {
                return {
                    value: 'id',             // ID字段名
                    label: 'label',         // 显示名称
                    children: 'children'    // 子级字段名
                }
            }
        },
        /* 选项列表数据(树形结构的对象数组) */
        options: {
            type: Array,
            default: () => {
                return []
            }
        },
        /* 初始值 */
        value: {
            type: String,
            default: () => {
                return ''
            }
        },
        /* 可清空选项 */
        clearable: {
            type: Boolean,
            default: () => {
                return true
            }
        },
        /* 自动收起 */
        accordion: {
            type: Boolean,
            default: () => {
                return false
            }
        },
        placeholder: {
            type: String,
            default: () => {
                return "检索关键字"
            }
        }
    },
    methods: {
        // 初始化值
        initHandle() {
            if (this.valueId) {
                this.valueTitle = this.$refs.selectTree.getNode(this.valueId).data[this.props.label]     // 初始化显示
                this.$refs.selectTree.setCurrentKey(this.valueId)       // 设置默认选中
                this.defaultExpandedKey = [this.valueId]      // 设置默认展开
            }
            this.initScroll()
        },
        // 初始化滚动条
        initScroll() {
            this.$nextTick(() => {
                let scrollWrap = document.querySelectorAll('.el-scrollbar .el-select-dropdown__wrap')[0]
                let scrollBar = document.querySelectorAll('.el-scrollbar .el-scrollbar__bar')
                scrollWrap.style.cssText = 'margin: 0px; max-height: none; overflow: hidden;'
                scrollBar.forEach(ele => ele.style.width = 0)
            })
        },
        // 切换选项
        handleNodeClick(node) {
            this.valueTitle = node[this.props.label]
            this.valueId = node[this.props.value]
            this.$emit('getValue', this.valueId)
            this.defaultExpandedKey = []
        },
        // 清除选中
        clearHandle() {
            this.valueTitle = ''
            this.valueId = null
            this.defaultExpandedKey = []
            this.clearSelected()
            this.$emit('getValue', null)
        },
        /* 清空选中样式 */
        clearSelected() {
            let allNode = document.querySelectorAll('#tree-option .el-tree-node')
            allNode.forEach((element) => element.classList.remove('is-current'))
        },
        filterNode(value, data) {
            if (!value) return true;
            return data.name.indexOf(value) !== -1;
        }
    },
    mounted() {
        this.initHandle()
    },
    watch: {
        value: {
            deep: true,
            handler(newvalue) {
                this.valueId = newvalue
                this.initHandle()
            }
        },
        filterText(val) {
            this.$refs.selectTree.filter(val);
        }
    },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.el-scrollbar .el-scrollbar__view .el-select-dropdown__item {
    height: auto;
    max-height: 274px;
    padding: 0;
    overflow: hidden;
    overflow-y: auto;
}

.el-select-dropdown__item.selected {
    font-weight: normal;
}

ul li >>> .el-tree .el-tree-node__content {
    height: auto;
    padding: 0 20px;
}

.el-tree-node__label {
    font-weight: normal;
}

.el-tree >>> .is-current .el-tree-node__label {
    color: #409EFF;
    font-weight: 700;
}

.el-tree >>> .is-current .el-tree-node__children .el-tree-node__label {
    color: #606266;
    font-weight: normal;
}

.selectInput {
    padding: 0 5px;
    box-sizing: border-box;
}

/* 开发禁用 */
/* .el-tree-node:focus>.el-tree-node__content{
  background-color:transparent;
  background-color: #f5f7fa;
  color: #c0c4cc;
  cursor: not-allowed;
}
.el-tree-node__content:hover{
  background-color: #f5f7fa;
} */
</style>
