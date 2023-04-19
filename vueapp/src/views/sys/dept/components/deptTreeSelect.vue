<!--组织树选择器-->
<template>
    <SelectTree
            ref="selectTree"
            :props="props"
            :options="treeData"
            :value="valueId"
            :clearable="isClearable"
            :accordion="isAccordion"
            @getValue="getValue($event)"
    />
</template>

<script>
import SelectTree from "@/components/TreeSelect/index.vue";
import {Tree} from "@/api/sys/dept";

export default {
    name: "DeptTreeSelect",
    components: {
        SelectTree
    },
    data() {
        return {
            treeData: [],
            isClearable: true, // 可清空（可选）
            isAccordion: true, // 可收起（可选）
            valueId: '', // 初始ID（可选）
            props: {
                // 配置项（必选）
                value: "id",
                label: "name",
                children: "children"
                // disabled:true
            },
            // 选项列表（必选）
            list: [],
            deptId: ''
        };
    },
    methods: {
        // 获取选中的值，并传递给父组件
        getValue(value) {
            this.valueId = value;
            //向父组件传值
            this.$emit('getValue', value)
        },
        // 清空选中值 父组件可通过refs 清空值
        clearValue() {
            //调用树选择器组件 清空方法
            this.$refs.selectTree.clearHandle()
        },
        // 获取值，树选择器加载数据
        loadTree() {
            Tree().then(response => {
                this.treeData = response.data
                //请求需要时间 此处需要等待组件加载完在渲染数据
                this.$nextTick(e => {
                    this.valueId = this.deptId
                })
            })
        }
    },
    created() {
        this.loadTree();
    }
};
</script>

<style>
#treeSelect {
    font-family: "Avenir", Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
}
</style>
